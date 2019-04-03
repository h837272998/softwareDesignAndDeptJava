import java.util.Iterator;
import java.util.Vector;
/**
 * �ڲ������ӣ�vector��
 * @author HJH
 *
 */
public class AllCandidates implements Iterator{
	Vector vs = Expert.getExperts();
	
	int cursor = 0;
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return cursor < vs.size();
	}

	@Override
	public Object next() {
		// TODO Auto-generated method stub
		Object result = vs.get(cursor);
		cursor++;
		return result;
	}
	
}
