package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.DashboardView;

public class AjouterCommande extends JFrame {
    private JTextField txtIdProduit, txtIdClient, txtNomProduit, txtQuantite, txtPrixTotal;
    private JButton btnAjouter, btnAnnuler;
    private DashboardView parent;

    public AjouterCommande(DashboardView parent) {
        this.parent = parent;
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(6, 2, 10, 10));
        add(new JLabel("ID Produit : "));
        txtIdProduit =  new JTextField();
        add(txtIdProduit);
        
        add(new JLabel("ID Client : "));
        txtIdClient = new JTextField();
        add(txtIdClient);

        add(new JLabel("Nom Produit : "));
        txtNomProduit = new JTextField();
        add(txtNomProduit);

        add(new JLabel("Quantite : "));
        txtQuantite = new JTextField();
        add(txtQuantite);

        add(new JLabel("Prix Total : "));
        txtPrixTotal = new JTextField();
        add(txtPrixTotal);

        btnAjouter = new JButton("Ajouter");
        btnAnnuler = new JButton("Annuler");
        add(btnAjouter);
        add(btnAnnuler);
        }

      //  btnAjouter.addActionListener(new ActionListener() {
         //   @Override
       //     public void actionPerformed(ActionEvent e) {
          //      ajouterCommande();
        //    }
       // });
        //btnAnnuler.addActionListener(e -> dispose());
   // }
  

    //private void ajouterCommande() {
       // try{
           // int idProduit = Integer.parseInt(txtIdProduit.getText());
            //int idClient = Integer.parseInt(txtIdClient.getText());
            //String nomProduit = txtNomProduit.getText();
            //int quantite = Integer.parseInt(txtQuantite.getText());
            //double prixTotal = Double.parseDouble(txtPrixTotal.getText());
            //JOptionPane.showMessageDialog(this, "Commande ajoutée avec succès.", "Succès",JOptionPane.INFORMATION_MESSAGE);
           // parent.refreshCommandeView();;
         //   dispose();
       // }catch(NumberFormatException ex){
        //    JOptionPane.showMessageDialog(this,"Veuillez entrer des valeurs valides.","Erreur",JOptionPane.ERROR_MESSAGE);
      //  }
    
    //}

}