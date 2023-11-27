/**
 * Clase para mostrar los errores que pertenece al paquete view
 */
package view;

import javax.swing.JOptionPane;

/**
 * Clase que maneja JOptionPane para mostrar los mensajes de error
 * 
 * @author Kevin
 * @author Daniela
 * @author Nicolas
 * 
 */

public class Vista {

	/**
	 * Metodo para mostrar el mensaje
	 * 
	 * @param msj designado para el tipo de error
	 */
	public void showError(String msj) {
		JOptionPane.showMessageDialog(null, msj, "Error", JOptionPane.WARNING_MESSAGE);
	}

}
