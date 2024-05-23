import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int max = 0;
    static int decreased = 0; // 감소한 블럭수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 배열 입력 받기
        int nblock = 0; // 블록의 수
        int[][] map = new int[n][n];
        for(int r=0; r<n; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int c=0; c<n; c++) {
                map[r][c] = Short.parseShort(st.nextToken());
                if(map[r][c] != 0) nblock++;
            }
        }

        // 블록 이동시키기 (상하좌우)
        for(int i=0; i<4; i++) {
            moveBlock(map, n, i,1, nblock);
        }

        System.out.println(max);

    }

    static void moveBlock(int[][] arr, int n, int dir, int cnt, int nblock) { // 배열, 배열 크기, 방향, 현재 이동 횟수, 남은 블럭의 수
        // 종료조건
        if(cnt >= 6 || nblock == 1) {
            // 현재 블록들 중에 최댓값 찾음
            max = Math.max(findMax(arr, n), max);
            return;
        }

        int[][] copy = new int[n][n];
        for(int r=0; r<n; r++) {
            copy[r] = Arrays.copyOf(arr[r], n);
        }

        int[][] movedArr = changBlockPos(copy, n, dir);

        // 값을 충돌시켜 합침
        decreased = 0;
        int[][] joinedArr = joinBlock(movedArr, n, dir);

        // 다시 블록 이동
        int[][] movedArr2 = changBlockPos(joinedArr, n, dir);

        // 블록 이동 방향을 바꿔감
        for(int i=0; i<4; i++) {
            // 그 다음 이동
            moveBlock(movedArr2, n, i, cnt+1, nblock - decreased);
        }

    }

    static int findMax(int[][] arr, int n) {
        int result = 0;
        for(int r=0; r<n; r++) {
            for(int c=0; c<n; c++) {
                if(result < arr[r][c]) {
                    result = arr[r][c];
                }
            }
        }
        return result;
    }

    static int[][] joinBlock(int[][] arr, int n, int dir) {
        switch(dir) {
            // 위쪽
            case 0:
                for(int c=0; c<n; c++) {
                    for(int r=0; r<n-1; r++) {
                        int up = r;
                        int down = r+1;

                        if(arr[up][c] == 0 || arr[down][c] == 0) continue; // 백트래킹 (블록 존재하지 않을 경우)

                        else if(arr[up][c] == arr[down][c]) {
                            arr[up][c] *= 2;
                            arr[down][c] = 0;
                            decreased++;
                        }
                    }
                }
                break;
            // 아래쪽
            case 1:
                for(int c=0; c<n; c++) {
                    for(int r=n-1; r>0; r--) {
                        int down = r;
                        int up = r-1;

                        if(arr[up][c] == 0 || arr[down][c] == 0) continue; // 백트래킹 (블록 존재하지 않을 경우)

                        else if(arr[up][c] == arr[down][c]) {
                            arr[down][c] *= 2;
                            arr[up][c] = 0;
                            decreased++;
                        }
                    }
                }
                break;
            // 왼쪽
            case 2:
                for(int r=0; r<n; r++) {
                    for(int c=0; c<n-1; c++) {
                        int left = c;
                        int right = c+1;

                        if(arr[r][left] == 0 || arr[r][right] == 0) continue; // 백트래킹 (블록 존재하지 않을 경우)

                        else if(arr[r][left] == arr[r][right]) {
                            arr[r][left] *= 2;
                            arr[r][right] = 0;
                            decreased++;
                        }
                    }
                }
                break;
            // 오른쪽
            case 3:
                for(int r=0; r<n; r++) {
                    for(int c=n-1; c>0; c--) {
                        int right = c;
                        int left = c-1;

                        if(arr[r][left] == 0 || arr[r][right] == 0) continue; // 백트래킹 (블록 존재하지 않을 경우)

                        else if(arr[r][left] == arr[r][right]) {
                            arr[r][right] *= 2;
                            arr[r][left] = 0;
                            decreased++;
                        }
                    }
                }
                break;
        }

        return arr;
    }

    static int[][] changBlockPos(int[][] arr, int n, int dir) {
        switch(dir) {
            // 위쪽
            case 0:
                for(int c=0; c<n; c++) {
                    for(int r=0; r<n; r++) {
                        // 현재 위치 이전에서 옮겨질 수 있는 위치 찾음
                        int idx = -1;
                        for(int l=0; l<r; l++) { //@@@ 여기 더 최적화 할 수 있을듯
                            if(arr[l][c] == 0) {
                                idx = l;
                                break;
                            }
                        }

                        // 해당 블록을 이동 // @@@ 여기 모듈화 할 수 있을듯
                        if(idx != -1) {
                            int tmp = arr[r][c];
                            arr[idx][c] = tmp;
                            arr[r][c] = 0;
                        }
                    }
                }
                break;
            // 아래쪽
            case 1:
                for(int c=0; c<n; c++) {
                    for(int r=n-1; r>=0; r--) {
                        // 현재 위치 이전에서 옮겨질 수 있는 위치 찾음
                        int idx = -1;
                        for(int l=n-1; l>r; l--) {
                            if(arr[l][c] == 0) {
                                idx = l;
                                break;
                            }
                        }
                        // 해당 블록을 이동
                        if(idx != -1) {
                            int tmp = arr[r][c];
                            arr[idx][c] = tmp;
                            arr[r][c] = 0;
                        }
                    }
                }
                break;
            // 왼쪽
            case 2:
                for(int r=0; r<n; r++) {
                    for(int c=0; c<n; c++) {
                        // 현재 위치 이전에서 옮겨질 수 있는 위치 찾음
                        int idx = -1;
                        for(int l=0; l<c; l++) { //@@@ 여기 더 최적화 할 수 있을듯
                            if(arr[r][l] == 0) {
                                idx = l;
                                break;
                            }
                        }
                        // 해당 블록을 이동
                        if(idx != -1) {
                            int tmp = arr[r][c];
                            arr[r][idx] = tmp;
                            arr[r][c] = 0;
                        }
                    }
                }
                break;
            // 오른쪽
            case 3:
                for(int r=0; r<n; r++) {
                    for(int c=n-1; c>=0; c--) {
                        // 현재 위치 이전에서 옮겨질 수 있는 위치 찾음
                        int idx = -1;
                        for(int l=n-1; l>c; l--) { //@@@ 여기 더 최적화 할 수 있을듯
                            if(arr[r][l] == 0) {
                                idx = l;
                                break;
                            }
                        }
                        // 해당 블록을 이동
                        if(idx != -1) {
                            int tmp = arr[r][c];
                            arr[r][idx] = tmp;
                            arr[r][c] = 0;
                        }
                    }
                }
                break;
        }

        return arr;
    }

}