package controller;

import model.Utilisateur;

import view.LoginView;
import view.DashboardView;
import view.DashbordViewPreparationCommande;

import org.mindrot.jbcrypt.BCrypt;

import basedonne.BaseDonnees;

public class LoginController {
    private LoginView view;
    private Utilisateur utilisateur;

    public LoginController(LoginView view) {
        this.utilisateur=new Utilisateur();
        this.view = view;
    }
    public Utilisateur login() {
        String email = view.getEmail();
        String password = view.getPassword();

        if (email.equals("Email")) {
            view.showMessage("Veuillez entrer un email valide.");
            return null;
        }
        if (password.isEmpty()) {
            view.showMessage("Veuillez entrer un mot de passe.");
            return null;
        }
        utilisateur.setEmail(email);
        utilisateur.setPassword(password);
        Utilisateur utilisateur_information = utilisateur.getInformation();

        if (utilisateur_information != null && BCrypt.checkpw(password, utilisateur_information.getPassword())) {
            view.showMessage("Connexion réussie !");
            if (utilisateur_information.getType().equals("AGC")) {
                // Fermer la vue actuelle
            	view.setVisible(false);
                // Créer le contrôleur pour le client et charger les clients
                ClientControleer clientController = new ClientControleer();
                CommandeController commandeController = new CommandeController();
                // Créer et afficher le Dashboard
                DashboardView dashboard = new DashboardView(clientController.clients(),view,commandeController.commandes(),utilisateur_information.getId());
                dashboard.setVisible(true);
            }else {
            	 // Fermer la vue actuelle
            	view.setVisible(false);
            	 // Créer et afficher le Dashboard
            	 CommandeController commandeController = new CommandeController();
            	 DashbordViewPreparationCommande dashboard = new DashbordViewPreparationCommande(view,commandeController.commandes(),utilisateur_information.getId());
                dashboard.setVisible(true);
            }
            
        } else {
            view.showMessage("Email ou mot de passe incorrect.");
            return null;
        }
        return utilisateur_information;
    }
 
}