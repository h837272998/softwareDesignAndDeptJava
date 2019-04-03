
public class L1_1_1_Array {
	public static void main(String[] args) {
		int a[] = new int[5];
		int arr1[][] = new int[3][4];
		a[0] = 10; 
        a[1] = 10 + a[0]; 
        a[2] = 30; 
        a[3] = 40; 
        a[4] = a[1] + a[2]; 
        arr1[0][0] = 0;
        arr1[0][1] = 1;
        arr1[0][2] = 2; 
        arr1[1][0] = 3;
        arr1[1][1] = 4;
        arr1[1][2] = 5; 
        arr1[2][0] = 6;
        arr1[2][1] = 7;
        arr1[2][2] = 8; 
        for(int i = 0; i<a.length;i++) {
        	System.out.print("a[" + i + "] = "+a[i] + ", ");
        }
        System.out.println();
        for(int i = 0;i<3;i++){
        	for(int j = 0;j<3;j++) {
        		System.out.println("arr1("+i+","+j+") = " + arr1[i][j] );
        	}
        }
	}
}
