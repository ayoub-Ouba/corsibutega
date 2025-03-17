package controller;
import model.Utilisateur;
import view.LoginView;

import org.mindrot.jbcrypt.BCrypt;

import basedonne.Utilisateur_BD;


public class LoginController {
	    private Utilisateur_BD Utilisateur_BD;
	    private LoginView view;

	    public LoginController(LoginView view) {
	        this.Utilisateur_BD = new Utilisateur_BD(); 
	        this.view = view;
	    }

	    public Utilisateur login() {
	        String email = view.getEmail();
	        String password = view.getPassword();
	       
	        Utilisateur utilisateur = Utilisateur_BD.get_information_apartir_email(email);
	        
	        if (email.isEmpty() || email.equals("Email")) {
                view.showMessage("Veuillez entrer un email valide.");
               
            }
	        else if (password.isEmpty()) {
                view.showMessage("Veuillez entrer un mot de passe.");
                
            }
	       
	        else if (utilisateur != null && BCrypt.checkpw(password, utilisateur.getPassword()) ) {
	            view.showMessage("Connexion réussie !");
	        } else {
	            view.showMessage("email ou mot de passe incorrect.");
	            
	        }
	        return utilisateur;
	    }
}
