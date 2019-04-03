package softwareDesign_Visitor1;

import java.util.Vector;

class OrderVisitor implements VisitorInterface {
	private Vector orderObjList;
	private double orderTotal;

	public OrderVisitor() {
		orderObjList = new Vector();
	}

	public void visit(NonCaliforniaOrder inp_order) {
		orderTotal = orderTotal + inp_order.getOrderAmount();
	}

	public void visit(CaliforniaOrder inp_order) {
		orderTotal = orderTotal + inp_order.getOrderAmount() + inp_order.getAdditionalTax();
	}

	public void visit(OverseasOrder inp_order) {
		orderTotal = orderTotal + inp_order.getOrderAmount() + inp_order.getAdditionalSH();
	}

	public double getOrderTotal() {
		return orderTotal;
	}

	@Override
	public void visit(DiscountOrder discountOrder) {
		orderTotal = orderTotal + discountOrder.getOrderAmount() * 0.8;
	}
}
