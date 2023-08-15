import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int dumplimit;				//덤프횟수 제한
	static int[] arr = new int[100];	// 100가지 높이 저장한 배열
	
	// 배열에서 최댓값 인덱스 구하는 함수
	static int MaxIdx() {
		int max = arr[0];
		int maxIdx = 0;
		
		for(int i=1; i<100; i++) {
			if(max < arr[i]) {
				max = arr[i];
				maxIdx = i;
			}
		}
		
		return maxIdx;
	}

	// 배열에서 최솟값 인덱스 구하는 함수
	static int MinIdx() {
		int min = arr[0];
		int minIdx = 0;
		
		for(int i=1; i<100; i++) {
			if(min > arr[i]) {
				min = arr[i];
				minIdx = i;
			}
		}
		
		return minIdx;
	}
	
	// Flatten하는 함수
	static int Flatten() {
		for(int i=0; i<dumplimit; i++) {
			arr[MaxIdx()] -= 1; // 최고점 1 감소
			arr[MinIdx()] += 1; // 최저점 1 증가
		}
		
		return arr[MaxIdx()]- arr[MinIdx()];
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int i=1; i<=10; i++) {
			dumplimit = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine()); // 100가지 높이가 들어옴
			
			// 배열에 입력받기
			for(int j=0; j<100; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			
			System.out.println("#" + i + " " + Flatten());
		}
	}
}
