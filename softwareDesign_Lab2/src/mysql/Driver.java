package mysql;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Driver implements myjdbc.Driver {

	static {
		Properties p = new Properties();
		try {
			InputStream instream = new FileInputStream("src/SQL.properties");
			p.load(instream);
			instream.close();
			OutputStream outstream = new FileOutputStream("src/SQL.properties");
			p.setProperty("type", "mysql");
			System.out.println("»ñÈ¡" + p.getProperty("type") + "Çý¶¯");
			p.store(outstream, "");
			outstream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
}
