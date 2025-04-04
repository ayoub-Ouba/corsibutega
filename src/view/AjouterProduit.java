package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.CommandeController;
import model.Produit;
import java.util.ArrayList;

public class AjouterProduit extends JDialog {
    private JComboBox<Produit> comboBoxProduits;
    private JTextField txtQuantite;
    private JButton btnAjouter, btnAnnuler;
    private DashboardView parent;

    public AjouterProduit(DashboardView parent, ArrayList<Produit> produits,int idcommande) {
        this.parent = parent;

        setTitle("Ajouter des Produits");
        setSize(400, 200);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(4, 2, 10, 10));
        // Titre principal
        add(new JLabel("Voulez-vous ajouter des produits ?"));
        add(new JLabel("")); 

        // Sélection produit
        comboBoxProduits = new JComboBox<>(produits.toArray(new Produit[0]));
        add(new JLabel("Sélectionner un Produit : "));
        add(comboBoxProduits);

        // Quantité
        add(new JLabel("Quantité : "));
        txtQuantite = new JTextField();
        add(txtQuantite);

        // Boutons
        btnAjouter = new JButton("Ajouter");
        btnAnnuler = new JButton("Annuler");
        add(btnAjouter);
        add(btnAnnuler);

        // Action Ajouter
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produit produitSelectionner = (Produit) comboBoxProduits.getSelectedItem();
                int quantite;
                try {
                    quantite = Integer.parseInt(txtQuantite.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AjouterProduit.this, "Quantité invalide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                CommandeController cmdcontr=new CommandeController();
                if(cmdcontr.ajouterProduitAuCommande(idcommande, quantite, produitSelectionner.getid())==true) {
                    JOptionPane.showMessageDialog(AjouterProduit.this, "Produit ajouté avec succès !");
                    parent.refreshCommandeView();

                }
        
            }
        });

        // Action Annuler
        btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
