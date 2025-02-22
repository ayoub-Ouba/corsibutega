package View;

import java.util.Scanner;

public class loginview {
	
    private Scanner scanner;

    public loginview() {
    	System.out.println("Bienvenutu");
    	System.out.println("vous pouvez se connectez à votre compte ");
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
