package view;

import java.util.Scanner;

public class CommandView {
	  private Scanner scanner;

	    public CommandView() {
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
