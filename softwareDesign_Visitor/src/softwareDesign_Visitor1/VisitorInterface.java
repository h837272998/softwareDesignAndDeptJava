package softwareDesign_Visitor1;

public interface VisitorInterface {
	public void visit(NonCaliforniaOrder nco);

	public void visit(CaliforniaOrder co);

	public void visit(OverseasOrder oo);

	public void visit(DiscountOrder discountOrder);
}
