package model;

import java.awt.Point;
import java.util.ArrayList;

public class MovimientoCaballo {
	private int altura;
	private int ancho;
	private int MAX_INTENTOS = 10000;
	private int obt;
	private ArrayList<Point> moves;
	private boolean solucion;

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

		int[] xMovimiento = { p, q, -q, -p, -p, -q, q, p };
		int[] yMovimiento = { q, p, p, q, -q, -p, -p, -q };

		sol[filaInicio][colInicio] = 1;

		moves.add(new Point(filaInicio, colInicio));

		if (!resolverRecorridoCaballoUtil(filaInicio, colInicio, 2, sol, xMovimiento, yMovimiento, filaObjetivo,
				colObjetivo, 0)) {
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

				moves.add(new Point(siguiente_x, siguiente_y));

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

	/**
	 * @return the altura
	 */
	public int getAltura() {
		return altura;
	}

	/**
	 * @param altura the altura to set
	 */
	public void setAltura(int altura) {
		this.altura = altura;
	}

	/**
	 * @return the ancho
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * @param ancho the ancho to set
	 */
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	/**
	 * @return the mAX_INTENTOS
	 */
	public int getMAX_INTENTOS() {
		return MAX_INTENTOS;
	}

	/**
	 * @param mAX_INTENTOS the mAX_INTENTOS to set
	 */
	public void setMAX_INTENTOS(int mAX_INTENTOS) {
		MAX_INTENTOS = mAX_INTENTOS;
	}

	/**
	 * @return the obt
	 */
	public int getObt() {
		return obt;
	}

	/**
	 * @param obt the obt to set
	 */
	public void setObt(int obt) {
		this.obt = obt;
	}

	/**
	 * @return the moves
	 */
	public ArrayList<Point> getMoves() {
		return moves;
	}

	/**
	 * @param moves the moves to set
	 */
	public void setMoves(ArrayList<Point> moves) {
		this.moves = moves;
	}

	/**
	 * @return the solucion
	 */
	public boolean isSolucion() {
		return solucion;
	}

	/**
	 * @param solucion the solucion to set
	 */
	public void setSolucion(boolean solucion) {
		this.solucion = solucion;
	}




}
