
	import java.io.BufferedWriter;
	import java.io.IOException;

	public class CocheSegundaMano extends Coche{

		// Atributos
		private String extra1;
		private String extra2;
		private String extra3;
		
		//M�todos
		CocheSegundaMano(){
			
		}
		

		public String getExtra1() {
			return extra1;
		}


		public void setExtra1(String extra1) {
			this.extra1 = extra1;
		}
		
		public String getExtra2() {
			return extra2;
		}


		public void setExtra2(String extra2) {
			this.extra2 = extra2;
		}


		public String getExtra3() {
			return extra3;
		}


		public void setExtra3(String extra3) {
			this.extra3 = extra3;
		}


		@Override
		public String toString() {
			return super.toString()+"-"+extra1+"-"+extra2+"-"+extra3;
		}
		
		
		@Override
		void introducirDatosEspecificos() {
			System.out.print("Introduce el año del vehiculo : ");
			extra1=Aplicacion.teclado.nextLine();
			System.out.print("Introduce el kilometraje : ");
			extra2=Aplicacion.teclado.nextLine();
			System.out.print("Introduce la matricula : ");
			extra3=Aplicacion.teclado.nextLine();
		}
/*
		@Override
		void mostrarFicha() {
			System.out.println("Datos del coche");
			System.out.println("----------------");
			System.out.println("Matr�cula : " + this.getMatricula());
			System.out.println("Marca : " + this.getMarca());
			System.out.println("Modelo : " + this.getModelo());
			System.out.println("Fecha pr�xima revision: " + this.getFechaRevision());
			System.out.println("N�mero de caballos : " + this.getCaballos());
			System.out.println("Segmento : " + this.getSegmento());
			
		}

		@Override
		void cargarDatosLineaFichero(String linea) {
			String[] partes=linea.split(",");
			this.setMatricula(partes[1]);
			this.setMarca(partes[2]);
			this.setModelo(partes[3]);
			this.setCombustible(partes[4]);
			this.setFechaRevision(partes[5]);
			this.setEmail(partes[6]);
			this.setTelefono(Integer.parseInt(partes[7]));
			this.setCaballos(Integer.parseInt(partes[8]));
			this.setSegmento(partes[9]);
		}

		@Override
		char queTipoVehiculoSoy() {
			// TODO Auto-generated method stub
			return NUEVO;
		}
		
		@Override
		public int compareTo(Coche o) {
			return super.compareTo(o);
		}


		@Override
		void EscribirObjetoFichero(BufferedWriter fichero) {
			
			try {
				fichero.write(COCHE + ","
							+ getMatricula() + ","
							+ getMarca() + ","
							+ getModelo() + ","
							+ getCombustible() + ","
							+ getFechaRevision() + ","
							+ getEmail() + ","
							+ getTelefono() + ","
							+ caballos + ","
							+ segmento + "\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
*/
}
