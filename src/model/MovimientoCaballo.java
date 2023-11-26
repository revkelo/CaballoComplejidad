package model;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MovimientoCaballo {
	private int altura;
	private int ancho;
	private int MAX_INTENTOS = 10000;
	private int obt;
	private static ArrayList<Point> moves = new ArrayList<Point>();
	static ArrayList<Point> intermedios = new ArrayList<Point>();
	private boolean solucion;
	private int[][] heuristic;
	static int paso = 0, intermedio = 0;

	public MovimientoCaballo() {
		moves = new ArrayList<Point>();
		intermedios = new ArrayList<Point>();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Tablero de Ajedrez con Caballo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 400);

		// Crear un JPanel con GridLayout
		JPanel panel = new JPanel(new GridLayout(5, 5, 5, 5));

		// Crear una matriz de botones
		JButton[][] buttons = new JButton[5][5];

		// Inicializar y agregar botones al panel
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				buttons[i][j] = new JButton("(" + i + "," + j + ")");
				buttons[i][j].setBackground(Color.white);
				panel.add(buttons[i][j]);
			}
		}

		// Botón para pintar coordenadas intermedias
		JButton paintButton = new JButton("Pintar Coordenadas");
		paintButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				new Thread(new Runnable() {
					public void run() {
						paintIntermediateCoordinates(buttons);

					}
				}).start();
			}
		});
		frame.getContentPane().add(paintButton, "South"); // Agregar el botón en la parte inferior

		JButton siguientePasoButton = new JButton("Siguiente Paso");
		JButton pasoAnteriorButton = new JButton("Paso Anterior");

		// Agregar ActionListener para los nuevos botones
		siguientePasoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Botón 'Siguiente Paso' presionado.");
				new Thread(new Runnable() {
					public void run() {
						siguientePaso(buttons);

					}
				}).start();
			}
		});

		pasoAnteriorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Botón 'Paso Anterior' presionado.");
				new Thread(new Runnable() {
					public void run() {
						anteriorPaso(buttons);

					}
				}).start();
			}
		});

		// Agregar los nuevos botones al contenedor
		frame.getContentPane().add(siguientePasoButton, "East");
		frame.getContentPane().add(pasoAnteriorButton, "West");

		frame.getContentPane().add(panel);

		frame.setVisible(true);

		int p = 2;
		int q = 1;
		
		testKnightMovement(p, q, 1, 1, 3, 2);
		testKnightMovement(p, q, 3, 2, 4, 4);
//		testKnightMovement(p, q, 0, 4, 2, 3);
//		testKnightMovement(p, q, 2, 3, 0, 2);
//		testKnightMovement(p, q, 0, 2, 1, 4);
//		testKnightMovement(p, q, 1, 4, 2, 2);
//		testKnightMovement(p, q, 2, 2, 0, 3);
//		
		
//		testKnightMovement(p, q, 0, 0, 1, 2);
//		testKnightMovement(p, q, 1, 2, 0, 4);
//		testKnightMovement(p, q, 0, 4, 2, 3);
//		testKnightMovement(p, q, 2, 3, 0, 2);
//		testKnightMovement(p, q, 0, 2, 1, 4);
//		testKnightMovement(p, q, 1, 4, 2, 2);
//		testKnightMovement(p, q, 2, 2, 0, 3);

		// Moves: (0,0) (1,2) (0,4) (2,3) (0,2) (1,4) (2,2) (0,3)

		for (int i = 0; i < intermedios.size(); i++) {
			System.out.println(intermedios.get(i).getX() + "  " + intermedios.get(i).getY());
		}
		
		moves.add(new Point(1, 1));
		moves.add(new Point(3, 2));
		moves.add(new Point(4, 4));
//		moves.add(new Point(2, 3));
//		moves.add(new Point(0, 2));
//		moves.add(new Point(1, 4));
//		moves.add(new Point(2, 2));
//		moves.add(new Point(0, 3));
		
		// Agregar las coordenadas adicionales
