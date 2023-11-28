/**
 * Clase que pertenece al paquete vista que se encarga de unir las clases de la vista 
 */
package co.edu.unbosque.view;

/**
 * Clase que sirve como fachada para acceder a las funcionalidades del vista
 * relacionadas con la interfaz grafica.
 * 
 * @author Kevin
 * @author Daniela
 * @author Nicolas
 * 
 */

public class FachadaView {

	/**
	 * Atributo Ventana no,brado vp
	 */
	private Ventana vp;

	/**
	 * Atributo Vista no,brado msj
	 */
	private Vista msj;

	/**
	 * Metodo constructor
	 */
	public FachadaView() {
		vp = new Ventana();
		msj = new Vista();
	}

	/**
	 * El metodo get funciona para tomar o llamar el atributo
	 * 
	 * @return the vp
	 */
	public Ventana getVp() {
		return vp;
	}

	/**
	 * El metodo set funciona para actualizar el atributo
	 * 
	 * @param vp the vp to set
	 */
	public void setVp(Ventana vp) {
		this.vp = vp;
	}

	/**
	 * El metodo get funciona para tomar o llamar el atributo
	 * 
	 * @return the msj
	 */
	public Vista getMsj() {
		return msj;
	}

	/**
	 * El metodo set funciona para actualizar el atributo
	 * 
	 * @param msj the msj to set
	 */
	public void setMsj(Vista msj) {
		this.msj = msj;
	}

}
