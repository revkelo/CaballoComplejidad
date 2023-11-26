package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Ventana extends JFrame {

	private JPanel panelUno, panelDos, panelTres, pmat;
	private JLabel img, titulo, rta, e1, e2, e3, px, py, e4, e5, e6, e7, e8;
	private JTextField alturaTextField, anchuraTextField, pxUnoTf, pyUnoTf, pxDosTf, pyDosTf, valorQ, valorP;
	private JButton mostrar, siguiente, anterior, volver;
	private JButton crearGridButton, resaltar, inicio;
	private JButton[][] botones;

	public Ventana() {

		setTitle("Vamo a pasar muchachos");
		setSize(1000, 800);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		inicializarComponentes();

		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

	}

	private void inicializarComponentes() {

		// boton volver

		volver = new JButton("Volver");
		volver.setBounds(80, 630, 100, 50);
		volver.setFont(new Font("Monospaced", Font.BOLD, 10));
		volver.setForeground(Color.black);
		volver.setActionCommand("volver");
		volver.setVisible(false);
		add(volver);

		// panel uno inicial

		panelUno = new JPanel();
		panelUno.setLayout(null);
		panelUno.setVisible(true);
		panelUno.setBounds(300, 290, 380, 200);
		panelUno.setBackground(Color.black);
		add(panelUno);

		titulo = new JLabel("Taller Caballito");
		titulo.setBounds(40, 30, 450, 40);
		titulo.setFont(new Font("Showcard Gothic", Font.BOLD, 35));
		titulo.setForeground(Color.white);
		panelUno.add(titulo);

		inicio = new JButton("Iniciar");
		inicio.setFont(new Font("Monospaced", Font.BOLD, 25));
		inicio.setBackground(Color.white);
		inicio.setBounds(140, 100, 120, 50);
		inicio.setActionCommand("botIniciar");
		inicio.setBorder(new LineBorder(Color.white, 3));
		panelUno.add(inicio);

		// panel dos

		panelDos = new JPanel();
		panelDos.setLayout(null);
		panelDos.setVisible(false);
		panelDos.setBackground(Color.white);
		panelDos.setBounds(20, 50, 250, 330);
		add(panelDos);

		e1 = new JLabel("Ingrese los datos");
		e1.setBounds(10, 10, 450, 20);
		e1.setFont(new Font("Showcard Gothic", Font.BOLD, 18));
		e1.setForeground(Color.black);
		panelDos.add(e1);

		e2 = new JLabel("Dimension matriz");
		e2.setBounds(10, 30, 450, 20);
		e2.setFont(new Font("Monospaced", Font.BOLD, 14));
		e2.setForeground(Color.black);
		panelDos.add(e2);

		e3 = new JLabel("Numeros entre 2 y 100");
		e3.setBounds(10, 50, 450, 20);
		e3.setFont(new Font("Monospaced", Font.BOLD, 14));
		e3.setForeground(Color.black);
		panelDos.add(e3);

		px = new JLabel("Anchura:");
		px.setBounds(10, 80, 60, 20);
		px.setFont(new Font("Monospaced", Font.BOLD, 10));
		px.setForeground(Color.black);
		panelDos.add(px);

		py = new JLabel("Altura:");
		py.setBounds(130, 80, 50, 20);
		py.setFont(new Font("Monospaced", Font.BOLD, 10));
		py.setForeground(Color.black);
		panelDos.add(py);

		e4 = new JLabel("Punto inicial [    ,     ]");
		e4.setBounds(10, 110, 450, 16);
		e4.setFont(new Font("Monospaced", Font.BOLD, 14));
		e4.setForeground(Color.black);
		panelDos.add(e4);

		e5 = new JLabel("Punto final   [    ,     ]");
		e5.setBounds(10, 140, 450, 16);
		e5.setFont(new Font("Monospaced", Font.BOLD, 14));
		e5.setForeground(Color.black);
		panelDos.add(e5);

		e6 = new JLabel("Valor de P: ");
		e6.setBounds(10, 170, 450, 20);
		e6.setFont(new Font("Monospaced", Font.BOLD, 14));
		e6.setForeground(Color.black);
		panelDos.add(e6);

		e7 = new JLabel("Valor de Q: ");
		e7.setBounds(10, 200, 450, 20);
		e7.setFont(new Font("Monospaced", Font.BOLD, 14));
		e7.setForeground(Color.black);
		panelDos.add(e7);
		
		e8 = new JLabel(" ");
		e8.setBounds(10, 230, 450, 20);
		e8.setFont(new Font("Monospaced", Font.BOLD, 12));
		e8.setForeground(Color.black);
		panelDos.add(e8);

		alturaTextField = new JTextField();
		alturaTextField.setBounds(180, 75, 40, 25);
		alturaTextField.setFont(new Font("Monospaced", Font.BOLD, 20));
		panelDos.add(alturaTextField);

		anchuraTextField = new JTextField();
		anchuraTextField.setBounds(70, 75, 40, 25);
		anchuraTextField.setFont(new Font("Monospaced", Font.BOLD, 20));
		panelDos.add(anchuraTextField);

		pxUnoTf = new JTextField();
		pxUnoTf.setBounds(145, 110, 30, 25);
		pxUnoTf.setFont(new Font("Monospaced", Font.BOLD, 15));
		panelDos.add(pxUnoTf);

		pyUnoTf = new JTextField();
		pyUnoTf.setBounds(195, 110, 30, 25);
		pyUnoTf.setFont(new Font("Monospaced", Font.BOLD, 15));
		panelDos.add(pyUnoTf);

		pxDosTf = new JTextField();
		pxDosTf.setBounds(145, 140, 30, 25);
		pxDosTf.setFont(new Font("Monospaced", Font.BOLD, 15));
		panelDos.add(pxDosTf);

		pyDosTf = new JTextField();
		pyDosTf.setBounds(195, 140, 30, 25);
		pyDosTf.setFont(new Font("Monospaced", Font.BOLD, 15));
		panelDos.add(pyDosTf);

		valorP = new JTextField();
		valorP.setBounds(120, 170, 50, 25);
		valorP.setFont(new Font("Monospaced", Font.BOLD, 20));
		panelDos.add(valorP);

		valorQ = new JTextField();
		valorQ.setBounds(120, 200, 50, 25);
		valorQ.setFont(new Font("Monospaced", Font.BOLD, 20));
		panelDos.add(valorQ);

		crearGridButton = new JButton("Crear Grid");
		crearGridButton.setBounds(10, 280, 100, 30);
		crearGridButton.setFont(new Font("Monospaced", Font.BOLD, 10));
		crearGridButton.setActionCommand("Crear");
		panelDos.add(crearGridButton);

		resaltar = new JButton("Resaltar");
		resaltar.setBounds(135, 280, 100, 30);
		resaltar.setFont(new Font("Monospaced", Font.BOLD, 10));
		resaltar.setActionCommand("Resaltar");
		panelDos.add(resaltar);

		// panel tres

		panelTres = new JPanel();
		panelTres.setLayout(null);
		panelTres.setVisible(false);
		panelTres.setBackground(Color.white);
		panelTres.setBounds(20, 400, 250, 200);
		add(panelTres);

		rta = new JLabel("Ingrese los datos");
		rta.setBounds(40, 10, 450, 30);
		rta.setFont(new Font("Showcard Gothic", Font.BOLD, 18));
		rta.setForeground(Color.black);
		panelTres.add(rta);

		siguiente = new JButton("Siguiente");
		siguiente.setBounds(10, 120, 100, 50);
		siguiente.setFont(new Font("Monospaced", Font.BOLD, 10));
		siguiente.setForeground(Color.black);
		siguiente.setActionCommand("siguiente");
		panelTres.add(siguiente);

		anterior = new JButton("Anterior");
		anterior.setBounds(130, 120, 100, 50);
		anterior.setFont(new Font("Monospaced", Font.BOLD, 10));
		anterior.setForeground(Color.black);
		anterior.setActionCommand("anterior");
		panelTres.add(anterior);

		mostrar = new JButton("Mostrar pasos");
		mostrar.setBounds(40, 60, 160, 50);
		mostrar.setFont(new Font("Monospaced", Font.BOLD, 10));
		mostrar.setForeground(Color.black);
		mostrar.setActionCommand("mostrar");

		panelTres.add(mostrar);

		// panel matriz

		pmat = new JPanel();
		pmat.setLayout(null);
		pmat.setBackground(Color.black);
		pmat.setVisible(false);
		pmat.setBounds(300, 50, 650, 650);
		add(pmat);

		getContentPane().setLayout(new BorderLayout());
		img = new JLabel("", JLabel.CENTER);
		img.setIcon(new ImageIcon("img/inicio.png"));
		add(img);

	}

	public void resaltar(int x, int y) {

		botones[x][y].setBackground(new Color(116, 131, 171));

		panelDos.repaint();

	}
	
	public void resetearMatriz() {
      System.out.println("hpla");
		botones = null;
		panelDos.repaint();

    }

	public void resaltarCamino(int x, int y) {

		botones[x][y].setBackground(Color.cyan);

		panelDos.repaint();

	}

	public void cambiar(int x, int y, String nom) {

		botones[x][y].setText(nom);
		;

		panelDos.repaint();

	}

	public void crearGrid(int altura, int anchura) {

		pmat.removeAll();

		botones = new JButton[altura][anchura];
		pmat.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;

		JPanel panelBotones = new JPanel(new GridLayout(altura, anchura));

		for (int i = 0; i < altura; i++) {
			for (int j = 0; j < anchura; j++) {
				botones[i][j] = new JButton();
				botones[i][j].setBackground(Color.white);
				botones[i][j].setPreferredSize(new Dimension(80, 80));
				panelBotones.add(botones[i][j]);
			}
		}

		if (altura > 7 || anchura > 7) {
			JScrollPane scrollPane = new JScrollPane(panelBotones);
			pmat.add(scrollPane, gbc);
		} else {
			gbc.gridx = (7 - anchura) / 2;
			gbc.gridy = (7 - altura) / 2;
			pmat.add(panelBotones, gbc);
		}

		pmat.revalidate();
		pmat.repaint();
	}

	/**
	 * @return the rta
	 */
	public JLabel getRta() {
		return rta;
	}

	/**
	 * @param rta the rta to set
	 */
	public void setRta(JLabel rta) {
		this.rta = rta;
	}

	/**
	 * @return the mostrar
	 */
	public JButton getMostrar() {
		return mostrar;
	}

	/**
	 * @param mostrar the mostrar to set
	 */
	public void setMostrar(JButton mostrar) {
		this.mostrar = mostrar;
	}

	/**
	 * @return the siguiente
	 */
	public JButton getSiguiente() {
		return siguiente;
	}

	/**
	 * @param siguiente the siguiente to set
	 */
	public void setSiguiente(JButton siguiente) {
		this.siguiente = siguiente;
	}

	/**
	 * @return the anterior
	 */
	public JButton getAnterior() {
		return anterior;
	}

	/**
	 * @param anterior the anterior to set
	 */
	public void setAnterior(JButton anterior) {
		this.anterior = anterior;
	}

	/**
	 * @return the volver
	 */
	public JButton getVolver() {
		return volver;
	}

	/**
	 * @param volver the volver to set
	 */
	public void setVolver(JButton volver) {
		this.volver = volver;
	}

	/**
	 * @return the pmat
	 */
	public JPanel getPmat() {
		return pmat;
	}

	/**
	 * @param pmat the pmat to set
	 */
	public void setPmat(JPanel pmat) {
		this.pmat = pmat;
	}

	/**
	 * @return the panelTres
	 */
	public JPanel getPanelTres() {
		return panelTres;
	}

	/**
	 * @param panelTres the panelTres to set
	 */
	public void setPanelTres(JPanel panelTres) {
		this.panelTres = panelTres;
	}

	/**
	 * @return the panelUno
	 */
	public JPanel getPanelUno() {
		return panelUno;
	}

	/**
	 * @param panelUno the panelUno to set
	 */
	public void setPanelUno(JPanel panelUno) {
		this.panelUno = panelUno;
	}

	/**
	 * @return the panelDos
	 */
	public JPanel getPanelDos() {
		return panelDos;
	}

	/**
	 * @param panelDos the panelDos to set
	 */
	public void setPanelDos(JPanel panelDos) {
		this.panelDos = panelDos;
	}

	/**
	 * @return the titulo
	 */
	public JLabel getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(JLabel titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the inicio
	 */
	public JButton getInicio() {
		return inicio;
	}

	/**
	 * @param inicio the inicio to set
	 */
	public void setInicio(JButton inicio) {
		this.inicio = inicio;
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
		return panelDos;
	}

	/**
	 * @param panel the panel to set
	 */
	public void setPanel(JPanel panel) {
		this.panelDos = panel;
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

	/**
	 * @return the pxUnoTf
	 */
	public JTextField getPxUnoTf() {
		return pxUnoTf;
	}

	/**
	 * @param pxUnoTf the pxUnoTf to set
	 */
	public void setPxUnoTf(JTextField pxUnoTf) {
		this.pxUnoTf = pxUnoTf;
	}

	/**
	 * @return the pyUnoTf
	 */
	public JTextField getPyUnoTf() {
		return pyUnoTf;
	}

	/**
	 * @param pyUnoTf the pyUnoTf to set
	 */
	public void setPyUnoTf(JTextField pyUnoTf) {
		this.pyUnoTf = pyUnoTf;
	}

	/**
	 * @return the pxDosTf
	 */
	public JTextField getPxDosTf() {
		return pxDosTf;
	}

	/**
	 * @param pxDosTf the pxDosTf to set
	 */
	public void setPxDosTf(JTextField pxDosTf) {
		this.pxDosTf = pxDosTf;
	}

	/**
	 * @return the pyDosTf
	 */
	public JTextField getPyDosTf() {
		return pyDosTf;
	}

	/**
	 * @param pyDosTf the pyDosTf to set
	 */
	public void setPyDosTf(JTextField pyDosTf) {
		this.pyDosTf = pyDosTf;
	}

	/**
	 * @return the valorQ
	 */
	public JTextField getValorQ() {
		return valorQ;
	}

	/**
	 * @param valorQ the valorQ to set
	 */
	public void setValorQ(JTextField valorQ) {
		this.valorQ = valorQ;
	}

	/**
	 * @return the valorP
	 */
	public JTextField getValorP() {
		return valorP;
	}

	/**
	 * @param valorP the valorP to set
	 */
	public void setValorP(JTextField valorP) {
		this.valorP = valorP;
	}

	/**
	 * @return the e8
	 */
	public JLabel getE8() {
		return e8;
	}

	/**
	 * @param e8 the e8 to set
	 */
	public void setE8(JLabel e8) {
		this.e8 = e8;
	}

}
