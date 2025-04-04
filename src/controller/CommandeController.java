package controller;

import java.sql.Connection;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import basedonne.BaseDonnees;
//basededonne
import basedonne.CommandBd;
import basedonne.ProduitBd;

//model
import model.Client;
import model.Commande;
import model.Produits;
import model.Utilisateur;

//View
import view.CommandView;
import view.ProduitView;



public class CommandeController {
		  private CommandBd command_bd;
		  private int utilisateur;

	    public CommandeController(int utilisateur){
	        this.utilisateur=utilisateur;
	    }
	    
	    public CommandeController(){
	        
	    }
	    
	    public boolean supprimerCommande(int id) {
	    	return command_bd.supprimerCommande( id);
	    }
	    
	    public int commander(int idproduit,int idient,int id_utilisateur,int qunt) {
	    	LocalDateTime date_commande = LocalDateTime.now();
	        Commande commande=new Commande(0,"En cours",idproduit,"",qunt,0,date_commande,null,null,idient,id_utilisateur);
	        int id_commande=commande.ajouterCommande();
	        return id_commande;
	    }
	    
	    public boolean ajouterProduitAuCommande(int idcommande,int qnt,int idproduit) {	    	
	       Commande commande=new Commande(idcommande,"En cours",idproduit,"",qnt,0,null,null,null,0,0);
	       return commande.ajouterProduitauCommande();
	    	
	    }

	    
		public void updateCommandeStatus(int id, String newStatus) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // Obtenez la connexion à la base (la méthode getConnection() est à adapter selon votre projet)
            conn = BaseDonnees.getConnection();
            String sql = "UPDATE commandes SET status = ? WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, newStatus);
            stmt.setInt(2, id);
            int rowsUpdated = stmt.executeUpdate();
            if(rowsUpdated > 0){
                System.out.println("Le statut de la commande a été mis à jour.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(stmt != null)
                    stmt.close();
                if(conn != null)
                    conn.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
	
		public static void genererFacture(int id) {
			try {
	            // Récupérer la liste des commandes
	            List<Commande> commandes = Commande.facture(id);  
	            if (commandes.isEmpty()) {
	                System.out.println("Aucune commande trouvée.");
	                return;
	            }

	            // Créer un document PDF
	            Document document = new Document();
	            PdfWriter.getInstance(document, new FileOutputStream("facture_commande_" + commandes.get(0).getid() + ".pdf"));
	            document.open();

	            // En-tête de la facture
	            Paragraph title = new Paragraph("Facture - Commande " + commandes.get(0).getid(),
	            		FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLACK));
	            title.setAlignment(Element.ALIGN_CENTER);
	            document.add(title);

	            // Ajouter des informations sur la commande
	            document.add(new Paragraph("Status: " + commandes.get(0).getstatus()));
	            document.add(new Paragraph("Date Commande: " + commandes.get(0).getdate_commande()));
	            document.add(new Paragraph("Date Préparation: " + commandes.get(0).getdate_preparation()));
	            document.add(new Paragraph("Date Paiement: " + commandes.get(0).getdate_payment()));
	            document.add(new Paragraph("Client ID: " + commandes.get(0).getid_client()));
	            document.add(new Paragraph(" "));
	            
	            // Créer un tableau pour afficher les produits
	            PdfPTable table = new PdfPTable(4); 
	            table.setWidthPercentage(100); 

	            // Ajouter les en-têtes de colonnes
	            table.addCell("Produit");
	            table.addCell("Quantité");
	            table.addCell("Prix unitaire (€)");
	            table.addCell("Total (€)");

	            // Ajouter les lignes de produits
	            DecimalFormat df = new DecimalFormat("#.##");
	            double totalFacture = 0;
	            for (Commande commande : commandes) {
	                table.addCell(commande.getlabel()); 
	                table.addCell(String.valueOf(commande.getQuantiter_commander()));  
	                table.addCell(df.format(commande.getprix())); 
	                double totalProduit = commande.getQuantiter_commander() * commande.getprix();
	                table.addCell(df.format(totalProduit)); 
	                totalFacture += totalProduit;
	            }

	            // Ajouter le tableau à la facture
	            document.add(table);
	            document.add(new Paragraph(" "));  // Ajouter un espace

	            // Ajouter le total de la commande
	            Paragraph totalParagraph = new Paragraph("Total de la commande: " + df.format(totalFacture) + " €",
	                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK));
	            totalParagraph.setAlignment(Element.ALIGN_RIGHT);
	            document.add(totalParagraph);

	            // Ajouter un pied de page 
	            document.add(new Paragraph(" "));
	            Paragraph footer = new Paragraph("Merci pour votre achat !",
	                    FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.GRAY));
	            footer.setAlignment(Element.ALIGN_CENTER);
	            document.add(footer);
	            
	            // Fermer le document
	            document.close();
	            System.out.println("Facture générée avec succès!");

	        } catch (FileNotFoundException | DocumentException e) {
	            e.printStackTrace();
	        }
	    }
		
		public static int nombreCommande() {
			return Commande.nombreCommande();
		}
	    public static  List<Commande> commandes() {		    
	    	List<Commande> Commandes = Commande.listCommande();
	        if (Commandes != null) {
	            System.out.println("Commandes récupérés : " + Commandes.size());
	        } else {
	            System.out.println("Aucun Commande trouvé");
	        }
	        return Commandes;
	    }
		
    

}