public class TMain {
    public static void main(String[] args) {
        Logger l = new FileLogger(new HTMLLogger(new EncryLogger(null)));
        l.log("hello World");
    }
}
