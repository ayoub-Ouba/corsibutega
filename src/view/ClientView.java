package view;
import java.util.Scanner;
public class ClientView {
		  private Scanner scanner;

		    public ClientView() {
		    	System.out.println("Gestion Client");
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
