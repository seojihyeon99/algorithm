import java.io.*;
import java.math.BigInteger;

public class Main {
	static double sum = 0;
	
	// n에 따라 2^0 + 2^1 + 2^2 + ...와 같은 구조임
    static BigInteger sumHanoii(int n) {
        BigInteger sum = BigInteger.ONE;
		for(int i=0; i<n; i++) {
            sum = sum.multiply(BigInteger.TWO);
		}
        return sum.subtract(BigInteger.ONE);
	}
	
	static void printHanoii(int n, int start, int end) {
		int middle = 6-(start+end);
		if(n>1) {
			printHanoii(n-1,start,middle);
			printHanoii(1,start,end);
			printHanoii(n-1,middle,end);
		}
		else {
			System.out.println(start +" " + end);
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		System.out.println(sumHanoii(n));
		
        // n이 20이하일때만 움직임 출력
		if (n<=20) {
			printHanoii(n,1,3);
		}
	}

}
