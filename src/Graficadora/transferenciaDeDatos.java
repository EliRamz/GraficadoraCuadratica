package Graficadora;
/*En esta clase se almacenan los datos necsarios para poder hacer funcionar la graficadora, aqui se toman y se establecen los nnuevos valores
 * de cada una.*/

public class transferenciaDeDatos {

	private double valorA;
	private double valorB;
	private double valorC;
	private int escala = 20;

	public double getValorA() {
		return valorA;
	}

	public void setValorA(double valorA) {
		this.valorA = valorA;
	}

	public double getValorB() {
		return valorB;
	}

	public void setValorB(double valorB) {
		this.valorB = valorB;
	}

	public double getValorC() {
		return valorC;
	}

	public void setValorC(double valorC) {
		this.valorC = valorC;
	}

	public int getEscala() {
		return escala;
	}

	public void setEscala(int escala) {
		this.escala = escala;
	}

}
