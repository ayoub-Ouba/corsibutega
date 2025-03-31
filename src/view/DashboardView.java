package view;

import java.util.List;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

import controller.ClientControleer;
import controller.DashbordController;
import controller.LoginController;
import model.Client;
import model.Commande;
import java.text.DecimalFormat;

public class DashboardView extends JFrame {

    private DefaultTableModel model;
    private JTable table;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private DashbordController dashcontroller;
    private List<Client> clients;
    private List<Commande> commandes;
    private LoginView view;

    public DashboardView(List<Client> clients, LoginView view, List<Commande> commandes) {
        this.clients = clients;
        this.commandes = commandes;
        this.view = view;
        
        // Initialisation du contrôleur avec les données
        this.dashcontroller = new DashbordController(clients, commandes);

        setTitle("Dashboard");
        setSize(1200, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        // Header et Sidebar
        JPanel header = createHeader();
        JPanel sidebar = createSidebar();

        // Panneau central avec CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        contentPanel.add(createAccueilPanel(), "Accueil");
        contentPanel.add(createClientPanel(), "Clients");
        contentPanel.add(createCommandePanel(), "Commandes");

        getContentPane().add(sidebar, BorderLayout.WEST);
        getContentPane().add(header, BorderLayout.NORTH);
        getContentPane().add(contentPanel, BorderLayout.CENTER);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setPreferredSize(new Dimension(getWidth(), 70));

        JLabel titleLabel = new JLabel("CORSIBUTTEGA", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(66, 133, 244));

        header.add(titleLabel, BorderLayout.CENTER);
        return header;
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(66, 133, 244));
        sidebar.setPreferredSize(new Dimension(210, getHeight()));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        JButton btnAccueil = createSidebarButton("🏠 Accueil");
        JButton btnClients = createSidebarButton("👤 Clients");
        JButton btnCommandes = createSidebarButton("🛒 Commandes ");
        JButton btnDeconnexion = createSidebarButton("🚪 Déconnexion");
        btnDeconnexion.addActionListener(e -> setVisible(false));

        sidebar.add(btnAccueil);
        sidebar.add(btnClients);
        sidebar.add(btnCommandes);
        sidebar.add(btnDeconnexion);

        btnAccueil.addActionListener(e -> cardLayout.show(contentPanel, "Accueil"));
        btnClients.addActionListener(e -> cardLayout.show(contentPanel, "Clients"));
        btnCommandes.addActionListener(e -> cardLayout.show(contentPanel, "Commandes"));

        return sidebar;
    }

    private JPanel createAccueilPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JButton btnClients = new JButton("Clients : " + dashcontroller.getNombreClients());
        JButton btnCommandes = new JButton("Commandes : " + dashcontroller.getNombreCommandes());

        Dimension buttonSize = new Dimension(250, 100);
        configureButton(btnClients, buttonSize);
        configureButton(btnCommandes, buttonSize);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(btnClients, gbc);

        gbc.gridx = 1;
        panel.add(btnCommandes, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.weighty = 1.0;
        panel.add(new JLabel(" "), gbc);

        return panel;
    }

    private void configureButton(JButton button, Dimension size) {
        button.setPreferredSize(size);
        button.setBackground(new Color(66, 133, 244));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
    }

    private JPanel createClientPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel topPanel = createTopPanel("Liste des Clients", () -> new AddClient(this).setVisible(true));

        String[] columnNames = {"ID", "Nom", "Prénom", "Email", "Téléphone"};
        model = new DefaultTableModel(columnNames, 0);
        updateClientTable();

        table.setModel(model);
        table.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createCommandePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel topPanel = createTopPanel("Liste des commandes", () -> new AjouterCommande(this).setVisible(true));

        String[] columnNames = {"ID", "Id Client", "Status", "Id Produit", "Produit", "Quantité", "Prix", "Date Commande", "Date Préparation", "Date Paiement"};
        DefaultTableModel commandeModel = new DefaultTableModel(columnNames, 0);
        updateCommandeTable(commandeModel);

        JTable commandeTable = new JTable(commandeModel);
        commandeTable.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(commandeTable);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createTopPanel(String title, Runnable addAction) {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));

        JButton btnAjouter = new JButton("➕ Ajouter");
        btnAjouter.setFont(new Font("Serif", Font.BOLD, 14));
        btnAjouter.setBackground(new Color(66, 133, 244));
        btnAjouter.setForeground(Color.WHITE);
        btnAjouter.addActionListener(e -> addAction.run());

        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(btnAjouter, BorderLayout.EAST);
        return topPanel;
    }

    private void updateClientTable() {
        model.setRowCount(0);
        for (Client client : clients) {
            model.addRow(new Object[]{client.getid(), client.getnom(), client.getprenom(), client.getemail(), client.gettele()});
        }
    }

    private void updateCommandeTable(DefaultTableModel model) {
        model.setRowCount(0);
        DecimalFormat df = new DecimalFormat("#.##");
        for (Commande commande : commandes) {
            model.addRow(new Object[]{
                    commande.getid(),
                    commande.getid_client(),
                    commande.getstatus(),
                    commande.getid_produit(),
                    commande.getlabel(),
                    commande.getQuantiter_commander(),
                    df.format(commande.getprix()) + " €",
                    commande.getdate_commande(),
                    commande.getdate_preparation(),
                    commande.getdate_payment()
            });
        }
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Serif", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(66, 133, 244));
        return button;
    }
}
