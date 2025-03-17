package controller;
import model.Utilisateur;
import view.LoginView;

import org.mindrot.jbcrypt.BCrypt;

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 1264f539e04be219c41c47d6e04ef9954577c518
import basedonne.Utilisateur_BD;


public class LoginController {
	    private Utilisateur_BD Utilisateur_BD;
	    private LoginView view;

	    public LoginController(LoginView view) {
	        this.Utilisateur_BD = new Utilisateur_BD(); 
<<<<<<< HEAD
=======
=======
import basedonne.UtilisateurBD;


public class LoginController {
	    private UtilisateurBD Utilisateur_BD;
	    private LoginView view;

	    public LoginController(LoginView view) {
	        this.Utilisateur_BD = new UtilisateurBD(); 
>>>>>>> b68b217 (Version Dashboard)
>>>>>>> 1264f539e04be219c41c47d6e04ef9954577c518
	        this.view = view;
	    }

	    public Utilisateur login() {
<<<<<<< HEAD
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
=======
	        String email = view.getInput("Entrez votre email: ");
	        String password = view.getInput("Entrez votre mot de passe: ");

	        Utilisateur utilisateur = Utilisateur_BD.get_information_apartir_email(email);

	        if (utilisateur != null && BCrypt.checkpw(password, utilisateur.getPassword()) ) {
	        
	            view.afficherMessage("Connexion réussie !");
	           
	            
	            
	        } else {
	            view.afficherMessage("email ou mot de passe incorrect.");
>>>>>>> 1264f539e04be219c41c47d6e04ef9954577c518
	            
	        }
	        return utilisateur;
	    }
}
