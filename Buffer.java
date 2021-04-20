import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
public class Buffer {
	public void bufferNotClosed () throws InterruptedException, IOException {
		String str = "";
	    URLConnection conn = new URL("https://www.w3.org/TR/PNG/iso_8859-1.txt").openConnection();
	    BufferedReader br = new BufferedReader(
	      new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
	    while (br.readLine() != null) {
	        str += br.readLine();
	    }
	}
	public void bufferClosed () throws InterruptedException, IOException {
		String str = "";
	    URLConnection conn = new URL("https://www.w3.org/TR/PNG/iso_8859-1.txt").openConnection();
	    try (BufferedReader br = new BufferedReader(
	    		  new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
	    	
	    	while (br.readLine() != null) {
		        str += br.readLine();
		    }
	    	br.close();
	    } 
	    catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
}
