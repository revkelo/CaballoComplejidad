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
/**
 * @author Kevin
 * @author Daniela
 * @author Nicolas
 * 
 * La clase Ventana representa la interfaz gráfica de usuario para la aplicación "Taller Caballito".
 * Extiende la clase JFrame para crear la ventana principal.
 */
public class Ventana extends JFrame {
	   /**
     * Panel de la interfaz gráfica que contiene elementos relacionados con la configuración inicial.
     */
    private JPanel panelUno;

    /**
     * Panel de la interfaz gráfica que contiene elementos relacionados con la entrada de datos y la creación de la cuadrícula.
     */
    private JPanel panelDos;

    /**
     * Panel de la interfaz gráfica que contiene elementos relacionados con la visualización de pasos y opciones de navegación.
     */
    private JPanel panelTres;

    /**
     * Panel de la interfaz gráfica destinado a mostrar la cuadrícula de botones.
     */
    private JPanel pmat;

    /**
     * Etiqueta que muestra una imagen en la interfaz gráfica.
     */
    private JLabel img;

    /**
     * Etiqueta que muestra el título principal en la interfaz gráfica.
     */
    private JLabel titulo;

    /**
     * Etiqueta que muestra información o resultados en la interfaz gráfica.
     */
    private JLabel rta;

    /**
     * Etiquetas que contienen mensajes o información en la interfaz gráfica.
     */
    private JLabel e1, e2, e3, px, py, e4, e5, e6, e7, e8;

    /**
     * Campos de texto para la entrada de datos en la interfaz gráfica.
     */
    private JTextField alturaTextField, anchuraTextField, pxUnoTf, pyUnoTf, pxDosTf, pyDosTf, valorQ, valorP;

    /**
     * Botones que permiten realizar acciones específicas en la interfaz gráfica, como mostrar pasos, avanzar, retroceder y volver.
     */
    private JButton mostrar, siguiente, anterior, volver;

    /**
     * Botones que activan acciones relacionadas con la creación de la cuadrícula, resaltado y inicio en la interfaz gráfica.
     */
    private JButton crearGridButton, resaltar, inicio;

    /**
     * Matriz de botones que representa la cuadrícula en la interfaz gráfica.
     */
    private JButton[][] botones;

	 /**
     * Constructor de la clase Ventana. Inicializa y configura la interfaz gráfica.
     */
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

	 /**
     * Inicializa todos los componentes de la interfaz gráfica.
     */
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
		e8.setForeground(Color.orange);
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
		siguiente.setBounds(130, 120, 100, 50);
		siguiente.setFont(new Font("Monospaced", Font.BOLD, 10));
		siguiente.setForeground(Color.black);
		siguiente.setActionCommand("siguiente");
		panelTres.add(siguiente);

		anterior = new JButton("Anterior");
		anterior.setBounds(10, 120, 100, 50);
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

	/**
	 * Reinicia el contenido de la matriz de botones en la interfaz gráfica.
	 * Establece el texto de todos los botones a una cadena vacía y restaura el color de fondo a blanco.
	 */
	public void reiniciar() {
		for (int i = 0; i < botones.length; i++) {
			for (int j = 0; j < botones[0].length; j++) {
				botones[i][j].setText("");
				botones[i][j].setBackground(Color.white);
			}
		}

	}

	/**
	 * Resalta el botón en la posición dada cambiando su color de fondo.
	 * @param x La coordenada x del botón.
	 * @param y La coordenada y del botón.
	 */
	public void resaltar(int x, int y) {

		botones[x][y].setBackground(new Color(116, 131, 171));

		panelDos.repaint();

	}

	/**
	 * Reinicia la matriz de botones, estableciéndola como nula.
	 * Imprime un mensaje en la consola indicando que se ha realizado el reseteo.
	 */
	public void resetearMatriz() {
		System.out.println("hpla");
		botones = null;
		panelDos.repaint();

	}
	
	/**
	 * Resalta el botón en la posición dada con un color específico.
	 * @param x La coordenada x del botón.
	 * @param y La coordenada y del botón.
	 * @param c El color con el que se resaltará el botón.
	 */
	public void resaltarCamino(int x, int y, Color c) {

		botones[x][y].setBackground(c);

		panelDos.repaint();

	}

	/**
	 * Cambia el texto del botón en la posición dada.
	 * @param x La coordenada x del botón.
	 * @param y La coordenada y del botón.
	 * @param nom El nuevo texto que se establecerá en el botón.
	 */
	public void cambiar(int x, int y, String nom) {

		botones[x][y].setText(nom);
		;

		panelDos.repaint();

	}