//		moves.add(new Point(0, 0));
//		moves.add(new Point(1, 2));
//		moves.add(new Point(0, 4));
//		moves.add(new Point(2, 3));
//		moves.add(new Point(0, 2));
//		moves.add(new Point(1, 4));
//		moves.add(new Point(2, 2));
//		moves.add(new Point(0, 3));

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

		List<int[]> movimientosOrdenados = ordenarMovimientos(x, y, sol, xMovimiento, yMovimiento, filaObjetivo,
				colObjetivo);

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
				movimientos.add(new int[] { siguiente_x, siguiente_y, distancia });
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

	private static void testKnightMovement(int p, int q, int filaInicial, int columnaInicial, int filaFinal,
			int columnaFinal) {
		if (intermedios.isEmpty()) {
			intermedios.add(new Point(filaInicial, columnaInicial));
		}

		System.out.println("Tablero con casillas disponibles y posición del caballo:");
		mostrarTablero(p, q, filaInicial, columnaInicial, filaFinal, columnaFinal);
	}

	private static void mostrarTablero(int p, int q, int filaCaballo, int columnaCaballo, int filaFinal,
			int columnaFinal) {
		int[][] matriz = { { p, q, 1 }, { q, p, 2 }, { -q, p, 3 }, { -p, q, 4 }, { -p, -q, 5 }, { -q, -p, 6 },
				{ q, -p, 7 }, { p, -q, 8 } };

		System.out.println("Casillas disponibles:");

		for (int i = 0; i < matriz.length; i++) {
			int nuevaFila = filaCaballo + matriz[i][0];
			int nuevaColumna = columnaCaballo + matriz[i][1];
			if (nuevaFila == filaFinal && nuevaColumna == columnaFinal) {
				System.out.println(nuevaFila + " / " + columnaFinal);
				System.out.println("son iguales el caso es el " + matriz[i][2]);
				intermedios(matriz[i][2], filaCaballo, columnaCaballo, matriz[i][0], matriz[i][1]);
				break;
			}
		}
	}

	public static void intermedios(int caso, int filaCaballo, int columnaCaballo, int filaFinal, int columnaFinal) {
		switch (caso) {
		case 1:
			for (int i = 0; i < filaFinal; i++) {
				filaCaballo++;
				intermedios.add(new Point(filaCaballo, columnaCaballo));
			}
			for (int i = 0; i < columnaFinal; i++) {
				columnaCaballo++;
				intermedios.add(new Point(filaCaballo, columnaCaballo));
			}
			break;
		case 2:
			for (int i = 0; i < columnaFinal; i++) {
				columnaCaballo++;
				intermedios.add(new Point(filaCaballo, columnaCaballo));
			}
			for (int i = 0; i < filaFinal; i++) {
				filaCaballo++;
				intermedios.add(new Point(filaCaballo, columnaCaballo));
			}
			break;
		case 3:
			for (int i = 0; i < columnaFinal; i++) {
				columnaCaballo++;
				intermedios.add(new Point(filaCaballo, columnaCaballo));
			}
			for (int i = 0; i < Math.abs(filaFinal); i++) {
				filaCaballo--;
				intermedios.add(new Point(filaCaballo, columnaCaballo));
			}
			break;
		case 4:
			for (int i = 0; i < Math.abs(filaFinal); i++) {
				filaCaballo--;
				intermedios.add(new Point(filaCaballo, columnaCaballo));
			}
			for (int i = 0; i < columnaFinal; i++) {
				columnaCaballo++;
				intermedios.add(new Point(filaCaballo, columnaCaballo));
			}
			break;
		case 5:
			for (int i = 0; i < Math.abs(filaFinal); i++) {
				filaCaballo--;
				intermedios.add(new Point(filaCaballo, columnaCaballo));
			}
			for (int i = 0; i < Math.abs(columnaFinal); i++) {
				columnaCaballo--;
				intermedios.add(new Point(filaCaballo, columnaCaballo));
			}
			break;
		case 6:
			for (int i = 0; i < Math.abs(columnaFinal); i++) {
				columnaCaballo--;
				intermedios.add(new Point(filaCaballo, columnaCaballo));
			}
			for (int i = 0; i < Math.abs(filaFinal); i++) {
				filaCaballo--;
				intermedios.add(new Point(filaCaballo, columnaCaballo));
			}
			break;
		case 7:
			for (int i = 0; i < Math.abs(columnaFinal); i++) {
				columnaCaballo--;
				intermedios.add(new Point(filaCaballo, columnaCaballo));
			}
			for (int i = 0; i < filaFinal; i++) {
				filaCaballo++;
				intermedios.add(new Point(filaCaballo, columnaCaballo));
			}
			break;
		case 8:
			for (int i = 0; i < filaFinal; i++) {
				filaCaballo++;
				intermedios.add(new Point(filaCaballo, columnaCaballo));
			}
			for (int i = 0; i < Math.abs(columnaFinal); i++) {
				columnaCaballo--;
				intermedios.add(new Point(filaCaballo, columnaCaballo));
			}
			break;
		default:
			break;
		}
	}

	private static void siguientePaso(JButton[][] buttons) {
		System.out.println(moves.get(paso).getX() + "  " + moves.get(paso).getY());
		int otro = paso;
		int pasoaux = 0;
		for (int i = intermedio; i < intermedios.size(); i++) {
			for (int k = 0; k < buttons.length; k++) {
				for (int j = 0; j < buttons[0].length; j++) {
					buttons[k][j].setBackground(Color.white);

				}
			}

			if (moves.get(otro).getX() == intermedios.get(i).x && moves.get(otro).getY() == intermedios.get(i).y) {

				int x = (int) intermedios.get(i).x;
				int y = (int) intermedios.get(i).y;
				buttons[x][y].setBackground(Color.yellow); // Cambiar el color del botón
				paso = otro;
				intermedio = i;
				otro++;
				pasoaux++;
			} else {

				int x = (int) intermedios.get(i).x;
				int y = (int) intermedios.get(i).y;
				buttons[x][y].setBackground(Color.green); // Cambiar el color del botón
			}

			if (pasoaux == 2) {
				return;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private static void anteriorPaso(JButton[][] buttons) {
		System.out.println(moves.get(paso).getX() + "  " + moves.get(paso).getY());
		int otro = paso;
		int pasoaux = 0;
		for (int i = intermedio; i >= 0; i--) {

			for (int k = 0; k < buttons.length; k++) {
				for (int j = 0; j < buttons[0].length; j++) {
					buttons[k][j].setBackground(Color.white);

				}
			}

			if (moves.get(otro).getX() == intermedios.get(i).x && moves.get(otro).getY() == intermedios.get(i).y) {

				int x = (int) intermedios.get(i).x;
				int y = (int) intermedios.get(i).y;
				buttons[x][y].setBackground(Color.yellow);
				paso = otro;
				intermedio = i;
				otro--;
				pasoaux++;
			} else {

				int x = (int) intermedios.get(i).x;
				int y = (int) intermedios.get(i).y;
				buttons[x][y].setBackground(Color.green);
			}

			if (pasoaux == 2) {
				return;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private static void paintIntermediateCoordinates(JButton[][] buttons) {
		int otro = 0;
		for (Point point : intermedios) {

			for (int i = 0; i < buttons.length; i++) {
				for (int j = 0; j < buttons[0].length; j++) {
					buttons[i][j].setBackground(Color.white); // Cambiar el color del botón

				}
			}

			if (moves.get(otro).getX() == point.getX() && moves.get(otro).getY() == point.getY()) {

				int x = (int) point.getX();
				int y = (int) point.getY();
				buttons[x][y].setBackground(Color.yellow); // Cambiar el color del botón
				otro++;

			} else {

				int x = (int) point.getX();
				int y = (int) point.getY();
				buttons[x][y].setBackground(Color.green); // Cambiar el color del botón
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
