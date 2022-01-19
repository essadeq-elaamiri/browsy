package browsy.presentation.downloader;

import java.io.File;
import java.util.Properties;

public class MainDownload {
	//	private  String title ;
	/* sample URL
	 * http://sound30.mp3slash.net/320/indian/raees/02%20-%20Zaalima%20[Songspk.GURU].mp3
	 * */

	public void startDownload(String url,String title){

		DownloadThread thread = new DownloadThread(url, downloadFolder(),title);
		System.setProperty("java.net.preferIPv4Stack" , "true");
		thread.start();		
	}


	/*
	 *  *Method to add proxy configuration in downloader.
	 *  */
	private void setProxy(){
		Properties systemProperties = System.getProperties();
		systemProperties.setProperty("http.proxyHost","172.16.0.2");
		systemProperties.setProperty("http.proxyPort","8080");
		systemProperties.setProperty("https.proxyHost","172.16.0.2");
		systemProperties.setProperty("https.proxyPort","8080");

	}

	/*
	 * Method create the Folder Downloads in the home/ if does not exists .To store the Download Stuff
	 * */
	/**
	 * @return
	 */
	private String downloadFolder(){
		File home = new File(System.getProperty("user.home"));
		File folder = new File (home,"Downloads");
		if (!folder.exists()){
			folder.mkdir();
		}
		return folder.getAbsolutePath();
	}

		public static void main (String[] args){
	//	MainDownload main = new MainDownload();
	//	main.startDownload("http://files.all-free-download.com/downloadfiles/graphic/graphic_6/9m5uf0jybsa_607805.zip");
	//		System.out.println("Yes");
	//		new MainDownload().startDownload("https://examples.javacodegeeks.com/core-java/net/sockettimeoutexception/java-net-sockettimeoutexception-how-to-solve-sockettimeoutexception/");
	//		new MainDownload().startDownload("https://www.namal.edu.pk/wp-content/uploads/2017/01/Cloud-Gaming.pdf");
	//		new MainDownload().startDownload("https://github-windows.s3.amazonaws.com/GitHubSetup.exe");
	//		new MainDownload().startDownload("https://mail-attachment.googleusercontent.com/attachment/u/0/?ui=2&ik=4711ea205e&view=att&th=15a183b2c9894079&attid=0.1&disp=safe&realattid=f_iyvezxzz0&zw&saddbat=ANGjdJ-Emx2dda42840wBZtN2MCXLRtPkpvp6kYfURU9_lj9K2-Gzc7qjXQcRo64970VZoMpNAdz46EnyFXMHsk-LmJs-VwJez4UOQ9F-TE2j37tUCYfW6tQNifFzRq7xOc6Q2qAwBXR7sD8DcqWsK8JbN78EXOakdOtH7iAtd2zc4WLeqClmglAc9e9Svf7U38ahOYBdYk8KGveqPSZYEGji5rf0LpVsr_buYmO2ayfk7N2cOK4_PXdAh1MkHg4C4yWJqMsvojpZD18GPr4ClOLtRXvjfGcCzJoD_lFVuMar2YvxpYhXOhwyG746vjH1O8JoMWzW2eQCUawoBaHKiTkGOQuVkD4A1xFcEXxD1S2DHXK8vV4grXMsJoxmzPfQzypIivoQpcuwB9_NsJlTtkspPfdaiIZf_rtJKwbeLGdG2Axk6j7SeEWZy4_pHUsHvtxT9b1gdSfbH-ScFIkI-r0c2g0Zh99Wb4OlE5mloxCPyBj429TMulHL7Ixm79W-lgqmuK05Qb0t6Nizggn53mjzWDboPfQxZWJlb9DukfjEZM-PcavjtlAkI9svgLwUpxb7A7QgN6jHc7fzvk2hXT0HknFRYCqlllVflzhb2FN9pTyc9i5mSYgGcvqUFB0GQUS6vpZfHAXCL3TYwrKYkw8fZi7ZhtZZU0GzGFwdQ");
	//https://mail-attachment.googleusercontent.com/attachment/u/0/?ui=2&ik=4711ea205e&view=att&th=15a183b2c9894079&attid=0.1&disp=safe&realattid=f_iyvezxzz0&zw&saddbat=ANGjdJ-Emx2dda42840wBZtN2MCXLRtPkpvp6kYfURU9_lj9K2-Gzc7qjXQcRo64970VZoMpNAdz46EnyFXMHsk-LmJs-VwJez4UOQ9F-TE2j37tUCYfW6tQNifFzRq7xOc6Q2qAwBXR7sD8DcqWsK8JbN78EXOakdOtH7iAtd2zc4WLeqClmglAc9e9Svf7U38ahOYBdYk8KGveqPSZYEGji5rf0LpVsr_buYmO2ayfk7N2cOK4_PXdAh1MkHg4C4yWJqMsvojpZD18GPr4ClOLtRXvjfGcCzJoD_lFVuMar2YvxpYhXOhwyG746vjH1O8JoMWzW2eQCUawoBaHKiTkGOQuVkD4A1xFcEXxD1S2DHXK8vV4grXMsJoxmzPfQzypIivoQpcuwB9_NsJlTtkspPfdaiIZf_rtJKwbeLGdG2Axk6j7SeEWZy4_pHUsHvtxT9b1gdSfbH-ScFIkI-r0c2g0Zh99Wb4OlE5mloxCPyBj429TMulHL7Ixm79W-lgqmuK05Qb0t6Nizggn53mjzWDboPfQxZWJlb9DukfjEZM-PcavjtlAkI9svgLwUpxb7A7QgN6jHc7fzvk2hXT0HknFRYCqlllVflzhb2FN9pTyc9i5mSYgGcvqUFB0GQUS6vpZfHAXCL3TYwrKYkw8fZi7ZhtZZU0GzGFwdQ
	//		new MainDownload().startDownload("https://www.google.com.pk/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&cad=rja&uact=8&ved=0ahUKEwiE2ObT1vrRAhUBtRQKHShJDvYQFggXMAA&url=http%3A%2F%2Fwww.pdf995.com%2Fsamples%2Fpdf.pdf&usg=AFQjCNFUjjZAJw2DPPGOy7FpbX0wEqSnzA&bvm=bv.146094739,d.d24");
			//new MainDownload().startDownload("https://www.emse.fr/~picard/cours/1A/java/livretJava.pdf","test");
			new MainDownload().startDownload("https://www.google.com/search?q=java+pdf&oq=java+pdf&aqs=chrome..69i57j0i512l5j69i60j69i61.5610j0j1&sourceid=chrome&ie=UTF-8","test");
		}
}