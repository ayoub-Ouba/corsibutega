package view;

import java.util.List;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

import controller.ClientControleer;
import controller.DashbordController;
import model.Client;


public class DashboardView extends JFrame {

    private DefaultTableModel model;
    private JTable table;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    public List<Client> clients;
    public DashbordController dashcontroller;

    public DashboardView(List<Client> clients) {
    	 this.clients = clients;
    	 
        setTitle("Dashboard");
        setSize(1200, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        // Assure-toi que la table est initialisée ici
        table = new JTable(); // ou avec un modèle spécifique
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

       
        // Header, Sidebar et Content Panel (Rien à changer ici)
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
        contentPanel.add(createCommandePanel(), "Commandes");
        

        // ActionListeners pour changer de vue
        btnAccueil.addActionListener(e -> cardLayout.show(contentPanel, "Accueil"));
        btnClients.addActionListener(e -> cardLayout.show(contentPanel, "Clients"));
        btnCommandes.addActionListener(e -> cardLayout.show(contentPanel, "Commandes"));
        

        getContentPane().add(sidebar, BorderLayout.WEST);
        getContentPane().add(header, BorderLayout.NORTH);
        getContentPane().add(contentPanel, BorderLayout.CENTER);
    }

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

    private JPanel createAccueilPanel() {
    	dashcontroller=new DashbordController();
        JPanel panel = new JPanel();

        // Utilisation d'un GridBagLayout pour un contrôle plus précis sur l'alignement
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; // Remplir horizontalement
        gbc.insets = new Insets(10, 10, 10, 10); // Espacement autour des boutons

        // Positionner les boutons dans la première ligne avec l'ancrage en haut
        gbc.anchor = GridBagConstraints.NORTH; // Aligner les composants en haut
        gbc.anchor = GridBagConstraints.WEST ; // Aligner les composants en haut

        // Boutons avec un texte dynamique et une taille fixe
        
        JButton btnClients = new JButton("Clients : "+dashcontroller.countClientCommandeProduit().get(0));
        JButton btnCommandes = new JButton("Commandes :"+dashcontroller.countClientCommandeProduit().get(1));
     

        // Définir la taille des boutons
        Dimension buttonSize = new Dimension(250, 100); // Augmenter la largeur et la hauteur pour des boutons plus grands
        btnClients.setPreferredSize(buttonSize);
        btnClients.setBackground(new Color(66, 133, 244));
        btnClients.setForeground(Color.WHITE);

        btnCommandes.setPreferredSize(buttonSize);
        btnCommandes.setBackground(new Color(66, 133, 244));
        btnCommandes.setForeground(Color.WHITE);

       

        // Personnaliser l'apparence des boutons
        btnClients.setFont(new Font("Arial", Font.BOLD, 16)); // Police plus professionnelle
        btnCommandes.setFont(new Font("Arial", Font.BOLD, 16));
       

        // Ajouter les boutons au panel avec des contraintes GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0; // Positionner le bouton Clients en haut
        panel.add(btnClients, gbc);

        gbc.gridx = 1;
        panel.add(btnCommandes, gbc);

   

        // Ajouter un espace vide sous les boutons (si nécessaire, pour éviter que les boutons soient trop espacés)
        gbc.gridx = 0;
        gbc.gridy = 1; // Placer un composant (par exemple, un espace vide) sous les boutons
        gbc.gridwidth = 3; // Cela prend toute la ligne
        gbc.weighty = 1.0; // Donner du poids vertical pour occuper l'espace restant
        panel.add(new JLabel(" "), gbc); // Ajouter un label vide pour "manger" l'espace sous les boutons

        return panel;

    }



    // Vue Clients
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

        // Utilisation de la table déjà déclarée (table) et non clientTable
        String[] columnNames = {"ID", "Nom", "Prénom", "Email", "Téléphone"};
        model = new DefaultTableModel(columnNames, 0);
        table.setModel(model);

        // Mettre à jour la table après récupération des clients
        updateClientTable(model, clients);

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

    public void refreshClientView() {
        if (clients != null) {
            ClientControleer clientController = new ClientControleer();
            this.clients = clientController.clients(); // Récupérer la liste mise à jour
            updateClientTable((DefaultTableModel) table.getModel(), this.clients); // Mettre à jour la table
        } else {
            System.out.println("La table est null, impossible de rafraîchir les données.");
        }
    }

    private void updateClientTable(DefaultTableModel model, List<Client> clients) {
        // Vérification si la liste est vide ou nulle
        if (clients == null || clients.isEmpty()) {
            System.out.println("Aucun client trouvé.");
            model.setRowCount(0); // Vider le tableau si la liste est vide
        }

        // Réinitialiser les lignes existantes dans le tableau
        model.setRowCount(0); // Supprimer toutes les lignes avant d'ajouter les nouvelles données

        // Ajouter chaque client à la table
     
        	for (Client client : clients) {
        	    if (client.getid()!=0 && client.getnom() != null && client.getprenom() != null) {
        	        model.addRow(new Object[]{
        	            client.getid(),
        	            client.getnom(),
        	            client.getprenom(),
        	            client.getemail(),
        	            client.gettele()
        	        });
        	    } else {
        	        System.out.println("Client avec données manquantes : " + client);
        	        
        	    }
        	}  
    }
    private JPanel createCommandePanel() {
   	 JPanel panel = new JPanel(new BorderLayout());
        
        // Panel pour le titre et le bouton
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel titleLabel = new JLabel("Liste des commandes");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        
        JButton btnAjouterClient = new JButton("➕ Ajouter");
        btnAjouterClient.setFont(new Font("Serif", Font.BOLD, 14));
        btnAjouterClient.setBackground(new Color(66, 133, 244));
        btnAjouterClient.setForeground(Color.WHITE);
        
        // Ajout du titre à gauche et du bouton à droite
        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(btnAjouterClient, BorderLayout.EAST);
        
        // Tableau des clients
        String[] columnNames = {"ID", "Status", "Date Commande", "Date Préparation", "Date Payement"};
        DefaultTableModel clientModel = new DefaultTableModel(columnNames, 0);
        
        JTable clientTable = new JTable(clientModel);
        clientTable.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(clientTable);
        
        // Ajout des composants au panel
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;

   }
    private void createDexonePanel() {
      	this.dispose();

      }
}