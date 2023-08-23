/**
 * [아이디어]
 * union-find 알고리즘
 * 
 * 트리가 만약 / <- 이렇게 생겼다면 최악의 시간복잡도 O(n)
 * 1) 경로 압축(path-compression)을 통해 트리의 높이를 낮춰주는 최적화 작업 => find 연산 많으면 이것만 해도 빠름
 * 2) rank 관리를 통해 트리의 높이들을 관리하는 최적화 작업 => 만약 find 연산 많이 없고 union연산만 많이 있는 경우를 고려
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[] parents; // 자신의 부모노드를 저장
	static int[] rank; // 높이 관리를 위한 배열
	static int n; // 원소수
	
	// 초기에 단위집합을 만드는 함수
	public static void make() {
		parents = new int[n+1]; // 인덱스 0은 쓰지 x
		rank = new int[n+1]; // 인덱스 0은 쓰지 x
		for(int i=1; i<=n; i++) { // 원소수만큼 반복하며
			parents[i] = i; // 자신의 부모를 자신으로 저장
			rank[i] = 0; // 높이를 0으로 초기화
		}
	}
	
	// 해당 집합의 대표자를 찾는 함수
	public static int find(int v) {
		if(parents[v] == v) return v; // 만약 자신의 부모가 자신이라면 => 내가 대표 => 나 리턴
		
		// 만약 자신의 부모가 자신과 같지 않다면 => 다시 해당 부모의 부모를 찾으러~
		return parents[v] = find(parents[v]); // 경로 압축 들어감(자신의 부모를, 최종적으로 찾은 대표자로)
	}
	
	// 집합 2개를 합치는 함수(b를 포함한 집합을 -> a를 포함한 집합에 흡수) 
	public static boolean union(int a, int b) { 
		int aRoot = find(a); // a를 포함한 집합의 대표자 찾음
		int bRoot = find(b); // b를 포함한 집합의 대표자 찾음
		
		if(aRoot == bRoot) return false; // 두 원소가 같은 집합에 있다면 -> false반환(union할 필요 x)
		
		parents[bRoot] = parents[aRoot]; // 두 원소가 다른 집합에 있다면, b를 포함한 집합을 a를 포함한 집합에 흡수시킴
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		// 테스트 케이스수만큼 반복
		for(int t=1; t<=tc; t++) {
			sb.append("#" + t + " ");
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			make(); // 단위집합을 만듦
			for(int i=0; i<m; i++) { // 연산수만큼 반복
				st = new StringTokenizer(br.readLine());
				
				int oper = Integer.parseInt(st.nextToken()); // 연산(0: union, 1: find)
				int left = Integer.parseInt(st.nextToken()); // 왼쪽값
				int right = Integer.parseInt(st.nextToken()); // 오른쪽값

				// 연산 0 : union
				if(oper == 0) {
					int aRoot = find(left);
					int bRoot = find(right);
					if(rank[aRoot] > rank[bRoot]) { // a높이 > b높이 => a에 합침(높이 변화 x)
						union(left, right);
					}
					else if(rank[aRoot] < rank[bRoot]) { // a높이 < b높이 => b에 합침(높이 변화 x)
						union(right, left);
					}
					else { // a높이 == b높이 => a에 합쳐도 되고 b에 합쳐도 됨(높이 변화 +1)
						// 나는 일단 a에 합치겠음
						rank[aRoot]++; // 높이 1 증가
						union(left, right);
					}
				}
				// 연산 1 : find
				else if(oper == 1) {
					if(find(left) == find(right)) { // 같은 집합에 속해있다면
						sb.append(1);
					}
					else { // 다른 집합에 속해있다면
						sb.append(0);
					}
				}
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
}
