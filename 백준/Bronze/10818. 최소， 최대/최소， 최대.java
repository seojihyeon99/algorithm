import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int[] arr;
		int n = input.nextInt();
		arr = new int[n];
		
		for(int i=0; i<n; i++) {
			arr[i] = input.nextInt();
		}
		
		int min=arr[0], max=arr[0];
		for(int i=1;i<n;i++) {
			if(arr[i]<min) min =arr[i];
			else if(arr[i]>max) max = arr[i];
		}
		
		System.out.println(min +" "+ max);
		input.close();
	}
}
