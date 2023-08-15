import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;
	static int nstudent;
	
	static List<Integer> sex = new ArrayList<>();
	static List<Integer> getnum = new ArrayList<>();
	
	static void changeSwitch(int it) {
		// 남성(1)이라면
		if(sex.get(it - 1) == 1) {
			int basis = getnum.get(it - 1);
			int curidx = basis;

			while(true) {
				// 종료조건
				if(curidx >= arr.length) {
					break;
				}
				
				arr[curidx] = (1 - arr[curidx]); // 0이면 -> 1로, 1이면 -> 0으로
				curidx += basis; // 배수인것들 처리
			}
		} 
		
		// 여성(2)이라면
		else {
			int curidx = getnum.get(it - 1);
			
			// 스위치의 개수가 1개 일때
			if(arr.length == 2) {
				// 일단 현재 받은 스위치를 반대로 바꿈
				arr[curidx] = (1 - arr[curidx]);
				return;
			}
			
			// 일단 현재 받은 스위치를 반대로 바꿈
			arr[curidx] = (1 - arr[curidx]);

			for(int i=1; i<arr.length; i++) {	
				int left = curidx - i;	// 현재 받은 스위치 번호 기준 왼쪽 인덱스
				int right = curidx + i;	// 현재 받은 스위치 번호 기준 오른쪽 인덱스
				
				// 배열의 인덱스 벗어나면 -> 반복종료
				if(left < 1 || right > arr.length - 1) {
					break;
				}
				
				// 대칭이면
				if(arr[left] == arr[right]) {
					arr[left] = (1 - arr[left]); // 0이면 -> 1로, 1이면 -> 0으로
					arr[right] = (1 - arr[right]); // 0이면 -> 1로, 1이면 -> 0으로
				} 
				// 대칭이 아니면
				else {
					break;
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 스위치 개수 입력
		int n = Integer.parseInt(br.readLine());
		
		// 스위치를 저장하는 크기가 n인 배열 생성
		arr = new int[n + 1]; // 인덱스 0은 사용하지 x
		
		// 스위치 초기 상태(1 -> 켜져 있음. 0 -> 꺼져있음) 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 학생수 입력
		nstudent = Integer.parseInt(br.readLine());
		
		// 학생수만큼 반복하면서, 학생의 성별(1 -> 남자, 2 -> 여자) + 학생이 받은 수 입력받음
		for(int i=0; i<nstudent; i++) {
			st = new StringTokenizer(br.readLine());
			sex.add(Integer.parseInt(st.nextToken()));
			getnum.add(Integer.parseInt(st.nextToken()));
		}
		
		// 성별(여 or 남)에 따라 처리 로직 다름
		for(int i=1; i<=nstudent; i++) {
			changeSwitch(i); // 성별, 받은수를 매개변수로 넘겨줌
		}

		// 스위치 출력
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<arr.length; i++) {
			sb.append(arr[i] + " ");
			// 한 줄에 20개씩만 출력함. 21번 스위치 있다면 -> 2번째 줄로
			if(i % 20 == 0) {
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
		
	}
}
