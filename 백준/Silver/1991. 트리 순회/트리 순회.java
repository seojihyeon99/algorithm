import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int n;
	static int start;	
	static int arr[][] = new int[27][2];
	
	static void preorder(int idx) {
		//종료조건(리프노드일때)
		if(idx==0) return;
		
		System.out.print((char)('A'+idx-1)); //현재 노드
		preorder(arr[idx][0]); //왼쪽자식 노드
		preorder(arr[idx][1]); //오른쪽자식 노드
	}
	
	static void inorder(int idx) {
		//종료조건(리프노드일때)
		if(idx==0) return;
		
		inorder(arr[idx][0]); //왼쪽자식 노드
		System.out.print((char)('A'+idx-1)); //현재 노드
		inorder(arr[idx][1]); //오른쪽자식 노드				
	}
	
	static void postorder(int idx) {
		//종료조건(리프노드일때)
		if(idx==0) return;	
		
		postorder(arr[idx][0]); //왼쪽자식 노드
		postorder(arr[idx][1]); //오른쪽자식 노드
		System.out.print((char)('A'+idx-1)); //현재 노드
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//첫째 줄 이진노드의 개수 n 주어짐
		n = Integer.parseInt(br.readLine());
		
		String str;
		char alpha1, alpha2, alpha3;
		//둘째 줄부터 n번 "노드 왼쪽자식노드 오른쪽자식노드" 입력 반복(자식노드 없으면 .)
		for(int i=1; i<=n; i++) {
			str = br.readLine();
			alpha1 = str.charAt(0); //노드
			alpha2 = str.charAt(2); //왼쪽자식
			alpha3 = str.charAt(4); //오른쪽자식
			
			if(alpha2!='.') {
				arr[alpha1-'A'+1][0] = alpha2-'A'+1; //왼쪽자식
			}
			if(alpha3!='.') {
				arr[alpha1-'A'+1][1] = alpha3-'A'+1; //오른쪽자식
			}
		}
		
		preorder(1);
		System.out.println();
		
		inorder(1);
		System.out.println();
		
		postorder(1);		
		System.out.println();
	}
}
