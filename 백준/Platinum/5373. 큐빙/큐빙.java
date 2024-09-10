import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

class Side {
	char[][] arr;
	
	public Side(char c) {
		arr = new char[3][3];
		for(int i=0; i<3; i++) {
			Arrays.fill(arr[i], c);
		}
	}
}

class Cube {
	Side U; // 윗면
	Side D; // 아랫면
	Side F; // 앞면
	Side B; // 뒷면
	Side L; // 왼쪽면
	Side R; // 오른쪽면
	
	public Cube() {
		this.U = new Side('w');
		this.D = new Side('y');
		this.F = new Side('r');
		this.B = new Side('o');
		this.L = new Side('g');
		this.R = new Side('b');
	}
	
	public void rotate(char side, char dir) {
		if(side == 'U') {
			rotateByU(dir);
		}
		else if(side == 'D') {
			rotateByD(dir);
		}
		else if(side == 'F') {
			rotateByF(dir);
		}
		else if(side == 'B') {
			rotateByB(dir);	
		}
		else if(side == 'L') {
			rotateByL(dir);	
		}
		else if(side == 'R') {
			rotateByR(dir);	
		}
	}

	public void rotateByR(char dir) {
		ArrayDeque<Character> tmp = new ArrayDeque<>();
		tmp.offer(U.arr[2][2]); tmp.offer(U.arr[1][2]); tmp.offer(U.arr[0][2]);
		tmp.offer(B.arr[0][0]); tmp.offer(B.arr[1][0]); tmp.offer(B.arr[2][0]); 
		tmp.offer(D.arr[2][2]); tmp.offer(D.arr[1][2]); tmp.offer(D.arr[0][2]); 
		tmp.offer(F.arr[2][2]); tmp.offer(F.arr[1][2]); tmp.offer(F.arr[0][2]);		
		
		// 시계방향
		if(dir == '+') {
			tmp.addFirst(tmp.pollLast()); tmp.addFirst(tmp.pollLast()); tmp.addFirst(tmp.pollLast());
		}
		// 반시계방향
		else if(dir == '-') {
			tmp.addLast(tmp.pollFirst()); tmp.addLast(tmp.pollFirst()); tmp.addLast(tmp.pollFirst());
		}

		U.arr[2][2] = tmp.poll(); U.arr[1][2] = tmp.poll(); U.arr[0][2] = tmp.poll();
		B.arr[0][0] = tmp.poll(); B.arr[1][0] = tmp.poll(); B.arr[2][0] = tmp.poll();	
		D.arr[2][2] = tmp.poll(); D.arr[1][2] = tmp.poll(); D.arr[0][2] = tmp.poll();
		F.arr[2][2] = tmp.poll(); F.arr[1][2] = tmp.poll(); F.arr[0][2] = tmp.poll();
		
		rotateSide(R, dir);
	}
	
	public void rotateByL(char dir) {
		ArrayDeque<Character> tmp = new ArrayDeque<>();
		tmp.offer(U.arr[0][0]); tmp.offer(U.arr[1][0]); tmp.offer(U.arr[2][0]);
		tmp.offer(F.arr[0][0]); tmp.offer(F.arr[1][0]); tmp.offer(F.arr[2][0]);		
		tmp.offer(D.arr[0][0]); tmp.offer(D.arr[1][0]); tmp.offer(D.arr[2][0]); 
		tmp.offer(B.arr[2][2]); tmp.offer(B.arr[1][2]); tmp.offer(B.arr[0][2]); 
		
		// 시계방향
		if(dir == '+') {
			tmp.addFirst(tmp.pollLast()); tmp.addFirst(tmp.pollLast()); tmp.addFirst(tmp.pollLast());
		}
		// 반시계방향
		else if(dir == '-') {
			tmp.addLast(tmp.pollFirst()); tmp.addLast(tmp.pollFirst()); tmp.addLast(tmp.pollFirst());
		}

		U.arr[0][0] = tmp.poll(); U.arr[1][0] = tmp.poll(); U.arr[2][0] = tmp.poll();
		F.arr[0][0] = tmp.poll(); F.arr[1][0] = tmp.poll(); F.arr[2][0] = tmp.poll();	
		D.arr[0][0] = tmp.poll(); D.arr[1][0] = tmp.poll(); D.arr[2][0] = tmp.poll();
		B.arr[2][2] = tmp.poll(); B.arr[1][2] = tmp.poll(); B.arr[0][2] = tmp.poll();
		
		rotateSide(L, dir);
	}
	
