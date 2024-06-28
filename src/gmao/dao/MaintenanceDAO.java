package gmao.dao;

import gmao.model.Maintenance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceDAO {
    private Connection connection;

    public MaintenanceDAO(Connection connection) {
        this.connection = connection;
    }

    // Method to add a new maintenance record to the database
    public void addMaintenance(Maintenance maintenance) throws SQLException {
        String sql = "INSERT INTO maintenance (id_client, id_ticket, type_maintenance, date_demande, statut_maintenance, description_maintenance) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, maintenance.getIdClient());
            stmt.setInt(2, maintenance.getIdTicket());
            stmt.setString(3, maintenance.getTypeMaintenance());
            stmt.setDate(4, new java.sql.Date(maintenance.getDateDemande().getTime()));
            stmt.setString(5, maintenance.getStatutMaintenance());
            stmt.setString(6, maintenance.getDescriptionMaintenance());
            stmt.executeUpdate();
            
            // Get the generated key
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    maintenance.setIdMaintenance(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating maintenance failed, no ID obtained.");
                }
            }
        }
    }

    public void createMaintenance(Maintenance maintenance) throws SQLException {
        String sql = "INSERT INTO maintenance (id_client, id_ticket, type_maintenance, date_demande, statut_maintenance, description_maintenance) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, maintenance.getIdClient());
            stmt.setInt(2, maintenance.getIdTicket());
            stmt.setString(3, maintenance.getTypeMaintenance());
            stmt.setDate(4, new java.sql.Date(maintenance.getDateDemande().getTime()));
            stmt.setString(5, maintenance.getStatutMaintenance());
            stmt.setString(6, maintenance.getDescriptionMaintenance());
            stmt.executeUpdate();
        }
    }

    public Maintenance getMaintenance(int id) throws SQLException {
        String sql = "SELECT * FROM maintenance WHERE id_maintenance = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToMaintenance(rs);
                }
            }
        }
        return null;
    }

    public void updateMaintenance(Maintenance maintenance) throws SQLException {
        String sql = "UPDATE maintenance SET id_client = ?, id_ticket = ?, type_maintenance = ?, date_demande = ?, statut_maintenance = ?, description_maintenance = ? WHERE id_maintenance = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, maintenance.getIdClient());
            stmt.setInt(2, maintenance.getIdTicket());
            stmt.setString(3, maintenance.getTypeMaintenance());
            stmt.setDate(4, new java.sql.Date(maintenance.getDateDemande().getTime()));
            stmt.setString(5, maintenance.getStatutMaintenance());
            stmt.setString(6, maintenance.getDescriptionMaintenance());
            stmt.setInt(7, maintenance.getIdMaintenance());
            stmt.executeUpdate();
        }
    }

    public void deleteMaintenance(int id) throws SQLException {
        String sql = "DELETE FROM maintenance WHERE id_maintenance = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Maintenance> getAllMaintenances() throws SQLException {
        List<Maintenance> maintenances = new ArrayList<>();
        String sql = "SELECT * FROM maintenance";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                maintenances.add(mapRowToMaintenance(rs));
            }
        }
        return maintenances;
    }

    private Maintenance mapRowToMaintenance(ResultSet rs) throws SQLException {
        Maintenance maintenance = new Maintenance();
        maintenance.setIdMaintenance(rs.getInt("id_maintenance"));
        maintenance.setIdClient(rs.getInt("id_client"));
        maintenance.setIdTicket(rs.getInt("id_ticket"));
        maintenance.setTypeMaintenance(rs.getString("type_maintenance"));
        maintenance.setDateDemande(rs.getDate("date_demande"));
        maintenance.setStatutMaintenance(rs.getString("statut_maintenance"));
        maintenance.setDescriptionMaintenance(rs.getString("description_maintenance"));
        return maintenance;
    }
}
