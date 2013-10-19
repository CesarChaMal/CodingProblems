import java.io.*;
import java.util.*;

/*
 * 
 * 

https://www.hackerrank.com/contests/oct13/challenges/chocolate-feast

Little Bob loves chocolates and goes to the store with a $N bill with $C being the price of each chocolate. In addition, the store offers a discount: for every M wrappers he gives the store, he'll get one chocolate for free. How many chocolates does Bob get to eat?

Input Format:
The first line contains the number of test cases T (<=1000). 
Each of the next T lines contains three integers N, C and M

Output Format:
Print the total number of chocolates Bob eats.

Constraints:
2 <= N <= 100000
1 <= C <= N
1 < M <= N

Sample input

3
10 2 5
12 4 4
6 2 2
Sample Output

6
3
5
Explanation 
In the first case, he can buy 5 chocolates with $10 and exchange the 5 wrappers to get one more chocolate thus making the total number of chocolates he can eat as 6

In the second case, he can buy 3 chocolates for $12. However, it takes 4 wrappers to get one more chocolate. He can���t avail the offer and hence the total number of chocolates remains 3.

In the third case, he can buy 3 chocolates for $6. Now he can give 2 of this 3 wrappers and get 1 chocolate. Again, he can use his 1 unused wrapper and 1 wrapper of new chocolate to get one more chocolate. Total is 5.
 
 *
 *
 *Solution: 
 *The trick here is in test cases like 15 3 2
 *where you will have wrappers left from the free chocolates that you get in exchange for wrappers
 *calculate the total wrappers
 *calculate remaining wrappers after exchange
 *while remaining wrappers are more than the number for which we can get an extra chocolate calculate
 *add the chocolates eaten so far
 *print output
 */

class Solution{
	
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		
		for(int i=0;i<t;i++){
			int n = in.nextInt();
			int c = in.nextInt();
			int m = in.nextInt();
			
			int totalWrappers = n/c;
			int wrappersRemaining = totalWrappers;
			int ans = totalWrappers;
			while(wrappersRemaining>=m){
				ans+=(wrappersRemaining/m);
				wrappersRemaining = wrappersRemaining%m + wrappersRemaining/m;
			}
			
			System.out.println(ans);
		}
		
	}
}