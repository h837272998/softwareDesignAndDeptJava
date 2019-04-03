
public class FileComponent implements FileSystemComponent {
	final int size;
	
	
	public FileComponent(int size) {
		super();
		this.size = size;
	}

	@Override
	public long getComponentSize() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public void addComponent(FileSystemComponent fc) {
		// TODO Auto-generated method stub
		throw new CompositeException();
	}

	@Override
	public FileSystemComponent getComponent(int i) {
		// TODO Auto-generated method stub
		throw new CompositeException();
	}
	
}
