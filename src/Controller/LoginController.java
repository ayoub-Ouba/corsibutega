package Controller;
import bd.Utilisateur_BD;



import org.mindrot.jbcrypt.BCrypt;

import Model.Utilisateur;
import View.loginview;


public class LoginController {
	    private Utilisateur_BD Utilisateur_BD;
	    private loginview view;

	    public LoginController(loginview view) {
	        this.Utilisateur_BD = new Utilisateur_BD(); 
	        this.view = view;
	    }

	    public Utilisateur login() {
	        String email = view.getInput("Entrez votre email: ");
	        String password = view.getInput("Entrez votre mot de passe: ");

	        Utilisateur utilisateur = Utilisateur_BD.get_information_apartir_email(email);

	        if (utilisateur != null && BCrypt.checkpw(password, utilisateur.getPassword()) ) {
	        
	            view.afficherMessage("Connexion réussie !");
	           
	            
	            
	        } else {
	            view.afficherMessage("email ou mot de passe incorrect.");
	            
	        }
	        return utilisateur;
	    }
}
