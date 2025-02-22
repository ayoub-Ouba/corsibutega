import View.loginview;
import View.Commandeview;
import View.clientView;
import Model.Utilisateur;
import Controller.LoginController;
import Controller.commandeController;
import Controller.ClientControleer;
import Model.Client;
public class Main {

	    public static void main(String[] args) {
	    	loginview view = new loginview();
	    	LoginController controller = new LoginController(view);
	    	Utilisateur user_connecter =controller.login();
	    	if(user_connecter!=null) {
	       //ajouter une commande 
	      /*  Commandeview view_commande = new Commandeview();
	        commandeController controller_commande = new commandeController(view_commande,user_connecter);
	        controller_commande.commander();*/
	        
	        //ajouter Client 
	        clientView view_client = new clientView();
	        ClientControleer controller_client = new ClientControleer(view_client);
	        
	        //controller_client.ajouter_client();
	        Client client=controller_client.find_client();
	        
	    }
	    }



}
