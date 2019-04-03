package softwareDesign_Visitor1;

public class DiscountOrder implements Order {
	private double orderAmount;

	@Override
	public void accept(VisitorInterface v) {
		v.visit(this);
	}

	public double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}
}
