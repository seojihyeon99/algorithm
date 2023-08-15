import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 과일의 개수
		int l = Integer.parseInt(st.nextToken()); // 스네이크버드의 초기 길이
		
		// 배열 생성 및 초기화
		int[] fruits = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			fruits[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(fruits);
		
		int cur = 0;
		for(int i=0; i<n; i++) {
			if(fruits[i]<=l) {
				l++;
				cur++;
			}
			else {
				break;
			}
		}
		
		System.out.println(l);
	}
}
