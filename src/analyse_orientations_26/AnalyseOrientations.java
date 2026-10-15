package analyse_orientations_26;

import java.util.Scanner;

public class AnalyseOrientations {

    private static final Scanner scanner = new Scanner(System.in);

    private static int saisirEntier(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print("Entrée invalide ! " + message);
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static String saisirString(String message) {
        System.out.print(message);
        String saisie;
        do {
            saisie = scanner.nextLine();
        } while (saisie.isEmpty());
        return saisie;
    }

    private static void afficherMenu() {
        System.out.println();
        System.out.println("** Analyse des orientations 2026 **");
        System.out.println();
        System.out.println(" 1  : Chargement du jeu de données A");
        System.out.println(" 2  : Chargement du jeu de données B");
        System.out.println(" 3  : Chargement du jeu de données C");
        System.out.println(" 4  : Affichage de la liste des médecins");
        System.out.println(" 5  : Affichage de la liste des orientations");
        System.out.println(" 6  : Recherche d'un médecin à partir de son identifiant");
        System.out.println(" 7  : Calcul du nombre total d'orientations enregistrées");
        System.out.println(" 8  : Détermination du médecin ayant orienté le plus de patients");
        System.out.println(" 9  : Détermination du médecin ayant reçu le plus de patients orientés");
        System.out.println(" 10 : Affichage des médecins vers lesquels un médecin donné oriente des patients");
        System.out.println(" 11 : Affichage des médecins ayant orienté des patients vers un médecin donné");
        System.out.println(" 12 : Recherche et affichage d'un chemin d'orientation entre deux médecins");
        System.out.println(" 13 : Vérification de l'existence d'un chemin entre deux médecins");
        System.out.println(" 14 : Calcul du nombre de médecins atteignables à partir d'un médecin donné");
        System.out.println(" 15 : Identification des médecins isolés");
        System.out.println(" 16 : Quitter l'application");
        System.out.println();
    }

    private static void traiterChoix(ReseauMedecins reseau, int choix) {
        String identifiant;
        String idSource;
        String idDestination;
        Medecin medecin;
        int total;

        switch (choix) {
        case 1:
            reseau.chargerDonnees("A");
            break;
        case 2:
            reseau.chargerDonnees("B");
            break;
        case 3:
            reseau.chargerDonnees("C");
            break;
        case 4:
            reseau.afficherMedecins();
            break;
        case 5:
            reseau.afficherOrientations();
            break;
        case 6:
            identifiant = saisirString("Identifiant du médecin ? ");
            medecin = reseau.rechercherMedecin(identifiant);
            if (medecin != null)
                medecin.afficher();
            else
                System.out.println("Médecin introuvable.");
            break;
        case 7:
            System.out.println("Nombre total de patients orientés : " + reseau.nbTotalPatientsOrientes());
            break;
        case 8:
            medecin = reseau.medecinAyantOrienteLePlus();
            if (medecin != null) {
                medecin.afficher();
                System.out.println("Nombre de patients orientés : " + reseau.nbPatientsOrientesPar(medecin));
            } else
                System.out.println("Aucun médecin chargé.");
            break;
        case 9:
            medecin = reseau.medecinAyantRecuLePlus();
            if (medecin != null) {
                medecin.afficher();
                System.out.println("Nombre de patients reçus : " + reseau.nbPatientsRecusPar(medecin));
            } else
                System.out.println("Aucun médecin chargé.");
            break;
        case 10:
            identifiant = saisirString("Identifiant du médecin ? ");
            reseau.afficherDestinations(identifiant);
            break;
        case 11:
            identifiant = saisirString("Identifiant du médecin ? ");
            reseau.afficherSources(identifiant);
            break;
        case 12:
            idSource = saisirString("Identifiant du médecin source ? ");
            idDestination = saisirString("Identifiant du médecin destination ? ");
            reseau.afficherChemin(idSource, idDestination);
            break;
        case 13:
            idSource = saisirString("Identifiant du médecin source ? ");
            idDestination = saisirString("Identifiant du médecin destination ? ");
            if (reseau.existeChemin(idSource, idDestination))
                System.out.println("Il existe un chemin.");
            else
                System.out.println("Il n'existe pas de chemin.");
            break;
        case 14:
            identifiant = saisirString("Identifiant du médecin ? ");
            total = reseau.nbMedecinsAtteignables(identifiant);
            if (total == -1)
                System.out.println("Médecin introuvable.");
            else
                System.out.println("Nombre de médecins atteignables : " + total);
            break;
        case 15:
            reseau.afficherMedecinsIsoles();
            break;
        case 16:
            System.out.println("Fin du programme.");
            System.exit(0);
            break;
        default:
            System.out.println("Choix invalide.");
        }
    }

    public static void main(String[] args) {
        ReseauMedecins reseau = new ReseauMedecins();
        int choix;

        do {
            afficherMenu();
            choix = saisirEntier("Votre choix -> ");
            traiterChoix(reseau, choix);
        } while (true);
    }
}
