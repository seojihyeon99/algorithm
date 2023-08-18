import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

                 
public class Main {
    static int n; // 행크기
    static int m; // 열크기
    static int d; // 궁수의 공격 거리 제한
    static int[][] arr;
    static int enemysum; // 적의수
    static int maxkill; // 죽일 수 있는 적의 최대 수
    static boolean[] archer; // 궁수의 배치 열 인덱스 저장
    
    static List<int[]> list = new ArrayList<>();
    
    // 방향벡터(좌,우,상)
    static int[] dx = {0,-1,0};
    static int[] dy = {-1,0,1};
    
    static void comb(int start, int count) { // start : 현재 보기를 시작하는 궁수 열 인덱스, count : 현재까지 배치한 궁수 수
        // 궁수를 모두 배치 완료했다면
        if(count == 3) {
            // 배열 임시로 복사 및 생성
            int[][] map = new int[n][m];
            for(int i=0; i<n; i++) {
                map[i] = Arrays.copyOf(arr[i], m);
            }

            int killcnt = 0;
            int enemycnt = enemysum;
            // 게임 시작
            while(true) {
            	list.clear(); // 다음을 위해 리스트 초기화 
            	
                for(int i=0; i<m; i++) { // 열크기만큼 반복하면서
                    if(archer[i]) { // 해당 열에 궁수가 배치됐으면
                        int[] curinfo = findNear(map, i); //0:x좌표, 1:y좌표, 2:거리값
                        if(curinfo[2]<=d) { // 궁수의 공격 거리 제한 내에 있으면 -> 공격을 위해 리스트에 추가
                            list.add(new int[] {curinfo[0], curinfo[1]});
                        }
                    }
                }
                
                // 적 제거 시작
                for(int i=0; i<list.size(); i++) {
                    if(map[list.get(i)[0]][list.get(i)[1]] != 0) {
                        map[list.get(i)[0]][list.get(i)[1]] = 0;
                        list.remove(i); // 현재 적 제거함
                        i--; // 제거했으므로 인덱스 바뀌므로 +1 해줌
                        killcnt++; // 죽인 개수 1 증가
                        enemycnt--; // 남은 적의 수 1 감소
                    }
                }
                
                
                // 궁수의 공격이 끝나면, 적 아래로 1칸 이동
                for(int r=n-1; r>=0; r--) {
                    for(int c=0; c<m; c++) {
                        if(map[r][c] == 1) { // 적이 있다면
                            if(r+1 >=n) { // 배열 범위 벗어난다면
                                map[r][c] = 0; // 그냥 적 out!
                                enemycnt--; // 적의 개수 1개 감소
                            }
                            else {
                                map[r+1][c] = 1; // 적 1칸 아래로 이동
                                map[r][c] = 0; // 해당 칸에 이제 적 없음(0)으로 표시
                            }
                        }
                    }
                }
                
                // 적이 모두 없어지면 -> 게임 종료
                if(enemycnt <= 0) {
                    if(maxkill < killcnt) {
                        maxkill = killcnt;
                    }
                    break;
                }

            }
            
            return;
        }
        
        for(int i=start; i<m; i++) {
            archer[i] = true; // 현재 열에 궁수 배치
            comb(i+1, count+1); // 다음 반복으로 넘어감
            
            archer[i] = false; // 현재 열에 궁수 배치하지 않음
        }
    }
    
    // 가장 가까운 적 찾기 -> 반환값 : x좌표, y좌표, 거리값
//    public static int[] findNear(int[][] map, int col) { // col : 현재 궁수의 열 위치
//        int mindist = Integer.MAX_VALUE;
//        int minx=0;
//        int miny=0;
//        for(int r=n-1; r>=0; r--) {
//            for(int c=0; c<m; c++) { // 왼쪽부터 열을 보므로 -> 문제 조건의 여러명일경우 가장 왼쪽에 있는 적 해결
//                if(map[r][c] == 1) {
//                    int tempdist = Math.abs(r-n)+Math.abs(c-col); // 현재 적 ~ 현재 궁수까지의 거리
//                    if(tempdist < mindist) { // 더 작으면 -> 업데이트
//                        mindist = tempdist;
//                        minx = r;
//                        miny = c;
//                    }
//                }
//            }
//        }
//        return new int[] {minx,miny,mindist};
//    }
    
    // bfs로 가장 가까운 적 찾기 -> 반환값 : x좌표, y좌표, 거리값
    public static int[] findNear(int[][] map, int col) { // col : 현재 궁수의 열 위치
        int mindist=0;
        int minx=0;
        int miny=0;
        
        boolean[][] visited = new boolean[n][m];
        
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {n-1,col,1}); 
        visited[n-1][col] = true;
        
        while(!queue.isEmpty()) {
        	int[] cur = queue.poll();
        	
        	if(map[cur[0]][cur[1]]==1) {
        		minx = cur[0];
        		miny = cur[1];
        		mindist = cur[2];
        		break;
        	}
        	
        	for(int i=0; i<3; i++) {
        		int x = cur[0] + dx[i];
        		int y = cur[1] + dy[i];
        		if(x>=0 && x<n && y>=0 && y<m && !visited[x][y]) {
        			visited[x][y] = true;
        			queue.offer(new int[] {x,y, cur[2]+1});
        		}
        	}
        }
        
        return new int[] {minx,miny,mindist};
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 행크기
        m = Integer.parseInt(st.nextToken()); // 열크기
        d = Integer.parseInt(st.nextToken()); // 궁수의 공격 거리 제한
        
        // 배열 생성 및 초기화
        arr = new int[n][m];
        for(int r=0; r<n; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<m; c++) {
                arr[r][c] = Integer.parseInt(st.nextToken());
                if(arr[r][c] == 1) enemysum++; // 만약 적이라면, 적의 숫자 1 증가
            }
        }
        
        archer = new boolean[m]; // 궁수의 배치 열 인덱스 저장
        
        // 궁수의 공격의로 제거할 수 있는 적의 최대수
        comb(0, 0);
        
        System.out.println(maxkill);
    }
    
}