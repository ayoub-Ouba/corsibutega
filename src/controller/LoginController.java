package controller;
import model.Utilisateur;
import view.LoginView;

import org.mindrot.jbcrypt.BCrypt;

<<<<<<< HEAD
import basedonne.Utilisateur_BD;


public class LoginController {
	    private Utilisateur_BD Utilisateur_BD;
	    private LoginView view;

	    public LoginController(LoginView view) {
	        this.Utilisateur_BD = new Utilisateur_BD(); 
=======
import basedonne.UtilisateurBD;


public class LoginController {
	    private UtilisateurBD Utilisateur_BD;
	    private LoginView view;

	    public LoginController(LoginView view) {
	        this.Utilisateur_BD = new UtilisateurBD(); 
>>>>>>> b68b217 (Version Dashboard)
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
