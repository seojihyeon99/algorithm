import java.io.*;
import java.util.*;
public class Main {

	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s);
		int sum = 0;
		for(int i=0; i<n; i++) {
			int num = Integer.parseInt(st.nextToken());
			boolean isprime = true;
			for(int j=2; j<num; j++) {
				if(num%j==0) {
					isprime = false;
					break;
				}		
			}
			if (isprime==true && num!=1){
				sum++;
			}
		}
		System.out.println(sum);
	}

}
