package model;


import java.util.Scanner;

public class RecorridoCaballo {
    static int altura = 8; // Altura del tablero
    static int ancho = 9; // Anchura del tablero

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

    public static boolean resolverRecorridoCaballo(int filaInicio, int colInicio, int filaObjetivo, int colObjetivo) {
        int sol[][] = new int[altura][ancho];

        for (int x = 0; x < altura; x++) {
            for (int y = 0; y < ancho; y++) {
                sol[x][y] = 0;
            }
        }

        int[] xMovimiento = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] yMovimiento = {1, 2, 2, 1, -1, -2, -2, -1};

        sol[filaInicio][colInicio] = 1; // Inicia desde 1

        if (!resolverRecorridoCaballoUtil(filaInicio, colInicio, 2, sol, xMovimiento, yMovimiento, filaObjetivo, colObjetivo)) {
            System.out.println("No hay solución");
            return false;
        } else {
            imprimirSolucion(sol);
            return true;
        }
    }

    public static boolean resolverRecorridoCaballoUtil(int x, int y, int movimiento, int sol[][], int xMovimiento[], int yMovimiento[], int filaObjetivo, int colObjetivo) {
        int k, siguiente_x, siguiente_y;

        if (movimiento == altura * ancho + 1) {
            return true;
        }

        for (k = 0; k < 8; k++) {
            siguiente_x = x + xMovimiento[k];
            siguiente_y = y + yMovimiento[k];

            if (esSeguro(siguiente_x, siguiente_y, sol)) {
                sol[siguiente_x][siguiente_y] = movimiento;

                if (siguiente_x == filaObjetivo && siguiente_y == colObjetivo) {
                    // El caballo ha alcanzado la posición objetivo
                    return true;
                }

                if (resolverRecorridoCaballoUtil(siguiente_x, siguiente_y, movimiento + 1, sol, xMovimiento, yMovimiento, filaObjetivo, colObjetivo)) {
                    return true;
                } else {
                    sol[siguiente_x][siguiente_y] = 0; // Restablece la casilla a 0 en el backtracking
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

        if (altura <= 0 || ancho <= 0) {
            System.out.println("La altura y la anchura deben ser mayores que cero.");
            return;
        }

        if (filaInicio == filaObjetivo && colInicio == colObjetivo) {
            System.out.println("El caballo ya está en la posición objetivo.");
            return;
        }

        resolverRecorridoCaballo(filaInicio, colInicio, filaObjetivo, colObjetivo);
    }
}