	/**
	 * Crea una cuadrícula de botones en la interfaz gráfica con la altura y anchura especificadas.
	 * @param altura La cantidad de filas en la cuadrícula.
	 * @param anchura La cantidad de columnas en la cuadrícula.
	 */
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
	 * Obtiene el componente de etiqueta (JLabel) asociado a esta instancia.
	 *
	 * @return El componente de etiqueta (JLabel) asociado.
	 */
	public JLabel getRta() {
	    return rta;
	}

	/**
	 * Establece el componente de etiqueta (JLabel) asociado a esta instancia.
	 *
	 * @param rta El nuevo componente de etiqueta (JLabel) a establecer.
	 */
	public void setRta(JLabel rta) {
	    this.rta = rta;
	}

	/**
	 * Obtiene el botón (JButton) "Mostrar" asociado a esta instancia.
	 *
	 * @return El botón (JButton) "Mostrar" asociado.
	 */
	public JButton getMostrar() {
	    return mostrar;
	}

	/**
	 * Establece el botón (JButton) "Mostrar" asociado a esta instancia.
	 *
	 * @param mostrar El nuevo botón (JButton) "Mostrar" a establecer.
	 */
	public void setMostrar(JButton mostrar) {
	    this.mostrar = mostrar;
	}

	/**
	 * Obtiene el botón (JButton) "Siguiente" asociado a esta instancia.
	 *
	 * @return El botón (JButton) "Siguiente" asociado.
	 */
	public JButton getSiguiente() {
	    return siguiente;
	}

	/**
	 * Establece el botón (JButton) "Siguiente" asociado a esta instancia.
	 *
	 * @param siguiente El nuevo botón (JButton) "Siguiente" a establecer.
	 */
	public void setSiguiente(JButton siguiente) {
	    this.siguiente = siguiente;
	}

	/**
	 * Obtiene el botón (JButton) "Anterior" asociado a esta instancia.
	 *
	 * @return El botón (JButton) "Anterior" asociado.
	 */
	public JButton getAnterior() {
	    return anterior;
	}

	/**
	 * Establece el botón (JButton) "Anterior" asociado a esta instancia.
	 *
	 * @param anterior El nuevo botón (JButton) "Anterior" a establecer.
	 */
	public void setAnterior(JButton anterior) {
	    this.anterior = anterior;
	}

	/**
	 * Obtiene el botón (JButton) "Volver" asociado a esta instancia.
	 *
	 * @return El botón (JButton) "Volver" asociado.
	 */
	public JButton getVolver() {
	    return volver;
	}

	/**
	 * Establece el botón (JButton) "Volver" asociado a esta instancia.
	 *
	 * @param volver El nuevo botón (JButton) "Volver" a establecer.
	 */
	public void setVolver(JButton volver) {
	    this.volver = volver;
	}

	/**
	 * Obtiene el panel (JPanel) asociado a esta instancia.
	 *
	 * @return El panel (JPanel) asociado.
	 */
	public JPanel getPmat() {
	    return pmat;
	}

	/**
	 * Establece el panel (JPanel) asociado a esta instancia.
	 *
	 * @param pmat El nuevo panel (JPanel) a establecer.
	 */
	public void setPmat(JPanel pmat) {
	    this.pmat = pmat;
	}
	
	/**
	 * Obtiene el panel (JPanel) llamado "panelTres" asociado a esta instancia.
	 *
	 * @return El panel (JPanel) "panelTres" asociado.
	 */
	public JPanel getPanelTres() {
	    return panelTres;
	}

	/**
	 * Establece el panel (JPanel) llamado "panelTres" asociado a esta instancia.
	 *
	 * @param panelTres El nuevo panel (JPanel) "panelTres" a establecer.
	 */
	public void setPanelTres(JPanel panelTres) {
	    this.panelTres = panelTres;
	}

	/**
	 * Obtiene el panel (JPanel) llamado "panelUno" asociado a esta instancia.
	 *
	 * @return El panel (JPanel) "panelUno" asociado.
	 */
	public JPanel getPanelUno() {
	    return panelUno;
	}

	/**
	 * Establece el panel (JPanel) llamado "panelUno" asociado a esta instancia.
	 *
	 * @param panelUno El nuevo panel (JPanel) "panelUno" a establecer.
	 */
	public void setPanelUno(JPanel panelUno) {
	    this.panelUno = panelUno;
	}

	/**
	 * Obtiene el panel (JPanel) llamado "panelDos" asociado a esta instancia.
	 *
	 * @return El panel (JPanel) "panelDos" asociado.
	 */
	public JPanel getPanelDos() {
	    return panelDos;
	}

