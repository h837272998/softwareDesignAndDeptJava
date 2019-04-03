public class ConsoleLogger extends LoggerDecator {
    public ConsoleLogger(Logger logger) {
        super(logger);
    }

    @Override
    public String log(String msg) {
        String m = logger.log(msg);
        System.out.println("console:" + m);
        return m;
    }
}
