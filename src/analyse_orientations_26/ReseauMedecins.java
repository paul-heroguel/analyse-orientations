package analyse_orientations_26;

import java.io.IOException;

public class ReseauMedecins {

    public static final int MAX_MEDECINS = 200;
    public static final int MAX_ORIENTATIONS = 1000;

    private Medecin[] medecins;
    private Orientation[] orientations;
    private int nbMedecins;
    private int nbOrientations;

    public ReseauMedecins() {
        medecins = new Medecin[MAX_MEDECINS];
        orientations = new Orientation[MAX_ORIENTATIONS];
        nbMedecins = 0;
        nbOrientations = 0;
    }

    public int getNbMedecins() {
        return nbMedecins;
    }

    public int getNbOrientations() {
        return nbOrientations;
    }

    public void chargerDonnees(String lettre) {
        nbMedecins = 0;
        nbOrientations = 0;

        try {
            chargerMedecins("medecins_" + lettre + ".csv");
            chargerOrientations("orientations_" + lettre + ".csv");
            System.out.println("Jeu de données " + lettre + " chargé.");
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement : " + e.getMessage());
        }
    }

    private void chargerMedecins(String nomFichier) throws IOException {
        String[][] donnees = LecteurCSV.lireCSV(nomFichier);

        for (int i = 1; i < donnees.length; i++) {
            if (donnees[i].length >= 4 && nbMedecins < MAX_MEDECINS) {
                String identifiant = donnees[i][0];
                String nom = donnees[i][1];
                String specialite = donnees[i][2];
                int age = Integer.parseInt(donnees[i][3]);

                medecins[nbMedecins] = new Medecin(identifiant, nom, specialite, age);
                nbMedecins++;
            }
        }
    }

    private void chargerOrientations(String nomFichier) throws IOException {
        String[][] donnees = LecteurCSV.lireCSV(nomFichier);

        for (int i = 1; i < donnees.length; i++) {
            if (donnees[i].length >= 3 && nbOrientations < MAX_ORIENTATIONS) {
                Medecin source = rechercherMedecin(donnees[i][0]);
                Medecin destination = rechercherMedecin(donnees[i][1]);
                int nbPatients = Integer.parseInt(donnees[i][2]);

                if (source != null && destination != null) {
                    orientations[nbOrientations] = new Orientation(source, destination, nbPatients);
                    nbOrientations++;
                }
            }
        }
    }

    public void afficherMedecins() {
        if (nbMedecins == 0) {
            System.out.println("Aucun médecin chargé.");
        } else {
            for (int i = 0; i < nbMedecins; i++)
                medecins[i].afficher();
        }
    }

    public void afficherOrientations() {
        if (nbOrientations == 0) {
            System.out.println("Aucune orientation chargée.");
        } else {
            for (int i = 0; i < nbOrientations; i++)
                orientations[i].afficher();
        }
    }

    public Medecin rechercherMedecin(String identifiant) {
        int i = 0;
        while (i < nbMedecins) {
            if (medecins[i].getIdentifiant().equals(identifiant))
                return medecins[i];
            i++;
        }
        return null;
    }

    public int nbTotalPatientsOrientes() {
        int total = 0;

        for (int i = 0; i < nbOrientations; i++)
            total = total + orientations[i].getNbPatients();

        return total;
    }

    public int nbPatientsOrientesPar(Medecin medecin) {
        int total = 0;

        for (int i = 0; i < nbOrientations; i++)
            if (orientations[i].getSource().egal(medecin))
                total = total + orientations[i].getNbPatients();

        return total;
    }

    public int nbPatientsRecusPar(Medecin medecin) {
        int total = 0;

        for (int i = 0; i < nbOrientations; i++)
            if (orientations[i].getDestination().egal(medecin))
                total = total + orientations[i].getNbPatients();

        return total;
    }

    public Medecin medecinAyantOrienteLePlus() {
        if (nbMedecins == 0)
            return null;

        Medecin meilleur = medecins[0];
        int max = nbPatientsOrientesPar(meilleur);

        for (int i = 1; i < nbMedecins; i++) {
            int total = nbPatientsOrientesPar(medecins[i]);
            if (total > max) {
                max = total;
                meilleur = medecins[i];
            }
        }

        return meilleur;
    }

    public Medecin medecinAyantRecuLePlus() {
        if (nbMedecins == 0)
            return null;

        Medecin meilleur = medecins[0];
        int max = nbPatientsRecusPar(meilleur);

        for (int i = 1; i < nbMedecins; i++) {
            int total = nbPatientsRecusPar(medecins[i]);
            if (total > max) {
                max = total;
                meilleur = medecins[i];
            }
        }

        return meilleur;
    }

