import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int cnt, n;
    static int visit_y[] = new int[16]; //아래로 확인
    static int visit_l[] = new int[33]; //왼쪽에서 내려오는 대각선 확인
    static int visit_r[] = new int[33]; //오른쪽에서 내려오는 대각선 확인

    static void line(int x)
    {
    	//종료조건
        if(x==n)
        {
            cnt++;
            return;
        }

        for(int i=0;i<n;i++)
        {
            if(visit_y[i]==0&&visit_l[x-i+(n-1)]==0&&visit_r[x+i]==0) {
                visit_y[i]=1;
                visit_l[x-i+(n-1)]=1;
                visit_r[x+i]=1;
                //재귀호출구문
                line(x+1);
                //다시 다음 열을 위한 초기화
                visit_y[i]=0;
                visit_l[x-i+(n-1)]=0;
                visit_r[x+i]=0;
            }
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        line(0);

        System.out.println(cnt);
    }

}