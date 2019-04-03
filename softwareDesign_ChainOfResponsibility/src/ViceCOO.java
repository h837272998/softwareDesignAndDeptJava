public class ViceCOO extends  PRHandler {
    private final int LIMIT = 200000;
    @Override
    void authorize(PRRequest requ) {
        if (requ.getMoney()>LIMIT){
            nextHandler.authorize(requ);
            return ;
        }
        System.out.println("requ = " + requ);
    }
}
