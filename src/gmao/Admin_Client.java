package gmao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import gmao.dao.ClientDAO;
import gmao.dao.MaintenanceDAO;
import gmao.dao.OperateurDAO;
import gmao.dao.TicketDAO;
import gmao.model.Client;
import gmao.model.Maintenance;
import gmao.model.Operateur;
import gmao.model.Ticket;
import gmao.util.DBConnection;

public class Admin_Client {

    private JFrame frame;
    private JPanel contentPanel; // Panel to hold the content views
    private CardLayout cardLayout; // CardLayout for the content panel

    public Admin_Client() {
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
/*
        JButton btnClient = new JButton("Client");
        btnClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showClientPanel();
            }
        });
        menuPanel.add(btnClient);
        */
        
        
        JButton btnTicket = new JButton("Ticket");
        btnTicket.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	showTicketPanel();
            }
        });
        menuPanel.add(btnTicket);
        
        
        
        JButton btnOperateur = new JButton("Operateur");
        btnOperateur.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	showOperateurPanel();
            }
        });
        menuPanel.add(btnOperateur);
        
        JButton btnMaintenance = new JButton("Maintenance");
        btnMaintenance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	showMaintenancePanel();
            }
        });
        menuPanel.add(btnMaintenance);
        
        
        

        JButton btnRapport = new JButton("Rapport");
        btnRapport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	showRapportPanel();
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

    
    
    
    
    private void showRapportPanel() {
        JPanel RapportPanel = new JPanel(new BorderLayout());

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

        RapportPanel.add(menuPanel, BorderLayout.WEST);
        contentPanel.add(RapportPanel, "Rapport");
        cardLayout.show(contentPanel, "Rapport");
    }
    
    
    
    
    private void showMaintenancePanel() {
        JPanel MaintenancePanel = new JPanel(new BorderLayout());

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(2, 1, 5, 5));

        JButton btnNew = new JButton("Nouveau");
        btnNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMaintenanceNouveauForm();
            }
        });
        menuPanel.add(btnNew);

        JButton btnList = new JButton("Liste");
        btnList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMaintenanceList();
            }
        });
        menuPanel.add(btnList);

        MaintenancePanel.add(menuPanel, BorderLayout.WEST);
        contentPanel.add(MaintenancePanel, "Maintenance");
        cardLayout.show(contentPanel, "Maintenance");
    }   
    
    
    
    
    
    
    private void showOperateurPanel() {
        JPanel OperateurPanel = new JPanel(new BorderLayout());

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(2, 1, 5, 5));

        JButton btnNew = new JButton("Nouveau");
        btnNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showOperateurNouveauForm();
            }
        });
        menuPanel.add(btnNew);

        JButton btnList = new JButton("Liste");
        btnList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showOperateurList();
            }
        });
        menuPanel.add(btnList);

        OperateurPanel.add(menuPanel, BorderLayout.WEST);
        contentPanel.add(OperateurPanel, "Operateur");
        cardLayout.show(contentPanel, "Operateur");
    }
    
    
    
    
    
    
    
    
    
    
    
    private void showTicketPanel() {
        JPanel TicketPanel = new JPanel(new BorderLayout());

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(2, 1, 5, 5));

        JButton btnNew = new JButton("Nouveau");
        btnNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showTicketNouveauForm();
            }
        });
        menuPanel.add(btnNew);

        JButton btnList = new JButton("Liste");
        btnList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showTicketList();
            }
        });
        menuPanel.add(btnList);

        TicketPanel.add(menuPanel, BorderLayout.WEST);
        contentPanel.add(TicketPanel, "Ticket");
        cardLayout.show(contentPanel, "Ticket");
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
    
    
      private void showOperateurNouveauForm() {
        showForm("OperateurNouveauForm", new String[]{"Nom", "Prenom", "Adresse", "Qualification", "Code_OPE"}, (fields) -> {
            try (Connection connection = DBConnection.getConnection()) {
                OperateurDAO operateurDAO = new OperateurDAO(connection);
                Operateur operateur = new Operateur();
                operateur.setNom(fields[0].getText());
                operateur.setPrenom(fields[1].getText());
                operateur.setAdresse(fields[2].getText());
                operateur.setSpecialite(fields[3].getText());
                operateur.setCodeOpe(fields[4].getText());
                operateurDAO.addOperateur(operateur);
                JOptionPane.showMessageDialog(frame, "Opérateur ajouté avec succès.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Erreur lors de l'ajout de l'opérateur.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void showMaintenanceNouveauForm() {
        showForm("MaintenanceNouveauForm", new String[]{"ID Client", "ID Ticket", "Type Maintenance", "Date Demande", "Statut Maintenance", "Description Maintenance"}, (fields) -> {
            try (Connection connection = DBConnection.getConnection()) {
                MaintenanceDAO maintenanceDAO = new MaintenanceDAO(connection);
                Maintenance maintenance = new Maintenance();
                maintenance.setIdClient(Integer.parseInt(fields[0].getText()));
                maintenance.setIdTicket(Integer.parseInt(fields[1].getText()));
                maintenance.setTypeMaintenance(fields[2].getText());
                maintenance.setDateDemande(new Date()); // Assuming the form has a Date Picker component for selecting dates
                maintenance.setStatutMaintenance(fields[4].getText());
                maintenance.setDescriptionMaintenance(fields[5].getText());
                maintenanceDAO.addMaintenance(maintenance);
                JOptionPane.showMessageDialog(frame, "Maintenance ajoutée avec succès.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Erreur lors de l'ajout de la maintenance.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void showTicketNouveauForm() {
        showForm("TicketNouveauForm", new String[]{"Date Ouverture", "Date Fermeture", "Compte Rendu", "Statut Ticket", "Description"}, (fields) -> {
            try (Connection connection = DBConnection.getConnection()) {
                TicketDAO ticketDAO = new TicketDAO(connection);
                Ticket ticket = new Ticket();
                ticket.setDateOuverture(new Date()); // Assuming the form has a Date Picker component for selecting dates
                ticket.setDateFermeture(new Date()); // Assuming the form has a Date Picker component for selecting dates
                ticket.setCompteRendu(fields[2].getText());
                ticket.setStatutTicket(fields[3].getText());
                ticket.setDescription(fields[4].getText());
                ticketDAO.addTicket(ticket);
                JOptionPane.showMessageDialog(frame, "Ticket ajouté avec succès.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Erreur lors de l'ajout du ticket.", "Erreur", JOptionPane.ERROR_MESSAGE);
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
        showList("ClientList", new String[]{"Sélectionner", "Nom", "Numero IFU", "Numero RCCM"}, (model) -> {
            try (Connection connection = DBConnection.getConnection()) {
                ClientDAO clientDAO = new ClientDAO(connection);
                java.util.List<Client> clients = clientDAO.getAllClients();
                for (Client client : clients) {
                    model.addRow(new Object[]{false, client.getNom(), client.getNumeroIfu(), client.getNumeroRccm()});
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Erreur lors de la récupération des clients.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    
    
      private void showOperateurList() {
        showList("OperateurList", new String[]{"Sélectionner", "Nom", "Prenom", "Specialite", "Code_OPE"}, (model) -> {
            try (Connection connection = DBConnection.getConnection()) {
                OperateurDAO operateurDAO = new OperateurDAO(connection);
                java.util.List<Operateur> operateurs = operateurDAO.getAllOperateurs();
                for (Operateur operateur : operateurs) {
                    model.addRow(new Object[]{false, operateur.getNom(), operateur.getPrenom(), operateur.getSpecialite(), operateur.getCodeOpe()});
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Erreur lors de la récupération des opérateurs.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void showMaintenanceList() {
        showList("MaintenanceList", new String[]{"Sélectionner", "ID Maintenance", "ID Client", "ID Ticket", "Type Maintenance", "Date Demande", "Statut Maintenance", "Description Maintenance"}, (model) -> {
            try (Connection connection = DBConnection.getConnection()) {
                MaintenanceDAO maintenanceDAO = new MaintenanceDAO(connection);
                java.util.List<Maintenance> maintenances = maintenanceDAO.getAllMaintenances();
                for (Maintenance maintenance : maintenances) {
                    model.addRow(new Object[]{false, maintenance.getIdMaintenance(), maintenance.getIdClient(), maintenance.getIdTicket(), maintenance.getTypeMaintenance(), maintenance.getDateDemande(), maintenance.getStatutMaintenance(), maintenance.getDescriptionMaintenance()});
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Erreur lors de la récupération des maintenances.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void showTicketList() {
        showList("TicketList", new String[]{"Sélectionner", "ID Ticket", "Date Ouverture", "Date Fermeture", "Compte Rendu", "Statut Ticket", "Description"}, (model) -> {
            try (Connection connection = DBConnection.getConnection()) {
                TicketDAO ticketDAO = new TicketDAO(connection);
                java.util.List<Ticket> tickets = ticketDAO.getAllTickets();
                for (Ticket ticket : tickets) {
                    model.addRow(new Object[]{false, ticket.getIdTicket(), ticket.getDateOuverture(), ticket.getDateFermeture(), ticket.getCompteRendu(), ticket.getStatutTicket(), ticket.getDescription()});
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Erreur lors de la récupération des tickets.", "Erreur", JOptionPane.ERROR_MESSAGE);
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
        buttonPanel.add(btnModifier);

        JButton btnSupprimer = new JButton("Supprimer");
        buttonPanel.add(btnSupprimer);

        listPanel.add(scrollPane, BorderLayout.CENTER);
        listPanel.add(buttonPanel, BorderLayout.SOUTH);

        contentPanel.add(listPanel, cardName);
        cardLayout.show(contentPanel, cardName);
    }

    public JFrame getFrame() {
        return frame;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Admin_Client window = new Admin_Client();
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
