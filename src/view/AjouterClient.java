package view;

import javax.swing.*;
import java.awt.*;

public class AjouterClient extends JFrame {
    private DashboardView dashboardViewInstance;

    public AjouterClient(DashboardView dashboard) {
        this.dashboardViewInstance = dashboard;
        setTitle("Ajouter un Client");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel lblNom = new JLabel("Nom:");
        JTextField txtNom = new JTextField();

        JLabel lblPrenom = new JLabel("Prénom:");
        JTextField txtPrenom = new JTextField();

        JLabel lblNumero = new JLabel("Numéro Client:");
        JTextField txtNumero = new JTextField();

        JButton btnAjouter = new JButton("Ajouter");
        btnAjouter.addActionListener(e -> {
            String numero = txtNumero.getText();
            String nom = txtNom.getText();
            String prenom = txtPrenom.getText();

            // Vérifier que les champs ne sont pas vides
            if (numero.isEmpty() || nom.isEmpty() || prenom.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Vérifier que DashboardView est accessible et ajouter le client
            if (dashboardViewInstance != null) { 
                dashboardViewInstance.ajouterClientTableau(numero, nom, prenom);
            }

            // Fermer la fenêtre après ajout
            dispose();
        });

        add(lblNumero);
        add(txtNumero);
        add(lblNom);
        add(txtNom);
        add(lblPrenom);
        add(txtPrenom);
        add(new JLabel()); // Espace vide
        add(btnAjouter);

        setVisible(true);
    }
}
