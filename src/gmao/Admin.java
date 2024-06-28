package gmao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import gmao.dao.ClientDAO;
import gmao.model.Client;
import gmao.util.DBConnection;

public class Admin {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JPanel contentPanel; // Panel to hold the content views
    private CardLayout cardLayout; // CardLayout for the content panel

    public Admin() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 711, 432);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel menuPanel = new JPanel();
        menuPanel.setBounds(10, 51, 116, 233);
        frame.getContentPane().add(menuPanel);
        menuPanel.setLayout(new GridLayout(6, 1, 5, 5));

        JLabel lblMenu = new JLabel("    MENU     ");
        lblMenu.setFont(new Font("Tahoma", Font.BOLD, 20));
        menuPanel.add(lblMenu);

        JButton btnClient = new JButton("Client");
        btnClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showClientPanel();
            }
        });
        menuPanel.add(btnClient);

        // Retirer les boutons "Maintenance", "Operateur" et "Ticket" ainsi que leurs actions
        /*
        JButton btnMaintenance = new JButton("Maintenance");
        btnMaintenance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMaintenancePanel();
            }
        });
        menuPanel.add(btnMaintenance);

        JButton btnOperateur = new JButton("Operateur");
        btnOperateur.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showOperateurPanel();
            }
        });
        menuPanel.add(btnOperateur);

        JButton btnTicket = new JButton("Ticket");
        btnTicket.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showTicketPanel();
            }
        });
        menuPanel.add(btnTicket);
        */

        JButton btnRapport = new JButton("Rapport");
        btnRapport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAccessDeniedMessage();
            }
        });
        menuPanel.add(btnRapport);

        JButton btnDeconnexion = new JButton("Deconnexion");
        btnDeconnexion.setBackground(new Color(255, 0, 0));
        btnDeconnexion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                Acceuil acceuilWindow = new Acceuil();
                acceuilWindow.getFrame().setVisible(true);
            }
        });

        JPanel deconnexionPanel = new JPanel();
        deconnexionPanel.setBounds(10, 300, 120, 50);
        deconnexionPanel.add(btnDeconnexion);
        frame.getContentPane().add(deconnexionPanel);

        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setBounds(136, 50, 527, 291);
        contentPanel.setLayout(cardLayout);
        frame.getContentPane().add(contentPanel);
    }

    private void showAccessDeniedMessage() {
        JOptionPane.showMessageDialog(frame, "Vous n'avez pas accès à ces options.", "Accès refusé", JOptionPane.WARNING_MESSAGE);
    }

    private void showClientPanel() {
        JPanel clientPanel = new JPanel(new BorderLayout());

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(2, 1, 5, 5));

        JButton btnNew = new JButton("Nouveau");
        btnNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showClientNouveauForm();
            }
        });
        menuPanel.add(btnNew);

        JButton btnList = new JButton("Liste");
        btnList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showClientList();
            }
        });
        menuPanel.add(btnList);

        clientPanel.add(menuPanel, BorderLayout.WEST);
        contentPanel.add(clientPanel, "Client");
        cardLayout.show(contentPanel, "Client");
    }

    private void showClientNouveauForm() {
        showForm("ClientNouveauForm", new String[]{"Nom", "Numero IFU", "Numero RCCM", "Adresse", "Code APE"}, (fields) -> {
            try (Connection connection = DBConnection.getConnection()) {
                ClientDAO clientDAO = new ClientDAO(connection);
                Client client = new Client();
                client.setNom(fields[0].getText());
                client.setNumeroIfu(fields[1].getText());
                client.setNumeroRccm(fields[2].getText());
                client.setAdresse(fields[3].getText());
                client.setCodeApe(fields[4].getText());
                clientDAO.addClient(client);
                JOptionPane.showMessageDialog(frame, "Client ajouté avec succès.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Erreur lors de l'ajout du client.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    
    

    private void showForm(String cardName, String[] labels, FormSubmitListener submitListener) {
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField[] textFields = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            formPanel.add(new JLabel(labels[i] + ":"), gbc);

            gbc.gridx = 1;
            textFields[i] = new JTextField(20);
            formPanel.add(textFields[i], gbc);
        }

        gbc.gridx = 1;
        gbc.gridy = labels.length;
        gbc.anchor = GridBagConstraints.EAST;
        JButton btnEnregistrer = new JButton("Enregistrer");
        btnEnregistrer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitListener.onSubmit(textFields);
            }
        });
        formPanel.add(btnEnregistrer, gbc);

        contentPanel.add(formPanel, cardName);
        cardLayout.show(contentPanel, cardName);
    }

  

    
    
    
    
    
    
    
    
    private void showClientList() {
        showList("ClientList", new String[]{"Sélectionner", "Nom", "Numero IFU", "Numero RCCM", "ID"}, (model) -> {
            try (Connection connection = DBConnection.getConnection()) {
                ClientDAO clientDAO = new ClientDAO(connection);
                java.util.List<Client> clients = clientDAO.getAllClients();
                for (Client client : clients) {
                    model.addRow(new Object[]{false, client.getNom(), client.getNumeroIfu(), client.getNumeroRccm(), client.getIdClient()});
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Erreur lors de la récupération des clients.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void showList(String cardName, String[] columnNames, ListDataProvider dataProvider) {
        JPanel listPanel = new JPanel(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Boolean.class;
                }
                return String.class;
            }
        };

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        dataProvider.provideData(model);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton btnModifier = new JButton("Modifier");
        btnModifier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int clientId = (int) model.getValueAt(selectedRow, 4);
                    showClientUpdateForm(clientId);
                } else {
                    JOptionPane.showMessageDialog(frame, "Veuillez sélectionner un client à modifier.");
                }
            }
        });
        buttonPanel.add(btnModifier);

        JButton btnSupprimer = new JButton("Supprimer");
        btnSupprimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int clientId = (int) model.getValueAt(selectedRow, 4);
                    try (Connection connection = DBConnection.getConnection()) {
                        ClientDAO clientDAO = new ClientDAO(connection);
                        clientDAO.deleteClient(clientId);
                        model.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(frame, "Client supprimé avec succès.");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Erreur lors de la suppression du client.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Veuillez sélectionner un client à supprimer.");
                }
            }
        });
        buttonPanel.add(btnSupprimer);

        listPanel.add(scrollPane, BorderLayout.CENTER);
        listPanel.add(buttonPanel, BorderLayout.SOUTH);

        contentPanel.add(listPanel, cardName);
        cardLayout.show(contentPanel, cardName);
    }

    private void showClientUpdateForm(int clientId) {
        showForm("ClientUpdateForm", new String[]{"Nom", "Numero IFU", "Numero RCCM", "Adresse", "Code APE"}, (fields) -> {
            try (Connection connection = DBConnection.getConnection()) {
                ClientDAO clientDAO = new ClientDAO(connection);
                Client client = new Client();
                client.setIdClient(clientId);
                client.setNom(fields[0].getText());
                client.setNumeroIfu(fields[1].getText());
                client.setNumeroRccm(fields[2].getText());
                client.setAdresse(fields[3].getText());
                client.setCodeApe(fields[4].getText());
                clientDAO.updateClient(client);
                JOptionPane.showMessageDialog(frame, "Client mis à jour avec succès.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Erreur lors de la mise à jour du client.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
    }


    public JFrame getFrame() {
        return frame;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Admin window = new Admin();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private interface FormSubmitListener {
        void onSubmit(JTextField[] fields);
    }

    private interface ListDataProvider {
        void provideData(DefaultTableModel model);
    }
}
