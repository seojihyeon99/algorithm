import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static List<Long> list = new ArrayList<Long>();

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		list.add(Long.valueOf(0));
		list.add(Long.valueOf(1));
		

		
		for(int i=2; i<=n; i++) {
			list.add(list.get(i-1)+list.get(i-2));
		}
		
		System.out.println(list.get(n));
		
	}
}
