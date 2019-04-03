
public class LogTest {
	public static void main(String[] args) {
		LoggerFactory lf = new LoggerFactory();
		Logger loger = lf.getLogger();
		loger.log("message");
	}
}
