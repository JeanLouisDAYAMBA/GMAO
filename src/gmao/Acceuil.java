package gmao;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Acceuil {

    private JFrame frame;
    private JTextField textField;
    private JPasswordField passwordField;

    // Variables pour les informations de connexion valides
    private final String validUsername = "GMAO";
    private final String validPassword = "77777777";

    private final String validUsername1 = "CLIENT";
    private final String validPassword1 = "7777777";


    
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Acceuil window = new Acceuil();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Acceuil() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 711, 432);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(20, 39, 376, 45);
        frame.getContentPane().add(panel);
        
        JLabel lblNewLabel = new JLabel("Veuillez entrer vos informations de connexion");
        panel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(37, 137, 282, 30);
        frame.getContentPane().add(panel_1);
        
        JLabel lblNewLabel_1 = new JLabel("Nom d'Utilisateur");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel_1.add(lblNewLabel_1);
        
        textField = new JTextField();
        panel_1.add(textField);
        textField.setColumns(10);
        
        JPanel panel_5 = new JPanel();
        panel_5.setBounds(414, 39, 256, 321);
        frame.getContentPane().add(panel_5);
        
        JLabel lblNewLabel_2 = new JLabel("New label");
        lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\HP\\Downloads\\acceuil.png"));
        panel_5.add(lblNewLabel_2);
        
        JPanel panel_1_1 = new JPanel();
        panel_1_1.setBounds(47, 178, 358, 45);
        frame.getContentPane().add(panel_1_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Mot de Passe");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel_1_1.add(lblNewLabel_1_1);
        
        passwordField = new JPasswordField();
        passwordField.setColumns(10);
        panel_1_1.add(passwordField);
        
        JRadioButton rdbtnNewRadioButton = new JRadioButton("Afficher");
        panel_1_1.add(rdbtnNewRadioButton);
        rdbtnNewRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rdbtnNewRadioButton.isSelected()) {
                    // Afficher le mot de passe
                    passwordField.setEchoChar((char) 0);
                } else {
                    // Masquer le mot de passe
                    passwordField.setEchoChar('*');
                }
            }
        });
        
        JPanel panel_2 = new JPanel();
        panel_2.setBounds(115, 234, 183, 30);
        frame.getContentPane().add(panel_2);
        
        JCheckBox chckbxNewCheckBox = new JCheckBox("Se souvenir de moi ?");
        panel_2.add(chckbxNewCheckBox);
        
        JPanel panel_2_1 = new JPanel();
        panel_2_1.setBackground(new Color(240, 240, 240));
        panel_2_1.setBounds(115, 297, 183, 41);
        frame.getContentPane().add(panel_2_1);
        
        JButton btnNewButton = new JButton("Connexion");
        btnNewButton.setBackground(new Color(0, 255, 0));
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        panel_2_1.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Récupérer les informations saisies
                String username = textField.getText();
                String password = new String(passwordField.getPassword());
                
                // Vérifier les informations de connexion
                if (validUsername.equals(username) && validPassword.equals(password)) {
                    // Informations de connexion valides, ouvrir la fenêtre Admin
                    Admin adminWindow = new Admin();
                    adminWindow.getFrame().setVisible(true);
                    // Masquer la fenêtre actuelle
                    frame.setVisible(false);
                } 
                
                
                else if (validUsername1.equals(username) && validPassword1.equals(password)) {
                	 // Informations de connexion valides, ouvrir la fenêtre Admin_client
                    Admin_Client adminClientWindow = new Admin_Client();
                    adminClientWindow.getFrame().setVisible(true);
                    // Masquer la fenêtre actuelle
                    frame.setVisible(false);
                }
                
                else {
                    // Informations de connexion invalides, afficher un message d'erreur
                    JOptionPane.showMessageDialog(frame, "Nom d'utilisateur ou mot de passe incorrect", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public JFrame getFrame() {
        return frame;
    }
}
