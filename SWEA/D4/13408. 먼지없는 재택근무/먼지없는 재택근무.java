import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
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
             
            // 커맨드 수만큼 반복
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
         
        bw.write(sb.toString());
        bw.flush(); 
    }
     
    static void clean(int idx) { // idx : 현재 커맨드의 인덱스
        List<int[]> pos = new ArrayList<>(); // 가까운 먼지들 좌표 저장
        int minDist = Integer.MAX_VALUE; // 최소거리
        char cmd = command[idx];
        switch(cmd) {
        case 'L':
            for(int r=0; r<n; r++) {
                for(int c=0; c<n; c++) {
                    if(arr[r][c] == 1 && r-c >= row-col && r+c <= row+col) { // 먼지가 존재하고, 방사형 공간내에 있다면
                        int dist = calDistance('L',r,c,row,col);
                        if(dist < minDist) {
                            pos.clear();
                            minDist = dist;
                            pos.add(new int[] {r,c});
                        }
                        else if(dist == minDist) {
                            pos.add(new int[] {r,c});                           
                        }
                    }
                }
            }
            break;
        case 'R':
            for(int r=0; r<n; r++) {
                for(int c=0; c<n; c++) {
                    if(arr[r][c] == 1 && r-c <= row-col && r+c >= row+col) { // 먼지가 존재하고, 방사형 공간내에 있다면
                        int dist = calDistance('R',r,c,row,col);
                        if(dist < minDist) {
                            pos.clear();
                            minDist = dist;
                            pos.add(new int[] {r,c});
                        }
                        else if(dist == minDist) {
                            pos.add(new int[] {r,c});                           
                        }
                    }
                }
            }           
            break;
        case 'U':
            for(int r=0; r<n; r++) {
                for(int c=0; c<n; c++) {
                    if(arr[r][c] == 1 && r-c <= row-col && r+c <= row+col) { // 먼지가 존재하고, 방사형 공간내에 있다면
                        int dist = calDistance('U',r,c,row,col);
                        if(dist < minDist) {
                            pos.clear();
                            minDist = dist;
                            pos.add(new int[] {r,c});
                        }
                        else if(dist == minDist) {
                            pos.add(new int[] {r,c});                           
                        }
                    }
                }
            }           
            break;
        case 'D':
            for(int r=0; r<n; r++) {
                for(int c=0; c<n; c++) {
                    if(arr[r][c] == 1 && r-c >= row-col && r+c >= row+col) { // 먼지가 존재하고, 방사형 공간내에 있다면
                        int dist = calDistance('D',r,c,row,col);
                        if(dist < minDist) {
                            pos.clear();
                            minDist = dist;
                            pos.add(new int[] {r,c});
                        }
                        else if(dist == minDist) {
                            pos.add(new int[] {r,c});                           
                        }
                    }
                }
            }               
            break;
        }
         
        // 제거할 먼지가 없다면
        if(pos.size() == 0) {
            return;
        }
         
        int x = -1, y = -1; // 제일 오른쪽 좌표  
         
        switch(cmd) {
        case 'L' : // 왼쪽 방향 바라본다면, 제일 오른쪽 좌표는 제일 위쪽 좌표임
            int toppos = Integer.MAX_VALUE;
            for(int i=0; i<pos.size(); i++) {
                if(pos.get(i)[0] < toppos) {
                    toppos = pos.get(i)[0];
                    x = pos.get(i)[0];
                    y = pos.get(i)[1];
                }
            }
             
            break;
        case 'R' : // 오른쪽 방향 바라본다면, 제일 오른쪽 좌표는 제일 아래쪽 좌표임
            int bottompos = Integer.MIN_VALUE;
            for(int i=0; i<pos.size(); i++) {
                if(pos.get(i)[0] > bottompos) {
                    bottompos = pos.get(i)[0];
                    x = pos.get(i)[0];
                    y = pos.get(i)[1];
                }
            }   
            break;
        case 'U' : // 위쪽 방향 바라본다면, 제일 오른쪽 좌표는 제일 오른쪽 좌표임
            int rightpos = Integer.MIN_VALUE;
            for(int i=0; i<pos.size(); i++) {
                if(pos.get(i)[1] > rightpos) {
                    rightpos = pos.get(i)[1];
                    x = pos.get(i)[0];
                    y = pos.get(i)[1];
                }
            }   
            break;
        case 'D' : // 아래쪽 방향 바라본다면, 제일 오른쪽 좌표는 제일 왼쪽 좌표임
            int leftpos = Integer.MAX_VALUE;
            for(int i=0; i<pos.size(); i++) {
                if(pos.get(i)[1] < leftpos) {
                    leftpos = pos.get(i)[1];
                    x = pos.get(i)[0];
                    y = pos.get(i)[1];
                }
            }           
            break;
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
        case 'L' :
        case 'R' :          
            return Math.abs(y1-y2)*100 + Math.abs(x1-x2);
        case 'U' :
        case 'D' :
            return Math.abs(x1-x2)*100 + Math.abs(y1-y2);
        }
        return 0;
    }
}