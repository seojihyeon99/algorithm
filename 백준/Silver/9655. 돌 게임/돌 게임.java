/**
 * f(n) : n개의 돌이 주어졌을 때, 이기는 사람
 * f(n) = f(n-1) + 1 = !f(n-1) : n-1개의 돌이 주어졌을 때, 이기지 않는 사람(지는 사람)
 * 
 * f(n-1)이 n-1개의 돌이 주어졌을 때 이기는 사람이므로, 거기서 +1개의 돌이 추가됐을때는 당연히 그 다음 사람이 가져갈 수 밖에 없음
 * (돌을 1개 또는 3개로 밖에 못가져감!! 즉, 2개를 가져갈 순 x므로 n-1개의 돌과 n개의 돌일 때는 당연히 이기는 사람이 다름!!)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		if(n%2 == 1) System.out.println("SK");
		else System.out.println("CY");
	}
}