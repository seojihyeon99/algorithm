import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // 몇명의 사람
		int k = sc.nextInt(); // 몇번째 사람 제거할지
		
		List<Integer> list = new LinkedList<>();
		for(int i=1; i<=n; i++) {
			list.add(i);
		}
		StringBuilder sb = new StringBuilder();
		
		sb.append("<");
		int curIdx = k - 1; // 현재 제거해야 되는 원소 가리키는 인덱스 (리스트 인덱스 0부터 시작하므로 -1)
		for(int i=0; i<n; i++) {
			sb.append(list.remove(curIdx));
			if(list.size()==0) break; // 리스트 크기 remove 후 0되면 break
			curIdx = (curIdx - 1 + list.size()) % list.size();
			curIdx = (curIdx + k) % list.size(); // 그 다음 제거해야되는 인덱스를 업데이트함
			sb.append(", ");
		}		
		sb.append(">");
		
		System.out.println(sb.toString());
	}
}
