import java.io.IOException;
import java.util.Properties;

public class LoggerFactory {
	public String getLoggerType() {
		Properties p = new Properties();
		try {
			p.load(ClassLoader.getSystemResourceAsStream("Logger.properties")); //获得类的地址并获得文件
			return p.getProperty("loggerType");//返回loggerType属性
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
	
	public Logger getLogger() {
		String type = getLoggerType();
		try {
			return (Logger) Class.forName(type).newInstance();
		}catch(InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
