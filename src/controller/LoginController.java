package controller;

import model.Client;
import model.Utilisateur;
import view.LoginView;
import view.DashboardView;
import controller.ClientControleer;

import basedonne.ClientBd;

import java.util.List;

import javax.swing.SwingUtilities;

import org.mindrot.jbcrypt.BCrypt;
import basedonne.UtilisateurBD;

public class LoginController {
    private UtilisateurBD Utilisateur_BD;
    private LoginView view;

    public LoginController(LoginView view) {
        this.Utilisateur_BD = new UtilisateurBD();
        this.view = view;
       
    }

    public Utilisateur login() {
    
        String email = view.getEmail();
        String password = view.getPassword();

        if (password.isEmpty() || email.equals("Email")) {
            view.showMessage("Veuillez entrer un email valide.");
            return null;
        }

        if (password.isEmpty()) {
            view.showMessage("Veuillez entrer un mot de passe.");
            return null;
        }

        Utilisateur utilisateur = Utilisateur_BD.get_information_apartir_email(email);

        if (utilisateur != null && BCrypt.checkpw(password, utilisateur.getPassword())) {
            view.showMessage("Connexion réussie !");
            
           
            if (utilisateur.getType().equals("AGC")) {
                // Fermer la vue actuelle
            	view.setVisible(false);
                
               

                // Créer le contrôleur pour le client et charger les clients
                ClientControleer clientController = new ClientControleer();
                clientController.clients();
                // Créer et afficher le Dashboard
                DashboardView dashboard = new DashboardView(clientController.clients());
                dashboard.setVisible(true);
            }

        } else {
            view.showMessage("Email ou mot de passe incorrect.");
            return null;
        }
        return utilisateur;
    }
}