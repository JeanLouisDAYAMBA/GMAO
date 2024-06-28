package gmao.dao;

import gmao.model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    private Connection connection;

    public ClientDAO(Connection connection) {
        this.connection = connection;
    }

    public void addClient(Client client) throws SQLException {
        String clientQuery = "INSERT INTO client (nom, numero_ifu, numero_rccm, adresse, code_ape) VALUES (?, ?, ?, ?, ?)";
        System.out.println("Preparing to execute query: " + clientQuery);
        try (PreparedStatement preparedStatement = connection.prepareStatement(clientQuery, new String[] {"id_client"})) {
            preparedStatement.setString(1, client.getNom());
            preparedStatement.setString(2, client.getNumeroIfu());
            preparedStatement.setString(3, client.getNumeroRccm());
            preparedStatement.setString(4, client.getAdresse());
            preparedStatement.setString(5, client.getCodeApe());

            System.out.println("Executing update...");
            preparedStatement.executeUpdate();

            System.out.println("Fetching generated keys...");
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1); // Utilisation de getInt() pour obtenir l'ID généré
                    client.setIdClient(id);
                    System.out.println("Generated ID: " + client.getIdClient());

                    // Create user in the database using numero_ifu as username and nom as password
                   //addUser(client.getNumeroIfu(), client.getNom());
                } else {
                    throw new SQLException("La création du client a échoué, aucun ID généré.");
                }
            }
        }
    }

    private void addUser(String username, String password) throws SQLException {
        // Ensure to have the necessary privileges to create a new user
    	
        String userQuery = "CREATE USER " + username + " IDENTIFIED BY " + password; 
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(userQuery);
        }
        
        

        // Optionally, grant privileges to the new user
        String grantQuery = "GRANT CONNECT TO " + username;
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(grantQuery);
        }
    }

    private void deleteUser(String username) throws SQLException {
        // Ensure to have the necessary privileges to drop a user
        String userQuery = "DROP USER " + username + " CASCADE";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(userQuery);
        }
    }

    public void deleteClient(int clientId) throws SQLException {
        // Fetch the client before deletion to get the numero_ifu
        Client client = getClientById(clientId);
        if (client == null) {
            throw new SQLException("Client not found with ID: " + clientId);
        }

        // Delete the user associated with the client's numero_ifu
       // deleteUser(client.getNumeroIfu());

        // Delete the client
        String query = "DELETE FROM client WHERE id_client = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, clientId);
            preparedStatement.executeUpdate();
        }
    }

    public List<Client> getAllClients() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                clients.add(mapRowToClient(rs));
            }
        }
        return clients;
    }

    public void updateClient(Client client) throws SQLException {
        String query = "UPDATE client SET nom = ?, numero_ifu = ?, numero_rccm = ?, adresse = ?, code_ape = ? WHERE id_client = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, client.getNom());
            preparedStatement.setString(2, client.getNumeroIfu());
            preparedStatement.setString(3, client.getNumeroRccm());
            preparedStatement.setString(4, client.getAdresse());
            preparedStatement.setString(5, client.getCodeApe());
            preparedStatement.setInt(6, client.getIdClient());
            preparedStatement.executeUpdate();
        }
    }

    public Client getClientById(int clientId) throws SQLException {
        String query = "SELECT * FROM client WHERE id_client = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, clientId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Client client = new Client();
                    client.setIdClient(resultSet.getInt("id_client"));
                    client.setNom(resultSet.getString("nom"));
                    client.setNumeroIfu(resultSet.getString("numero_ifu"));
                    client.setNumeroRccm(resultSet.getString("numero_rccm"));
                    client.setAdresse(resultSet.getString("adresse"));
                    client.setCodeApe(resultSet.getString("code_ape"));
                    return client;
                }
            }
        }
        return null; // or throw an exception if the client is not found
    }

    private Client mapRowToClient(ResultSet rs) throws SQLException {
        Client client = new Client();
        client.setIdClient(rs.getInt("id_client"));
        client.setNom(rs.getString("nom"));
        client.setNumeroIfu(rs.getString("numero_ifu"));
        client.setNumeroRccm(rs.getString("numero_rccm"));
        client.setAdresse(rs.getString("adresse"));
        client.setCodeApe(rs.getString("code_ape"));
        return client;
    }
}
