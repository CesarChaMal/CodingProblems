/*
Harit wants to climb ladder having n steps but he want to climb only in step of 2 and 5. Now he felt boring repeating same thing again and again. So he discovered new way, now each time Harit wants to climb in step of k also. In other words he can use steps of 2, 5 or k to climb the ladder. So Harit want to calculate number of different ways to reach at cur step , where k and cur are integers . Although Harit is intelligent yet lazy so he wants your help to calculate number of ways. As number of ways could be too large so he wants you to output it modulo 1000000007 (10^9 + 7) .

Input :
First line consists two space separated integers t and n as number of test cases and total steps in ladder. Each of t lines contains two space separated integers cur and k .

Output :
For each test case output an integer calculated number of ways modulo (10^9 + 7) .

Constraints
1 <= t <= 100000
1 <= n <= 1000000
5 <= k <= 1000000000 
cur <= n and sum of all cur such that ( cur >= k ) will not exceed 10000000 .

solution:
keep appending the steps from previously calculated results
*/

import java.io.*;
import java.util.*;

class TestClass{
	
	static Integer noOfWaysOriginal[];
	static Integer noOfWaysActual[];
	
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		
		int t = in.nextInt();
		int n = in.nextInt();
		
		noOfWaysOriginal = new Integer[n+5];
		noOfWaysOriginal[0]=0;
		noOfWaysOriginal[1]=0;
		noOfWaysOriginal[2]=1;
		noOfWaysOriginal[3]=0;
		noOfWaysOriginal[4]=1;
		noOfWaysOriginal[5]=1;
		
		for(int i=6;i<=n;i++){			
			noOfWaysOriginal[i] = (noOfWaysOriginal[i-2] + noOfWaysOriginal[i-5])%1000000007;
		}
		
		
		
		for(int y=0;y<t;y++){
			int curr = in.nextInt();
			int k = in.nextInt();
			
			if(curr<k || k==5 || k==2){
				System.out.println(noOfWaysOriginal[curr]);
				continue;
			} else if (curr == k){
				System.out.println((noOfWaysOriginal[curr]+1)%1000000007);
				continue;
			}
			
			noOfWaysActual = new Integer[curr+1];
			
			for(int i=0;i<=k;i++){
				noOfWaysActual[i] = noOfWaysOriginal[i];
			}
			
			noOfWaysActual[k] = (noOfWaysActual[k] + 1)%1000000007;
			
			for(int i=k+1;i<=curr;i++){
				long temp = noOfWaysActual[i-k];; 
				long temp1 = noOfWaysActual[i-2];
				long temp2 = noOfWaysActual[i-5];
				
				long temp3 = (temp + temp1 + temp2)%1000000007;
				
				noOfWaysActual[i]=(int) temp3;
			}
			
			System.out.println(noOfWaysActual[curr]);
		}
		
	}
	
}