
//public class Simon {
/*	
	//ATRIBUTOS	
	
	// ROJO-1 AMARILLO-2 VERDE-3 AZUL-4
	int rojo1;
	int azul2;
	int verde3;
	int amarillo4;
	
	
	String cadena="1,2,3,4";
	
	for (int=0; i< cadena.length();i++) 
	{
		char caracter=cadena.charAt();
		System.out.print(caracter);
		try {
			Theread.sleep(1000);
		}catch (IterruptedException e) {
			e.printStackTrace();
			
		}
		System.out.print("\b");
	}
	

}
*/
	import java.util.Random;
	import java.util.Scanner;

	public class Simon {

	    public static void main(String[] args) {
	        playSimonGame();
	    }

	    public static void playSimonGame() {
	        Scanner scanner = new Scanner(System.in);
	        Random random = new Random();

	        String sequence = "";
	        int round = 1;

	        System.out.println("Bienvenido a Simon Game. ¡Comencemos!");

	        while (true) {
	            System.out.println("Ronda " + round + ":");
	            addRandomNumberToSequence(sequence);
	            displaySequence(sequence);

	            if (!getUserInput(scanner, sequence)) {
	                System.out.println("¡Incorrecto! Fin del juego.");
	                break;
	            }

	            System.out.println("¡Correcto! Avanzas a la siguiente ronda.");
	            round++;
	        }

	        scanner.close();
	    }

	    public static void addRandomNumberToSequence(String sequence) {
	        Random random = new Random();
	        int randomNumber = random.nextInt(4) + 1;
	        sequence += String.valueOf(randomNumber);
	    }

	    public static void displaySequence(String sequence) {
	        for (char digit : sequence.toCharArray()) {
	            System.out.print(digit + " ");
	            try {
	                Thread.sleep(1000); // Mostrar cada número durante 1 segundo
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	        System.out.println();
	    }

	    public static boolean getUserInput(Scanner scanner, String sequence) {
	        for (char digit : sequence.toCharArray()) {
	            int userGuess = scanner.nextInt();
	            if (userGuess != Character.getNumericValue(digit)) {
	                return false;
	            }
	        }
	        return true;
	    }
	}