	public void rotateByB(char dir) {
		ArrayDeque<Character> tmp = new ArrayDeque<>();
		tmp.offer(U.arr[0][2]); tmp.offer(U.arr[0][1]); tmp.offer(U.arr[0][0]);
		tmp.offer(L.arr[0][0]); tmp.offer(L.arr[1][0]); tmp.offer(L.arr[2][0]); 
		tmp.offer(D.arr[2][0]); tmp.offer(D.arr[2][1]); tmp.offer(D.arr[2][2]); 
		tmp.offer(R.arr[2][2]); tmp.offer(R.arr[1][2]); tmp.offer(R.arr[0][2]);		
		
		// 시계방향
		if(dir == '+') {
			tmp.addFirst(tmp.pollLast()); tmp.addFirst(tmp.pollLast()); tmp.addFirst(tmp.pollLast());
		}
		// 반시계방향
		else if(dir == '-') {
			tmp.addLast(tmp.pollFirst()); tmp.addLast(tmp.pollFirst()); tmp.addLast(tmp.pollFirst());
		}

		U.arr[0][2] = tmp.poll(); U.arr[0][1] = tmp.poll(); U.arr[0][0] = tmp.poll();
		L.arr[0][0] = tmp.poll(); L.arr[1][0] = tmp.poll(); L.arr[2][0] = tmp.poll();	
		D.arr[2][0] = tmp.poll(); D.arr[2][1] = tmp.poll(); D.arr[2][2] = tmp.poll();
		R.arr[2][2] = tmp.poll(); R.arr[1][2] = tmp.poll(); R.arr[0][2] = tmp.poll();
		
		rotateSide(B, dir);
	}
	
	public void rotateByF(char dir) {
		ArrayDeque<Character> tmp = new ArrayDeque<>();
		tmp.offer(U.arr[2][0]); tmp.offer(U.arr[2][1]); tmp.offer(U.arr[2][2]);
		tmp.offer(R.arr[0][0]); tmp.offer(R.arr[1][0]); tmp.offer(R.arr[2][0]); 	
		tmp.offer(D.arr[0][2]); tmp.offer(D.arr[0][1]); tmp.offer(D.arr[0][0]); 
		tmp.offer(L.arr[2][2]); tmp.offer(L.arr[1][2]); tmp.offer(L.arr[0][2]); 
		
		// 시계방향
		if(dir == '+') {
			tmp.addFirst(tmp.pollLast()); tmp.addFirst(tmp.pollLast()); tmp.addFirst(tmp.pollLast());
		}
		// 반시계방향
		else if(dir == '-') {
			tmp.addLast(tmp.pollFirst()); tmp.addLast(tmp.pollFirst()); tmp.addLast(tmp.pollFirst());
		}

		U.arr[2][0] = tmp.poll(); U.arr[2][1] = tmp.poll(); U.arr[2][2] = tmp.poll();
		R.arr[0][0] = tmp.poll(); R.arr[1][0] = tmp.poll(); R.arr[2][0] = tmp.poll();	
		D.arr[0][2] = tmp.poll(); D.arr[0][1] = tmp.poll(); D.arr[0][0] = tmp.poll();
		L.arr[2][2] = tmp.poll(); L.arr[1][2] = tmp.poll(); L.arr[0][2] = tmp.poll();
		
		rotateSide(F, dir);
	}
	
