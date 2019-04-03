import java.util.Iterator;
import java.util.Vector;

/**
 * �ⲿ������
 * @author HJH
 *
 */
public class Copy_2_of_AllCandidates {
	Vector vs = Expert.getExperts();

	
	Iterator getIterator(){
		return new MyIterator();
	}
	class MyIterator implements Iterator{
		int cursor = 0;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			// cursor <= last item in vs
			return cursor < vs.size();
		}

		/**
		 * 1.�ȷ���cursor��ָ����
		 * 2.cursor ����1λ��
		 */
		@Override
		public Object next() {
			Object result = vs.get(cursor);
			cursor++;
			return result;
		}
	}
}
