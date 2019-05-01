import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.io.BufferedReader;
import java.io.File;

public class DlAndStore {

	/**
	 * Télécharge une url et met à jours le registre
	 * 
	 * @param url de la page
	 * @return Nom du fichier html
	 * @throws IOException
	 */

	public static String go(String url) throws IOException {
		String nom = url.replaceAll("/", "-") + ".html"; // sert à ne pas avoir de "/"
		String SavePlace = Launch.path+"/"+ nom;
		System.out.println(nom);
		File f = new File(SavePlace);
		try {
			f.createNewFile(); // crée le fichier
		} catch (IOException e) {

			e.printStackTrace();
		}

		try {
			URL website = new URL(url); // nouvel objet url
			ReadableByteChannel rbc = Channels.newChannel(website.openStream()); // flux vers le site
			FileOutputStream fos = new FileOutputStream(SavePlace); // flux vers le fichier
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE); // gestion du flux
			rbc.close();
			fos.close();
			Crt_or_update.Crt_of_update(url, true);
		} catch (MalformedURLException e) {
			System.out.println("MalformedURLException    " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();

		}

		return nom;
	}

	public static boolean LineExist(String line) {
		try {
			String lecture;
			BufferedReader flux = new BufferedReader(new FileReader(Launch.path + "/log"));
			while (flux.ready()) {
				lecture = flux.readLine();
				if (lecture.contains(line)) {
					flux.close();
					return true;
				}
			}
			flux.close();
			return false;
		} catch (IOException e) {

			System.out.println("The log file has not yet been created");
			return false;
		}

	}

}
