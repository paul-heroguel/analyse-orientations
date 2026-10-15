package analyse_orientations_26;

public class Orientation {

    private Medecin source;
    private Medecin destination;
    private int nbPatients;

    public Orientation(Medecin source, Medecin destination, int nbPatients) {
        this.source = source;
        this.destination = destination;
        this.nbPatients = nbPatients;
    }

    public Medecin getSource() {
        return source;
    }

    public Medecin getDestination() {
        return destination;
    }

    public int getNbPatients() {
        return nbPatients;
    }

    public void afficher() {
        System.out.println(source.getIdentifiant() + " (" + source.getNom() + ") -> "
                + destination.getIdentifiant() + " (" + destination.getNom() + ") : "
                + nbPatients + " patient(s)");
    }
}
