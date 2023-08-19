import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine()); // 테스트 케이스 수
		// 테스트 케이스수만큼 반복
		for(int t=1; t<=tc; t++) { 
			int treenum = Integer.parseInt(br.readLine()); // 나무의 개수
			
			int maxlen = 0; // 최대 나무 높이
			// 나무들의 높이를 저장하는 배열 생성 및 입력받기
			int[] trees = new int[treenum]; // 나무들의 높이를 저장하는 배열
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<treenum; i++) {
				int tree = Integer.parseInt(st.nextToken()); // 현재 입력받은 나무의 높이
				trees[i] = tree;
				if(tree>maxlen) maxlen = tree; // 현재까지의 최대 나무 높이보다 크다면 업데이트
			}
			
			List<Integer> odddiff = new ArrayList<>(); // 최대 나무 높이(maxlen)와 홀수차가 나는 나무들 저장하는 리스트
			List<Integer> evendiff = new ArrayList<>(); // 최대 나무 높이(maxlen)와 짝수차가 나는 나무들 저장하는 리스트
			
			for(int i=0; i<treenum; i++) { // 나무의 개수만큼 반복하면서
				if(trees[i] == maxlen) continue; // 최대 나무 높이라면 -> 리스트에 넣지 않고 pass~
				
				int curdiff = maxlen-trees[i]; // 최대 나무 높이와의 차를 구해서
				if(curdiff%2 == 1) { // 그 차가 홀수라면
					odddiff.add(curdiff); // 홀수차 리스트에 저장
				}
				else { // 그 차가 짝수라면
					evendiff.add(curdiff); // 짝수차 리스트에 저장
				}
			}
			
			int curdiff = 0; // 현재 최대 나무높이와의 차
			int day = 0; // 최소 날짜 수
			// 나무에 물주기 시작~!
			while(true) {
				if(odddiff.size() == 0 && evendiff.size() == 0) { // 두 리스트가 모두 비었다면 -> 물주기 종료
					break;
				}
				
				day++; // 날짜 1일 증가

				// 홀수날일때
				if(day%2==1) {
					// 홀수차 리스트가 비어있지 않다면
					if(odddiff.size() != 0) { 
						curdiff = odddiff.get(0) - 1; // 홀수차 리스트의 맨 앞을 꺼내어 물 줌(최대 나무높이와의 차가 1 감소)
						odddiff.remove(0); // 홀수차 리스트의 맨 앞은 이미 물을 줬으므로 제거
						// 현재 최대 나무높이의 차가 0이 아니라면(아직 최대나무 높이에 도달 x)
						if(curdiff != 0) {  
							evendiff.add(curdiff); // 홀수 - 1 = 짝수이므로, 짝수차 리스트에 curdiff 저장
						}
					}
					//홀수차 리스트가 비어있다면 -> 짝수차 비어있지 않다면, 짝수차에서 빼와야
					else { 
						int evenmax = Collections.max(evendiff); // 짝수차 리스트에서 최대값 구함
						if(evendiff.size()>1) { // 짝수차의 개수가 1개보다 많다면
							curdiff = evenmax - 1; // 짝수차 리스트의 최댓값 - 1
							evendiff.remove(evendiff.indexOf(evenmax)); // 짝수차 리스트에서 해당 max 인덱스에 해당하는 원소 제거
							odddiff.add(curdiff); // 짝수 - 1 = 홀수이므로, 홀수차 리스트에 curdiff 저장
						}
					}					
				}
				// 짝수날일때
				else {
					//짝수차 리스트가 비어있지 않다면
					if(evendiff.size() != 0) {
						curdiff = evendiff.get(0) - 2; // 짝수차 리스트의 맨 앞을 꺼내어 물 줌(최대 나무높이와의 차가 2 감소)
						evendiff.remove(0); // 짝수차 리스트의 맨 앞은 이미 물을 줬으므로 제거
						// 현재 최대 나무높이의 차가 0이 아니라면(아직 최대나무 높이에 도달 x)
						if(curdiff != 0) {
							evendiff.add(curdiff); // 짝수 - 2 = 짝수이므로, 짝수차 리스트에 curdiff 저장
						}
					}
					//짝수차 리스트가 비어있다면 -> 홀수에서 빼와야
					else {
						int oddmax = Collections.max(odddiff);; // 홀수차 리스트에서 최대값 구함
						if(oddmax > 2) { // 홀수차 최댓값이 2보다 크다면(2 뺄 수 있음)
							curdiff = oddmax - 2; // 홀수차 리스트의 최댓값 - 1
							odddiff.remove(odddiff.indexOf(oddmax)); // 홀수차 리스트에서 해당 max 인덱스에 해당하는 원소 제거
							odddiff.add(curdiff); // 홀수 - 2 = 홀수이므로, 홀수차 리스트에 curdiff 저장
						}
					}
				}
			}

			sb.append("#" + t + " " + day + "\n");
		}
		
		System.out.println(sb.toString());
		
	}
}
