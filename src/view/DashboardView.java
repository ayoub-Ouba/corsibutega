package view;

import java.util.List;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

import controller.ClientControleer;
import controller.ProduitController;
import controller.CommandeController;

import model.Client;
import model.Commande;
import model.Produits;
// pour afficher un nombre aprés vergule
import java.text.DecimalFormat;


public class DashboardView extends JFrame {

    private DefaultTableModel model;
    private JTable table;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    public List<Client> clients;
    public List<Commande> commandes;
    private LoginView loginView;
    private int id_utilisateur;
    private JTable commandeTable; 

    public DashboardView(List<Client> clients,LoginView view,List<Commande> commandes,int id_utilisateur) {
    	 this.clients = clients;
    	 this.commandes=commandes;
    	 this.loginView=view;
    	 this.id_utilisateur=id_utilisateur;
    	 
        setTitle("Dashboard");
        setSize(1200, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        table = new JTable(); 
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
       
        // Header, Sidebar et Content Panel
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setPreferredSize(new Dimension(getWidth(), 70));
        JLabel titleLabel = new JLabel("CORSIBUTTEGA", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(66, 133, 244));
        header.add(titleLabel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setBackground(Color.WHITE);
        header.add(rightPanel, BorderLayout.EAST);

        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(66, 133, 244));
        sidebar.setPreferredSize(new Dimension(210, getHeight()));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        JButton btnAccueil = createSidebarButton("🏠 Accueil");
        JButton btnClients = createSidebarButton("👤 Clients");
        JButton btnCommandes = createSidebarButton("🛒 Commandes ");
        JButton btnDeconnexion = createSidebarButton("🚪 Déconnexion");
        btnDeconnexion.addActionListener(e -> {
	        this.dispose();
	        loginView.setVisible(true);
        });

        sidebar.add(btnAccueil);
        sidebar.add(btnClients);
        sidebar.add(btnCommandes);
        sidebar.add(btnDeconnexion);

        // Panneau central avec CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Ajouter les vues
        contentPanel.add(createAccueilPanel(), "Accueil");
        contentPanel.add(createClientPanel(this.clients), "Clients");
        contentPanel.add(createCommandePanel(this.commandes), "Commandes");

        // ActionListeners pour changer de vue
        btnAccueil.addActionListener(e -> cardLayout.show(contentPanel, "Accueil"));
        btnClients.addActionListener(e -> cardLayout.show(contentPanel, "Clients"));
        btnCommandes.addActionListener(e -> cardLayout.show(contentPanel, "Commandes"));
       
        getContentPane().add(sidebar, BorderLayout.WEST);
        getContentPane().add(header, BorderLayout.NORTH);
        getContentPane().add(contentPanel, BorderLayout.CENTER);
    }
    
    // creation button de sidebar
    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Serif", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(66, 133, 244));
        button.setMinimumSize(new Dimension(210, 50));
        button.setMaximumSize(new Dimension(210, 50));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        return button;
    }

    // Partie Accueil
    private JPanel createAccueilPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // Remplissage horizontal pour que les composants s'étendent sur toute la largeur disponible
        gbc.fill = GridBagConstraints.HORIZONTAL; 

        // Définition des marges internes entre les composants
        gbc.insets = new Insets(10, 10, 10, 10); 

        // Alignement des composants en haut à gauche du panel
        gbc.anchor = GridBagConstraints.NORTH; 
        gbc.anchor = GridBagConstraints.WEST;

        // Création des boutons
        JButton btnClientsNombre = new JButton("Clients : " + ClientControleer.nombreClient());
        JButton btnCommandesNombre = new JButton("Commandes : " + CommandeController.nombreCommande());

        // Définition de la taille des boutons 
        Dimension buttonSize = new Dimension(250, 100); 
        btnClientsNombre.setPreferredSize(buttonSize);
        btnCommandesNombre.setPreferredSize(buttonSize);

        // Personnalisation de la bouton
        btnClientsNombre.setBackground(new Color(66, 133, 244)); 
        btnClientsNombre.setForeground(Color.WHITE); 
        btnCommandesNombre.setBackground(new Color(66, 133, 244)); 
        btnCommandesNombre.setForeground(Color.WHITE);

        // Définition de la police et du style du texte des boutons
        btnClientsNombre.setFont(new Font("Arial", Font.BOLD, 16));
        btnCommandesNombre.setFont(new Font("Arial", Font.BOLD, 16));

        // Ajouter des boutons au panel avec les contraintes GridBagLayout
        gbc.gridx = 0;  
        gbc.gridy = 0;  
        panel.add(btnClientsNombre, gbc);

        gbc.gridx = 1; 
        panel.add(btnCommandesNombre, gbc);

        // Ajout d'un espace vide sous les boutons pour améliorer l'affichage
        gbc.gridx = 0;
        gbc.gridy = 1;  
       
