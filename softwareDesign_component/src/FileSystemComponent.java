
public interface FileSystemComponent {
	long getComponentSize();
	public void addComponent(FileSystemComponent fc);
	public FileSystemComponent getComponent(int i);
}
