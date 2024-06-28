package gmao.model;

public class Operateur {
    private int idOperateur;
    private String nom;
    private String prenom;
    private String adresse;
    
    private String codeOpe;
    private String specialite;

    // Getter and Setter for idOperateur
    public int getIdOperateur() {
        return idOperateur;
    }

    public void setIdOperateur(int idOperateur) {
        this.idOperateur = idOperateur;
    }

    // Getter and Setter for nom
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Getter and Setter for prenom
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    // Getter and Setter for adresse
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    // Getter and Setter for codeOpe
    public String getCodeOpe() {
        return codeOpe;
    }

    public void setCodeOpe(String codeOpe) {
        this.codeOpe = codeOpe;
    }

    // Getter and Setter for specialite
    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}
