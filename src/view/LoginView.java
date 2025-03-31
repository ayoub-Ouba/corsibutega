package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * Classe représentant la vue de connexion (Login).
 */
public class LoginView extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;

    /**
     * Constructeur de la fenêtre de connexion.
     */
    public LoginView() {
        setTitle("Login");
        setSize(950, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel principal divisé en 2 parties
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        add(mainPanel, BorderLayout.CENTER);

        // Partie gauche (Bleu avec le logo)
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(66, 133, 244));
        leftPanel.setLayout(new GridBagLayout());
        JLabel logo = new JLabel("CORSIBUTTEGA"); // Correction du nom (CORSIBUTEGGA → CORSIBUTTEGA)
        logo.setFont(new Font("Serif", Font.BOLD, 25));
        logo.setForeground(Color.WHITE);
        leftPanel.add(logo);
        mainPanel.add(leftPanel);

        // Partie droite (Blanche avec le formulaire)
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        // Titre de la section
        JLabel title = new JLabel("Benvenutu"); // Correction de "Benvinutu" en "Benvenutu" (correction du corse)
        title.setFont(new Font("Serif", Font.BOLD, 25));
        rightPanel.add(title, gbc);

        // Sous-titre
        gbc.gridy++;
        JLabel subtitle = new JLabel("Connectez-vous à votre compte"); // Correction "Connecter" → "Connectez-vous"
        subtitle.setFont(new Font("Serif", Font.PLAIN, 13));
        rightPanel.add(subtitle, gbc);

        // Champ de texte pour l'email avec placeholder
        gbc.gridy++;
        emailField = new JTextField(20);
        emailField.setPreferredSize(new Dimension(400, 35));
        emailField.setBorder(new RoundedBorder(10, "I"));
        emailField.setForeground(Color.GRAY);
        emailField.setText("Email");

        // Ajout d'un focus listener pour gérer le placeholder
        emailField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (emailField.getText().equals("Email")) {
                    emailField.setText("");
                    emailField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (emailField.getText().isEmpty()) {
                    emailField.setForeground(Color.GRAY);
                    emailField.setText("Email");
                }
            }
        });

        rightPanel.add(emailField, gbc);

        // Champ de mot de passe
        gbc.gridy++;
        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(400, 35));
        passwordField.setBorder(new RoundedBorder(10, "I"));
        rightPanel.add(passwordField, gbc);

        // Bouton de connexion
        gbc.gridy++;
        loginButton = new JButton("Connexion");
        loginButton.setPreferredSize(new Dimension(200, 40));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorder(new RoundedBorder(10, "B"));
        loginButton.setBackground(new Color(66, 133, 244));

        // Effet de survol pour le bouton (changement de couleur au survol)
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setBackground(new Color(52, 120, 230));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setBackground(new Color(66, 133, 244));
            }
        });

        rightPanel.add(loginButton, gbc);
        mainPanel.add(rightPanel);
    }

    /**
     * Récupère l'email saisi par l'utilisateur.
     * @return L'email sous forme de chaîne de caractères.
     */
    public String getEmail() {
        return emailField.getText();
    }

    /**
     * Récupère le mot de passe saisi par l'utilisateur.
     * @return Le mot de passe sous forme de chaîne de caractères.
     */
    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    /**
     * Ajoute un écouteur d'événement au bouton de connexion.
     * @param listener L'écouteur d'événements.
     */
    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    /**
     * Affiche un message sous forme de boîte de dialogue.
     * @param message Le message à afficher.
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * Classe interne pour créer une bordure arrondie.
     */
    class RoundedBorder implements Border {
        private int radius;
        private String type_input;

        /**
         * Constructeur de la bordure arrondie.
         * @param radius Rayon des coins arrondis.
         * @param type_input Type d'élément (ex: "B" pour bouton, "I" pour champ de texte).
         */
        public RoundedBorder(int radius, String type_input) {
            this.radius = radius;
            this.type_input = type_input;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Définition de la couleur de la bordure selon le type d'élément
            if (type_input.equals("B")) {
                g2d.setColor(new Color(66, 133, 244)); // Couleur bleue pour le bouton
            } else {
                g2d.setColor(new Color(220, 236, 255)); // Couleur claire pour les champs de texte
            }

            g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2d.dispose();
        }
    }
}
