package View;
import java.util.Scanner;
public class clientView {
		  private Scanner scanner;

		    public clientView() {
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
