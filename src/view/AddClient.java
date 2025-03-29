package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;

import controller.ClientControleer;

public class AddClient extends JFrame {
    private DashboardView dashboardViewInstance;
    public String nom;
    public String prenom;
    public String tele;
    public String email;

    public AddClient(DashboardView dashboard) {
        this.dashboardViewInstance = dashboard;
        setTitle("Ajouter un Client");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

       
        getContentPane().setBackground(new Color(245, 245, 245));

        // Création de GridBagConstraints pour gérer l'espacement
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Marges autour des composants

        // Labels
        JLabel lblNom = new JLabel("Nom:");
        lblNom.setFont(new Font("Segoe UI", Font.BOLD, 14));  
        JTextField txtNom = new JTextField();
        styleInputField(txtNom);

        JLabel lblPrenom = new JLabel("Prénom:");
        lblPrenom.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
        JTextField txtPrenom = new JTextField();
        styleInputField(txtPrenom);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
        JTextField txtEmail = new JTextField();
        styleInputField(txtEmail);
        
        JLabel lbltele = new JLabel("Numéro Téléphone:");
        lbltele.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
        JTextField txttele = new JTextField();
        styleInputField(txttele);

        // Le bouton "Ajouter"
        JButton btnAjouter = new JButton("Ajouter");
        btnAjouter.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnAjouter.setBackground(new Color(66, 133, 244)); 
        btnAjouter.setForeground(Color.WHITE); 
        btnAjouter.setPreferredSize(new Dimension(100, 40)); 
        btnAjouter.setBorderPainted(false);
        btnAjouter.setFocusPainted(false);
        btnAjouter.setCursor(new Cursor(Cursor.HAND_CURSOR)); 

        // Action du bouton "Ajouter"
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = txtNom.getText();
                setnom(nom);
                String prenom = txtPrenom.getText();
                setnom(prenom);
                String email = txtNom.getText();
                setnom(email);
                String tele = txtPrenom.getText();
                setnom(tele);

                // Vérifier que les champs ne sont pas vides
                if (email.isEmpty() || nom.isEmpty() || prenom.isEmpty()|| tele.isEmpty()) {
                    JOptionPane.showMessageDialog(AddClient.this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }else {
                	  ClientControleer clientController = new ClientControleer();
                	  clientController.addClient(nom,prenom,email,tele,AddClient.this) ;
                	  dashboardViewInstance.refreshClientView();
                	  dispose();
                	                  }
            }
        });

        // Placement des composants avec GridBagLayout
      

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblNom, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtNom, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblPrenom, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtPrenom, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblEmail, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtEmail, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lbltele, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(txttele, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Prendre toute la largeur pour le bouton
        add(btnAjouter, gbc);

        // Rendre la fenêtre visible
        setVisible(true);
    }
	public void setnom(String nom) {
		 this.nom= nom;
	}
	public void setprenom(String prenom) {
		 this.prenom= prenom;
	}
	public void setemail(String mail) {
		 this.email= mail;
	}
	public void settele(String tele) {
		 this.tele= tele;
	}
    // Fonction pour styliser les champs de texte (JTextField)
    private void styleInputField(JTextField field) {
        // Bordure arrondie pour les champs de texte
        Border border = BorderFactory.createLineBorder(new Color(200, 200, 200), 1);
        field.setBorder(border);
        field.setBackground(new Color(255, 255, 255));  // Fond blanc
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // Police moderne et lisible
        field.setPreferredSize(new Dimension(200, 30)); // Taille plus grande pour le confort de saisie
    }

    // Fonction principale pour tester la fenêtre
    public static void main(String[] args) {
        new AddClient(null);  // Exemple d'utilisation
    }
}
