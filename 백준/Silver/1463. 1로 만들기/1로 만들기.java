import java.util.Scanner;

public class Main {
	static int count = 0;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		makeOne(n, 0);
		
		System.out.println(min);
	}
	
	static void makeOne(int num, int count) {
		if(count > min) return;
		
		if(num == 1) {
			if(count < min) min = count;

			return;
		}
		
		if(num%3 == 0) {
			makeOne(num/3, count+1);
		}
		if(num%2 == 0) {
			makeOne(num/2, count+1);			
		}

		makeOne(num-1, count+1);

	}
}
