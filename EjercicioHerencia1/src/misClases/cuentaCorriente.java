package misClases;

public class cuentaCorriente extends Cuenta{
	final double interes=1.5;
	
	public cuentaCorriente(Persona clliente, int numeroCuenta) {
		super(clliente, numeroCuenta);

	}
	
	@Override
	public String toString(){
		return "Cuenta Corriente - Num_cuenta : " + getNumeroCunta() + " Saldo " + getSaldo();
	}
	
	
	 void retirar (double cantidad) {
		 double saldoActual = getSaldo();
		 if(saldoActual >= cantidad)
			 setSaldo(saldoActual - cantidad);			 
	 }
	 void actualizarSaldoSegunInteres() {
		 double saldoActual = getSaldo();
		 double cantidad = saldoActual*interes/100;
		 ingresar(cantidad);
	 }


}
