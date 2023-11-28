/**
 * Clase que maneja la logica del movimiento en el paquete model
 */
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

/**
 * 
 * Clase MovimientoCaballo: representa un algoritmo para encontrar el recorrido
 * para ir de un punto a a un punto b con p y q en un tablero.
 * 
 * @author Kevin
 * @author Daniela
 * @author Nicolas
 * 
 */
public class MovimientoCaballo {
	/**
	 * Altura del tablero para el recorrido .
	 */
	private int altura;

	/**
	 * Ancho del tablero para el recorrido.
	 */
	private int ancho;

	/**
	 * Número máximo de intentos para encontrar una solución al recorrido.
	 */
	private int maxIntentos = 10000;

	/**
	 * Contador de intentos realizados durante el proceso de búsqueda de solución.
	 */
	private int obt;

	/**
	 * Lista que almacena los movimientos realizados durante el recorrido.
	 */
	private ArrayList<Point> moves;

	/**
	 * Lista que almacena los movimientos intermedios entre las casillas durante el
	 * recorrido.
	 */
	private ArrayList<Point> intermedios;

	/**
	 * Indica si se ha encontrado una solución al recorrido.
	 */
	private boolean solucion;

	/**
	 * Matriz que almacena la heurística de distancia euclidiana para cada posición
	 * en el tablero.
	 */
	private int[][] heuristic;

	/**
	 * Variable estática que indica el paso actual en el recorrido .
	 */
	static int paso = 0;

	/**
	 * Variable estática que indica el índice actual en la lista de movimientos
	 * intermedios.
	 */
	static int intermedio = 0;

	/**
	 * Bandera que indica si la variable auxiliar está habilitada.
	 */
	boolean aux;

	/**
	 * Bandera que indica si la variable auxiliar 2 está habilitada.
	 */
	boolean aux2;

	/**
	 * Constructor de la clase MovimientoCaballo. Inicializa las listas y variables
	 * necesarias.
	 */
	public MovimientoCaballo() {
		moves = new ArrayList<Point>();
		intermedios = new ArrayList<Point>();
	}

	/**
	 * Muestra el camino seguido desde la posición inicial hasta la posición
	 * objetivo.
	 * 
	 * @param p Fila inicial.
	 * @param q Columna inicial.
	 */
	public void camino(int p, int q) {
		if (solucion == true) {
			for (int i = 0; i < moves.size(); i++) {
//				System.out.println(moves.get(i).x + "  " + moves.get(i).y);
				try {
					internosMovimientos(p, q, moves.get(i).x, moves.get(i).y, moves.get(i + 1).x, moves.get(i + 1).y);
				} catch (Exception e) {

				}

			}
		}

	}

	/**
	 * Imprime las coordenadas de los movimientos realizados.
	 */
	public void printMoves() {
		System.out.print("Moves: ");
		for (int i = 0; i < moves.size(); i++) {
			if (moves.get(i) != null) {
				System.out.print("(" + moves.get(i).x + "," + moves.get(i).y + ") ");
			}
		}
//		System.out.println();
	}

	/**
	 * Verifica si una posición en el tablero es segura.
	 * 
	 * @param x   Fila
	 * @param y   Columna.
	 * @param sol Matriz que representa el estado del tablero.
	 * @return Verdadero si la posición es segura, falso en caso contrario.
	 */
	public boolean esSeguro(int x, int y, int sol[][]) {
		return (x >= 0 && x < altura && y >= 0 && y < ancho && sol[x][y] == 0);
	}

	/**
	 * Imprime la solución del recorrido en el tablero.
	 * 
	 * @param sol Matriz que representa el estado del tablero.
	 */
	public void imprimirSolucion(int sol[][]) {
		for (int x = 0; x < altura; x++) {
			for (int y = 0; y < ancho; y++) {
				System.out.print(sol[x][y] + " ");
			}
//			System.out.println();
		}
	}

