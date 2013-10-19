import java.util.*;

/*
 * hackerRank: https://www.hackerrank.com/challenges/stockmax
 * 
 * 
	**** Stock maximize *** 

Your algorithms have become so good at predicting the market that you now know what the share price of Wooden Orange Toothpicks Inc. (WOT) will be for the next N days.

Each day, you can either buy one share of WOT, sell any number of shares of WOT that you own or not make any transaction at all. What is the maximum profit you can obtain with an optimum trading strategy?

Input

The first line contains the number of test cases T. T test cases follow:

The first line of each test case contains a number N. The next line contains N integers, denoting the predicted price of WOT shares for the next N days.

Output

Output T lines, containing the maximum profit which can be obtained for the corresponding test case.

Constraints

1 <= T <= 10
1 <= N <= 50000

All share prices are between 1 and 100000

Sample Input

3
3
5 3 2
3
1 2 100
4
1 3 1 2
Sample Output

0
197
3
Explanation

For the first case, you cannot obtain any profit because the share price never rises. For the second case, you can buy one share on the first two days, and sell both of them on the third day.


* Solution:
* You will know the maximum selling price if you reverse traverse the array
* Book profit if the max selling price you have is more than the cost of the stock for the day
* else
* if max selling you have is lesser than the stock price for the day - change your selling price to this new max price and continue 
*/

class Solution {

	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for(int i=0;i<t;i++){
			int n = in.nextInt();
			long a[] = new long[n];
			for(int j=0;j<n;j++)
				a[j] = in.nextLong();
			System.out.println(profit(a));
		}		
	}
	
	public static long profit(long a[]){
		long maxSoFar = a[a.length-1];
		long profit = 0L;
		for(int i = a.length-2;i>=0;i--){
			if(a[i]>maxSoFar)
				maxSoFar = a[i];
			else
				profit+=maxSoFar-a[i];
		}
		return profit;
	}    
}