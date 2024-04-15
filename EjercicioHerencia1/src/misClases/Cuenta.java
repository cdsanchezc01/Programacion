package misClases;

public abstract class Cuenta {
	private int numeroCuenta;
	private double saldo;
	private Persona cliente;
	
	public Cuenta(Persona clliente, int numeroCuenta) {
		this.numeroCuenta=numeroCuenta;
		this.cliente=clliente;
		this.saldo=0;
	}
	
	void ingresar(double cantidad) {
		saldo += cantidad;
	}
	
	abstract void retirar (double cantidad);
	abstract void actualizarSaldoSegunInteres();

	public int getNumeroCunta() {
		return numeroCuenta;
	}

	public void setNumeroCunta(int numeroCunta) {
		this.numeroCuenta = numeroCunta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Persona getCliente() {
		return cliente;
	}

	public void setCliente(Persona cliente) {
		this.cliente = cliente;
	}
	
	
	
	
	
	
	
	
	
}
