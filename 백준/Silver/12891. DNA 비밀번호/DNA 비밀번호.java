import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int dnalength = Integer.parseInt(st.nextToken()); // dna 문자열 길이
		int sublength = Integer.parseInt(st.nextToken()); // 부분 문자열 길이
		
		int[] subcount = new int[4]; // A C G T 사용 개수 저장
		
		String s = br.readLine(); // dna 문자열 입력받음
		
		// 최소 써야하는 A C G T의 개수 저장하는 배열 생성 및 조기화
		int[] minlimit = new int[4];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
			minlimit[i] = Integer.parseInt(st.nextToken());
		}
		
		// 시작 포인터(왼쪽, 오른쪽) 설정
		int left = 0; //부분 문자열에서 왼쪽을 가리키는 포인터
		int right = left + sublength - 1; //부분 문자열에서 오른쪽을 가리키는 포인터
		
		int count = 0; // 비밀번호 종류 수

		// 시작 포인터에서 현재 부분문자열에서 A C G T 문자의 사용 개수 저장
		for(int i=0; i<sublength; i++) {
			if(s.charAt(i)=='A') {
				subcount[0]=subcount[0]+1;
			} else if(s.charAt(i)=='C') {
				subcount[1]=subcount[1]+1;
			} else if(s.charAt(i)=='G') {
				subcount[2]=subcount[2]+1;
			} else if(s.charAt(i)=='T') {
				subcount[3]=subcount[3]+1;
			}		
		}

		while(true) {
			// 최소 써야하는 개수 넘었는지 여부 체크
			if(subcount[0]>=minlimit[0] && subcount[1]>=minlimit[1] && subcount[2]>=minlimit[2] && subcount[3]>=minlimit[3]) {
				count++;
			}
			
			//오른쪽 포인터가 문자열의 마지막이면
			if(right == dnalength - 1) {
				break;
			}
			
			// 왼쪽 포인터를 1증가 시키기 위한 전처리
			if(s.charAt(left)=='A') {
				subcount[0]--;
			} else if(s.charAt(left)=='C') {
				subcount[1]--;
			} else if(s.charAt(left)=='G') {
				subcount[2]--;
			} else if(s.charAt(left)=='T') {
				subcount[3]--;
			}	
			
			left+=1; // 왼쪽 포인터 증가
			
			// 오른쪽 포인터를 1증가 시키기 위한 전처리
			if(s.charAt(right+1)=='A') {
				subcount[0]++;
			} else if(s.charAt(right+1)=='C') {
				subcount[1]++;
			} else if(s.charAt(right+1)=='G') {
				subcount[2]++;
			} else if(s.charAt(right+1)=='T') {
				subcount[3]++;
			}	
			
			right+=1; // 오른쪽 포인터 증가
			
		}
		
		System.out.println(count);
	}
}
