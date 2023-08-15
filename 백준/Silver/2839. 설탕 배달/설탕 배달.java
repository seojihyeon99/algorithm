import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // 배달해야하는 설탕 무게
		int count = 0;
		
		while(true) {
			if(n%5 != 0) {
				if(n<3) {
					break;
				}
				n = n-3; // 3kg 개수 증가
				count++;
			}
			else {
				count += n/5;
				n = 0;
				break;
			}
		}

		if(n!= 0) count = -1;
		System.out.println(count);
	}
}
