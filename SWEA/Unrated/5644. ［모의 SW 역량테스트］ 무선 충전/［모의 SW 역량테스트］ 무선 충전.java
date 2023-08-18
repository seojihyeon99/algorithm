import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int user1sum;
	static int user2sum;
	
	static int[][] map;
	
	static int[] user1; // 사용자 A의 이동 방향 정보
	static int[] user2; // 사용자 B의 이동 방향 정보
	
	static int totaltime; // 사용자의 총이동시간
	static int bcnum; // BC의 개수
	static int[][] bcinfo; // BC 정보(좌표, 충전 범위, 성능)
	
	// 0:이동x, 1:상, 2:우, 3:하, 4:좌
	static int[] dx = {0,-1,0,1,0};
	static int[] dy = {0,0,1,0,-1};
	
	static void simulation(int user1x, int user1y, int user2x, int user2y, int time) {
				
		boolean[] bcuser1used = new boolean[bcnum];
		boolean[] bcuser2used = new boolean[bcnum];
		
		for(int i=0; i<bcnum; i++) {
			// 충전 범위 내에 들어오면
			if((Math.abs(user1x-bcinfo[i][0]) + Math.abs(user1y-bcinfo[i][1])) <= bcinfo[i][2]) {
				bcuser1used[i] = true;
			}
			if((Math.abs(user2x-bcinfo[i][0]) + Math.abs(user2y-bcinfo[i][1])) <= bcinfo[i][2]) {
				bcuser2used[i] = true;
			}
		}
		
		int user1max = 0;
		int user2max = 0;
		
		List<Integer> list = new ArrayList<>();
		for(int i=0; i<bcnum; i++) {
			// 둘다 같이 사용할 수 있을때
			if(bcuser1used[i] && bcuser2used[i]) {
				list.add(bcinfo[i][3]);
				continue;
			}

			// 둘다 따로 사용할 때	
			if(bcuser1used[i]) {
				if(bcinfo[i][3] > user1max) {
					user1max = bcinfo[i][3]; 
				}
			}				
			else if(bcuser2used[i]) {
				if(bcinfo[i][3] > user2max) {
					user2max = bcinfo[i][3]; 
				}
			}
		}
		Collections.sort(list); // 리스트 정렬
		Collections.reverse(list);
		
		// 둘다 0일 경우
		if(user1max == 0 && user2max == 0) {
			if(list.size()>0) {
				if(list.size()==1) {
					user1max = list.get(0)/2;
					user2max = list.get(0)/2;				
				}
				else {
					user1max = list.get(0);
					user2max = list.get(1);
				}				
			}
		}
		
		// 한쪽만 0일 경우
		else if(user1max == 0) {
			if(list.size()>0) {
				user1max = list.get(0);
				if(list.size() > 1) {
					if(user2max<list.get(1)) {
						user2max = list.get(1);
					}							
				}				
			}
		}
		else if(user2max == 0) {
			if(list.size() > 0) {
				user2max = list.get(0);
				if(list.size() > 1) {
					if(user1max<list.get(1)) {
						user1max = list.get(1);
					}							
				}				
			}
		}
		// 둘다 0이 아닐경우
		else {
			if(list.size()>0) {
				if(list.size() == 1) {
					if(user1max>user2max && user2max < list.get(0)) {
						user2max = list.get(0);
					}
					else if(user2max>user1max && user1max < list.get(0)) {
						user1max = list.get(0);
					}
					
				}
				
				if(list.size() > 1) {
//					int tmp1 = list.get(0);
//					int tmp2 = list.get(0);
//					if(user1max<tmp1 && user2max<tmp2) {
//						user1max = tmp1;
//						user2max = tmp2;
//					}
					for(int i=0; i<list.size(); i++) {
						if(user1max < list.get(i)) {
							user1max = list.remove(i);
							break;
						}
					}
					for(int i=0; i<list.size(); i++) {
						if(user2max < list.get(i)) {
							user2max = list.remove(i);
							break;
						}
					}
//					
//					else if(user2max < tmp1) {
//						user2max = tmp1;
//					}
//					else if(user2max < tmp2) {
//						user2max = tmp2;
//					}
			
				}				
			}
		}		
		
		user1sum += user1max;
		user2sum += user2max;
//		System.out.println("현재시간 : " + time);
//		System.out.println("user1max : " +user1max);
//		System.out.println("user2max : " +user2max);
		
		if(time == totaltime) {
			return;
		}
		
		int next1x = user1x + dx[user1[time]];
		int next1y = user1y + dy[user1[time]];
		
		int next2x = user2x + dx[user2[time]];
		int next2y = user2y + dy[user2[time]];
		
		simulation(next1x, next1y, next2x, next2y, time+1);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=tc; t++) {
			user1sum = 0;
			user2sum = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			totaltime = Integer.parseInt(st.nextToken()); // M
			bcnum = Integer.parseInt(st.nextToken()); // A
//			chargerinfo = new boolean[bcnum][10][10];
			
			user1 = new int[totaltime];
			user2 = new int[totaltime];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<totaltime; i++) {
				user1[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<totaltime; i++) {
				user2[i] = Integer.parseInt(st.nextToken());
			}
						
			bcinfo = new int[bcnum][4]; // x좌표, y좌표, 충전범위, 성능
			for(int i=0; i<bcnum; i++) {
				st = new StringTokenizer(br.readLine());
				bcinfo[i][1] = Integer.parseInt(st.nextToken())-1;
				bcinfo[i][0] = Integer.parseInt(st.nextToken())-1;
				bcinfo[i][2] = Integer.parseInt(st.nextToken());
				bcinfo[i][3] = Integer.parseInt(st.nextToken());
	
				
			}
			
			simulation(0,0,9,9,0);
			
			System.out.println("#" + t +" " + (user1sum+user2sum));
		}
	}
}
