import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [아이디어]
 * U(up),D(down),L(left),R(right)을 만나는 경우 -> 방향을 해당 방향으로 바꾸고, 맵의 인덱스를 넘지 않고 평지라면 그 방향으로 1칸 전진
 * 
 * S(shoot)을 만나는 경우
 * 포탄이 그냥 맵 밖으로 나가는 경우
 * 포탄이 벽돌벽에 부딪히는 경우 : 벽돌벽칸 -> 평지. 포탄 소멸
 * 포탄이 강철벽에 부딪히는 경우 : 아무일도 발생 x. 포탄 소멸 
 * 
 * [메모리]
 * 20,180 kb
 * [시간]
 * 124 ms
 * @author SSAFY
 *
 */

public class Solution {
    static char[][] map; // 게임 맵 정보
    static int h, w, n; // 맵의 높이, 맵의 너비, 사용자가 넣을 입력 개수
    static int tankx, tanky; //  y좌표
    static int[] op; // 사용자의 입력 정보(U/D/L/R/S) 받음
    // 방향벡터(상,하,좌,우)
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int tc = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        
        // 테스트 케이스만큼 입력 받기
        for(int t=1; t<=tc; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken()); // 맵의 높이
            w = Integer.parseInt(st.nextToken()); // 맵의 너비
            
            // 게임 맵 생성 및 입력받기
            String s;
            map = new char[h][w];
            for(int r=0; r<h; r++) {
                s = br.readLine();
                for(int c=0; c<w; c++) {
                    map[r][c] = s.charAt(c);
                    // 전차의 위치이면
                    if(map[r][c] == '<' ||map[r][c] == '>'||map[r][c] == '^'||map[r][c] == 'v') {
                        tankx = r; // 전차의 x좌표 업데이트
                        tanky = c; // 전차의 y좌표 업데이트
                    }
                }
            }
            
            n = Integer.parseInt(br.readLine()); // 사용자가 넣을 입력의 개수
            
            // 사용자 입력 받는 배열 생성 및 입력 받기
            s = br.readLine();
            op = new int[n]; 
            for(int i=0; i<n; i++) {
                op[i] = s.charAt(i);
            }
            
            // 사용자 입력 수만큼 시뮬레이션 돌림
            for(int i=0; i<n; i++) {
                simulation(i);
            }
            
            //출력
            sb.append("#"+t+" ");
            for(int r=0; r<h; r++) {
                for(int c=0; c<w; c++) {
                    sb.append(map[r][c]);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }
    
    static void simulation(int idx) { // idx는 현재 보는 사용자 입력 인덱스
        int x, y; // 전차가 이동할 x좌표, y좌표
        switch(op[idx]) {
        // 이동 관련
        case 'U':
            map[tankx][tanky] = '^';
            x = tankx+dx[0]; // 해당 방향으로 1칸 이동할 x좌표
            y = tanky+dy[0]; // 해당 방향으로 1칸 이동할 y좌표
            // 맵의 인덱스 넘지 않고, 평지라면 -> 1칸 전진 가능
            if(x>=0 && x<h && y>=0 && y<w && map[x][y] == '.') {
                map[tankx][tanky] = '.'; // 현재 위치를 평지로 바꾸고
                tankx = x; // 전차의 x좌표 업데이트
                tanky = y; // 전차의 y좌표 업데이트 
                map[tankx][tanky] = '^'; // 새로 업데이트된 위치에 전차 기록
            }
            break;
        case 'D':
            map[tankx][tanky] = 'v';
            x = tankx+dx[1]; // 해당 방향으로 1칸 이동할 x좌표
            y = tanky+dy[1]; // 해당 방향으로 1칸 이동할 y좌표
            // 맵의 인덱스 넘지 않고, 평지라면 -> 1칸 전진 가능
            if(x>=0 && x<h && y>=0 && y<w && map[x][y] == '.') {
                map[tankx][tanky] = '.'; // 현재 위치를 평지로 바꾸고
                tankx = x; // 전차의 x좌표 업데이트
                tanky = y; // 전차의 y좌표 업데이트 
                map[tankx][tanky] = 'v'; // 새로 업데이트된 위치에 전차 기록
            }
            break;
        case 'L':
            map[tankx][tanky] = '<';
            x = tankx+dx[2]; // 해당 방향으로 1칸 이동할 x좌표
            y = tanky+dy[2]; // 해당 방향으로 1칸 이동할 y좌표
            // 맵의 인덱스 넘지 않고, 평지라면 -> 1칸 전진 가능
            if(x>=0 && x<h && y>=0 && y<w && map[x][y] == '.') {
                map[tankx][tanky] = '.'; // 현재 위치를 평지로 바꾸고
                tankx = x; // 전차의 x좌표 업데이트
                tanky = y; // 전차의 y좌표 업데이트 
                map[tankx][tanky] = '<'; // 새로 업데이트된 위치에 전차 기록
            }
            break;
        case 'R':
            map[tankx][tanky] = '>';
            x = tankx+dx[3]; // 해당 방향으로 1칸 이동할 x좌표
            y = tanky+dy[3]; // 해당 방향으로 1칸 이동할 y좌표
            // 맵의 인덱스 넘지 않고, 평지라면 -> 1칸 전진 가능
            if(x>=0 && x<h && y>=0 && y<w && map[x][y] == '.') {
                map[tankx][tanky] = '.'; // 현재 위치를 평지로 바꾸고
                tankx = x; // 전차의 x좌표 업데이트
                tanky = y; // 전차의 y좌표 업데이트 
                map[tankx][tanky] = '>'; // 새로 업데이트된 위치에 전차 기록
            }
            break;
        // 포탄 발사 관련
        case 'S':
            int shotx = tankx; // 포탄의 x위치
            int shoty = tanky; // 포탄의 y위치
            
            // 탱크의 방향 기록하기
            int dir = 0;
            if(map[tankx][tanky] == '^') {
                dir = 0; // 위방향이라면 0으로
            } else if(map[tankx][tanky] =='v') {
                dir = 1; // 아래방향이라면 0으로
            } else if(map[tankx][tanky] == '<') {
                dir = 2; // 왼쪽방향이라면 0으로
            } else if(map[tankx][tanky] == '>') {
                dir = 3; // 오른쪽방향이라면 0으로
            }
            
            // 포탈 발쏴아아아~~~!!
            while(true) {
                int nextshotx = shotx + dx[dir]; // 포탄의 날아간 x위치
                int nextshoty = shoty + dy[dir]; // 포탄의 날아간 y위치
                
                // 포탄이 맵 범위를 벗어났다면 -> break
                if(!(nextshotx>=0 && nextshotx<h && nextshoty>=0 && nextshoty<w)) {
                    break;
                }
                // 포탄이 벽돌벽에 부딪히는 경우 : 벽돌벽칸 -> 평지. 포탄 소멸
                if(map[nextshotx][nextshoty] == '*') {
                    map[nextshotx][nextshoty] = '.';
                    break;
                    
                }
                // 포탄이 강철벽에 부딪히는 경우 : 아무일도 발생 x. 포탄 소멸 
                else if(map[nextshotx][nextshoty] == '#') {
                    break;
                }
                // 포탄이 벽돌벽도 아니고 강철벽도 아닐 경우 -> 포탄 1칸 전진
                else {
                    shotx = nextshotx; // 포탄의 x위치 업데이트
                    shoty = nextshoty; // 포탄의 y위치 업데이트
                }
            }        
            break;
        }
    }
}