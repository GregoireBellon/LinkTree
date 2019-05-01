import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Launch {
	
	public static String url;
	public static String path = System.getProperty("user.dir")+"/Temp"; //where will be store the pages

	public static void main(String[] args) throws IOException {
		System.out.println("Path : "+path);
		creatTemp();
		clearLog();
		String nom;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the url");
		url = sc.nextLine();
		nom=DlAndStore.go(url);
		LinkFinder.SearchAndIndex(nom, url);
		System.out.println("End!");
		sc.close();
	}
	
	public static void creatTemp() {
		File f = new File(path);
		if(f.mkdir()) {
			System.out.println("Temp/ has been created");
		}
		else {
			System.out.println("Temp/ hasn't been created");

		}
	}
	
	public static void clearLog() { //vide le log
		try {
			FileWriter nettoyage = new FileWriter(path+"/log", false);
			nettoyage.write("");
			nettoyage.close();
			System.out.println("Le registre à été vidé");
			
		} catch (IOException e) {
			
			System.out.println("Le fichier log n'a pas encore été créé");
		}
		
	}
}
