/*
Given a string, please get the length of the longest substring which does not have duplicated characters. Supposing all characters in the string are in the range from ‘a’ to ‘z’.
*/

import java.io.*;
import java.util.*;

class MaxSubstringWithoutDuplication{
	
	public static void main(String args[]){
		
		Scanner in = new Scanner(System.in);
		String input ="";
		System.out.println("max substring length = "+getLongestSubstring(input));
		
	}
	
	public static int getLongestSubstring(String s){
		
		int maxSoFar = 0;
		int max = 0;
		
		int a[] = new int[26];
		
		for(int i=0;i<26;i++)
			a[i] = -1;
		
		
		for(int i=0;i<s.length();i++){
			int prevOcc = a[s.charAt(i)-'a'];
			if(prevOcc < 0 || i - prevOcc > maxSoFar){
				maxSoFar++;
			} else {
				maxSoFar = i - prevOcc;
			}
			
			a[s.charAt(i)-'a'] = i;
			
			if(maxSoFar > max)
				max = maxSoFar;
		}
		
		return max;	
	}
}