    public void afficherDestinations(String identifiant) {
        Medecin medecin = rechercherMedecin(identifiant);
        boolean trouve = false;

        if (medecin == null) {
            System.out.println("Médecin introuvable.");
            return;
        }

        for (int i = 0; i < nbOrientations; i++) {
            if (orientations[i].getSource().egal(medecin)) {
                orientations[i].getDestination().afficher();
                trouve = true;
            }
        }

        if (!trouve)
            System.out.println("Aucun médecin trouvé.");
    }

    public void afficherSources(String identifiant) {
        Medecin medecin = rechercherMedecin(identifiant);
        boolean trouve = false;

        if (medecin == null) {
            System.out.println("Médecin introuvable.");
            return;
        }

        for (int i = 0; i < nbOrientations; i++) {
            if (orientations[i].getDestination().egal(medecin)) {
                orientations[i].getSource().afficher();
                trouve = true;
            }
        }

        if (!trouve)
            System.out.println("Aucun médecin trouvé.");
    }

    private int indiceMedecin(Medecin medecin) {
        for (int i = 0; i < nbMedecins; i++)
            if (medecins[i].egal(medecin))
                return i;

        return -1;
    }

    private boolean estVoisin(Medecin source, Medecin destination) {
        for (int i = 0; i < nbOrientations; i++)
            if (orientations[i].getSource().egal(source)
                    && orientations[i].getDestination().egal(destination))
                return true;

        return false;
    }

    public boolean existeChemin(String idSource, String idDestination) {
        Medecin source = rechercherMedecin(idSource);
        Medecin destination = rechercherMedecin(idDestination);

        if (source == null || destination == null)
            return false;

        int[] predecesseurs = parcoursLargeur(source);
        int indiceDestination = indiceMedecin(destination);

        return predecesseurs[indiceDestination] != -1 || source.egal(destination);
    }

    public void afficherChemin(String idSource, String idDestination) {
        Medecin source = rechercherMedecin(idSource);
        Medecin destination = rechercherMedecin(idDestination);

        if (source == null || destination == null) {
            System.out.println("Médecin introuvable.");
            return;
        }

        int[] predecesseurs = parcoursLargeur(source);
        int indiceSource = indiceMedecin(source);
        int indiceDestination = indiceMedecin(destination);

        if (indiceSource != indiceDestination && predecesseurs[indiceDestination] == -1) {
            System.out.println("Aucun chemin trouvé.");
            return;
        }

        int[] chemin = new int[nbMedecins];
        int nb = 0;
        int courant = indiceDestination;

        while (courant != -1) {
            chemin[nb] = courant;
            nb++;
            courant = predecesseurs[courant];
        }

        for (int i = nb - 1; i >= 0; i--) {
            System.out.print(medecins[chemin[i]].getIdentifiant());
            if (i != 0)
                System.out.print(" -> ");
        }
        System.out.println();
    }

    private int[] parcoursLargeur(Medecin source) {
        boolean[] visites = new boolean[nbMedecins];
        int[] predecesseurs = new int[nbMedecins];
        int[] file = new int[nbMedecins];
        int debut = 0;
        int fin = 0;

        for (int i = 0; i < nbMedecins; i++)
            predecesseurs[i] = -1;

        int indiceSource = indiceMedecin(source);
        visites[indiceSource] = true;
        file[fin] = indiceSource;
        fin++;

        while (debut < fin) {
            int indice = file[debut];
            debut++;

            for (int i = 0; i < nbMedecins; i++) {
                if (!visites[i] && estVoisin(medecins[indice], medecins[i])) {
                    visites[i] = true;
                    predecesseurs[i] = indice;
                    file[fin] = i;
                    fin++;
                }
            }
        }

        return predecesseurs;
    }

    public int nbMedecinsAtteignables(String identifiant) {
        Medecin source = rechercherMedecin(identifiant);

        if (source == null)
            return -1;

        int[] predecesseurs = parcoursLargeur(source);
        int indiceSource = indiceMedecin(source);
        int total = 0;

        for (int i = 0; i < nbMedecins; i++)
            if (i != indiceSource && predecesseurs[i] != -1)
                total++;

        return total;
    }

    public void afficherMedecinsIsoles() {
        boolean trouve = false;

        for (int i = 0; i < nbMedecins; i++) {
            if (nbPatientsOrientesPar(medecins[i]) == 0 && nbPatientsRecusPar(medecins[i]) == 0) {
                medecins[i].afficher();
                trouve = true;
            }
        }

        if (!trouve)
            System.out.println("Aucun médecin isolé.");
    }
}
