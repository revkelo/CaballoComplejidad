package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ventana extends JFrame {
	private JTextField alturaTextField;
	private JTextField anchuraTextField;
	private JPanel panel;
	private JButton crearGridButton, resaltar;
	private JButton[][] botones;

	public Ventana() {

		setTitle("Matriz de Botones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1800, 1000);
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);

		alturaTextField = new JTextField();
		alturaTextField.setBounds(50, 10, 50, 30);
		add(alturaTextField);

		anchuraTextField = new JTextField();
		anchuraTextField.setBounds(120, 10, 50, 30);
		add(anchuraTextField);

		crearGridButton = new JButton("Crear Grid");
		crearGridButton.setBounds(200, 10, 120, 30);
		crearGridButton.setActionCommand("Crear");
		add(crearGridButton);
		
		resaltar = new JButton("Resaltar");
		resaltar.setBounds(380, 10, 120, 30);
		resaltar.setActionCommand("Resaltar");
		add(resaltar);

		panel = new JPanel();
		panel.setLayout(null);

		panel.setBounds(50, 50, 400, 400);
		add(panel);

		setVisible(true);

	}

	public void resaltar(int x, int y) {

		botones[x][y].setBackground(Color.yellow);
		System.out.println("Cambiado  "+x+"    "+y);
		panel.repaint();

	}

	public void crearGrid() {

		int altura = Integer.parseInt(alturaTextField.getText());
		int anchura = Integer.parseInt(anchuraTextField.getText());
		panel.setLayout(new GridLayout(altura,anchura));
		panel.removeAll();

		botones = new JButton[altura][anchura];

		for (int i = 0; i < altura; i++) {
			for (int j = 0; j < anchura; j++) {
				botones[i][j] = new JButton( i +"  "+j );
				botones[i][j].setSize(100, 100);
				panel.add(botones[i][j]);
			}
		}

		panel.setSize(anchura * 80, altura * 80);

		panel.revalidate();
		panel.repaint();
	}

	/**
	 * @return the resaltar
	 */
	public JButton getResaltar() {
		return resaltar;
	}

	/**
	 * @param resaltar the resaltar to set
	 */
	public void setResaltar(JButton resaltar) {
		this.resaltar = resaltar;
	}

	/**
	 * @return the botones
	 */
	public JButton[][] getBotones() {
		return botones;
	}

	/**
	 * @param botones the botones to set
	 */
	public void setBotones(JButton[][] botones) {
		this.botones = botones;
	}

	/**
	 * @return the alturaTextField
	 */
	public JTextField getAlturaTextField() {
		return alturaTextField;
	}

	/**
	 * @param alturaTextField the alturaTextField to set
	 */
	public void setAlturaTextField(JTextField alturaTextField) {
		this.alturaTextField = alturaTextField;
	}

	/**
	 * @return the anchuraTextField
	 */
	public JTextField getAnchuraTextField() {
		return anchuraTextField;
	}

	/**
	 * @param anchuraTextField the anchuraTextField to set
	 */
	public void setAnchuraTextField(JTextField anchuraTextField) {
		this.anchuraTextField = anchuraTextField;
	}

	/**
	 * @return the panel
	 */
	public JPanel getPanel() {
		return panel;
	}

	/**
	 * @param panel the panel to set
	 */
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	/**
	 * @return the crearGridButton
	 */
	public JButton getCrearGridButton() {
		return crearGridButton;
	}

	/**
	 * @param crearGridButton the crearGridButton to set
	 */
	public void setCrearGridButton(JButton crearGridButton) {
		this.crearGridButton = crearGridButton;
	}

}
