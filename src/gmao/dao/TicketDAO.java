package gmao.dao;

import gmao.model.Ticket;
import oracle.jdbc.OraclePreparedStatement;
import oracle.sql.CLOB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
    private Connection connection;

    public TicketDAO(Connection connection) {
        this.connection = connection;
    }

    public void addTicket(Ticket ticket) throws SQLException {
        String sql = "INSERT INTO ticket (id_ticket, date_ouverture, date_fermeture, compte_rendu, statut_ticket, description) VALUES (ticket_seq.NEXTVAL, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, new String[]{"id_ticket"})) {
            stmt.setDate(1, new java.sql.Date(ticket.getDateOuverture().getTime()));
            stmt.setDate(2, ticket.getDateFermeture() != null ? new java.sql.Date(ticket.getDateFermeture().getTime()) : null);

            // Create and set CLOB for compte_rendu
            CLOB compteRenduClob = CLOB.createTemporary(connection, false, CLOB.DURATION_SESSION);
            compteRenduClob.setString(1, ticket.getCompteRendu());
            ((OraclePreparedStatement) stmt).setCLOB(3, compteRenduClob);

            stmt.setString(4, ticket.getStatutTicket());

            // Create and set CLOB for description
            CLOB descriptionClob = CLOB.createTemporary(connection, false, CLOB.DURATION_SESSION);
            descriptionClob.setString(1, ticket.getDescription());
            ((OraclePreparedStatement) stmt).setCLOB(5, descriptionClob);

            stmt.executeUpdate();

            // Get the generated key
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    ticket.setIdTicket(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating ticket failed, no ID obtained.");
                }
            }

            // Free temporary CLOBs
            CLOB.freeTemporary(compteRenduClob);
            CLOB.freeTemporary(descriptionClob);
        }
    }

    public List<Ticket> getAllTickets() throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM ticket";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tickets.add(mapRowToTicket(rs));
            }
        }
        return tickets;
    }

    private Ticket mapRowToTicket(ResultSet rs) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setIdTicket(rs.getInt("id_ticket"));
        ticket.setDateOuverture(rs.getDate("date_ouverture"));
        ticket.setDateFermeture(rs.getDate("date_fermeture"));
        ticket.setCompteRendu(rs.getString("compte_rendu"));
        ticket.setStatutTicket(rs.getString("statut_ticket"));
        ticket.setDescription(rs.getString("description"));
        return ticket;
    }
    public void updateTicket(Ticket ticket) throws SQLException {
        String sql = "UPDATE ticket SET date_ouverture = ?, date_fermeture = ?, compte_rendu = ?, statut_ticket = ?, description = ? WHERE id_ticket = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(ticket.getDateOuverture().getTime()));
            stmt.setDate(2, new java.sql.Date(ticket.getDateFermeture().getTime()));
            stmt.setString(3, ticket.getCompteRendu());
            stmt.setString(4, ticket.getStatutTicket());
            stmt.setString(5, ticket.getDescription());
            stmt.setInt(6, ticket.getIdTicket());
            stmt.executeUpdate();
        }
    }

    public void deleteTicket(int id) throws SQLException {
        String sql = "DELETE FROM ticket WHERE id_ticket = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

}