	/**
	 * Establece el panel (JPanel) llamado "panelDos" asociado a esta instancia.
	 *
	 * @param panelDos El nuevo panel (JPanel) "panelDos" a establecer.
	 */
	public void setPanelDos(JPanel panelDos) {
	    this.panelDos = panelDos;
	}

	/**
	 * Obtiene la etiqueta (JLabel) llamada "titulo" asociada a esta instancia.
	 *
	 * @return La etiqueta (JLabel) "titulo" asociada.
	 */
	public JLabel getTitulo() {
	    return titulo;
	}

	/**
	 * Establece la etiqueta (JLabel) llamada "titulo" asociada a esta instancia.
	 *
	 * @param titulo La nueva etiqueta (JLabel) "titulo" a establecer.
	 */
	public void setTitulo(JLabel titulo) {
	    this.titulo = titulo;
	}

	/**
	 * Obtiene el botón (JButton) llamado "inicio" asociado a esta instancia.
	 *
	 * @return El botón (JButton) "inicio" asociado.
	 */
	public JButton getInicio() {
	    return inicio;
	}

	/**
	 * Establece el botón (JButton) llamado "inicio" asociado a esta instancia.
	 *
	 * @param inicio El nuevo botón (JButton) "inicio" a establecer.
	 */
	public void setInicio(JButton inicio) {
	    this.inicio = inicio;
	}

	/**
	 * Obtiene el botón (JButton) llamado "resaltar" asociado a esta instancia.
	 *
	 * @return El botón (JButton) "resaltar" asociado.
	 */
	public JButton getResaltar() {
	    return resaltar;
	}

	/**
	 * Establece el botón (JButton) llamado "resaltar" asociado a esta instancia.
	 *
	 * @param resaltar El nuevo botón (JButton) "resaltar" a establecer.
	 */
	public void setResaltar(JButton resaltar) {
	    this.resaltar = resaltar;
	}

	/**
	 * Obtiene una matriz bidimensional de botones (JButton) llamada "botones" asociada a esta instancia.
	 *
	 * @return La matriz bidimensional de botones (JButton) "botones" asociada.
	 */
	public JButton[][] getBotones() {
	    return botones;
	}

	/**
	 * Establece una matriz bidimensional de botones (JButton) llamada "botones" asociada a esta instancia.
	 *
	 * @param botones La nueva matriz bidimensional de botones (JButton) "botones" a establecer.
	 */
	public void setBotones(JButton[][] botones) {
	    this.botones = botones;
	}

	/**
	 * Obtiene el campo de texto (JTextField) llamado "alturaTextField" asociado a esta instancia.
	 *
	 * @return El campo de texto (JTextField) "alturaTextField" asociado.
	 */
	public JTextField getAlturaTextField() {
	    return alturaTextField;
	}

	/**
	 * Establece el campo de texto (JTextField) llamado "alturaTextField" asociado a esta instancia.
	 *
	 * @param alturaTextField El nuevo campo de texto (JTextField) "alturaTextField" a establecer.
	 */
	public void setAlturaTextField(JTextField alturaTextField) {
	    this.alturaTextField = alturaTextField;
	}

	/**
	 * Obtiene el campo de texto (JTextField) llamado "anchuraTextField" asociado a esta instancia.
	 *
	 * @return El campo de texto (JTextField) "anchuraTextField" asociado.
	 */
	public JTextField getAnchuraTextField() {
	    return anchuraTextField;
	}

	/**
	 * Establece el campo de texto (JTextField) llamado "anchuraTextField" asociado a esta instancia.
	 *
	 * @param anchuraTextField El nuevo campo de texto (JTextField) "anchuraTextField" a establecer.
	 */
	public void setAnchuraTextField(JTextField anchuraTextField) {
	    this.anchuraTextField = anchuraTextField;
	}

	/**
	 * Obtiene el panel (JPanel) llamado "panelDos" asociado a esta instancia.
	 *
	 * @return El panel (JPanel) "panelDos" asociado.
	 */

	public JPanel getPanel() {
	    return panelDos;
	}

	
	/**
	 * Establece el panel (JPanel) llamado "panel" asociado a esta instancia.
	 *
	 * @param panel El nuevo panel (JPanel) a establecer.
	 */
	public void setPanel(JPanel panel) {
	    this.panelDos = panel;
	}

	/**
	 * Obtiene el botón (JButton) llamado "crearGridButton" asociado a esta instancia.
	 *
	 * @return El botón (JButton) "crearGridButton" asociado.
	 */
	public JButton getCrearGridButton() {
	    return crearGridButton;
	}

