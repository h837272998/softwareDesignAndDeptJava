import java.util.Vector;

public class DirComponent implements FileSystemComponent{
	final int size;
	Vector<FileSystemComponent> children = new Vector();
	
	public DirComponent(int size) {
		super();
		this.size = size;
	}

	@Override
	public long getComponentSize() {
		// TODO Auto-generated method stub
		long total = 0;
		for(FileSystemComponent f : children)
			total += f.getComponentSize();
		return total;
	}

	@Override
	public void addComponent(FileSystemComponent fc) {
		// TODO Auto-generated method stub
		children.add(fc);
	}

	@Override
	public FileSystemComponent getComponent(int i) {
		// TODO Auto-generated method stub
		return children.get(i);
	}
	
}
