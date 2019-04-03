public abstract class PRHandler {
    PRHandler nextHandler;

    public void  setNextHandler(PRHandler nextHandler){
        this.nextHandler = nextHandler;
    }

    abstract  void authorize(PRRequest requ);
}
