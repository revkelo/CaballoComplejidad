package controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.FachadaModel;
import view.Ventana;

public class Controller implements ActionListener {
	private Ventana vp;
	private ArrayList<Point> coordenadas;
	private FachadaModel md;

	public Controller() {
		coordenadas = new ArrayList<Point>();
		md = new FachadaModel();
		vp = new Ventana();
		actionListeners();
	}

	public void actionListeners() {
		vp.getCrearGridButton().addActionListener(this);
		vp.getResaltar().addActionListener(this);
		vp.getInicio().addActionListener(this);
		vp.getVolver().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		if (comando.equals("Crear")) {

			vp.crearGrid(Integer.parseInt(vp.getAlturaTextField().getText()),
					Integer.parseInt(vp.getAnchuraTextField().getText()));
			int aux[][] = md.getMc().resolverRecorridoCaballo(Integer.parseInt(vp.getAlturaTextField().getText()),
					Integer.parseInt(vp.getAnchuraTextField().getText()), 0, 0, 0, 3, 2, 1);

			System.out.println(md.getMc().isSolucion());
			System.out.println("////////////////////////////");
			for (int i = 0; i < aux.length; i++) {
				for (int j = 0; j < aux[0].length; j++) {
					vp.cambiar(i, j, aux[i][j] + "");
				}
				System.out.println();
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
		}

		if (comando.equals("Resaltar")) {

			for (int i = 0; i < md.getMc().getMoves().size(); i++) {
				vp.resaltar(md.getMc().getMoves().get(i).x, md.getMc().getMoves().get(i).y);
			}

		}

	}
}
