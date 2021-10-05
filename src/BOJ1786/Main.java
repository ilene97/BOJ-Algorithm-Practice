package BOJ1786;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		char[] text = in.readLine().toCharArray();
		char[] pattern = in.readLine().toCharArray();
		
		int tLength = text.length, pLength = pattern.length;
		
		int[] pi = new int[pLength];
	    for(int i=1, j=0; i<pLength; i++){
	    	while(j > 0 && pattern[i] != pattern[j]) {
	        	j = pi[j-1];  
	        }
	        if(pattern[i] == pattern[j]) pi[i] = ++j;
	    }
		
		int cnt = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i=0,j=0; i<tLength; ++i) { 
			
			while(j>0 && text[i] != pattern[j]) j = pi[j-1]; 
			
			if(text[i] == pattern[j]) {
				if(j == pLength-1) {
					cnt++;
					list.add((i+1)-pLength); 
					j=pi[j]; 
				}else { 
					j++;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(cnt).append("\n");
		for(int i = 0; i < list.size(); i++) {
			sb.append(list.get(i) + 1).append(" ");
		}
		sb.setLength(sb.length()-1);
		System.out.println(sb);
	}
}