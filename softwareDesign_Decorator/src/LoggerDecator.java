public abstract class LoggerDecator implements Logger{
    Logger logger;

    public LoggerDecator(Logger logger) {
        this.logger = logger;
    }
}
