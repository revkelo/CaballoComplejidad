package model;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Caballito {
	static ArrayList<Point> intermedios = new ArrayList<Point>();
	static ArrayList<Point> otrasCoordenadas = new ArrayList<Point>();
	static int paso = 0, intermedio = 0;

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
		
		otrasCoordenadas.add(new Point(1, 1));
		otrasCoordenadas.add(new Point(3, 2));
		otrasCoordenadas.add(new Point(4, 4));
//		otrasCoordenadas.add(new Point(2, 3));
//		otrasCoordenadas.add(new Point(0, 2));
//		otrasCoordenadas.add(new Point(1, 4));
//		otrasCoordenadas.add(new Point(2, 2));
//		otrasCoordenadas.add(new Point(0, 3));
		
		// Agregar las coordenadas adicionales
//		otrasCoordenadas.add(new Point(0, 0));
//		otrasCoordenadas.add(new Point(1, 2));
//		otrasCoordenadas.add(new Point(0, 4));
//		otrasCoordenadas.add(new Point(2, 3));
//		otrasCoordenadas.add(new Point(0, 2));
//		otrasCoordenadas.add(new Point(1, 4));
//		otrasCoordenadas.add(new Point(2, 2));
//		otrasCoordenadas.add(new Point(0, 3));

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
		System.out.println(otrasCoordenadas.get(paso).getX() + "  " + otrasCoordenadas.get(paso).getY());
		int otro = paso;
		int pasoaux = 0;
		for (int i = intermedio; i < intermedios.size(); i++) {
			for (int k = 0; k < buttons.length; k++) {
				for (int j = 0; j < buttons[0].length; j++) {
					buttons[k][j].setBackground(Color.white);

				}
			}

			if (otrasCoordenadas.get(otro).getX() == intermedios.get(i).x
					&& otrasCoordenadas.get(otro).getY() == intermedios.get(i).y) {

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
		System.out.println(otrasCoordenadas.get(paso).getX() + "  " + otrasCoordenadas.get(paso).getY());
		int otro = paso;
		int pasoaux = 0;
		for (int i = intermedio; i >= 0; i--) {
			
			for (int k = 0; k < buttons.length; k++) {
				for (int j = 0; j < buttons[0].length; j++) {
					buttons[k][j].setBackground(Color.white);

				}
			}

			if (otrasCoordenadas.get(otro).getX() == intermedios.get(i).x
					&& otrasCoordenadas.get(otro).getY() == intermedios.get(i).y) {

				int x = (int) intermedios.get(i).x;
				int y = (int) intermedios.get(i).y;
				buttons[x][y].setBackground(Color.yellow); // Cambiar el color del botón
				paso = otro;
				intermedio = i;
				otro--;
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

	private static void paintIntermediateCoordinates(JButton[][] buttons) {
		int otro = 0;
		for (Point point : intermedios) {

			for (int i = 0; i < buttons.length; i++) {
				for (int j = 0; j < buttons[0].length; j++) {
					buttons[i][j].setBackground(Color.white); // Cambiar el color del botón

				}
			}

			if (otrasCoordenadas.get(otro).getX() == point.getX()
					&& otrasCoordenadas.get(otro).getY() == point.getY()) {

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
}