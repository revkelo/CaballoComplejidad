/**
 * Clase que maneja la conexion de vista y model que pertenece al paquete controller
 */
package co.edu.unbosque.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import co.edu.unbosque.model.FachadaModel;
import co.edu.unbosque.view.FachadaView;

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
	 * Fachada para acceder al modelo relacionado con el movimiento del caballo.
	 */
	private FachadaModel md;

	/**
	 * Constructor de la clase `Controller`. Inicializa las variables y establece
	 * los action listeners.
	 */
	public Controller() {
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
		view.getVp().getParar().addActionListener(this);
	}

	/**
	 * Restablece completamente la interfaz gráfica y los datos asociados. Limpia y
	 * restablece los campos de entrada de la vista, oculta elementos visuales,
	 * limpia la matriz en el controlador, y reinicia la visualización de la matriz
	 * en la vista. También oculta elementos adicionales como botones y textos.
	 */
	public void resetTotal() {
		view.getVp().getAlturaTextField().setText("");
		view.getVp().getAnchuraTextField().setText("");
		view.getVp().getPxUnoTf().setText("");
		view.getVp().getPyUnoTf().setText("");
		view.getVp().getPxDosTf().setText("");
		view.getVp().getPyDosTf().setText("");
		view.getVp().getValorP().setText("");
		view.getVp().getValorQ().setText("");
		view.getVp().getPmat().setVisible(false);
		view.getVp().getPanelBotones().setVisible(false);
		md.getMc().limpiar();
		view.getVp().resetearMatriz();
		md.getMc().setPaso(0);
		md.getMc().setIntermedio(0);
		view.getVp().getRta().setText("");
		view.getVp().getTiste().setVisible(false);
		view.getVp().getAnterior().setVisible(false);
		view.getVp().getSiguiente().setVisible(false);
		view.getVp().getMostrar().setVisible(false);

	}

	/**
	 * Maneja las acciones realizadas por el usuario en la interfaz gráfica.
	 *
	 * @param e Objeto `ActionEvent` que representa la acción realizada.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		String comando = e.getActionCommand();

		if (comando.equals("Stop")) {
			md.getMc().setStop(true);
		}

		if (comando.equals("ReiniciarTodo")) {
			resetTotal();
		}
		if (comando.equals("mostrar")) {
			new Thread(new Runnable() {
				public void run() {
					view.getVp().getParar().setVisible(true);
					view.getVp().getVolver().setVisible(false);
					view.getVp().menuVisible(false);
					view.getVp().getCrearGridButton().setVisible(false);
					view.getVp().getResaltar().setVisible(false);
					md.getMc().mostrarPasos(view.getVp().getBotones(), view.getVp().getMostrar(),
							view.getVp().getAnterior(), view.getVp().getSiguiente());
					view.getVp().getCrearGridButton().setVisible(true);
					view.getVp().getResaltar().setVisible(true);
					view.getVp().menuVisible(true);
					view.getVp().getVolver().setVisible(true);
					view.getVp().getParar().setVisible(false);
				}
			}).start();
		}

		if (comando.equals("siguiente")) {
			if (md.getMc().isAux()) {
				new Thread(new Runnable() {
					public void run() {
						view.getVp().getVolver().setVisible(false);
						view.getVp().menuVisible(false);
						view.getVp().getCrearGridButton().setVisible(false);
						view.getVp().getResaltar().setVisible(false);
						view.getVp().getSiguiente().setVisible(false);
						md.getMc().siguientePaso(view.getVp().getBotones(), view.getVp().getMostrar(),
								view.getVp().getAnterior());
						view.getVp().getSiguiente().setVisible(true);
						view.getVp().getCrearGridButton().setVisible(true);
						view.getVp().getResaltar().setVisible(true);
						view.getVp().menuVisible(true);
						view.getVp().getVolver().setVisible(true);
					}
				}).start();
			} else {
				view.getMsj().showError("Primero cree la matriz");

			}
		}

		if (comando.equals("anterior")) {
			if (md.getMc().isAux2()) {
				new Thread(new Runnable() {
					public void run() {
						view.getVp().getVolver().setVisible(false);
						view.getVp().menuVisible(false);
						view.getVp().getCrearGridButton().setVisible(false);
						view.getVp().getResaltar().setVisible(false);
						view.getVp().getAnterior().setVisible(false);
						md.getMc().anteriorPaso(view.getVp().getBotones(), view.getVp().getMostrar(),
								view.getVp().getSiguiente());
						view.getVp().getAnterior().setVisible(true);
						view.getVp().getCrearGridButton().setVisible(true);
						view.getVp().getResaltar().setVisible(true);
						view.getVp().menuVisible(true);
						view.getVp().getVolver().setVisible(true);
					}
				}).start();
			} else {
				view.getMsj().showError("Primero cree la matriz");

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

				} else {

					int altura = Integer.parseInt(view.getVp().getAlturaTextField().getText());
					int anchura = Integer.parseInt(view.getVp().getAnchuraTextField().getText());
					int filaInicio = Integer.parseInt(view.getVp().getPxUnoTf().getText());
					int colinicio = Integer.parseInt(view.getVp().getPyUnoTf().getText());
					int filaObjetivo = Integer.parseInt(view.getVp().getPyDosTf().getText());
					int colObjetivo = Integer.parseInt(view.getVp().getPxDosTf().getText());
					int p = Integer.parseInt(view.getVp().getValorP().getText());
					int q = Integer.parseInt(view.getVp().getValorQ().getText());

					if (filaInicio < altura && filaInicio >= 0 && filaObjetivo < altura && filaObjetivo >= 0
							&& colinicio < anchura && colinicio >= 0 && colObjetivo < anchura && colObjetivo >= 0) {

						if (p > 0 && q > 0) {
							if (!((filaInicio == filaObjetivo) && (colinicio == colObjetivo))) {

								if (altura >= 2 && altura <= 100 && anchura >= 2 && anchura <= 100) {
									resetTotal();
									view.getVp().crearGrid(altura, anchura);
									int aux[][] = md.getMc().resolverRecorridoCaballo(altura, anchura, filaInicio,
											colinicio, filaObjetivo, colObjetivo, p, q);
									md.getMc().camino(p, q);

									view.getVp().getAlturaTextField().setText("");
									view.getVp().getAnchuraTextField().setText("");
									view.getVp().getPxUnoTf().setText("");
									view.getVp().getPyUnoTf().setText("");
									view.getVp().getPxDosTf().setText("");
									view.getVp().getPyDosTf().setText("");
									view.getVp().getValorP().setText("");
									view.getVp().getValorQ().setText("");

									for (int i = 0; i < aux.length; i++) {
										for (int j = 0; j < aux[0].length; j++) {
											view.getVp().cambiar(i, j, aux[i][j] + "");
										}

									}
									if (md.getMc().isSolucion()) {

										view.getVp().getRta().setText("Si hay solución");
										view.getVp().getResaltar().setVisible(true);
										view.getVp().getTiste().setVisible(false);
										view.getVp().getAnterior().setVisible(true);
										view.getVp().getSiguiente().setVisible(true);
										view.getVp().getMostrar().setVisible(true);
										view.getVp().resaltarCamino(filaInicio, colinicio, Color.green);
										view.getVp().resaltarCamino(filaObjetivo, colObjetivo, Color.yellow);

									} else {

										resetTotal();
										view.getVp().getPanelBotones().setVisible(false);
										view.getVp().getResaltar().setVisible(false);
										view.getVp().getTiste().setVisible(true);
										view.getVp().getAnterior().setVisible(false);
										view.getVp().getSiguiente().setVisible(false);
										view.getVp().getMostrar().setVisible(false);
										view.getVp().getRta().setVisible(true);
										view.getVp().getRta().setText("No hay solución");
									}
								} else {

									view.getMsj().showError("Matriz no válida");

								}
							} else {
								view.getMsj().showError("Coordenadas iniciales iguales a las coordenadas finales");
							}

						} else {
							view.getMsj().showError("Valores inválidos para p y/o q");
						}

					} else {
						view.getMsj().showError("Valores inválidos para los puntos");
					}

				}
			} catch (NumberFormatException e2) {

				view.getMsj().showError("No ingresar letras y/o símbolos");

			} catch (Exception e2) {

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
			view.getVp().getPmat().setVisible(false);
			view.getVp().menuVisible(false);
			resetTotal();
		}

		if (comando.equals("Resaltar")) {
			for (int i = 0; i < md.getMc().getMoves().size(); i++) {
				view.getVp().resaltar(md.getMc().getMoves().get(i).x, md.getMc().getMoves().get(i).y);
			}
		}
	}
}
