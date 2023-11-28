/**
 * Clase que maneja la conexion de vista y model que pertenece al paquete controller
 */
package controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.FachadaModel;
import view.FachadaView;

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
	private FachadaView view;

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
		view = new FachadaView();
		actionListeners();
	}

	/**
	 * Establece los action listeners para los componentes de la vista.
	 */
	public void actionListeners() {
		view.getVp().getCrearGridButton().addActionListener(this);
		view.getVp().getResaltar().addActionListener(this);
		view.getVp().getInicio().addActionListener(this);
		view.getVp().getVolver().addActionListener(this);
		view.getVp().getSiguiente().addActionListener(this);
		view.getVp().getAnterior().addActionListener(this);
		view.getVp().getMostrar().addActionListener(this);
		view.getVp().getReiniciarItem().addActionListener(this);
	}

	/**
	 * Maneja las acciones realizadas por el usuario en la interfaz gráfica.
	 *
	 * @param e Objeto `ActionEvent` que representa la acción realizada.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equals("ReiniciarTodo")) {
System.out.println("rei");
		}
		if (comando.equals("mostrar")) {
			new Thread(new Runnable() {
				public void run() {
					md.getMc().mostrarPasos(view.getVp().getBotones());
				}
			}).start();
		}

		if (comando.equals("siguiente")) {
			if (md.getMc().isAux()) {
				new Thread(new Runnable() {
					public void run() {
						md.getMc().siguientePaso(view.getVp().getBotones());

					}
				}).start();
			} else {
				view.getMsj().showError("Primero cree la matriz");
//				view.getVp().getE8().setText("Primero crea la matriz");
			}
		}

		if (comando.equals("anterior")) {
			if (md.getMc().isAux2()) {
				new Thread(new Runnable() {
					public void run() {
						md.getMc().anteriorPaso(view.getVp().getBotones());
					}
				}).start();
			} else {
				view.getMsj().showError("Primero cree la matriz");

//				vp.getE8().setText("Primero crea la matriz");
			}
		}

		if (comando.equals("Crear")) {
			try {
				if (view.getVp().getAlturaTextField().getText().equals("")
						|| view.getVp().getAnchuraTextField().getText().equals("")
						|| view.getVp().getPxUnoTf().getText().equals("")
						|| view.getVp().getPyUnoTf().getText().equals("")
						|| view.getVp().getPxDosTf().getText().equals("")
						|| view.getVp().getPyDosTf().getText().equals("")
						|| view.getVp().getValorP().getText().equals("")
						|| view.getVp().getValorQ().getText().equals("")) {

					view.getMsj().showError("Ingrese todos los datos solicitados");

//					view.getVp().getE8().setText("Llene todos los datos por favor");
//					view.getVp().getRta().setText("Ingrese los datos");

				} else {

					int altura = Integer.parseInt(view.getVp().getAlturaTextField().getText());
					int anchura = Integer.parseInt(view.getVp().getAnchuraTextField().getText());
					int filaInicio = Integer.parseInt(view.getVp().getPxUnoTf().getText());
					int colinicio = Integer.parseInt(view.getVp().getPyUnoTf().getText());
					int filaObjetivo = Integer.parseInt(view.getVp().getPxDosTf().getText());
					int colObjetivo = Integer.parseInt(view.getVp().getPyDosTf().getText());
					int p = Integer.parseInt(view.getVp().getValorP().getText());
					int q = Integer.parseInt(view.getVp().getValorQ().getText());

					if (filaInicio < altura && filaInicio >= 0 && filaObjetivo < altura && filaObjetivo >= 0
							&& colinicio < anchura && colinicio >= 0 && colObjetivo < anchura && colObjetivo >= 0) {

						if (p > 0 && q > 0) {

							if (altura >= 2 && altura <= 100 && anchura >= 2 && anchura <= 100) {
								view.getVp().crearGrid(altura, anchura);
								int aux[][] = md.getMc().resolverRecorridoCaballo(altura, anchura, filaInicio,
										colinicio, filaObjetivo, colObjetivo, p, q);
								md.getMc().camino(p, q);

//								System.out.println(md.getMc().isSolucion());
//								System.out.println("////////////////////////////");
								for (int i = 0; i < aux.length; i++) {
									for (int j = 0; j < aux[0].length; j++) {
										view.getVp().cambiar(i, j, aux[i][j] + "");
									}
//									System.out.println();
								}
								if (md.getMc().isSolucion()) {

									view.getVp().getRta().setText("Si hay solucion");
									view.getVp().getTiste().setVisible(false);
									view.getVp().getAnterior().setVisible(true);
									view.getVp().getSiguiente().setVisible(true);
									view.getVp().getMostrar().setVisible(true);

								} else {
									view.getVp().getRta().setText("No hay solucion");
									view.getVp().getTiste().setVisible(true);
									view.getVp().getAnterior().setVisible(false);
									view.getVp().getSiguiente().setVisible(false);
									view.getVp().getMostrar().setVisible(false);
								}
							} else {

								view.getMsj().showError("Matriz no valida");

//						view.getVp().getE8().setText("Matriz no valdia");
							}

						} else {
							view.getMsj().showError("Valores invalidos para p y/o q");
						}

					} else {
						view.getMsj().showError("Valores invalidos para los puntos");
					}

				}
			} catch (NumberFormatException e2) {

				view.getMsj().showError("No ingresar letras y/o símbolos");
//				view.getVp().getE8().setText("No ingresar Letras y/o símbolos");
			} catch (Exception e2) {
				// Manejar excepciones según sea necesario
			}
		}

		if (comando.equals("botIniciar")) {
			view.getVp().getPanelUno().setVisible(false);
			view.getVp().getPanelDos().setVisible(true);
			view.getVp().getPanelTres().setVisible(true);
			view.getVp().getPmat().setVisible(true);
			view.getVp().getVolver().setVisible(true);
			view.getVp().menuVisible(true);
		}

		if (comando.equals("volver")) {
			view.getVp().getPanelUno().setVisible(true);
			view.getVp().getPanelDos().setVisible(false);
			view.getVp().getPanelTres().setVisible(false);
			view.getVp().getPmat().setVisible(false);
			view.getVp().getVolver().setVisible(false);

			// Restablecer campos de entrada y matriz
			view.getVp().getAlturaTextField().setText("");
			view.getVp().getAnchuraTextField().setText("");
			view.getVp().getPxUnoTf().setText("");
			view.getVp().getPyUnoTf().setText("");
			view.getVp().getPxDosTf().setText("");
			view.getVp().getPyDosTf().setText("");
			view.getVp().getValorP().setText("");
			view.getVp().getValorQ().setText("");

			view.getVp().resetearMatriz();

			view.getVp().getRta().setText("");
			view.getVp().getTiste().setVisible(false);
			view.getVp().getAnterior().setVisible(false);
			view.getVp().getSiguiente().setVisible(false);
			view.getVp().getMostrar().setVisible(false);
		}

		if (comando.equals("Resaltar")) {
			for (int i = 0; i < md.getMc().getMoves().size(); i++) {
				view.getVp().resaltar(md.getMc().getMoves().get(i).x, md.getMc().getMoves().get(i).y);
			}
		}
	}
}
