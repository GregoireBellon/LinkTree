
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.io.BufferedReader;

public class LinkFinder {
	/**
	 * Cette méthode cherche les liens externes d'une page web
	 * 
	 * @param link_file Fichier html de la page
	 * @param url       Url de la page
	 */
	public static void SearchAndIndex(String link_file, String url) {
		try {
			String ligne;
			BufferedReader flux = new BufferedReader(new FileReader(Launch.path + "/" + link_file));
			FileWriter ecriture = new FileWriter(Launch.path + "/log", true); // sert à écrire dans le log LAISSER TRUE

			int coup; // sert à stocker l'index de la coupe
			int c = 1;
			while (flux.ready()) { // tant que le fichier n'est pas terminé...
				ligne = flux.readLine();

				if (ligne.contains("<a href")) { // recherche de balise
					System.out.println("Extraction de la ligne " + c + " : " + ligne.replaceAll("\\s+", ""));
					coup = ligne.indexOf("<a href"); // coupe du début à optimiser en une seule
					ligne = ligne.substring(coup + 9);
					coup = ligne.indexOf("\"");// coupe de la fin
					ligne = ligne.substring(0, coup);
					System.out.println("contenu extrait : " + ligne);
					filtre(url, ligne);

				}

				c++;
			}

			ecriture.close();
			flux.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Cette méthode sert à filtrer les url récoltées, elle transforme les liens
	 * relatifs en url utilisables
	 * 
	 * @param urlBase  l'url de base du site (ex : "https://www.youtube.com"
	 * @param urlRecup l'url que l'on a récupéré (ex : "/profile/videos")
	 * @throws IOException
	 */
	public static void filtre(String urlBase, String urlRecup) throws IOException {
		try {
			if (urlRecup.charAt(0) == '/') { // si c'est un lien relatif...
				Crt_or_update.Crt_of_update(urlBase + urlRecup, false);
			} else {
				Crt_or_update.Crt_of_update(urlRecup, false);
			}
		} catch (java.lang.StringIndexOutOfBoundsException e) {
			e.printStackTrace();
			System.out.println("Url empty");
		}
	}
}
