package controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import view.Ventana;

public class Controller implements ActionListener {
	private Ventana vp;
	private ArrayList<Point> cordenadas;

	public Controller() {
		cordenadas = new ArrayList<Point>();
		vp = new Ventana();
		actionListeners();
	}

	public void actionListeners() {
		vp.getCrearGridButton().addActionListener(this);
		vp.getResaltar().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();

		if (comando.equals("Crear")) {

			vp.crearGrid();

		}
		if (comando.equals("Resaltar")) {
			cordenadas.add(new Point(0, 0));
			cordenadas.add(new Point(0, 1));
			cordenadas.add(new Point(0, 2));

			for (int i = 0; i < cordenadas.size(); i++) {
				vp.resaltar(cordenadas.get(i).x, cordenadas.get(i).x);
			}

		}

	}

}
