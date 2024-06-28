package gmao.model;

import java.util.Date;

public class Ticket {
    private int idTicket;
    private Date dateOuverture;
    private Date dateFermeture;
    private String compteRendu;
    private String statutTicket;
    private String description;

    // Getters and Setters
    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public Date getDateOuverture() {
        return dateOuverture;
    }

    public void setDateOuverture(Date dateOuverture) {
        this.dateOuverture = dateOuverture;
    }

    public Date getDateFermeture() {
        return dateFermeture;
    }

    public void setDateFermeture(Date dateFermeture) {
        this.dateFermeture = dateFermeture;
    }

    public String getCompteRendu() {
        return compteRendu;
    }

    public void setCompteRendu(String compteRendu) {
        this.compteRendu = compteRendu;
    }

    public String getStatutTicket() {
        return statutTicket;
    }

    public void setStatutTicket(String statutTicket) {
        this.statutTicket = statutTicket;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
