package BOJ1244;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("C:\\SSAFY\\workspace\\04_Algorithm\\SWExpertAcademyAlgorithm\\src\\SWExpert3499\\sample_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int switches = Integer.parseInt(br.readLine());
		String stateTemp = br.readLine().replace(" ", "");

		int[] state = new int[switches];
		for (int i = 0; i < stateTemp.length(); i++)
			state[i] = stateTemp.charAt(i) - '0';

		int students = Integer.parseInt(br.readLine());

		for (int i = 0; i < students; i++) {
			String next = br.readLine();
			StringTokenizer st = new StringTokenizer(next);
			int gender = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());

			if (gender == 1)
				male(state, switches, num);
			else
				female(state, switches, num, 0);

		}
		
		for(int i=0; i<state.length; i++) {
			System.out.printf("%d", state[i]);
			if(i%20 == 19) System.out.printf("\n");
			else System.out.printf(" ");
		}

	}

	public static void male(int[] states, int size, int num) {
		for (int idx = num; idx <= size; idx += num)
			change(states, idx-1);
	}

	public static void female(int[] state, int size, int num, int offset) {
		int idx = num - 1;

		if (available(size, idx - offset) && available(size, idx + offset)
				&& state[idx - offset] == state[idx + offset]) {
			if (offset == 0) {
				change(state, idx);
			} else {
				change(state, idx - offset);
				change(state, idx + offset);
			}
			female(state, size, num, offset + 1);
		}
	}

	public static void change(int[] state, int idx) {
		if (state[idx] == 0)
			state[idx] = 1;
		else
			state[idx] = 0;
	}

	public static boolean available(int size, int idx) {
		if (0 <= idx && idx < size)
			return true;
		return false;
	}

}