	/**
	 * Establece el botón (JButton) llamado "crearGridButton" asociado a esta instancia.
	 *
	 * @param crearGridButton El nuevo botón (JButton) "crearGridButton" a establecer.
	 */
	public void setCrearGridButton(JButton crearGridButton) {
	    this.crearGridButton = crearGridButton;
	}

	/**
	 * Obtiene el campo de texto (JTextField) llamado "pxUnoTf" asociado a esta instancia.
	 *
	 * @return El campo de texto (JTextField) "pxUnoTf" asociado.
	 */
	public JTextField getPxUnoTf() {
	    return pxUnoTf;
	}

	/**
	 * Establece el campo de texto (JTextField) llamado "pxUnoTf" asociado a esta instancia.
	 *
	 * @param pxUnoTf El nuevo campo de texto (JTextField) "pxUnoTf" a establecer.
	 */
	public void setPxUnoTf(JTextField pxUnoTf) {
	    this.pxUnoTf = pxUnoTf;
	}

	/**
	 * Obtiene el campo de texto (JTextField) llamado "pyUnoTf" asociado a esta instancia.
	 *
	 * @return El campo de texto (JTextField) "pyUnoTf" asociado.
	 */
	public JTextField getPyUnoTf() {
	    return pyUnoTf;
	}

	/**
	 * Establece el campo de texto (JTextField) llamado "pyUnoTf" asociado a esta instancia.
	 *
	 * @param pyUnoTf El nuevo campo de texto (JTextField) "pyUnoTf" a establecer.
	 */
	public void setPyUnoTf(JTextField pyUnoTf) {
	    this.pyUnoTf = pyUnoTf;
	}

	/**
	 * Obtiene el campo de texto (JTextField) llamado "pxDosTf" asociado a esta instancia.
	 *
	 * @return El campo de texto (JTextField) "pxDosTf" asociado.
	 */
	public JTextField getPxDosTf() {
	    return pxDosTf;
	}

	/**
	 * Establece el campo de texto (JTextField) llamado "pxDosTf" asociado a esta instancia.
	 *
	 * @param pxDosTf El nuevo campo de texto (JTextField) "pxDosTf" a establecer.
	 */
	public void setPxDosTf(JTextField pxDosTf) {
	    this.pxDosTf = pxDosTf;
	}

	/**
	 * Obtiene el campo de texto (JTextField) llamado "pyDosTf" asociado a esta instancia.
	 *
	 * @return El campo de texto (JTextField) "pyDosTf" asociado.
	 */
	public JTextField getPyDosTf() {
	    return pyDosTf;
	}

	/**
	 * Establece el campo de texto (JTextField) llamado "pyDosTf" asociado a esta instancia.
	 *
	 * @param pyDosTf El nuevo campo de texto (JTextField) "pyDosTf" a establecer.
	 */
	public void setPyDosTf(JTextField pyDosTf) {
	    this.pyDosTf = pyDosTf;
	}

	/**
	 * Obtiene el campo de texto (JTextField) llamado "valorQ" asociado a esta instancia.
	 *
	 * @return El campo de texto (JTextField) "valorQ" asociado.
	 */
	public JTextField getValorQ() {
	    return valorQ;
	}

	/**
	 * Establece el campo de texto (JTextField) llamado "valorQ" asociado a esta instancia.
	 *
	 * @param valorQ El nuevo campo de texto (JTextField) "valorQ" a establecer.
	 */
	public void setValorQ(JTextField valorQ) {
	    this.valorQ = valorQ;
	}

	/**
	 * Obtiene el campo de texto (JTextField) llamado "valorP" asociado a esta instancia.
	 *
	 * @return El campo de texto (JTextField) "valorP" asociado.
	 */
	public JTextField getValorP() {
	    return valorP;
	}

	/**
	 * Establece el campo de texto (JTextField) llamado "valorP" asociado a esta instancia.
	 *
	 * @param valorP El nuevo campo de texto (JTextField) "valorP" a establecer.
	 */
	public void setValorP(JTextField valorP) {
	    this.valorP = valorP;
	}

	/**
	 * Obtiene la etiqueta (JLabel) llamada "e8" asociada a esta instancia.
	 *
	 * @return La etiqueta (JLabel) "e8" asociada.
	 */
	public JLabel getE8() {
	    return e8;
	}

	/**
	 * Establece la etiqueta (JLabel) llamada "e8" asociada a esta instancia.
	 *
	 * @param e8 La nueva etiqueta (JLabel) "e8" a establecer.
	 */
	public void setE8(JLabel e8) {
	    this.e8 = e8;
	}

}
