package misClases;

	import java.io.BufferedWriter;

	public abstract class Coche implements Comparable<Coche>{

		// Definimos las constantes
		final char NUEVO='N';
		final char SEGUNDAMANO='S';
		
		// Definimos los atributos
		private String tipo;
		private String marca;
		private String modelo;
		private double precio;
		private int caballos;
	
		
		// M�todos
				Coche() {
					
				}

		public String getTipo() {
			return tipo;
		}


		public void setTipo(String tipo) {
			this.tipo = tipo;
		}


		public String getMarca() {
			return marca;
		}


		public void setMarca(String marca) {
			this.marca = marca;
		}


		public String getModelo() {
			return modelo;
		}


		public void setModelo(String modelo) {
			this.modelo = modelo;
		}


		public double getPrecio() {
			return precio;
		}


		public void setPrecio(double precio) {
			this.precio = precio;
		}


		public int getCaballos() {
			return caballos;
		}


		public void setCaballos(int caballos) {
			this.caballos = caballos;
		}


	


		public void getIntroducirDatosEspec() {
			return introducirDatosEspec;
		}


		public void setIntroducirDatosEspec(void introducirDatosEspec) {
			this.introducirDatosEspec = introducirDatosEspec;
		}


		public char getNUEVO() {
			return NUEVO;
		}


		public char getSEGUNDAMANO() {
			return SEGUNDAMANO;
		}


		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return tipo+"-"+marca+"-"+modelo+"-"+precio+"-"+caballos;
		}
		

		void introducirDatosGenerales() {
			
			System.out.print("Introduce el tipo : ");
			tipo=Aplicacion.teclado.nextLine();
			
			System.out.print("Introduce la marca : ");
			marca=Aplicacion.teclado.nextLine();
			
			System.out.print("Introduce el modelo : ");
			modelo=Aplicacion.teclado.nextLine();
			
			System.out.print("Introduce el precio : ");
			precio=Aplicacion.teclado.nextDouble();
			
			System.out.print("Introduce los caballos : ");
			caballos=Aplicacion.teclado.nextInt();
		
		}
		
		abstract void introducirDatosEspecificos();
		abstract void mostrarFicha();
		abstract void cargarDatosLineaFichero(String linea);
		abstract char queTipoVehiculoSoy();
		abstract void EscribirObjetoFichero(BufferedWriter fichero);
		
		@Override
		public int compareTo(Coche o) {
			
			return fechaRevision.compareTo(o.getFechaRevision());
			
		}
	}


}