	/**
	 * Resuelve el recorrido en el tablero utilizando el algoritmo backtracking.
	 * 
	 * @param altura       Altura del tablero.
	 * @param ancho        Ancho del tablero.
	 * @param filaInicio   Fila de inicio.
	 * @param colInicio    Columna de inicio.
	 * @param filaObjetivo Fila objetivo del recorrido.
	 * @param colObjetivo  Columna objetivo del recorrido.
	 * @param p            Movimiento vertical .
	 * @param q            Movimiento horizontal.
	 * @return Matriz que representa el estado final del tablero después del
	 *         recorrido.
	 */
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
		aux = true;
		aux2 = true;
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

	/**
	 * Método utilitario para resolver el recorrido utilizando la técnica de
	 * backtracking.
	 * 
	 * @param x            Fila actual.
	 * @param y            Columna actual.
	 * @param movimiento   Número del movimiento actual.
	 * @param sol          Matriz que representa el estado del tablero.
	 * @param xMovimiento  Arreglo de movimientos verticales posibles.
	 * @param yMovimiento  Arreglo de movimientos horizontales posibles.
	 * @param filaObjetivo Fila objetivo del recorrido.
	 * @param colObjetivo  Columna objetivo del recorrido.
	 * @param intentos     Número de intentos realizados.
	 * @param cotaSuperior Cota superior para la búsqueda.
	 * @return Verdadero si se encuentra una solución, falso en caso contrario.
	 */
	public boolean resolverRecorridoCaballoUtil(int x, int y, int movimiento, int sol[][], int xMovimiento[],
			int yMovimiento[], int filaObjetivo, int colObjetivo, int intentos, int cotaSuperior) {
		int k, siguiente_x, siguiente_y;

		if (movimiento == altura * ancho + 1) {
			return true;
		}

		if (intentos >= 0) {
			obt++;
		}

		if (obt >= maxIntentos) {
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

	/**
	 * Ordena los movimientos posibles basándose en la heurística de distancia
	 * euclidiana.
	 * 
	 * @param x            Fila actual.
	 * @param y            Columna actual.
	 * @param sol          Matriz que representa el estado del tablero.
	 * @param xMovimiento  Arreglo de movimientos verticales posibles.
	 * @param yMovimiento  Arreglo de movimientos horizontales posibles.
	 * @param filaObjetivo Fila objetivo del recorrido.
	 * @param colObjetivo  Columna objetivo del recorrido.
	 * @return Lista de movimientos ordenados por distancia euclidiana ascendente.
	 */
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

		movimientos.sort(Comparator.comparingInt(mov -> heuristic[mov[0]][mov[1]]));

		return movimientos;
	}

	/**
	 * Calcula la distancia euclidiana entre dos puntos en el tablero.
	 * 
	 * @param x1 Fila del primer punto.
	 * @param y1 Columna del primer punto.
	 * @param x2 Fila del segundo punto.
	 * @param y2 Columna del segundo punto.
	 * @return Distancia euclidiana entre los dos puntos.
	 */
	private int distanciaEuclidiana(int x1, int y1, int x2, int y2) {
		return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
	}

	/**
	 * Precalcula la heurística de distancia euclidiana para cada posición en el
	 * tablero.
	 * 
	 * @param filaObjetivo Fila objetivo del recorrido.
	 * @param colObjetivo  Columna objetivo del recorrido.
	 */
	private void precalculateHeuristic(int filaObjetivo, int colObjetivo) {
		heuristic = new int[altura][ancho];
		for (int x = 0; x < altura; x++) {
			for (int y = 0; y < ancho; y++) {
				heuristic[x][y] = distanciaEuclidiana(x, y, filaObjetivo, colObjetivo);
			}
		}
	}

	/**
	 * Muestra el movimiento en el tablero y la posición de las casillas
	 * disponibles.
	 * 
	 * @param p              Movimiento vertical.
	 * @param q              Movimiento horizontal.
	 * @param filaInicial    Fila inicial.
	 * @param columnaInicial Columna inicial.
	 * @param filaFinal      Fila final.
	 * @param columnaFinal   Columna final.
	 */
	private void internosMovimientos(int p, int q, int filaInicial, int columnaInicial, int filaFinal,
			int columnaFinal) {
		if (intermedios.isEmpty()) {
			intermedios.add(new Point(filaInicial, columnaInicial));
		}

//		System.out.println("Tablero con casillas disponibles y posición :");
		mostrarTablero(p, q, filaInicial, columnaInicial, filaFinal, columnaFinal);
	}

	/**
	 * Muestra en la consola las casillas disponibles y la posición en el tablero.
	 * 
	 * @param p              Movimiento vertical.
	 * @param q              Movimiento horizontal.
	 * @param filaCaballo    Fila actual.
	 * @param columnaCaballo Columna actual.
	 * @param filaFinal      Fila final.
	 * @param columnaFinal   Columna final.
	 */
	private void mostrarTablero(int p, int q, int filaCaballo, int columnaCaballo, int filaFinal, int columnaFinal) {
		int[][] matriz = { { p, q, 1 }, { q, p, 2 }, { -q, p, 3 }, { -p, q, 4 }, { -p, -q, 5 }, { -q, -p, 6 },
				{ q, -p, 7 }, { p, -q, 8 } };

//		System.out.println("Casillas disponibles:");

		for (int i = 0; i < matriz.length; i++) {
			int nuevaFila = filaCaballo + matriz[i][0];
			int nuevaColumna = columnaCaballo + matriz[i][1];
			if (nuevaFila == filaFinal && nuevaColumna == columnaFinal) {
//				System.out.println(nuevaFila + " / " + columnaFinal);
//				System.out.println("son iguales el caso es el " + matriz[i][2]);
				intermedios(matriz[i][2], filaCaballo, columnaCaballo, matriz[i][0], matriz[i][1]);
				break;
			}
		}
	}

	/**
	 * Obtiene los movimientos intermedios y los almacena en la lista intermedios.
	 * 
	 * @param caso           Caso que determina la dirección del movimiento.
	 * @param filaCaballo    Fila actual.
	 * @param columnaCaballo Columna actual.
	 * @param filaFinal      Fila final.
	 * @param columnaFinal   Columna final.
	 */
	public void intermedios(int caso, int filaCaballo, int columnaCaballo, int filaFinal, int columnaFinal) {
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

	/**
	 * Método para avanzar al siguiente paso en la interfaz gráfica.
	 * 
	 * @param buttons Matriz de botones que representa el tablero.
	 */
	public void siguientePaso(JButton[][] buttons) {

		if (moves.isEmpty()) {
			aux = false;
		} else {
			aux = true;
//			System.out.println(moves.get(paso).getX() + "  " + moves.get(paso).getY());
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
					buttons[x][y].setBackground(new Color(253, 232, 98)); // Cambiar el color del botón
					paso = otro;
					intermedio = i;
					otro++;
					pasoaux++;
				} else {

					int x = (int) intermedios.get(i).x;
					int y = (int) intermedios.get(i).y;
					buttons[x][y].setBackground(new Color(120, 206, 214)); // Cambiar el color del botón
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
	}

	/**
	 * Método para retroceder al paso anterior en la interfaz gráfica.
	 * 
	 * @param buttons Matriz de botones que representa el tablero.
	 */
	public void anteriorPaso(JButton[][] buttons) {
		if (moves.isEmpty()) {
			aux2 = false;
		} else {
			aux2 = true;
//			System.out.println(moves.get(paso).getX() + "  " + moves.get(paso).getY());
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
					buttons[x][y].setBackground(new Color(253, 232, 98));
					paso = otro;
					intermedio = i;
					otro--;
					pasoaux++;
				} else {

					int x = (int) intermedios.get(i).x;
					int y = (int) intermedios.get(i).y;
					buttons[x][y].setBackground(new Color(120, 206, 214));
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
	}

	/**
	 * Muestra todos los pasos en la interfaz gráfica.
	 * 
	 * @param buttons Matriz de botones que representa el tablero.
	 */
	public void mostrarPasos(JButton[][] buttons) {
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
				buttons[x][y].setBackground(new Color(253, 232, 98)); // Cambiar el color amarillo del botón
				otro++;

			} else {

				int x = (int) point.getX();
				int y = (int) point.getY();
				buttons[x][y].setBackground(new Color(120, 206, 214)); // Cambiar el color azul del botón
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Obtiene la altura del tablero para el recorrido .
	 * 
	 * @return Altura del tablero.
	 */
	public int getAltura() {
		return altura;
	}

	/**
	 * Establece la altura del tablero para el recorrido .
	 * 
	 * @param altura Altura del tablero.
	 */
	public void setAltura(int altura) {
		this.altura = altura;
	}

	/**
	 * Obtiene el ancho del tablero para el recorrido .
	 * 
	 * @return Ancho del tablero.
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * Establece el ancho del tablero para el recorrido .
	 * 
	 * @param ancho Ancho del tablero.
	 */
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

//	/**
//	 * Obtiene el número máximo de intentos para encontrar una solución al recorrido
//	 * .
//	 * 
//	 * @return Número máximo de intentos.
//	 */
//	public int getMAX_INTENTOS() {
//		return maxIntentos;
//	}
//
//	/**
//	 * Establece el número máximo de intentos para encontrar una solución al
//	 * recorrido .
//	 * 
//	 * @param MAX_INTENTOS Número máximo de intentos.
//	 */
//	public void setMAX_INTENTOS(int MAX_INTENTOS) {
//		this.maxIntentos = MAX_INTENTOS;
//	}

	/**
	 * Obtiene el contador de intentos realizados durante el proceso de búsqueda de
	 * solución.
	 * 
	 * @return Contador de intentos.
	 */
	public int getObt() {
		return obt;
	}

	/**
	 * Establece el contador de intentos realizados durante el proceso de búsqueda
	 * de solución.
	 * 
	 * @param obt Contador de intentos.
	 */
	public void setObt(int obt) {
		this.obt = obt;
	}

	/**
	 * Obtiene la lista de movimientos realizados por el caballo durante el
	 * recorrido.
	 * 
	 * @return Lista de movimientos.
	 */
	public ArrayList<Point> getMoves() {
		return moves;
	}

	/**
	 * Establece la lista de movimientos realizados por el caballo durante el
	 * recorrido.
	 * 
	 * @param moves Lista de movimientos.
	 */
	public void setMoves(ArrayList<Point> moves) {
		this.moves = moves;
	}

	/**
	 * Verifica si se ha encontrado una solución al recorrido .
	 * 
	 * @return true si hay una solución, false en caso contrario.
	 */
	public boolean isSolucion() {
		return solucion;
	}

	/**
	 * Establece si se ha encontrado una solución al recorrido .
	 * 
	 * @param solucion true si hay una solución, false en caso contrario.
	 */
	public void setSolucion(boolean solucion) {
		this.solucion = solucion;
	}

	/**
	 * Obtiene el valor de la bandera auxiliar.
	 * 
	 * @return true si la bandera auxiliar está habilitada, false en caso contrario.
	 */
	public boolean isAux() {
		return aux;
	}

	/**
	 * Establece el valor de la bandera auxiliar.
	 * 
	 * @param aux Valor de la bandera auxiliar.
	 */
	public void setAux(boolean aux) {
		this.aux = aux;
	}

	/**
	 * Obtiene el valor de la bandera auxiliar 2.
	 * 
	 * @return true si la bandera auxiliar 2 está habilitada, false en caso
	 *         contrario.
	 */
	public boolean isAux2() {
		return aux2;
	}

	/**
	 * Establece el valor de la bandera auxiliar 2.
	 * 
	 * @param aux2 Valor de la bandera auxiliar 2.
	 */
	public void setAux2(boolean aux2) {
		this.aux2 = aux2;
	}

}
