package softwareDesign_Prototype1;

public class WinHostingKitFactory implements HostingKitFactory {

	@Override
	public BasicHostingKit getBasic() {
		// TODO Auto-generated method stub
		return new WinBasicHostingKit();
	}
	
}
