import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static int arr[];
	static boolean[] selected = new boolean[9];
	
	static void comb(int start, int count) {
		if(count == 7) {
			int sum = 0;
			for(int i=0; i<9; i++) {
				if(selected[i]) {
					sum+=arr[i];						
				}
			}
			if(sum == 100) {
				for(int i=0; i<9; i++) {
					if(selected[i]) {
						System.out.println(arr[i]);						
					}
				}
			}
			
			return;
		}
		
		for(int i=start; i<9; i++) {	
			selected[i] = true;
			comb(i+1, count+1);
			selected[i] = false;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		arr = new int[9];
		
		for(int i=0; i<9; i++) {
			arr[i] = sc.nextInt();
		}
		
		Arrays.sort(arr);
		
		comb(0, 0);
	}
}
