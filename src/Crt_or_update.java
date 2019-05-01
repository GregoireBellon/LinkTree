import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Crt_or_update {
	
	public static File log = new File(Launch.path+"/log");

	/**
	 * Permet d'ajouter un lien dans le registre ou de mettre celui ci à jours
	 * 
	 * @param url Url à enregistrer dans le registre
	 * @param download Est ce que l'url à été téléchargée?
	 * @throws IOException
	 */
	public static void Crt_of_update(String url, boolean download) throws IOException {
		
		if(!log.exists()) {
			log.createNewFile();
			System.out.println("The log was created at the path : "+Launch.path);
		}
		
		if(DlAndStore.LineExist(url)) {
			System.out.println("Line already present");
			return;
		}
		
		FileWriter write = new FileWriter(Launch.path+"/log", true);
		
		System.out.println("Download : "+download);
		
		if(download){

		write.write(url+"   <downloaded> \n");
		System.out.println("log completed (downloaded)");

	}
		else{
			write.write(url+"     \n");
			System.out.println("log completed");

		}
		
		
		write.flush();
		write.close();
}
}
