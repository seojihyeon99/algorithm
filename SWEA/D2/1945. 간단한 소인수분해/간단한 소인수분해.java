import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	
	static int[] arr = {2,3,5,7,11};	//2,3,5,7,11을 저장하고 있는 배열 -> 추후 반복문에서 하나씩 꺼내 사용
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int i=1; i<=t; i++) {
			int n = Integer.parseInt(br.readLine());
			int[] result = new int[5];	//a,b,c,d,e를 카운트하는 배열. 0으로 초기화되어 있음
			
			for(int j=0; j<5; j++) {
				while(n%arr[j]==0) {
					result[j]++;	//result에서 해당 인덱스의 count개수 증가
					n=n/arr[j];		//n=n%arr[j]로 줄어듦
				}
			}

			System.out.printf("#%d ", i);		//앞에 #테스트케이스 출력
			for(int num : result) {		
				System.out.printf("%d ", num);	//result의 모든 값 출력
			}
			System.out.println();				//다음 테스트케이스 출력을 위한 개행
		}
				
	}

}
