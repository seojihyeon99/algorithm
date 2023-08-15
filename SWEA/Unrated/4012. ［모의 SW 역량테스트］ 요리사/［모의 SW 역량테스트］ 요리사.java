import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int[][] synergy; // 시너지 저장
    static int n; // 식재료의 수
    static boolean[] selected; // 식재료 선택 여부 저장
    static int mindiff;
    static int allsum;
    
    static void combination(int idx, int count) { // idx: 현재 보는 식재료의 인덱스, count: 현재까지 고른 재료의 개수
        // 절반의 식재료를 골랐다면 -> 종료
        if(count == n/2) {
            // 재료의 수만큼 고른 재료의 합을 더함
            int choose = 0;
            for(int r=0; r<n; r++) {
                for(int c=0; c<n; c++) {
                    if(selected[r] == true && selected[c] == true) {
                        choose+=synergy[r][c];
                    }
                }
            }
            int notchoose = 0;
            for(int r=0; r<n; r++) {
                for(int c=0; c<n; c++) {
                    if(selected[r] == false && selected[c] == false) {
                    	notchoose+=synergy[r][c];
                    }
                }
            }
            if(Math.abs(choose-notchoose) < mindiff) {
//                System.out.println("현재 선택한거 : " + choose + ", 현재 선택하지 않은거 : " +notchoose);
                mindiff = Math.abs(choose-notchoose);
            }
            
            return;
        }
        
        for(int i = idx; i < n; i++) { // 현재 고를수 있는 재료들 리스트~?
            selected[i] = true; // 현재 식재료를 택함 o
            combination(i+1, count+1); // 다음 식재료로 떠남
            selected[i] = false; // 현재 식재료를 택하지 x
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int t = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        
        // 테스트 케이스만큼 반복
        for(int i=1; i<=t; i++) {
            n = Integer.parseInt(br.readLine()); // 식재료의 수
            
            selected = new boolean[n];
            mindiff = Integer.MAX_VALUE;
            allsum = 0;
            
            // 시너지 저장하는 배열 생성 및 초기화
            synergy = new int[n][n];
            for(int r=0; r<n; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int c=0; c<n; c++) {
                    synergy[r][c] = Integer.parseInt(st.nextToken());
                 }
            }
            
            combination(0, 0);
            
            System.out.println("#" + i + " " + mindiff);
        }
    }
}