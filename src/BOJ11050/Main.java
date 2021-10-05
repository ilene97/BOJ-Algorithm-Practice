package BOJ11050;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	// 이항계수1
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		//System.out.println(solve(N, K));
	}
/*
	private static BigInteger solve(BigInteger n, BigInteger r) {
		return fact(n).divide((fact(n.subtract(r)).multiply(fact(r))));
	}

	private static BigInteger fact(BigInteger n) {
		BigInteger result = 1;
		for(BigInteger i = n; i.compareTo(0) > 0; i--)
			result *= i;
		
		return result;
	}*/
}
