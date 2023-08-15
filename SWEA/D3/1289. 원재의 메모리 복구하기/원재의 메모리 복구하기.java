import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		for(int i=1; i<=t; i++) {
			int count = 0;
			String s = sc.next();
			String cur = "";
			
			// 현재 초기값(모든 bit를 0으로) 만듦
			for(int j=0; j<s.length(); j++) {
				cur += "0";
			}			
			
			// 문자열의 길이만큼 반복
			for(int j=0; j<s.length(); j++) {
				// 1) 자리 같을 경우 -> pass
				if(cur.charAt(j) == s.charAt(j)) {
					continue;
				}
				// 2) 자리 다를 경우 -> 바꿈
				else {
					char[] temp = cur.toCharArray();
					// 그 자리 뒤를 해당 숫자로 다 바꿈
					for(int k=j; k<s.length(); k++) {
						temp[k] = s.charAt(j);
					}
					cur = String.valueOf(temp); // char[] -> String으로 바꾸는 법!! .toString()아님
					count++;
				}
			}
			
			System.out.println("#" + i + " " + count);
		}
		
	}
}
