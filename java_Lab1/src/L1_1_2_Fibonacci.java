
public class L1_1_2_Fibonacci {
	public static void main(String[] args) {
		int f[] = new int[10];
		f[0] = f[1] = 1;
		for(int i = 2;i<f.length;i++){
			f[i] = f[i-1]+f[i-2];
		}
		System.out.println("����ķ�ʽ��");
		for(int a : f)
			System.out.print(a+" ");
		System.out.println();
		System.out.println("�ݹ�ķ�ʽ��");
		for(int i = 1;i<=10;i++)
			System.out.print(fibonacci(i)+" ");
	}
	
	/** ���õݹ�ķ���
	 * @param n
	 * @return
	 */
	public static int fibonacci(int n){
		if(n<3){
			return 1;
		}else{
			return fibonacci(n-1)+fibonacci(n-2);
		}
	}
	
	
}
