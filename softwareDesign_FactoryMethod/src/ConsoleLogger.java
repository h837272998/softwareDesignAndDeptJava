
public class ConsoleLogger implements Logger{
	public void log(String string) {
		System.out.println("log " + string + " in consoleLogger");
	}
}
