package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class DashboardView extends JFrame {
    
    private DefaultTableModel model;
    private JTable table;

    public DashboardView() {
        setTitle("Dashboard");
        setSize(1200, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setPreferredSize(new Dimension(getWidth(), 70));

        JLabel titleLabel = new JLabel("CORSIBUTTEGA", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(66, 133, 244));
        header.add(titleLabel, BorderLayout.CENTER);

        JButton btnAjouter = new JButton("➕ Ajouter Client");
        btnAjouter.setFont(new Font("Serif", Font.BOLD, 14));
        btnAjouter.setBackground(new Color(66, 133, 244));
        btnAjouter.setForeground(Color.WHITE);
        btnAjouter.setPreferredSize(new Dimension(180, 40));

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.add(btnAjouter);
        header.add(rightPanel, BorderLayout.EAST);

        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(66, 133, 244));
        sidebar.setPreferredSize(new Dimension(210, getHeight()));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        sidebar.add(createSidebarButton("🏠 Accueil"));
        sidebar.add(createSidebarButton("👤 Clients"));
        sidebar.add(createSidebarButton("🛒 Commandes "));
        sidebar.add(createSidebarButton("🚪 Déconnexion"));

        String[] columnNames = {"Numéro Client", "Nom", "Prénom", "Commande", "Prix Total (€)"};
        model = new DefaultTableModel(columnNames, 0) { // 0 lignes au début
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
        };

        table = new JTable(model);
        table.setRowHeight(100); // Augmenter la hauteur de ligne

        table.getColumnModel().getColumn(0).setPreferredWidth(50);  // Numéro Client
        table.getColumnModel().getColumn(1).setPreferredWidth(100); // Nom
        table.getColumnModel().getColumn(2).setPreferredWidth(100); // Prénom
        table.getColumnModel().getColumn(3).setPreferredWidth(650); // Commande (Grande colonne)
        table.getColumnModel().getColumn(4).setPreferredWidth(50);  // Prix Total 

        table.getColumnModel().getColumn(3).setCellRenderer(new CommandeRenderer());

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(sidebar, BorderLayout.WEST);
        getContentPane().add(header, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Action du bouton "Ajouter Client"
        btnAjouter.addActionListener(e -> {
            System.out.println("Bouton Ajouter Client cliqué !");
            new AjouterClient(this); // Ouvre la fenêtre d'ajout
        });
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

    class CommandePanel extends JPanel {
        public CommandePanel() {
            setLayout(new BorderLayout());

            String[] columnNames = {"N° Produit", "Nom Produit", "Quantité", "Prix (€)", "Actions"};
            Object[][] data = {
                {"P001", "Pain", "2", "5.00", ""},
                {"P002", "Fromage", "1", "10.00", ""},
            };

            DefaultTableModel model = new DefaultTableModel(data, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 4;
                }
            };

            JTable table = new JTable(model);
            table.setRowHeight(30);
            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane, BorderLayout.CENTER);
        }
    }

    public void ajouterClientTableau(String numero, String nom, String prenom) {
        SwingUtilities.invokeLater(() -> {
            model.addRow(new Object[]{numero, nom, prenom, new CommandePanel(), "0.00"});
            table.revalidate();
            table.repaint();
        });
    }

    class CommandeRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof CommandePanel) {
                return (CommandePanel) value;
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DashboardView().setVisible(true));
    }
}
