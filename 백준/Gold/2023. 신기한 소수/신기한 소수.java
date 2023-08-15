import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    static int[] first = {2,3,5,7};
    static int[] remain = {1,3,5,7,9};
    static int n;
    
    static void isPrime(int num, int cur) { // num은 현재 수, cur은 현재 자리
        if(cur == n-1) {
            System.out.println(num);
            return;
        }
        
//        System.out.println("현재 전달받은 수 : "+num);
        int curnum = num*10;
        for(int i=1; i<=9; i+=2) {
            int tempnum = curnum + i;
            boolean isPrimeNumber = true;
            
            for(int j=2; j<=tempnum-1; j++) {
                // 소수가 아니라면
                if(tempnum % j == 0) {
                    isPrimeNumber = false;
                    break;
                }
            }
            
            if(isPrimeNumber) {
                isPrime(tempnum, cur+1);
            
            }        
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        
        
        isPrime(2,0);
        isPrime(3,0);
        isPrime(5,0);
        isPrime(7,0);
    }
}