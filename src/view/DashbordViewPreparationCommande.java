package view;

import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import controller.ClientControleer;
import controller.DashbordController;
import controller.LoginController;
import controller.ProduitController;
import controller.CommandeController;
import model.Client;
import model.Commande;
import model.Produits;
import java.text.DecimalFormat;

public class DashbordViewPreparationCommande extends JFrame  {
    private DefaultTableModel model;
    private JTable table;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    public List<Commande> commandes;
    public DashbordController dashcontroller;
    private LoginView view;
    public List<Client> clients;
    private int id_utilisateur;
    private JTable commandeTable; 

    public DashbordViewPreparationCommande(LoginView view, List<Commande> commandes, int id_utilisateur) {
         this.commandes = commandes;
         this.view = view;
         this.id_utilisateur = id_utilisateur;
         
        setTitle("Dashboard Preparation");
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
        JButton btnCommandes = createSidebarButton("🛒 Commandes ");
        JButton btnDeconnexion = createSidebarButton("🚪 Déconnexion");
        btnDeconnexion.addActionListener(e -> {
            this.dispose();
            view.setVisible(true);
        });

        sidebar.add(btnAccueil);
        sidebar.add(btnCommandes);
        sidebar.add(btnDeconnexion);

        // Panneau central avec CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Ajouter les vues
        contentPanel.add(createAccueilPanel(), "Accueil");
        contentPanel.add(createCommandePanel(this.commandes), "Commandes");
        

        // ActionListeners pour changer de vue
        btnAccueil.addActionListener(e -> cardLayout.show(contentPanel, "Accueil"));
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
        JPanel panel = new JPanel();

        // Utilisation d'un GridBagLayout pour un contrôle plus précis sur l'alignement
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; // Remplir horizontalement
        gbc.insets = new Insets(10, 10, 10, 10); // Espacement autour des boutons

        // Positionner les boutons dans la première ligne avec l'ancrage en haut
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.anchor = GridBagConstraints.WEST;

        // Calculer le nombre des commandes encore en traitement
        int commandesEnCours = 0;
        for (Commande commande : commandes) {
            if (!commande.getstatus().equals("préparation finis")) {
                commandesEnCours++;
            }
        }

        JButton btnCommandes = new JButton("Commandes en cours : " + commandesEnCours);
     
        // Définir la taille des boutons
        Dimension buttonSize = new Dimension(250, 100); 
        btnCommandes.setPreferredSize(buttonSize);
        btnCommandes.setBackground(new Color(66, 133, 244));
        btnCommandes.setForeground(Color.WHITE);
        btnCommandes.setFont(new Font("Arial", Font.BOLD, 16));
       
        gbc.gridy = 0;
        gbc.gridx = 1;
        panel.add(btnCommandes, gbc);

        // Ajouter un espace vide sous les boutons
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3; 
        gbc.weighty = 1.0;
        panel.add(new JLabel(" "), gbc);

        return panel;
    }
    
    private JPanel createCommandePanel(List<Commande> commandes) {
        JPanel panel = new JPanel(new BorderLayout());

        // Panel pour le titre et le bouton
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Liste des commandes");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));

        JButton btnAjouterCommande = new JButton("➕ Ajouter");
        btnAjouterCommande.setFont(new Font("Serif", Font.BOLD, 14));
        btnAjouterCommande.setBackground(new Color(66, 133, 244));
        btnAjouterCommande.setForeground(Color.WHITE);

        // Ajout du titre à gauche et du bouton à droite
        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(btnAjouterCommande, BorderLayout.EAST);

        // Créer un modèle de table spécifique pour les commandes, avec une colonne Action en plus
        String[] columnNames = {"ID", "Id Client", "Status", "Id Produit", "Produit", "Quantité", "Prix", "Date Commande", "Date Préparation", "Date Payement", "Action"};
        DefaultTableModel commandeModel = new DefaultTableModel(columnNames, 0);
        updateCommandeTable(commandeModel, commandes); // Mettre à jour avec les données de commandes

        commandeTable = new JTable(commandeModel); // Utiliser la table spécifique
        commandeTable.setRowHeight(30);

        // Ajuster les largeurs des colonnes si nécessaire
        commandeTable.getColumnModel().getColumn(4).setPreferredWidth(150); // Produit
        commandeTable.getColumnModel().getColumn(7).setPreferredWidth(150); // Date Commande
        commandeTable.getColumnModel().getColumn(8).setPreferredWidth(150); // Date Préparation
        commandeTable.getColumnModel().getColumn(9).setPreferredWidth(150); // Date Payement

        JScrollPane scrollPane = new JScrollPane(commandeTable);

        // Ajouter l'action pour ajouter une commande
        btnAjouterCommande.addActionListener(e -> {
            ProduitController prd = new ProduitController();
            Produits produits = prd.liste_produit();
            //AjouterCommande ajtcommande_view = new AjouterCommande(this, this.id_utilisateur, produits.produits);
            //ajtcommande_view.setVisible(true);
        });

        // Ajouter un MouseListener pour gérer la colonne "Action"
        commandeTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int column = commandeTable.columnAtPoint(e.getPoint());
                int row = commandeTable.rowAtPoint(e.getPoint());
                // Vérifier si la colonne cliquée est la colonne "Action"
                if (column == 10) {
                    int id = (Integer) commandeTable.getValueAt(row, 0);
                    // Rechercher la commande correspondante dans la liste
                    for (Commande c : commandes) {
                        if (c.getid() == id && c.getstatus().equals("en cours de traitement")) {
                            // Mise à jour du statut via le contrôleur
                            CommandeController cc = new CommandeController();
                            cc.updateCommandeStatus(c.getid(), "préparation finis");
                            refreshCommandeView();
                            break;
                        }
                    }
                }
            }
        });

        // Ajouter les composants au panel
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    public void refreshCommandeView() {
        CommandeController commandeController = new CommandeController();
        this.commandes = commandeController.commandes(); // Récupérer la liste mise à jour des commandes
        updateCommandeTable((DefaultTableModel) commandeTable.getModel(), this.commandes); // Mettre à jour la table des commandes
    }

    private void updateCommandeTable(DefaultTableModel model, List<Commande> commandes) {
        // Réinitialiser les lignes existantes dans la table des commandes
        model.setRowCount(0); 

        // Ajouter chaque commande à la table si le statut n'est pas "préparation finis"
        for (Commande commande : commandes) {
            if (commande.getstatus().equals("préparation finis"))
                continue; // Ne pas afficher les commandes terminées

            DecimalFormat df = new DecimalFormat("#.##");
            // Pour la colonne Action, afficher "Terminer Préparation" si la commande est en cours de traitement, sinon vide
            String action = "";
            if (commande.getstatus().equals("en cours de traitement")) {
                action = "Terminer Préparation";
            }
            model.addRow(new Object[] {
                commande.getid(),
                commande.getid_client(),
                commande.getstatus(),
                commande.getid_produit(),
                commande.getlabel(),
                commande.getQuantiter_commander(),
                df.format(commande.getprix()) + " €",
                commande.getdate_commande(),
                commande.getdate_preparation(),
                commande.getdate_payment(),
                action
            });
        }
    }
}

