
public class FileApp {
	public static void main(String[] args) {
		FileSystemComponent f1 = new FileComponent(100);
		FileSystemComponent f2 = new FileComponent(200);
		FileSystemComponent f3 = new FileComponent(300);
		
		FileSystemComponent d1 = new DirComponent(10);
		FileSystemComponent d2 = new DirComponent(10);
		d1.addComponent(f2);
		d1.addComponent(f3);
		d2.addComponent(f1);
		d1.addComponent(d2);
		//f1.addComponent(f2);
		
		System.out.println(d1.getComponentSize());
	}
}
