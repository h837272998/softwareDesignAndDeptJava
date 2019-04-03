
import java.util.Enumeration;
import java.util.Vector;


public class L1_2_1_Vector {
	public static void main(String[] args) {
		Vector v=new Vector(1,1);
		v.addElement(args[0]); //������β�����Ԫ��
		v.addElement(args[1]);
		v.addElement("3"); //������β�����Ԫ��
		v.insertElementAt("0",0);//��ָ��λ�ò���Ԫ��
		v.insertElementAt("����aaa �ַ���Ԫ��",3);
		v.setElementAt("null",4);//�滻ָ��λ�õ�Ԫ��
		v.addElement("5");
		System.out.println("��4 ��Ԫ��Ϊ��"+v.elementAt(4));
		Enumeration enum1=v.elements();//ö�ٻ������Ա����ȡ��Ԫ��  1.4�汾��  ��֧��1.5��enumΪ�ؼ���
		StringBuffer buffer=new StringBuffer();//�ַ���������
		while(enum1.hasMoreElements())
			buffer.append(enum1.nextElement()).append(",");
		buffer.deleteCharAt(buffer.length()-1);
		System.out.println("����v ������Ԫ�أ�"+buffer.toString()+"\n");
		System.out.println("����v ��Ԫ�ظ�����"+v.size()+" v �ĳ���Ϊ��"+v.capacity()+"\n");
		if(v.elementAt(6)==null) {
			System.out.println(11111111);
		}
		v.removeAllElements();
		System.out.println("ɾ����Ԫ�ظ�����"+v.size()+" ����v ���ȣ�"+v.capacity()+"\n");
	}
}
