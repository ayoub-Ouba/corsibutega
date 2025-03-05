package view;

import java.util.Scanner;

public class LoginView {
	
    private Scanner scanner;

    public LoginView() {
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
