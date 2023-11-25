package model;

import java.util.Scanner;

public class RecorridoCaballo {
    static int altura = 8;
    static int ancho = 9;
    private static final int MAX_INTENTOS = 10000;
    private static int obt;

    static class Move {
        int x;
        int y;

        Move(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void printMoves() {
        System.out.print("Moves: ");
        for (int i = 0; i < moves.length; i++) {
            if (moves[i] != null) {
                System.out.print("(" + moves[i].x + "," + moves[i].y + ") ");
            }
        }
        System.out.println();
    }
    static Move[] moves; // Array to store moves

    public static boolean esSeguro(int x, int y, int sol[][]) {
        return (x >= 0 && x < altura && y >= 0 && y < ancho && sol[x][y] == 0);
    }

    public static void imprimirSolucion(int sol[][]) {
        for (int x = 0; x < altura; x++) {
            for (int y = 0; y < ancho; y++) {
                System.out.print(sol[x][y] + " ");
            }
            System.out.println();
        }
    }

    public static boolean resolverRecorridoCaballo(int filaInicio, int colInicio, int filaObjetivo, int colObjetivo,
            int p, int q) {
        int sol[][] = new int[altura][ancho];

        for (int x = 0; x < altura; x++) {
            for (int y = 0; y < ancho; y++) {
                sol[x][y] = 0;
            }
        }

        // Initialize moves array based on the total number of moves
        moves = new Move[altura * ancho];

        int[] xMovimiento = { p, q, -q, -p, -p, -q, q, p };
        int[] yMovimiento = { q, p, p, q, -q, -p, -p, -q };

        sol[filaInicio][colInicio] = 1; // Inicia desde 1

        // Store the initial coordinates in the moves array
        moves[0] = new Move(filaInicio, colInicio);

        if (!resolverRecorridoCaballoUtil(filaInicio, colInicio, 2, sol, xMovimiento, yMovimiento, filaObjetivo,
                colObjetivo, 0)) {
            System.out.println("No hay solución");
            return false;
        } else {
            imprimirSolucion(sol);
            printMoves(); // Print the (x, y) coordinates of each move
            return true;
        }
    }

    public static boolean resolverRecorridoCaballoUtil(int x, int y, int movimiento, int sol[][], int xMovimiento[],
            int yMovimiento[], int filaObjetivo, int colObjetivo, int intentos) {
        int k, siguiente_x, siguiente_y;

        if (movimiento == altura * ancho + 1) {
            return true;
        }

        if (intentos >= 0) {
            obt++;
        }

        if (obt >= MAX_INTENTOS) {
            return false;
        }

        for (k = 0; k < 8; k++) {
            siguiente_x = x + xMovimiento[k];
            siguiente_y = y + yMovimiento[k];

            if (esSeguro(siguiente_x, siguiente_y, sol)) {
                sol[siguiente_x][siguiente_y] = movimiento;

                // Store the move coordinates in the moves array
                moves[movimiento - 1] = new Move(siguiente_x, siguiente_y);

                if (siguiente_x == filaObjetivo && siguiente_y == colObjetivo) {
                    return true;
                }

                if (resolverRecorridoCaballoUtil(siguiente_x, siguiente_y, movimiento + 1, sol, xMovimiento,
                        yMovimiento, filaObjetivo, colObjetivo, intentos + 1)) {
                    return true;
                } else {
                    sol[siguiente_x][siguiente_y] = 0;
                }
            }
        }
        return false;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce la fila de inicio (0-7): ");
        int filaInicio = scanner.nextInt();

        System.out.print("Introduce la columna de inicio (0-8): ");
        int colInicio = scanner.nextInt();

        if (filaInicio < 0 || filaInicio >= altura || colInicio < 0 || colInicio >= ancho) {
            System.out.println("Posición de inicio inválida. Asegúrate de que esté dentro del tablero.");
            return;
        }

        System.out.print("Introduce la fila objetivo (0-7): ");
        int filaObjetivo = scanner.nextInt();

        System.out.print("Introduce la columna objetivo (0-8): ");
        int colObjetivo = scanner.nextInt();

        if (filaObjetivo < 0 || filaObjetivo >= altura || colObjetivo < 0 || colObjetivo >= ancho) {
            System.out.println("Posición objetivo inválida. Asegúrate de que esté dentro del tablero.");
            return;
        }

        System.out.print("Introduce el número de casillas para el primer movimiento (p): ");
        int p = scanner.nextInt();

        System.out.print("Introduce el número de casillas para el segundo movimiento (q): ");
        int q = scanner.nextInt();

        if (altura <= 0 || ancho <= 0 || p <= 0 || q <= 0) {
            System.out.println("La altura, la anchura, p y q deben ser mayores que cero.");
            return;
        }

        if (filaInicio == filaObjetivo && colInicio == colObjetivo) {
            System.out.println("El caballo ya está en la posición objetivo.");
            return;
        }

        resolverRecorridoCaballo(filaInicio, colInicio, filaObjetivo, colObjetivo, p, q);
    }
}
