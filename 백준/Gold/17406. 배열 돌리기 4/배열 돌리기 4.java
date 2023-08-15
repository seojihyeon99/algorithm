import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int resultmin = Integer.MAX_VALUE;
	static List<int[]> op;
	static int[][]arr;
	static int n; // 세로길이
	static int m; // 가로길이
	static int k; // 연산의 개수
	static boolean[] selected;
	static int[] index;
	
	static void perm(int count) {
		if(count == k) {
			List<int[]> list = new ArrayList<>();
			for(int i=0; i<k; i++) {
				list.add(op.get(index[i]));
			}
			
			// 배열 임시 복사
			int[][] temp = new int[n+1][m+1];
			for(int r=1; r<=n; r++) {
				temp[r] = Arrays.copyOf(arr[r], m+1);
			}
			
			// 회전시킴
			rotate(list);
			
			int min = Integer.MAX_VALUE;
			for(int r=1; r<=n; r++) {
				int sum = 0;
				for(int c=1; c<=m; c++) {
					sum+=arr[r][c];
				}
				if(sum < min) { // 현재 min보다 작으면
					min = sum; // 업데이트
				}
			}
			
			if(min<resultmin) {
				resultmin = min;
			}
//			System.out.println(Arrays.toString(index));
			
			// 해당 연산 끝나면 다시 배열 원위치
			arr = temp;
			
			return;
		}
		
		for(int i=0; i<k; i++) {
			if(selected[i]) continue;
			
			selected[i] = true;
			index[count] = i;
			perm(count+1);
			
			selected[i] = false;
		}
	}
	
	static void rotate(List<int[]> list) {
		for(int i=0; i<k; i++) { // 연산의 개수만큼 반복
			int[] info = list.get(i); // 해당 인덱스의 연산 정보
			int r = info[0]; // 행
			int c = info[1]; // 열
			int s = info[2]; // 크기
			
			for(int j=1; j<=s; j++) {
				// 좌표 하나 임시로 저장
				int temp = arr[r][c-j];
				
				// 왼쪽(아래 절반)				
				for(int k=1; k<=j; k++) {
					arr[r+k-1][c-j] = arr[r+k][c-j];
				}
				
				// 아래
				for(int k=1; k<=2*j; k++) {
					arr[r+j][c-j+k-1] = arr[r+j][c-j+k];
				}
				
				// 오른쪽
				for(int k=1; k<=2*j; k++) {
					arr[r+j-k+1][c+j] = arr[r+j-k][c+j];
				}
				
				// 위쪽
				for(int k=1; k<=2*j; k++) {
					arr[r-j][c+j-k+1] = arr[r-j][c+j-k];
				}
				
				// 왼쪽(위 절반)
				for(int k=1; k<=j-1; k++) {
					arr[r-j+k-1][c-j] = arr[r-j+k][c-j];
				}
				
				// 임시로 저장해 둔 좌표 넣음
				arr[r-1][c-j] = temp;
			}
	
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 세로길이
		m = Integer.parseInt(st.nextToken()); // 가로길이
		k = Integer.parseInt(st.nextToken()); // 연산의 개수
		
		// 배열 생성 및 초기화
		arr = new int[n+1][m+1]; //행 또는 열 인덱스 0인곳 쓰지 x
		for(int r=1; r<=n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=1; c<=m; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		// 연산 정보(r, c, s) 입력받기
		op = new ArrayList<>(k);
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			op.add(new int[] {r,c,s}); // 리스트에 추가
		}
		
		selected = new boolean[k];
		index = new int[k];
		
		perm(0);
		
		System.out.println(resultmin);
		
		
	}
}
