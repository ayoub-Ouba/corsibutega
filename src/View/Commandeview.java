package View;

import java.util.Scanner;

public class Commandeview {
	  private Scanner scanner;

	    public Commandeview() {
	    System.out.println("Faire Une Commande");
	        scanner = new Scanner(System.in);
	    }

	    public String getInput(String prompt) {
	        System.out.print(prompt);
	        return scanner.nextLine();
	    }

	    public void afficherMessage(String message) {
	        System.out.println(message);
	    }

}
