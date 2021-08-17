package BOJ2839;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		sc.close();
		
		int cnt = 0;
		while(true) {
			if(input % 5 == 0) {
				cnt += input / 5;
				System.out.println(cnt);
				break;
			}
			cnt++;
			input -= 3;
			
			if(input < 0) {
				System.out.println(-1);
				break;
			}
		}

	}

}
