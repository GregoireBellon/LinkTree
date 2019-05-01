import java.util.Scanner;

public class TestReader {

	public static void main(String[] args) { //test de la classe LinkFinder
		String chemin;
		String html_adress;
		Scanner sc = new Scanner(System.in); 
		System.out.println("Entrez le nom du fichier");
		chemin = sc.nextLine();
		System.out.println("Entrez l'adresse de la page web");
		html_adress = sc.nextLine();
		Launch.clearLog();
		LinkFinder.SearchAndIndex(chemin, html_adress);
		sc.close();
	}

}
