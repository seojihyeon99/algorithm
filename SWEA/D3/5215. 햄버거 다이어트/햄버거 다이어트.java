/**
 * [아이디어]
 * 오늘 수업시간에 배운 부분집합 관련 문제였음
 * 재료를 선택 o / 선택 x 를 재료수만큼 반복하여서,
 * 마지막 재료까지 완료하면, 선택된 재료들의 맛점수합과 칼로리점수합을 구해서, 
 * 제한 칼로리 > 칼로리점수합이고, 맛점수합 > 현재최대맛점수이면 맛점수합을 업데이트해줌
 * 
 * [메모리]
 * 20724kb
 * [시간]
 * 1096ms
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	static int n; // 재료수
	static int limitcal; // 제한 칼로리
	static boolean[] selected; // 선택된 재료 저장
	static List<Integer> taste = new ArrayList<>(); // 맛점수 저장 리스트
	static List<Integer> cal = new ArrayList<>(); // 칼로리 저장 리스트
	static int maxtaste; // 최대맛점수
	
	static void subset(int idx, int calsum, int tastesum) { // idx는 현재 몇번째 재료인지(0번부터 시작)
		if(calsum>limitcal) {
			return;
		}

		// 마지막 재료까지 끝났다면
		if(idx == n) {			
			// 최대 칼로리를 넘지않고, 최대맛점수보다 높으면
			if(tastesum>maxtaste) {
				maxtaste = tastesum; // 최대맛점수를 업데이트
			}		
			return;
		}
		
		
		selected[idx] = true; // 현재 재료를 선택
		subset(idx+1, calsum + cal.get(idx), tastesum + taste.get(idx)); // 다음 재료로 넘어감
		
		selected[idx] = false; // 현재 재료를 선택하지 않음
		subset(idx+1, calsum, tastesum); // 다음 재료로 넘어감
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tcase = Integer.parseInt(br.readLine()); // 테스트케이스 수
		
		// 테스트케이스만큼 반복
		for(int t=1; t<=tcase; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			limitcal = Integer.parseInt(st.nextToken());
			
			selected = new boolean[n];
			maxtaste = Integer.MIN_VALUE; // 최대맛점수를 정수의 최솟값으로 초기화
			taste.clear(); // 다음 반복을 위해 리스트 초기화
			cal.clear(); // 다음 반복을 위해 리스트 초기화
			
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				taste.add(Integer.parseInt(st.nextToken())); // 맛저장 리스트에 추가
				cal.add(Integer.parseInt(st.nextToken())); // 칼로리 저장 리스트에 추가
			}
			
			subset(0, 0, 0); // 부분집합 구하는 함수 호출
			
			sb.append("#" + t + " " + maxtaste + "\n");		
		}
		
		System.out.println(sb.toString());
	}
}
