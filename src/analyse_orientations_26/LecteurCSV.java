package analyse_orientations_26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LecteurCSV {

    public static String[][] lireCSV(String nomFichier) throws IOException {

        List<String[]> lignes = new ArrayList<>();

        InputStream is = LecteurCSV.class.getResourceAsStream(nomFichier);

        if (is == null)
            throw new IOException("Fichier introuvable : " + nomFichier);

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String ligne;

        while ((ligne = br.readLine()) != null)
            lignes.add(ligne.split(";"));

        br.close();

        String[][] resultat = new String[lignes.size()][];

        for (int i = 0; i < lignes.size(); i++)
            resultat[i] = lignes.get(i);

        return resultat;
    }
}
