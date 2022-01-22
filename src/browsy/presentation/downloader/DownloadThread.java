
package browsy.presentation.downloader;

//import database.DownloadDatabase;

import browsy.dataAccess.DownloadDA;
import browsy.entities.Download;
import browsy.presentation.utils.AlertMaker;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;
import java.io.*;
import java.net.*;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Locale;


public class DownloadThread extends Thread{

	private String url ;
	private String filePath ;
	private String title = "Unknown";
	private static final int BUFFER_SIZE = 4096;
	public DownloadThread(String url ,String path,String title){
		this.url = url;
		this.filePath = path;
		if(title!=null)
			this.title = title;
	}

	/**
	 * @param contentType  is a string which tells the types of file 
	 * @param url is a string which refers to the download link of file
	 * @return downloaded file
	 * @throws URISyntaxException url syntax not valid
	 * @throws MalformedURLException not a url
	/**
	 * @return
	 * @throws URISyntaxException 
	 * @throws MalformedURLException 
	 */
	private File createFile(String contentType , String url) throws URISyntaxException, MalformedURLException{
		String fileTitle = title;
		System.out.println(Paths.get(new URI(url).getPath()).getFileName().toString());
		if((url.length()-url.lastIndexOf('.'))==4){
			System.out.println("plain url.");
			fileTitle = url.substring(url.lastIndexOf("/") + 1,url.length());
		}else{

			String[] ext = contentType.split("/");
			System.out.println(ext[1]);
			fileTitle = fileTitle+"."+ext[1];
			System.out.println(fileTitle);
		}
		File downloadFile = new File(filePath+File.separator+fileTitle);		
		if(!downloadFile.exists()){
			try {
				downloadFile.createNewFile();
			} catch (IOException e) {
				System.err.println("Cannot create File to store.");
				//				System.exit(0);
			}
		}
		return downloadFile;
	}
	/**
	 * @param content is a string which contains the file content
	 * @return if the file is an application e.g pdf or exe or audio/video etc
	 */
	public boolean isDownloadable(String content){
		System.out.println(content);
		if(content.contains("application")){
			return true;
		}else if (content.contains("video")){
			return true;
		}else if (content.contains("audio")){
			return true;
		}else if(content.contains("image")){
			return true;
		}
		return false;
	}
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run(){
		try{
			URL obj = new URL(url); // create url object for the given string
			HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
			if(url.startsWith("https")){
				System.out.println("Establishing https URL connection. . .");
				connection = (HttpsURLConnection) obj.openConnection();
			}
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.setUseCaches(true);
			connection.setConnectTimeout(60000);
			connection.setReadTimeout(60000);
			connection.connect();
			int requestinfo = connection.getResponseCode();
			String  contentType = connection.getContentType();
			if (requestinfo == connection.HTTP_OK && isDownloadable(contentType)) {
				new Notification();
				System.out.println("Download started on link  "+url);
				//DownloadDatabase.insertDownload(url, title, "Downloaded", 1);
				Download download=new Download(0,title.toLowerCase(Locale.ROOT), Date.valueOf(LocalDate.now()),filePath,url,0,"Downloading");
				int id=new DownloadDA().save(download);
				BufferedInputStream in = new BufferedInputStream(connection.getInputStream()); // open the input stream on the established tcp connection.
				FileOutputStream out = new FileOutputStream(createFile(contentType,url)); // create a file and open the output stream to write on file.
				int size = connection.getContentLength(); // to get the total size of the file being downloaded it will be helpful making the GUI like progress bar.
				int len = -1; 
				int progress = 0 ; // to update the GUI progress bar.
				byte[] buffer = new byte[BUFFER_SIZE]; // byte array to get the content from the input stream.
				while((len = in.read(buffer,0,BUFFER_SIZE)) != -1){ // getting content from the input stream and saving into the buffer byte array.
					out.write(buffer,0,len); // writing the bytes to the file.
					//			out.write(buffer);
					progress+=len; // update progress variable 
					System.out.println("Downloded bytes "+progress/1024+" KB"+ " Remaining  bytes  "+(size-progress)/1024+" KB");
				} 
				out.flush(); // empty the buffer.
				in.close(); // close opened streams
				out.close();

				System.out.println("id="+id);
				download.setDownloadedAt(Date.valueOf(LocalDate.now()));
				download.setSize(progress/1024);
				download.setStatus("Completed");
				new DownloadDA().update(id,download);

				System.out.println("Download Complete . ");
			}else{
				System.out.println(isDownloadable(contentType));
				System.out.println("Cannot download File response code from server: " +requestinfo);
				connection.disconnect();
			}

		}catch(SocketTimeoutException e){
			System.out.println("Connection is not establishing...");
		}
		catch(FileNotFoundException e){
			System.err.println("Error While Downloading : file not found . ");
			e.printStackTrace();
		}catch (ConnectException ex){
			System.out.println("Connect Exception.");
		}catch(ProtocolException e){
			System.err.println("Error While Downloading : protocol exception . ");
			e.printStackTrace();
		}catch(MalformedURLException e){
			System.err.println("invalid url.");

		}catch(SSLHandshakeException e){
			System.err.println("Error while SSL handshake.");
		}
		catch(UnknownHostException e){
			System.err.println("Error while downloading : Unknown Host Exception.");
		}
		catch(IOException e){
			System.err.println("input output exception .");
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception occurred in filTitle URL.");
			e.printStackTrace();
		} 


	}

}