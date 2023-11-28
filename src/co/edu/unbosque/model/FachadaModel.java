/**
 * Clase que pertenece al paquete model que une todas las clases de model
 */
package co.edu.unbosque.model;

/**
 * 
 * Clase que sirve como fachada para acceder a las funcionalidades del modelo
 * relacionadas con el movimiento del caballo.
 * 
 * @author Kevin
 * @author Daniela
 * @author Nicolas
 * 
 */
public class FachadaModel {

	/**
	 * Objeto que encapsula las operaciones y lógica relacionadas con el movimiento
	 * del caballo.
	 */
	private MovimientoCaballo mc;

	/**
	 * Constructor de la clase `FachadaModel`. Inicializa el objeto
	 * `MovimientoCaballo`.
	 */
	public FachadaModel() {
		mc = new MovimientoCaballo();
	}

	/**
	 * Obtiene el objeto `MovimientoCaballo`.
	 *
	 * @return Instancia del objeto `MovimientoCaballo`.
	 */
	public MovimientoCaballo getMc() {
		return mc;
	}

	/**
	 * Establece el objeto `MovimientoCaballo`.
	 *
	 * @param mc Instancia del objeto `MovimientoCaballo` a establecer.
	 */
	public void setMc(MovimientoCaballo mc) {
		this.mc = mc;
	}
}
