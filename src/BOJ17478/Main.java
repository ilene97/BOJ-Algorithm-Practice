package BOJ17478;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		sc.close();
		sc = null;

		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		print(num, 0);

	}

	public static void print(int n, int cnt) {
		if (cnt == n) {
			printLastLines(cnt);
			return;
		}

		printLines(cnt);
		print(n, cnt + 1);

		printUnderbar(cnt);
		System.out.println("라고 답변하였지.");
	}

	public static void printUnderbar(int cnt) {
		for (int i = 1; i <= cnt; i++)
			System.out.print("____");
	}

	public static void printLines(int cnt) {
		printUnderbar(cnt);
		System.out.println("\"재귀함수가 뭔가요?\"");
		printUnderbar(cnt);
		System.out.println("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
		printUnderbar(cnt);
		System.out.println("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
		printUnderbar(cnt);
		System.out.println("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
	}
	
	public static void printLastLines(int cnt){
		printUnderbar(cnt);
		System.out.println("\"재귀함수가 뭔가요?\"");
		printUnderbar(cnt);
		System.out.println("\"재귀함수는 자기 자신을 호출하는 함수라네\"");
		printUnderbar(cnt);
		System.out.println("라고 답변하였지.");
	}

}

