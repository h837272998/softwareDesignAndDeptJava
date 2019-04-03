import java.util.ArrayList;
import java.util.Iterator;
/**
 * ����
 * @author HJH
 *
 */
public class CopyOfAllCandidates implements Iterator {
	ArrayList al = Expert.getExperts2();
	int cursor = 0;
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return cursor < al.size();
	}

	@Override
	public Object next() {
		// TODO Auto-generated method stub
		Object result = al.get(cursor);
		cursor++;
		return result;
	}

}
