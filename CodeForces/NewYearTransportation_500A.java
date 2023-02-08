import java.util.Scanner;

public class NewYearTransportation_500A {

	public static void main(String[] args) {
		int n, t;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        t = sc.nextInt();

        int a[] = new int[n];
        for(int i = 1; i < n; i++)
        {
            a[i] = sc.nextInt();
        }
        
        int r = 1;
        while (r < t) {	
				r = r + a[r];
		}
        
        if (r == t) {
			System.out.println("YES");
		}else
			System.out.println("NO");
        

	}

}
