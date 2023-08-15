import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static int n; // 배열 세로 길이
	static int m; // 배열 가로 길이
	
	//상하반전
	static void upDown() {
		for(int r=0; r<n/2; r++) {
			// r번째 행 <-> n-1-r번째 행   => 교환!!!
			int[] temp = arr[r]; // r번째 행을 임시로 저장
			arr[r] = arr[n-1-r]; //n-1-r번째 행과 교환
			arr[n-1-r] = temp;
		}
	}
	
	//좌우반전
	static void leftRight() {
		for(int r=0; r<n; r++) {
			for(int c=0; c<m/2; c++) {
				// c번째 열 <-> m-1-c번째 열   => 교환!!!
				int temp = arr[r][c]; // c번째 열을 임시로 저장
				arr[r][c] = arr[r][m-1-c]; // n-1-c번째 열과 교환
				arr[r][m-1-c] = temp;
			}			
		}
	}
	
	//오른쪽 90도 회전
	static void rotateRight() {
		int[][] result = new int[m][n];
		for(int c=0; c<m; c++) {
			for(int r=n-1; r>=0; r--) {
				result[c][n-1-r] = arr[r][c]; 			
			}			
		}
		arr= result;		
		int temp = n;
		n = m;
		m =temp;
	}	
	
	//왼쪽 90도 회전
	static void rotateLeft() {
		int[][] result = new int[m][n];
		for(int r=0; r<n; r++) {
			for(int c=0; c<m; c++) {
				result[m-1-c][r] = arr[r][c]; 			
			}			
		}
		arr= result;	
		int temp = n;
		n = m;
		m =temp;
	}	
	
	//그룹별 90도 오른쪽으로 회전
	static void partRotateRight() {
		int[][] result = new int[n][m];
		//1번째 그룹(왼쪽위) 옮기기
		for(int r=0; r<n/2; r++) {
			for(int c=0; c<m/2; c++) {
				result[r][c+m/2] = arr[r][c];
			}
		}
		//2번째 그룹(오른쪽위) 옮기기
		for(int r=0; r<n/2; r++) {
			for(int c=m/2; c<m; c++) {
				result[r+n/2][c] = arr[r][c];
			}
		}	
		//3번째 그룹(오른쪽아래) 옮기기
		for(int r=n/2; r<n; r++) {
			for(int c=m/2; c<m; c++) {
				result[r][c-m/2] = arr[r][c];
			}
		}	
		//4번째 그룹(왼쪽아래) 옮기기
		for(int r=n/2; r<n; r++) {
			for(int c=0; c<m/2; c++) {
				result[r-n/2][c] = arr[r][c];
			}
		}	
		
		arr = result;
	}
	
	// 그룹별 90도 왼쪽으로 회전
	static void partRotateLeft() {
		int[][] result = new int[n][m];
		//1번째 그룹(왼쪽위) 옮기기
		for(int r=0; r<n/2; r++) {
			for(int c=0; c<m/2; c++) {
				result[r+n/2][c] = arr[r][c];
			}
		}
		//2번째 그룹(오른쪽위) 옮기기
		for(int r=0; r<n/2; r++) {
			for(int c=m/2; c<m; c++) {
				result[r][c-m/2] = arr[r][c];
			}
		}	
		//3번째 그룹(오른쪽아래) 옮기기
		for(int r=n/2; r<n; r++) {
			for(int c=m/2; c<m; c++) {
				result[r-n/2][c] = arr[r][c];
			}
		}	
		//4번째 그룹(왼쪽아래) 옮기기
		for(int r=n/2; r<n; r++) {
			for(int c=0; c<m/2; c++) {
				result[r][c+m/2] = arr[r][c];
			}
		}	
		
		arr = result;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 배열 세로 길이
		m = Integer.parseInt(st.nextToken()); // 배열 가로 길이
		int r = Integer.parseInt(st.nextToken()); // 수행해야하는 연산의 수
		
		// 배열 입력 받기
		arr = new int[n][m];
		for(int row=0; row<n; row++) {
			st = new StringTokenizer(br.readLine());
			for(int col=0; col<m; col++) {
				arr[row][col]=Integer.parseInt(st.nextToken());
			}
		}
		
		// 연산 횟수만큼 반복
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<r; i++) {
			switch(Integer.parseInt(st.nextToken())) {
			case 1:
				upDown();
				break;
			case 2:
				leftRight();
				break;
			case 3:
				rotateRight();
				break;
			case 4:
				rotateLeft();
				break;
			case 5:
				partRotateRight();
				break;
			case 6:
				partRotateLeft();
				break;
			}
		}
		
		// 연산 결과 배열 출력
		StringBuffer sb = new StringBuffer();
		for(int row=0; row<n; row++) {
			for(int col=0; col<m; col++) {
				sb.append(arr[row][col] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
