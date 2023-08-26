/**
 * [아이디어]
 * 구현
 * 
 * 1) 거리 계산
 * 우리가 아는 유클리드거리, 맨해튼 거리가 아님! -> 문제에 불친절하게 안나와있음 ㅠㅠ
 * 커맨드가 L이거나 R일때 : 청소기 col 좌표 기준으로, 먼지의 col까지의 거리가 가까우면 더 거리 짧음 -> 만약 같다면 청소기 row 좌표 기준으로, 먼지의 row까지의 거리가 가까우면 더 거리 짧음
 * 커맨드가 U이거나 D일때 : 청소기 row 좌표 기준으로, 먼지의 row까지의 거리가 가까우면 더 거리 짧음 -> 만약 같다면 청소기 col 좌표 기준으로, 먼지의 col까지의 거리가 가까우면 더 거리 짧음
 * 
 * 2) 방사형 거리 구하기
 * 해당 방사형 거리에서
 * / 부분은 청소기의 row+col 좌표를 통해 구하고, \ 부분은 청소기의 row-col 좌표를 통해 구함 => 이둘의 범위를 잘 조합하면 구할 수 있음
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
  
public class Solution {
    static int n; // 집 한변의 길이
    static int[][] arr; // 집의 지도
    static char[] command; // 커맨드
    // 현재 로봇의 위치
    static int row;
    static int col;
      
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
          
        int tc = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        // 테스트 케이스 수만큼 반복
        for(int t=1; t<=tc; t++) {
            // 다음 로봇의 시작위치를 위해 초기화
            row = 0;
            col = 0;
            n = Integer.parseInt(br.readLine()); // 집 한변의 길이
              
            // 집의 지도 생성 및 입력받기
            arr = new int[n][n];
            for(int r=0; r<n; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int c=0; c<n; c++) {
                    arr[r][c] = Integer.parseInt(st.nextToken());
                }
            }
              
            // 커맨드 입력받기
            command = br.readLine().toCharArray();
              
            // 커맨드 수만큼 청소기 청소 반복
            for(int i=0; i<command.length; i++) {
                clean(i);
            }
              
            // 먼지 수행 제거 후 결과 출력
            sb.append("#" + t + "\n");
            for(int r=0; r<n; r++) {
                for(int c=0; c<n; c++) {
                    sb.append(arr[r][c] + " ");                 
                }
                sb.append("\n");
            }
        }
          
        System.out.println(sb);
    }
      
    static void clean(int idx) { // idx : 현재 커맨드의 인덱스
        List<int[]> pos = new ArrayList<>(); // 가까운 먼지들 좌표 저장하는 리스트
        int minDist = Integer.MAX_VALUE; // 현재 최소거리
        char cmd = command[idx]; // 현재 커맨드에 해당하는 문자(L/R/U/D)
        switch(cmd) {
        case 'L': // L 커맨드일 경우
        	// 2차원 배열을 순회하며
            for(int r=0; r<n; r++) {
                for(int c=0; c<n; c++) {
                    if(arr[r][c] == 1 && r-c >= row-col && r+c <= row+col) { // 먼지가 존재하고, 방사형 공간내에 있다면
                        int dist = calDistance('L',r,c,row,col); // 해당 거리 계산
                        if(dist < minDist) { // 현재 최소거리보다 더 짧다면 -> 리스트 비우고, 해당 좌표를 추가함
                            pos.clear();
                            minDist = dist; // 현재 최소거리 업데이트
                            pos.add(new int[] {r,c});
                        }
                        else if(dist == minDist) { // 현재 최소거리와 같다면 -> 해당 좌표를 추가함
                            pos.add(new int[] {r,c});                           
                        }
                    }
                }
            }
            break;
        case 'R': // R 커맨드일 경우
        	// 2차원 배열을 순회하며
            for(int r=0; r<n; r++) {
                for(int c=0; c<n; c++) {
                    if(arr[r][c] == 1 && r-c <= row-col && r+c >= row+col) { // 먼지가 존재하고, 방사형 공간내에 있다면
                        int dist = calDistance('R',r,c,row,col); // 해당 거리 계산
                        if(dist < minDist) { // 현재 최소거리보다 더 짧다면 -> 리스트 비우고, 해당 좌표를 추가함
                            pos.clear();
                            minDist = dist; // 현재 최소거리 업데이트
                            pos.add(new int[] {r,c});
                        }
                        else if(dist == minDist) { // 현재 최소거리와 같다면 -> 해당 좌표를 추가함
                            pos.add(new int[] {r,c});                           
                        }
                    }
                }
            }           
            break;
        case 'U': // U 커맨드일 경우
        	// 2차원 배열을 순회하며
            for(int r=0; r<n; r++) {
                for(int c=0; c<n; c++) {
                    if(arr[r][c] == 1 && r-c <= row-col && r+c <= row+col) { // 먼지가 존재하고, 방사형 공간내에 있다면
                        int dist = calDistance('U',r,c,row,col); // 해당 거리 계산
                        if(dist < minDist) { // 현재 최소거리보다 더 짧다면 -> 리스트 비우고, 해당 좌표를 추가함
                            pos.clear();
                            minDist = dist; // 현재 최소거리 업데이트
                            pos.add(new int[] {r,c});
                        }
                        else if(dist == minDist) { // 현재 최소거리와 같다면 -> 해당 좌표를 추가함
                            pos.add(new int[] {r,c});                           
                        }
                    }
                }
            }           
            break;
        case 'D': // D 커맨드일 경우
        	// 2차원 배열을 순회하며
            for(int r=0; r<n; r++) {
                for(int c=0; c<n; c++) {
                    if(arr[r][c] == 1 && r-c >= row-col && r+c >= row+col) { // 먼지가 존재하고, 방사형 공간내에 있다면
                        int dist = calDistance('D',r,c,row,col); // 해당 거리 계산
                        if(dist < minDist) { // 현재 최소거리보다 더 짧다면 -> 리스트 비우고, 해당 좌표를 추가함
                            pos.clear();
                            minDist = dist; // 현재 최소거리 업데이트
                            pos.add(new int[] {r,c});
                        }
                        else if(dist == minDist) { // 현재 최소거리와 같다면 -> 해당 좌표를 추가함
                            pos.add(new int[] {r,c});                           
                        }
                    }
                }
            }               
            break;
        }
          
        // 제거할 먼지가 없다면 -> 해당 커멘드 청소 종료
        if(pos.size() == 0) {
            return;
        }
        
        int x = -1, y = -1; // 현재 방향의 제일 오른쪽 좌표  
        if(pos.size() == 1) {
			x = pos.get(0)[0];
			y = pos.get(0)[1];        	
        }
        else if(pos.size() > 1){
        	switch(cmd) {
        	case 'L' : // 왼쪽 방향 바라본다면, 제일 오른쪽 좌표는 제일 위쪽 좌표임
        		int toppos = Integer.MAX_VALUE;
        		for(int i=0; i<pos.size(); i++) { // 리스트의 크기만큼 순회하며
        			if(pos.get(i)[0] < toppos) { // 행의 좌표가 더 작다면 -> 현재 방향의 제일 오른쪽 좌표 업데이트
        				toppos = pos.get(i)[0]; // 더 작은 행의 좌표 업데이트
        				x = pos.get(i)[0];
        				y = pos.get(i)[1];
        			}
        		}
        		
        		break;
        	case 'R' : // 오른쪽 방향 바라본다면, 제일 오른쪽 좌표는 제일 아래쪽 좌표임
        		int bottompos = Integer.MIN_VALUE;
        		for(int i=0; i<pos.size(); i++) { // 리스트의 크기만큼 순회하며
        			if(pos.get(i)[0] > bottompos) { // 행의 좌표가 더 크다면 -> 현재 방향의 제일 오른쪽 좌표 업데이트
        				bottompos = pos.get(i)[0]; // 더 큰 행의 좌표 업데이트
        				x = pos.get(i)[0];
        				y = pos.get(i)[1];
        			}
        		}   
        		break;
        	case 'U' : // 위쪽 방향 바라본다면, 제일 오른쪽 좌표는 제일 오른쪽 좌표임
        		int rightpos = Integer.MIN_VALUE;
        		for(int i=0; i<pos.size(); i++) { // 리스트의 크기만큼 순회하며
        			if(pos.get(i)[1] > rightpos) { // 열의 좌표가 더 크다면 -> 현재 방향의 제일 오른쪽 좌표 업데이트
        				rightpos = pos.get(i)[1]; // 더 큰 열의 좌표 업데이트
        				x = pos.get(i)[0];
        				y = pos.get(i)[1];
        			}
        		}   
        		break;
        	case 'D' : // 아래쪽 방향 바라본다면, 제일 오른쪽 좌표는 제일 왼쪽 좌표임
        		int leftpos = Integer.MAX_VALUE;
        		for(int i=0; i<pos.size(); i++) { // 리스트의 크기만큼 순회하며
        			if(pos.get(i)[1] < leftpos) { // 열의 좌표가 더 작다면 -> 현재 방향의 제일 오른쪽 좌표 업데이트
        				leftpos = pos.get(i)[1]; // 더 작은 열의 좌표 업데이트
        				x = pos.get(i)[0];
        				y = pos.get(i)[1];
        			}
        		}           
        		break;
        	}       
        }
          
        // 해당 위치의 먼지 제거
        arr[x][y] = 0;
        
        // 현재 로봇의 위치 업데이트
        row = x;
        col = y;
    }
     
    // 거리 계산하는 함수
    static int calDistance(char c, int x1, int y1, int x2, int y2) {
        switch(c) {
        // L이거나 R일때는, 해당 청소기 좌표 기준으로 col까지의 거리를 우선으로보고 -> 그다음 row까지의 거리를 봄 => col에 가중치를 더 많이 주었음!!!
        case 'L' :
        case 'R' :          
            return Math.abs(y1-y2)*100 + Math.abs(x1-x2);
        // U이거나 D일때는, 해당 청소기 좌표 기준으로 row까지의 거리를 우선으로보고 -> 그다음 col까지의 거리를 봄 => row에 가중치를 더 많이 주었음!!!
        case 'U' :
        case 'D' :
            return Math.abs(x1-x2)*100 + Math.abs(y1-y2);
        }
        return 0;
    }
}