public class FileLogger extends  LoggerDecator{

    public FileLogger(Logger logger) {
        super(logger);
    }

    @Override
    public String log(String msg) {
        System.out.print("File:" + logger.log(msg));
        return logger.log(msg);
    }
}
