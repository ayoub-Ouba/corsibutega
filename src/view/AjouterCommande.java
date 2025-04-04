package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import controller.CommandeController;
import model.Client;
import model.Produit;
import view.AjouterProduit;

public class AjouterCommande extends JFrame {
    private DashboardView parent;
    private JComboBox<Client> inputSlectClients;
    private JComboBox<Produit> inputSlectProduits;
    private JTextField txtQuantite;
    private JButton btnAjouter;
    private JButton btnAnnuler;
    private int id_utilisateur;

    public AjouterCommande(DashboardView parent, int id_utilisateur, ArrayList<Produit> produits) {
        this.parent = parent;
        this.id_utilisateur = id_utilisateur;

        setTitle("Ajouter une Commande");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        inputSlectClients = new JComboBox<>(parent.clients.toArray(new Client[0]));
        inputSlectProduits = new JComboBox<>(produits.toArray(new Produit[0]));
        txtQuantite = new JTextField();
        styleInputField(txtQuantite);

        btnAjouter = new JButton("Ajouter");
        btnAnnuler = new JButton("Annuler");
        styleButton(btnAjouter);
        styleButton(btnAnnuler);

        gbc.gridx = 0; gbc.gridy = 0; add(new JLabel("Sélectionner un Client :"), gbc);
        gbc.gridx = 1; add(inputSlectClients, gbc);

        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("Sélectionner un Produit :"), gbc);
        gbc.gridx = 1; add(inputSlectProduits, gbc);

        gbc.gridx = 0; gbc.gridy = 2; add(new JLabel("Quantité :"), gbc);
        gbc.gridx = 1; add(txtQuantite, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAjouter);
        buttonPanel.add(btnAnnuler);
        add(buttonPanel, gbc);

        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtQuantite.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(AjouterCommande.this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Client selectedClient = (Client) inputSlectClients.getSelectedItem();
                Produit selectedProduit = (Produit) inputSlectProduits.getSelectedItem();
                int qnt = Integer.parseInt(txtQuantite.getText());

                CommandeController cmdc = new CommandeController();
                int idcommande=cmdc.commander(selectedProduit.getid(), selectedClient.getid(), id_utilisateur, qnt);
                if ( idcommande!= 0) {
                    parent.refreshCommandeView();
                    dispose();
                    AjouterProduit ajtprd=new AjouterProduit(parent,produits,idcommande);
                    ajtprd.setVisible(true);
                }
            }
        });

        btnAnnuler.addActionListener(e -> dispose());
        setVisible(true);
    }

    private void styleInputField(JTextField field) {
        field.setPreferredSize(new Dimension(200, 30));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(new Color(66, 133, 244));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
    }
}