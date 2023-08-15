public class Solution {

	public static void main(String[] args) {
		char[] arr = new char[]{'+','+','+','+','+'};
		 for(int i=0; i<5; i++) {
			 arr[i]='#';
			 System.out.println(arr);
			 arr[i]='+';
		 }
	}

}
