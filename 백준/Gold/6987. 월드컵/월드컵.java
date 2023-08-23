import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static String input1;
	static String input2;
	static String input3;
	static String input4;
	
	// 6C2 = 15가지 조합
	static int[][] arr = {{0,1},{0,2},{0,3},{0,4},{0,5},
						  {1,2},{1,3},{1,4},{1,5},
						  {2,3},{2,4},{2,5},
						  {3,4},{3,5},
						  {4,5}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 4가지 결과 입력받기
		input1 = br.readLine();
		input1 = input1.replaceAll(" ", "");
		input2 = br.readLine();
		input2 = input2.replaceAll(" ", "");
		input3 = br.readLine();
		input3 = input3.replaceAll(" ", "");
		input4 = br.readLine();
		input4 = input4.replaceAll(" ", "");
		
		dfs(0);
		
		System.out.print(result1 + " " + result2 + " " + result3 + " " + result4);
	}
	
	static int result1 = 0;
	static int result2 = 0;
	static int result3 = 0;
	static int result4 = 0;
	
	static char[] s = "000000000000000000".toCharArray();
	
	static void dfs(int count) {
		if(count == 5) { // A는 이미 승무패 결정
			String temp = String.valueOf(s).substring(0, 3);
			if(!input1.substring(0,3).equals(temp) &&
			   !input2.substring(0,3).equals(temp) &&
			   !input3.substring(0,3).equals(temp) &&
			   !input4.substring(0,3).equals(temp)) {
				return;
			}
		}
		else if(count == 9) { // B는 이미 승무패 결정
			String temp = String.valueOf(s).substring(0, 6);
			if(!input1.substring(0,6).equals(temp) &&
			   !input2.substring(0,6).equals(temp) &&
			   !input3.substring(0,6).equals(temp) &&
			   !input4.substring(0,6).equals(temp)) {
				return;
			}			
		}
		else if(count == 12) { // C는 이미 승무패 결정
			String temp = String.valueOf(s).substring(0, 9);
			if(!input1.substring(0,9).equals(temp) &&
			   !input2.substring(0,9).equals(temp) &&
			   !input3.substring(0,9).equals(temp) &&
			   !input4.substring(0,9).equals(temp)) {
				return;
			}				
		}
		else if(count == 14) { // D는 이미 승무패 결정
			String temp = String.valueOf(s).substring(0, 12);
			if(!input1.substring(0,12).equals(temp) &&
			   !input2.substring(0,12).equals(temp) &&
			   !input3.substring(0,12).equals(temp) &&
			   !input4.substring(0,12).equals(temp)) {
				return;
			}				
		}
		else if(count == 15) {
			if(String.valueOf(s).equals(input1)) result1 = 1;
			if(String.valueOf(s).equals(input2)) result2 = 1;
			if(String.valueOf(s).equals(input3)) result3 = 1;
			if(String.valueOf(s).equals(input4)) result4 = 1;
			
			return;
		}
		
		// 1번째꺼 이기고, 2번째꺼 지는경우
		s[arr[count][0]*3]++;
		s[arr[count][1]*3+2]++;
		dfs(count+1);
		//원상 복귀
		s[arr[count][0]*3]--;
		s[arr[count][1]*3+2]--;	
		
		// 1번째꺼 지고, 2번째꺼 이기는경우
		s[arr[count][0]*3+2]++;
		s[arr[count][1]*3]++;
		dfs(count+1);
		//원상 복귀
		s[arr[count][0]*3+2]--;
		s[arr[count][1]*3]--;		
		
		// 둘다 비기는 경우
		s[arr[count][0]*3+1]++;
		s[arr[count][1]*3+1]++;	
		dfs(count+1);
		//원상 복귀
		s[arr[count][0]*3+1]--;
		s[arr[count][1]*3+1]--;				
	}
}
