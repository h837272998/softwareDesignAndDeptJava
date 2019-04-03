public class PRRequest {
    final  int money;
    final String name;

    public PRRequest(int money, String name) {
        this.money = money;
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PRRequest{" +
                "money=" + money +
                ", name='" + name + '\'' +
                '}';
    }
}
