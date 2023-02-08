import java.util.Scanner;

public class FashionInBerland_691A {

	public static void main(String[] args) {
		int n;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        int a[] = new int[n];
        for(int i = 0; i < n ; i++)
        {
            a[i] = sc.nextInt();
        }
        
        int cnt0 = 0;
        for (int i = 0; i < n  ; i++) {
			if (a[i] == 0) {
				cnt0++;
			}
		}
        
        if (n == 1 && cnt0 == 0) {
			System.out.println("YES");
			
		}else {
        
        if (n > 1 && cnt0 == 1) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
		}
        
	}
	

}
