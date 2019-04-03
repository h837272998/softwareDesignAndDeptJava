public class HTMLLogger extends  LoggerDecator {
    public HTMLLogger(Logger logger) {
        super(logger);
    }

    @Override
    public String log(String msg) {
        return "<h1>" + logger.log(msg) + "</h1>";
    }
}
