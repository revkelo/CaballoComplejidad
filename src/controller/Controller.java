package controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.FachadaModel;
import view.Ventana;

/**
 * Controlador que gestiona las interacciones entre la vista (`Ventana`) y el
 * modelo (`FachadaModel`) en la aplicación.
 * 
 * @author Kevin
 * @author Daniela
 * @author Nicolas
 */
public class Controller implements ActionListener {

	/**
	 * Referencia a la ventana de la aplicación.
	 */
	private Ventana vp;

	/**
	 * Lista de coordenadas utilizada en la aplicación.
	 */
	private ArrayList<Point> coordenadas;

	/**
	 * Fachada para acceder al modelo relacionado con el movimiento del caballo.
	 */
	private FachadaModel md;

	/**
	 * Constructor de la clase `Controller`. Inicializa las variables y establece
	 * los action listeners.
	 */
	public Controller() {
		coordenadas = new ArrayList<Point>();
		md = new FachadaModel();
		vp = new Ventana();
		actionListeners();
	}

	/**
	 * Establece los action listeners para los componentes de la vista.
	 */
	public void actionListeners() {
		vp.getCrearGridButton().addActionListener(this);
		vp.getResaltar().addActionListener(this);
		vp.getInicio().addActionListener(this);
		vp.getVolver().addActionListener(this);
		vp.getSiguiente().addActionListener(this);
		vp.getAnterior().addActionListener(this);
		vp.getMostrar().addActionListener(this);
	}

	/**
	 * Maneja las acciones realizadas por el usuario en la interfaz gráfica.
	 *
	 * @param e Objeto `ActionEvent` que representa la acción realizada.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
System.out.println(comando);
		if (comando.equals("mostrar")) {
			new Thread(new Runnable() {
				public void run() {
					md.getMc().mostrarPasos(vp.getBotones());
				}
			}).start();
		}

		if (comando.equals("siguiente")) {
			if (md.getMc().isAux()) {
				new Thread(new Runnable() {
					public void run() {
						md.getMc().siguientePaso(vp.getBotones());
						System.out.println("siguyebnte");
					}
				}).start();
			} else {
				vp.getE8().setText("Primero crea la matriz");
			}
		}

		if (comando.equals("anterior")) {
			if (md.getMc().isAux2()) {
				new Thread(new Runnable() {
					public void run() {
						md.getMc().anteriorPaso(vp.getBotones());
					}
				}).start();
			} else {
				vp.getE8().setText("Primero crea la matriz");
			}
		}

		if (comando.equals("Crear")) {
			try {
				if (vp.getAlturaTextField().getText().equals("") && vp.getAnchuraTextField().getText().equals("")
						&& vp.getPxUnoTf().getText().equals("") && vp.getPyUnoTf().getText().equals("")
						&& vp.getPxDosTf().getText().equals("") && vp.getPyDosTf().getText().equals("")
						&& vp.getValorP().getText().equals("") && vp.getValorQ().getText().equals("")) {

					vp.getE8().setText("Llene todos los datos por favor");
					vp.getRta().setText("Ingrese los datos");

				} else {

					int altura = Integer.parseInt(vp.getAlturaTextField().getText());
					int anchura = Integer.parseInt(vp.getAnchuraTextField().getText());
					int filaInicio = Integer.parseInt(vp.getPxUnoTf().getText());
					int colinicio = Integer.parseInt(vp.getPyUnoTf().getText());
					int filaObjetivo = Integer.parseInt(vp.getPxDosTf().getText());
					int colObjetivo = Integer.parseInt(vp.getPyDosTf().getText());
					int p = Integer.parseInt(vp.getValorP().getText());
					int q = Integer.parseInt(vp.getValorQ().getText());

					if (altura >= 2 && altura <= 100 && anchura >= 2 && anchura <= 100) {
						vp.crearGrid(altura, anchura);
						int aux[][] = md.getMc().resolverRecorridoCaballo(altura, anchura, filaInicio, colinicio,
								filaObjetivo, colObjetivo, p, q);
						md.getMc().camino(p, q);
						
						System.out.println(md.getMc().isSolucion());
						System.out.println("////////////////////////////");
						for (int i = 0; i < aux.length; i++) {
							for (int j = 0; j < aux[0].length; j++) {
								vp.cambiar(i, j, aux[i][j] + "");
							}
							System.out.println();
						}
						if (md.getMc().isSolucion()) {
							vp.getRta().setText("Si hay solucion");
						} else {
							vp.getRta().setText("No hay solucion");
						}
					}else {
						vp.getE8().setText("Matriz no valdia");
					}

				}
			} catch (NumberFormatException e2) {
				vp.getE8().setText("No ingresar Letras y/o símbolos");
			} catch (Exception e2) {
				// Manejar excepciones según sea necesario
			}
		}

		if (comando.equals("botIniciar")) {
			vp.getPanelUno().setVisible(false);
			vp.getPanelDos().setVisible(true);
			vp.getPanelTres().setVisible(true);
			vp.getPmat().setVisible(true);
			vp.getVolver().setVisible(true);
		}

		if (comando.equals("volver")) {
			vp.getPanelUno().setVisible(true);
			vp.getPanelDos().setVisible(false);
			vp.getPanelTres().setVisible(false);
			vp.getPmat().setVisible(false);
			vp.getVolver().setVisible(false);

			// Restablecer campos de entrada y matriz
			vp.getAlturaTextField().setText("");
			vp.getAnchuraTextField().setText("");
			vp.getPxUnoTf().setText("");
			vp.getPyUnoTf().setText("");
			vp.getPxDosTf().setText("");
			vp.getPyDosTf().setText("");
			vp.getValorP().setText("");
			vp.getValorQ().setText("");

			vp.resetearMatriz();
		}

		if (comando.equals("Resaltar")) {
			for (int i = 0; i < md.getMc().getMoves().size(); i++) {
				vp.resaltar(md.getMc().getMoves().get(i).x, md.getMc().getMoves().get(i).y);
			}
		}
	}
}
