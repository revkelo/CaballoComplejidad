package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MovimientoCaballo {
    private int altura;
    private int ancho;
    private int MAX_INTENTOS = 10000;
    private int obt;
    private ArrayList<Point> moves;
    private boolean solucion;
    private int[][] heuristic;

    public MovimientoCaballo() {
        moves = new ArrayList<>();
    }

    public void printMoves() {
        System.out.print("Moves: ");
        for (int i = 0; i < moves.size(); i++) {
            if (moves.get(i) != null) {
                System.out.print("(" + moves.get(i).x + "," + moves.get(i).y + ") ");
            }
        }
        System.out.println();
    }

    public boolean esSeguro(int x, int y, int sol[][]) {
        return (x >= 0 && x < altura && y >= 0 && y < ancho && sol[x][y] == 0);
    }

    public void imprimirSolucion(int sol[][]) {
        for (int x = 0; x < altura; x++) {
            for (int y = 0; y < ancho; y++) {
                System.out.print(sol[x][y] + " ");
            }
            System.out.println();
        }
    }

    public int[][] resolverRecorridoCaballo(int altura, int ancho, int filaInicio, int colInicio, int filaObjetivo,
                                            int colObjetivo, int p, int q) {
        int sol[][] = new int[altura][ancho];

        this.altura = altura;
        this.ancho = ancho;

        for (int x = 0; x < altura; x++) {
            for (int y = 0; y < ancho; y++) {
                sol[x][y] = 0;
            }
        }

        int[] xMovimiento = {p, q, -q, -p, -p, -q, q, p};
        int[] yMovimiento = {q, p, p, q, -q, -p, -p, -q};

        sol[filaInicio][colInicio] = 1;

        moves.add(new Point(filaInicio, colInicio));

        precalculateHeuristic(filaObjetivo, colObjetivo);

        if (!resolverRecorridoCaballoUtil(filaInicio, colInicio, 2, sol, xMovimiento, yMovimiento, filaObjetivo,
                colObjetivo, 0, Integer.MAX_VALUE)) {
            solucion = false;
            return sol;
        } else {
            solucion = true;
            imprimirSolucion(sol);
            printMoves();
            return sol;
        }
    }

    public boolean resolverRecorridoCaballoUtil(int x, int y, int movimiento, int sol[][], int xMovimiento[],
                                                int yMovimiento[], int filaObjetivo, int colObjetivo, int intentos, int cotaSuperior) {
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

        List<int[]> movimientosOrdenados = ordenarMovimientos(x, y, sol, xMovimiento, yMovimiento, filaObjetivo, colObjetivo);

        for (int[] mov : movimientosOrdenados) {
            siguiente_x = mov[0];
            siguiente_y = mov[1];

            if (esSeguro(siguiente_x, siguiente_y, sol)) {
                sol[siguiente_x][siguiente_y] = movimiento;

                moves.add(new Point(siguiente_x, siguiente_y));

                if (siguiente_x == filaObjetivo && siguiente_y == colObjetivo) {
                    return true;
                }

                // Agregar cota superior
                if (movimiento < cotaSuperior) {
                    if (resolverRecorridoCaballoUtil(siguiente_x, siguiente_y, movimiento + 1, sol, xMovimiento,
                            yMovimiento, filaObjetivo, colObjetivo, intentos + 1, cotaSuperior)) {
                        return true;
                    } else {
                        sol[siguiente_x][siguiente_y] = 0;
                    }
                }
            }
        }
        return false;
    }

    private List<int[]> ordenarMovimientos(int x, int y, int sol[][], int xMovimiento[], int yMovimiento[],
                                           int filaObjetivo, int colObjetivo) {
        List<int[]> movimientos = new ArrayList<>();

        for (int k = 0; k < 8; k++) {
            int siguiente_x = x + xMovimiento[k];
            int siguiente_y = y + yMovimiento[k];

            if (esSeguro(siguiente_x, siguiente_y, sol)) {
                int distancia = distanciaEuclidiana(siguiente_x, siguiente_y, filaObjetivo, colObjetivo);
                movimientos.add(new int[]{siguiente_x, siguiente_y, distancia});
            }
        }

        // Ordenar movimientos por distanciaEuclidiana ascendente
        movimientos.sort(Comparator.comparingInt(mov -> heuristic[mov[0]][mov[1]]));

        return movimientos;
    }

    private int distanciaEuclidiana(int x1, int y1, int x2, int y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }

    private void precalculateHeuristic(int filaObjetivo, int colObjetivo) {
        heuristic = new int[altura][ancho];
        for (int x = 0; x < altura; x++) {
            for (int y = 0; y < ancho; y++) {
                heuristic[x][y] = distanciaEuclidiana(x, y, filaObjetivo, colObjetivo);
            }
        }
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getMAX_INTENTOS() {
        return MAX_INTENTOS;
    }

    public void setMAX_INTENTOS(int MAX_INTENTOS) {
        this.MAX_INTENTOS = MAX_INTENTOS;
    }

    public int getObt() {
        return obt;
    }

    public void setObt(int obt) {
        this.obt = obt;
    }

    public ArrayList<Point> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Point> moves) {
        this.moves = moves;
    }

    public boolean isSolucion() {
        return solucion;
    }

    public void setSolucion(boolean solucion) {
        this.solucion = solucion;
    }
}
