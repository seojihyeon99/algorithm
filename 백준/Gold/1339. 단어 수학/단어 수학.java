import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[] alpha = new int[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int x=0; x<n; x++) {
            String str = br.readLine();

            int len = str.length(); //문자열 길이 구하기
            
            //가장 맨 앞자리의 자릿수(10의 거듭제곱으로)
            int base = (int) Math.pow(10, len-1);

            for (int y=0; y<len; y++) {
                alpha[str.charAt(y)-'A'] += base;
                base /= 10; //그 다음 자릿수로
            }
        }

        Arrays.sort(alpha);

        int maxresult = 0;
        int maxint = 9;
        
        for(int z=25; z>=0; z--) {
        	maxresult += alpha[z]*maxint--;
        	if(maxint==0) break;
        }

        System.out.println(maxresult);
    }
}