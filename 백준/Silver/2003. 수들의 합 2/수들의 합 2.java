import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n; //수열의 수
	static int m; //합
	static int i, j; //투 포인터
	
	static int sum;
	static int cnt;
	
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str = new StringTokenizer(br.readLine());	
        
		n = Integer.parseInt(str.nextToken());
		m = Integer.parseInt(str.nextToken());
		
		arr = new int[n];
		
		str = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++)
			arr[i]=Integer.parseInt(str.nextToken());
		
		i=0; j=0;
		sum=arr[0];
		
		while(true) {
			if(sum<m) {
				if(j+1>n-1) break;
				sum+=arr[++j];
			}
			else if(sum>m) {
				sum=sum-arr[i++];
				if(j>n-1 || i>n-1) break;
			}
			else {
				cnt++;
				sum-=arr[i++];
				if(j>n-1 || i>n-1) break;
			}
		}
		
		
		System.out.println(cnt);
	}
}
