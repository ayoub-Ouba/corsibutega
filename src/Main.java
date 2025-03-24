import controller.LoginController;
import view.LoginView;
import view.DashboardView;
import model.Utilisateur;

import model.Utilisateur;


public class Main {

	    public static void main(String[] args) {
	    	  // Créer l'instance de la vue
	        LoginView login_v = new LoginView();
	        
	        // Créer le contrôleur
	        LoginController controller = new LoginController(login_v);
	        
	        // Ajouter un listener au bouton de connexion
	        login_v.addLoginListener(e ->controller.login());
	       
	        
	        // Rendre la fenêtre visible
	        login_v.setVisible(true);
	        
			
}
}
