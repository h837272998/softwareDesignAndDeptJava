public class EncryLogger extends  LoggerDecator {
    public EncryLogger(Logger logger) {
        super(logger);
    }

    @Override
    public String log(String msg) {
        return msg.replace("l","$");
    }
}
