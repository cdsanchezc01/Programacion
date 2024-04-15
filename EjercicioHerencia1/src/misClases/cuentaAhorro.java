package misClases;

public class cuentaAhorro extends Cuenta {
	 private double interes;
	 private double saldoMinimo;
	
	public cuentaAhorro(Persona cliente, int numeroCuenta, double interes, double saldoMinimo) {
		super(cliente, numeroCuenta);
		this.interes=interes;
		this.saldoMinimo=saldoMinimo;
	}
	
	
	
	public double getInteres() {
		return interes;
	}



	public void setInteres(double interes) {
		this.interes = interes;
	}



	public double getSaldoMinimo() {
		return saldoMinimo;
	}



	public void setSaldoMinimo(double saldoMinimo) {
		this.saldoMinimo = saldoMinimo;
	}



	@Override
	public String toString(){
		return "Cuenta Ahorro - Num_cuenta : " + getNumeroCunta() + " Saldo " + getSaldo() + " Interes : " + interes;
	}

	

}
