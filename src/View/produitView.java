package View;

import java.util.Scanner;

public class produitView {
	  private Scanner scanner;

	    public produitView() {
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
