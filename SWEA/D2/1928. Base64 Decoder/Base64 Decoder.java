import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder(); // decoding된 2진수 결과를 담는 버퍼
		StringBuilder builder2 = new StringBuilder(); // 원래 문자로 변환한 결과를 담는 버퍼

		int t = Integer.parseInt(br.readLine()); // 테스트 케이스수

		for (int k = 1; k <= t; k++) {
			// 반복시마다 StringBuilder를 초기화함
			builder.setLength(0);
			builder2.setLength(0);
			String s = br.readLine(); // 문자열 라인으로 입력받음

			// encoding된 문자열 -> decoding하여 2진수로 ([표-1]의 Base64 Decoder 이용)
			for (int i = 0; i < s.length(); i += 4) {
				// encoding된 char 4개
				for (int j = i; j < i + 4; j++) {
					int c = s.charAt(j);

					// 대문자인 경우
					if (Character.isUpperCase(c)) {
						c = c - 65;
					}
					// 소문자인 경우
					else if (Character.isLowerCase(c)) {
						c = c - 71;
					}
					// '+'인 경우
					else if (c == '+') {
						c = c + 19;
					}
					// '/'인 경우
					else if (c == '/') {
						c = c + 16;
					}
					// 숫자인 경우
					else if (c >= '0' && c <= '9') {
						c = c + 4;
					}

					// 10진수 -> 2진수로 변환하여 6자리로 출력함. 앞에 0이 있으면 생략하지 않고 다 보이게 함
					builder.append(String.format("%6s", Integer.toBinaryString(c)).replace(' ', '0'));
				}
			}

			// 다시 2진수로 표현된 수 -> 10진수로 (ASCII 코드 이용) -> 문자로
			for (int i = 0; i < builder.toString().length(); i += 8) {
				String str = builder.toString().substring(i, i + 8);
				builder2.append((char) Integer.parseInt(str, 2));
			}

			System.out.println("#" + k + " " + builder2.toString());

		}
	}
}
