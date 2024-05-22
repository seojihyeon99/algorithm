import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 전체 맵 세로 길이
        int m = Integer.parseInt(st.nextToken()); // 전체 맵 가로 길이
        int k = Integer.parseInt(st.nextToken()); // 스티커의 개수
        boolean[][] isExist = new boolean[n][m]; // 해당 칸에 스티커 존재 여부

        Queue<List<int[]>> queue = new ArrayDeque<>(); // 스티커의 정보를 담고있는 큐

        // 스티커들의 좌표 입력받기
        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            int rsize = Integer.parseInt(st.nextToken()); // 스티커의 세로 길이
            int csize = Integer.parseInt(st.nextToken()); // 스티커의 가로 길이

            List<int[]> list = new ArrayList<>(); // 현재 스티커의 정보 담는 리스트

            for(int r=0; r<rsize; r++) {
                st = new StringTokenizer(br.readLine());
                for(int c=0; c<csize; c++) {
                    int cur = Integer.parseInt(st.nextToken());
                    // 스티커가 존재한다면, 스티커 정보 넣음
                    if(cur == 1) {
                        list.add(new int[]{r,c,rsize,csize}); // x좌표, y좌표, 세로길이, 가로길이
                    }
                }
            }

            queue.add(list);
        }

        // 스티커 붙히기
        for(int i=0; i<k; i++) {
            List<int[]> sticker = queue.poll(); // 현재 스티커의 정보

            for(int j=0; j<4; j++) {
                // 스티커를 붙일 수 있는 좌표 찾음
                int[] pos = findPos(n, m, isExist, sticker);

                // 스티커를 붙일 수 있는 경우
                if(pos[0] >= 0 && pos[1] >= 0) {
                    // 스티커 붙힘
                    isExist = attachSticker(n, m, isExist, sticker, pos[0], pos[1]);
                    break;
                }

                // 스티커를 붙일 수 없는 경우, 스티커를 시계방향으로 90도 회전시킴
                rotateStiker(n, m, sticker);
            }

        }

        // 스티커 붙은 칸 수 출력
        int cnt = 0;
        for(int r=0; r<n; r++) {
            for(int c=0; c<m; c++) {
                if(isExist[r][c]) cnt++;
            }
        }

        System.out.println(cnt);
    }

    static void rotateStiker(int n, int m, List<int[]> sticker) {
        for(int i=0; i<sticker.size(); i++) {
            int[] cur = sticker.get(i);
            int curx = cur[0]; // 현재 스티커 조각의 상대 x좌표
            int cury = cur[1]; // 현재 스티커 조각의 상대 y좌표

            int rlen = cur[2]; // 현재 세로 길이
            int clen = cur[3]; // 현재 가로 길이

            // 좌표는 (r,c) => (c, (n-1)-r)이되고, 세로와 가로 길이도 바뀜
            sticker.set(i, new int[]{cury, (rlen-1)-curx, clen, rlen});
        }
    }

    static boolean[][] attachSticker(int n, int m, boolean[][] isExsit, List<int[]> sticker, int sx, int sy) {
        // 원본 배열 복사
        boolean[][] newMap = new boolean[n][m];
        for(int i=0; i<n; i++) {
            newMap[i] = Arrays.copyOf(isExsit[i], m);
        }

        for(int i=0; i<sticker.size(); i++) {
            int[] cur = sticker.get(i);
            int curx = sx + cur[0]; // 현재 스티커 조각의 실제 x좌표
            int cury = sy + cur[1]; // 현재 스티커 조각의 실제 y좌표

            // 현재 스티커 조각을 붙힘
            newMap[curx][cury] = true;
        }

        return newMap;
    }

    static int[] findPos(int n, int m, boolean[][] isExist, List<int[]> sticker) {

        // 좌상단부터 스티커를 붙힐 수 있는 영역 찾음
        for(int r=0; r<n; r++) {
            for(int c=0; c<m; c++) {
                // 모든 스티커 조각 붙힐 수 있는 경우, 해당 위치를 반환
                if(canAttach(n, m, isExist, sticker, r, c)) {
                    return new int[]{r, c};
                }
            }
        }

        return new int[] {-1, -1};
    }

    static boolean canAttach(int n , int m, boolean[][] isExist, List<int[]> sticker, int sx, int sy) {

        for(int i=0; i<sticker.size(); i++) {
            int[] cur = sticker.get(i);
            int curx = sx + cur[0]; // 현재 스티커 조각의 실제 x좌표
            int cury = sy + cur[1]; // 현재 스티커 조각의 실제 y좌표

            // 노트북 벗어나면 스티커 붙일 수 없음
            if(curx<0 || curx>=n || cury<0 || cury>=m) {
                return false;
            }

            // 다른 스티커와 겹치면, 스티커 붙일 수 없음
            if(isExist[curx][cury]) {
                return false;
            }
        }

        return true;
    }
}