
import java.io.*;
import java.util.*;
public class Solution {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		for(int i=1; i<=t; i++) {
			String s = br.readLine();
			int month = Integer.parseInt(s.substring(4,6));
			int day = Integer.parseInt(s.substring(6,8));
			
			String result = "-1";
			if (month>=1 && month<=12 ){
				if(month==1 ||month==3||month==5 || month==7
						||month==8 ||month==10|| month==12) {
					if(day>=1 && day<=31) {
						result = s.substring(0,4)+"/"+s.substring(4,6)+"/"+s.substring(6,8);
					}
				}
				else if(month==4||month==6||month==9||month==11) {
					if(day>=1 && day<=30) {
						result = s.substring(0,4)+"/"+s.substring(4,6)+"/"+s.substring(6,8);
					}					
				}
				else if(month==2) {
					if(day>=1 && day<=28) {
						result = s.substring(0,4)+"/"+s.substring(4,6)+"/"+s.substring(6,8);
					}					
				}
				
			}
			System.out.printf("#%d %s\n", i, result);
		}
	}
}
