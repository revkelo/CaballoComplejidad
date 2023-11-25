package model;

public class FachadaModel {
	private MovimientoCaballo mc;
	public FachadaModel() {
	mc = new MovimientoCaballo();
	}
	/**
	 * @return the mc
	 */
	public MovimientoCaballo getMc() {
		return mc;
	}
	/**
	 * @param mc the mc to set
	 */
	public void setMc(MovimientoCaballo mc) {
		this.mc = mc;
	}

}