	public void rotateByD(char dir) {
		ArrayDeque<Character> tmp = new ArrayDeque<>();
		tmp.offer(F.arr[2][0]); tmp.offer(F.arr[2][1]); tmp.offer(F.arr[2][2]);
		tmp.offer(R.arr[2][0]); tmp.offer(R.arr[2][1]); tmp.offer(R.arr[2][2]); 
		tmp.offer(B.arr[2][0]); tmp.offer(B.arr[2][1]); tmp.offer(B.arr[2][2]); 
		tmp.offer(L.arr[2][0]); tmp.offer(L.arr[2][1]); tmp.offer(L.arr[2][2]); 
		
		// 시계방향
		if(dir == '+') {
			tmp.addFirst(tmp.pollLast()); tmp.addFirst(tmp.pollLast()); tmp.addFirst(tmp.pollLast());
		}
		// 반시계방향
		else if(dir == '-') {
			tmp.addLast(tmp.pollFirst()); tmp.addLast(tmp.pollFirst()); tmp.addLast(tmp.pollFirst());
		}

		F.arr[2][0] = tmp.poll(); F.arr[2][1] = tmp.poll(); F.arr[2][2] = tmp.poll();
		R.arr[2][0] = tmp.poll(); R.arr[2][1] = tmp.poll(); R.arr[2][2] = tmp.poll();	
		B.arr[2][0] = tmp.poll(); B.arr[2][1] = tmp.poll(); B.arr[2][2] = tmp.poll();
		L.arr[2][0] = tmp.poll(); L.arr[2][1] = tmp.poll(); L.arr[2][2] = tmp.poll();
		
		rotateSide(D, dir);
	}
	
	public void rotateByU(char dir) {
		ArrayDeque<Character> tmp = new ArrayDeque<>();
		tmp.offer(B.arr[0][2]); tmp.offer(B.arr[0][1]); tmp.offer(B.arr[0][0]); 
		tmp.offer(R.arr[0][2]); tmp.offer(R.arr[0][1]); tmp.offer(R.arr[0][0]); 
		tmp.offer(F.arr[0][2]); tmp.offer(F.arr[0][1]); tmp.offer(F.arr[0][0]);
		tmp.offer(L.arr[0][2]); tmp.offer(L.arr[0][1]); tmp.offer(L.arr[0][0]); 
		
		// 시계방향
		if(dir == '+') {
			tmp.addFirst(tmp.pollLast()); tmp.addFirst(tmp.pollLast()); tmp.addFirst(tmp.pollLast());
		}
		// 반시계방향
		else if(dir == '-') {
			tmp.addLast(tmp.pollFirst()); tmp.addLast(tmp.pollFirst()); tmp.addLast(tmp.pollFirst());
		}
		
		B.arr[0][2] = tmp.poll(); B.arr[0][1] = tmp.poll(); B.arr[0][0] = tmp.poll();
		R.arr[0][2] = tmp.poll(); R.arr[0][1] = tmp.poll(); R.arr[0][0] = tmp.poll();
		F.arr[0][2] = tmp.poll(); F.arr[0][1] = tmp.poll(); F.arr[0][0] = tmp.poll();
		L.arr[0][2] = tmp.poll(); L.arr[0][1] = tmp.poll(); L.arr[0][0] = tmp.poll();
		
		rotateSide(U, dir);
	}
	
	public void rotateSide(Side s, char dir) {
		ArrayDeque<Character> tmp = new ArrayDeque<>();
		tmp.add(s.arr[0][0]); tmp.add(s.arr[0][1]); tmp.add(s.arr[0][2]); tmp.add(s.arr[1][2]); 
		tmp.add(s.arr[2][2]); tmp.add(s.arr[2][1]); tmp.add(s.arr[2][0]); tmp.add(s.arr[1][0]);
		
		if(dir == '+') {
			tmp.addFirst(tmp.pollLast()); tmp.addFirst(tmp.pollLast());			
		} 
		else if(dir == '-') {
			tmp.addLast(tmp.pollFirst()); tmp.addLast(tmp.pollFirst());
		}
		
		s.arr[0][0] = tmp.poll(); s.arr[0][1] = tmp.poll(); s.arr[0][2] = tmp.poll(); s.arr[1][2] = tmp.poll();
		s.arr[2][2] = tmp.poll(); s.arr[2][1] = tmp.poll(); s.arr[2][0] = tmp.poll(); s.arr[1][0] = tmp.poll();
	}
	
	public String print() {
		StringBuilder sb = new StringBuilder();
		for(int r=0; r<3; r++) {
			for(int c=0; c<3; c++) {
				sb.append((char)U.arr[r][c]);
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
}

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		for(int t=0; t<tc; t++) {
			Cube cube = new Cube(); // 각 테스트케이스마다 큐브 초기화
			int n = Integer.parseInt(br.readLine()); // 큐브를 돌린 횟수
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				String s = st.nextToken();
				char side = s.charAt(0); // 회전할 면
				char dir = s.charAt(1); // 회전할 방향
				
				cube.rotate(side, dir);
			}
			sb.append(cube.print());
		}
		
		System.out.println(sb);
	}
}