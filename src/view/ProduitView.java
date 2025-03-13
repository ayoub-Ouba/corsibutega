package view;

import java.util.Scanner;

public class ProduitView {
	  private Scanner scanner;

	    public ProduitView() {
	        scanner = new Scanner(System.in);
	    }

	    public String getInput(String prompt) {
	        System.out.print(prompt);
	        return scanner.nextLine();
	    }
	    public int getInputQ(String prompt) {
	        System.out.print(prompt);
	        return scanner.nextInt();
	    }

	    public void afficherMessage(String message) {
	        System.out.println(message);
	    }

}
