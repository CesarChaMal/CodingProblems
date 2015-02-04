/*
*
As we all know RK loves his name very much especially the character 'R' so this time task for you is based on his awesome name. RK gives you a string S consisting of characters 'R' and 'K' only. Now you are allowed to do exactly one move that is you have to choose two indices i and j (1<=i<=j<=|S| where |S| is string length ) and flip all the characters at position X where i<=X<=j. Flipping the character means :

 if S[X]='R' :  //If character at position X is 'R' then change it to 'K'
      S[X]='K'  
 else :          //else if character at position X is 'K' then change it to 'R'
      S[X]='R'
Now your goal is that after exactly one move you have to obtain the maximum number of R's as RK loves this character very much. So help RK with maximum R's.

Input :
The first line contains the number of test cases T. Each test case contains a string S consisting of characters 'R' and 'K' only.

Output :
For each test case print the maximal number of R's that can be obtained after exactly one move.

Constraints: 
1<=T<=10 
1<=|S| <=10^6 (length of string)

Sample Input (Plaintext Link)
2
RKKRK
RKKR
Sample Output (Plaintext Link)
4
4


Solution:

K is denoted by -1
R by +1
find maximum increasing continous sum using kadane algorithm
totalNegatives + coverted K - lost R
coverted K - lost R = max so far
so
total negatives + max so far is the answer

o(n)
*/

import java.io.*;
import java.util.*;

class TestClass{
	
	public static void main(String args[]){
		
		Scanner in = new Scanner(System.in);
		
		int t = in.nextInt();
		
		for(int i=0; i<t; i++){
			String s = in.next();
			int arr[] = new int[s.length()];
			
			int totalNegative = 0;
			int maxSoFar = 0;
			int maxEndingHere = 0;
			for(int j=0; j<s.length();j++){
				if(s.charAt(j)=='R'){
					arr[j] = -1;
					totalNegative++; // total no of 'R'
				} else {
					arr[j] = 1;
				}
					
				maxEndingHere += arr[j];
				
				if(maxEndingHere<0){
					maxEndingHere = 0;
					
				}
					
				if(maxSoFar<maxEndingHere){
					maxSoFar = maxEndingHere;
					
				}
			}
			
			if(totalNegative == s.length()){
				System.out.println(s.length() - 1);
				continue;
			}
			
			System.out.println(totalNegative+maxSoFar);
			
		}
	}
}