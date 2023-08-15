import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int n; //프로그램 수행 횟수
	static List<String> oplist = new ArrayList<>();
	static List<Integer> numlist = new ArrayList<>();
	static List<Integer> numoplist = new ArrayList<>();
	static int j=0;
	static Long result1, result2, opresult;
	static Stack<Integer> process = new Stack<>();
	
	static String operate(int idx) {
		j=0;
		for(int i=0; i<oplist.size(); i++) {
			if(oplist.get(i).equals("NUM")) {
				process.push(numoplist.get(j++));
			}
			else if(oplist.get(i).equals("POP")) {
				if(process.isEmpty()) return "ERROR"; 
				result1 = Long.valueOf(process.pop());
				if(result1>=1000000000 || result1<=-1000000000) return "ERROR";
			}
			else if(oplist.get(i).equals("INV")) {
				if(process.isEmpty()) return "ERROR"; 
				result1 = Long.valueOf(process.pop());
				if(result1>=1000000000 || result1<=-1000000000) return "ERROR";
				process.push(-result1.intValue());
			}
			else if(oplist.get(i).equals("DUP")) {
				if(process.isEmpty()) return "ERROR"; 
				result1 = Long.valueOf(process.peek());
				if(result1>=1000000000 || result1<=-1000000000) return "ERROR";
				process.push(result1.intValue());
			}
			else if(oplist.get(i).equals("SWP")) {
				if(process.isEmpty()) return "ERROR";
				result1 = Long.valueOf(process.pop());
				if(result1>=1000000000 || result1<=-1000000000) return "ERROR";
				int temp1 = result1.intValue();
				if(process.isEmpty()) return "ERROR";
				result2 = Long.valueOf(process.pop());
				if(result2>=1000000000 || result2<=-1000000000) return "ERROR";				
				int temp2 = result2.intValue();
				process.push(temp1); 
				process.push(temp2);
			}
			else if(oplist.get(i).equals("ADD")) {
				if(process.isEmpty()) return "ERROR";
				result1 = Long.valueOf(process.pop());
				if(result1>=1000000000 || result1<=-1000000000) return "ERROR";
				int temp1 = result1.intValue();
				if(process.isEmpty()) return "ERROR";
				result2 = Long.valueOf(process.pop());
				if(result2>=1000000000 || result2<=-1000000000) return "ERROR";				
				int temp2 = result2.intValue();
				opresult = result2+result1;
				if(opresult>1000000000 || opresult<-1000000000) return "ERROR";
				process.push(temp2+temp1);
			}	
			else if(oplist.get(i).equals("SUB")) {
				if(process.isEmpty()) return "ERROR";
				result1 = Long.valueOf(process.pop());
				if(result1>=1000000000 || result1<=-1000000000) return "ERROR";
				int temp1 = result1.intValue();
				if(process.isEmpty()) return "ERROR";
				result2 = Long.valueOf(process.pop());
				if(result2>=1000000000 || result2<=-1000000000) return "ERROR";				
				int temp2 = result2.intValue();
				opresult = result2-result1;
				if(opresult>1000000000 || opresult<-1000000000) return "ERROR";
				process.push(temp2-temp1);
			}
			else if(oplist.get(i).equals("MUL")) {
				if(process.isEmpty()) return "ERROR";
				result1 = Long.valueOf(process.pop());
				if(result1>=1000000000 || result1<=-1000000000) return "ERROR";
				int temp1 = result1.intValue();
				if(process.isEmpty()) return "ERROR";
				result2 = Long.valueOf(process.pop());
				if(result2>=1000000000 || result2<=-1000000000) return "ERROR";				
				int temp2 = result2.intValue();
				opresult = result2*result1;
				if(opresult>1000000000 || opresult<-1000000000) return "ERROR";
				process.push(temp2*temp1);
			}
			else if(oplist.get(i).equals("DIV")) {
				if(process.isEmpty() || process.peek()==0) return "ERROR";
				result1 = Long.valueOf(process.pop());
				if(result1>=1000000000 || result1<=-1000000000) return "ERROR";
				int temp1 = result1.intValue();
				if(process.isEmpty()) return "ERROR";
				result2 = Long.valueOf(process.pop());
				if(result2>=1000000000 || result2<=-1000000000) return "ERROR";				
				int temp2 = result2.intValue();
				process.push(temp2/temp1);
			}
			else if(oplist.get(i).equals("MOD")) {
				if(process.isEmpty() || process.peek()==0) return "ERROR";
				result1 = Long.valueOf(process.pop());
				if(result1>=1000000000 || result1<=-1000000000) return "ERROR";
				int temp1 = result1.intValue();
				if(process.isEmpty()) return "ERROR";
				result2 = Long.valueOf(process.pop());
				if(result2>=1000000000 || result2<=-1000000000) return "ERROR";				
				int temp2 = result2.intValue();
				process.push(temp2%temp1);
			}
		}
		
		if(process.isEmpty()) return "ERROR";
		
		String tmp = Integer.toString(process.pop());
		if(process.isEmpty()) return tmp;
		else return "ERROR";
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			while(true) {
	            StringTokenizer str = new StringTokenizer(br.readLine());
				String tmp = str.nextToken();
				
				if(tmp.equals("QUIT")) System.exit(0);
				if(tmp.equals("NUM")) numoplist.add(Integer.parseInt(str.nextToken()));
				else if(tmp.equals("END")) break;
				
				oplist.add(tmp);
			}
			
			n = Integer.parseInt(br.readLine());
			for(int i=0; i<n; i++) {
				numlist.add(Integer.parseInt(br.readLine()));
			}
			
			for(int i=0; i<n; i++) {
				process.push(numlist.get(i));
				System.out.println(operate(i));
				process.clear();
			}
			
			//다음 기계를 위해 초기화
			oplist.clear();
			numlist.clear();
			numoplist.clear();
			j=0;
			System.out.println();
			br.readLine();
		}
	}
}
