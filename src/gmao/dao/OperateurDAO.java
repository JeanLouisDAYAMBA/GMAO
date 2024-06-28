package gmao.dao;

import gmao.model.Operateur;
import oracle.jdbc.OraclePreparedStatement;
import oracle.sql.CLOB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperateurDAO {
    private Connection connection;

    public OperateurDAO(Connection connection) {
        this.connection = connection;
    }

    public void addOperateur(Operateur operateur) throws SQLException {
        String sql = "INSERT INTO operateur (id_operateur, nom, prenom, adresse, code_ope, specialite) VALUES (operateur_seq.NEXTVAL, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, new String[]{"id_operateur"})) {
            stmt.setString(1, operateur.getNom());
            stmt.setString(2, operateur.getPrenom());
            stmt.setString(3, operateur.getAdresse());
            stmt.setString(4, operateur.getCodeOpe());

            // Create and set CLOB for specialite
            CLOB specialiteClob = CLOB.createTemporary(connection, false, CLOB.DURATION_SESSION);
            specialiteClob.setString(1, operateur.getSpecialite());
            ((OraclePreparedStatement) stmt).setCLOB(5, specialiteClob);

            stmt.executeUpdate();

            // Get the generated key
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    String generatedId = generatedKeys.getString(1);
                    operateur.setIdOperateur(Integer.parseInt(generatedId));
                } else {
                    throw new SQLException("Creating operateur failed, no ID obtained.");
                }
            }

            // Free temporary CLOB
            CLOB.freeTemporary(specialiteClob);
        }
    }


    public void updateOperateur(Operateur operateur) throws SQLException {
        String sql = "UPDATE operateur SET nom = ?, prenom = ?, adresse = ?, code_ope = ?, specialite = ? WHERE id_operateur = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, operateur.getNom());
            stmt.setString(2, operateur.getPrenom());
            stmt.setString(3, operateur.getAdresse());
            stmt.setString(4, operateur.getCodeOpe());
            stmt.setString(5, operateur.getSpecialite());
            stmt.setInt(6, operateur.getIdOperateur());
            stmt.executeUpdate();
        }
    }

    public Operateur getOperateur(int id) throws SQLException {
        String sql = "SELECT * FROM operateur WHERE id_operateur = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToOperateur(rs);
                }
            }
        }
        return null;
    }

    public void deleteOperateur(int id) throws SQLException {
        String sql = "DELETE FROM operateur WHERE id_operateur = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Operateur> getAllOperateurs() throws SQLException {
        List<Operateur> operateurs = new ArrayList<>();
        String sql = "SELECT * FROM operateur";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                operateurs.add(mapRowToOperateur(rs));
            }
        }
        return operateurs;
    }

    private Operateur mapRowToOperateur(ResultSet rs) throws SQLException {
        Operateur operateur = new Operateur();
        operateur.setIdOperateur(rs.getInt("id_operateur"));
        operateur.setNom(rs.getString("nom"));
        operateur.setPrenom(rs.getString("prenom"));
        operateur.setAdresse(rs.getString("adresse"));
        operateur.setCodeOpe(rs.getString("code_ope"));
        operateur.setSpecialite(rs.getString("specialite"));
        return operateur;
    }
}