        gbc.weighty = 1.0; // Prendre tout l'espace vertical restant
        panel.add(new JLabel(" "), gbc);
        // Retourner le panel construit
        return panel;
    }


    // View Clients
    public JPanel createClientPanel(List<Client> clients) {
        JPanel panel = new JPanel(new BorderLayout());

        // Panel pour le titre et le bouton
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Liste des Clients");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));

        JButton btnAjouterClient = new JButton("➕ Ajouter");
        btnAjouterClient.setFont(new Font("Serif", Font.BOLD, 14));
        btnAjouterClient.setBackground(new Color(66, 133, 244));
        btnAjouterClient.setForeground(Color.WHITE);

        // Ajout du titre à gauche et du bouton à droite
        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(btnAjouterClient, BorderLayout.EAST);

        // Utilisation de la table déjà déclarée 
        String[] columnNames = {"ID", "Nom", "Prénom", "Email", "Téléphone"};
        model = new DefaultTableModel(columnNames, 0);
        table.setModel(model);

        // Mettre à jour la table après récupération des clients
        modifierClientTable(model, clients);

        table.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(table);

        // Action pour ajouter un client
        btnAjouterClient.addActionListener(e -> {
            AddClient ajtclient_view = new AddClient(this);
            ajtclient_view.setVisible(true);
        });
        // Ajouter des marges autour du tableau
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(tablePanel, BorderLayout.CENTER);

        return panel;
    }
    
    //refresh les clients
    public void refreshClientView() {
        if (clients != null) {
            this.clients = ClientControleer.clients(); 
            // Mettre à jour la table
            modifierClientTable((DefaultTableModel) table.getModel(), this.clients); 
        } else {
            System.out.println("La table est null, impossible de rafraîchir les données.");
        }
    }

    // modifier la table aprés refreshion 
    private void modifierClientTable(DefaultTableModel model, List<Client> clients) {
        model.setRowCount(0); 
        // Ajouter chaque client à la table
        	for (Client client : clients) {
        	        model.addRow(new Object[]{
        	            client.getid(),
        	            client.getnom(),
        	            client.getprenom(),
        	            client.getemail(),
        	            client.gettele()
        	        });
        	}  
    }
    
    private JPanel createCommandePanel(List<Commande> commandes) {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Liste des commandes");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));

        JButton btnAjouterCommande = new JButton("➕ Ajouter");
        btnAjouterCommande.setFont(new Font("Serif", Font.BOLD, 14));
        btnAjouterCommande.setBackground(new Color(66, 133, 244));
        btnAjouterCommande.setForeground(Color.WHITE);

        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(btnAjouterCommande, BorderLayout.EAST);

        // Créer un modèle de table
        String[] columnNames = {"ID", "Id Client", "Status", "Id Produit", "Produit", "Quantité", "Prix", "Date Commande", "Date Préparation", "Date Payement", "Action"};
        DefaultTableModel commandeModel = new DefaultTableModel(columnNames, 0);
        modifierCommandeTable(commandeModel, commandes);

        commandeTable = new JTable(commandeModel);
        commandeTable.setRowHeight(30);

        // Ajout de l'action du bouton "Facture"
        commandeTable.getSelectionModel().addListSelectionListener(event -> {
            int selectedRow = commandeTable.getSelectedRow();
            if (selectedRow != -1) {
            	//selectionner la colonne 10
                String action = commandeTable.getValueAt(selectedRow, 10).toString();
                if (action.equals("Facture")) {
                    int idCommande = (int) commandeTable.getValueAt(selectedRow, 0);
                    System.out.println("Génération de la facture pour la commande ID : " + idCommande);
                    // Appelle ici ta méthode de génération de facture
                    CommandeController.genererFacture(idCommande); 
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(commandeTable);

        btnAjouterCommande.addActionListener(e -> {
            ProduitController prd = new ProduitController();
            Produits produits = prd.liste_produit();
            AjouterCommande ajtcommande_view = new AjouterCommande(this, this.id_utilisateur, produits.produits);
            ajtcommande_view.setVisible(true);
        });

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
    public void refreshCommandeView() {
        CommandeController commandeController = new CommandeController();
        this.commandes = commandeController.commandes(); // Récupérer la liste mise à jour des commandes

        modifierCommandeTable((DefaultTableModel) commandeTable.getModel(), this.commandes); // Mettre à jour la table des commandes
    }

    
    private void modifierCommandeTable(DefaultTableModel model, List<Commande> commandes) {
        // Réinitialiser les lignes existantes dans la table des commandes
        model.setRowCount(0); 

        // Ajouter chaque commande à la table
        for (int i=0;i<commandes.size();i++ ) {
            DecimalFormat df = new DecimalFormat("#.##");
            model.addRow(new Object[] {
            	commandes.get(i).getid(),
            	commandes.get(i).getid_client(),
            	commandes.get(i).getstatus(),
            	commandes.get(i).getid_produit(),
            	commandes.get(i).getlabel(),
            	commandes.get(i).getQuantiter_commander(),
                df.format(commandes.get(i).getprix()) + " €",
                commandes.get(i).getdate_commande(),
                commandes.get(i).getdate_preparation(),
                commandes.get(i).getdate_payment(),
                (i == 0 || commandes.get(i - 1).getid() != commandes.get(i).getid()) ? "Facture" : "" // Condition ternaire
               
            });
            JButton btnfactureCommande = new JButton("Facture");
            btnfactureCommande.setFont(new Font("Serif", Font.BOLD, 10));
            btnfactureCommande.setBackground(new Color(66, 133, 244));
            btnfactureCommande.setForeground(Color.WHITE);
        }
    }


  }