package Framework.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.InputStream;
import java.util.Properties;
//import java.io.OutputStream;

public class FileOperation {
	
	private static final String PROPERTIES_FOLDER_PATH =
		System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "Properties" + File.separator;

	public static void setProperty(String fileName, String propKey, String propValue) {
		//OutputStream outputStream = null;
		Properties props = getPropertiesFile(fileName);
		
		try {
			//OutputStream outputStream = new FileOutputStream;
			FileOutputStream outputStream = new FileOutputStream(PROPERTIES_FOLDER_PATH + fileName + ".properties");
			props.setProperty(propKey, propValue);
			props.store(outputStream, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String fileName, String propKey) {
		Properties props = getPropertiesFile(fileName);
		return props.getProperty(propKey);
	}
	
	public static Properties getPropertiesFile(String fileName) {
		//InputStream inputStream = null;
		Properties props = new Properties();
		
		try {
			File file = new File(PROPERTIES_FOLDER_PATH + fileName + ".properties");
			//InputStream inputStream = new FileInputStream(file);
			FileInputStream inputStream = new FileInputStream(file);
			props.load(inputStream);
			return props;
		} catch (Exception e) {
			System.out.println("Não foi possível carregar o arquivo. " + e.getMessage());
		}
		
		return props;
	}
	
}
