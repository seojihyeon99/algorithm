import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 서지현
 * 버튼이 서로 배수 관계이므로 그리디 알고리즘 적용가능
 * 제일 큰 초를 가지는 버튼부터 눌러야함
 */
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine()); // 요리시간
		
		int button1 = 0; // 1번 버튼(5분=300초) 누르는 횟수
		int button2 = 0; // 2번 버튼(1분=60초) 누르는 횟수
		int button3 = 0; // 3번 버튼(10초) 누르는 횟수
		
		button1 = t/300; // 1번 버튼을 누르는 횟수 업데이트
		t = t%300; // 1번버튼을 누르고 남은 시간
		button2 = t/60; // 2번 버튼을 누르는 횟수 업데이트
		t = t%60; // 2번버튼을 누르고 남은 시간
		button3 = t/10; // 3번 버튼을 누르는 횟수 업데이트
		t = t%10; // 3번버튼을 누르고 남은 시간
		
		
		if(t != 0) { // 남아있는 초가 있다면 -> t초 맞출 수 없는것!
			System.out.println(-1);
		}
		else { // 남아있는 초가 있다면 -> "1번 버튼 누른횟수    2번 버튼 누른횟수     3번 버튼 누른횟수"  출력
			System.out.println(button1 + " " + button2 + " " + button3);
		}
		
	}
}
