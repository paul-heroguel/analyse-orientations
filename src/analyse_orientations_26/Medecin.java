package analyse_orientations_26;

public class Medecin {

    private String identifiant;
    private String nom;
    private String specialite;
    private int age;

    public Medecin(String identifiant, String nom, String specialite, int age) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.specialite = specialite;
        this.age = age;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public String getNom() {
        return nom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public int getAge() {
        return age;
    }

    public boolean egal(Medecin medecin) {
        if (medecin == null)
            return false;
        return identifiant.equals(medecin.identifiant);
    }

    public void afficher() {
        System.out.println(identifiant + " - " + nom + " - " + specialite + " - " + age + " ans");
    }
}
