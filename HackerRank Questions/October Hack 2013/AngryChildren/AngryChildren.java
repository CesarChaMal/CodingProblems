/*
hacker rank: https://www.hackerrank.com/contests/oct13/challenges/angry-children

Bill Gates is on one of his philanthropic journeys to a village in Utopia. He has N packets of candies and would like to distribute one packet to each of the K children in the village (each packet may contain different number of candies). To avoid a fight between the children, he would like to pick K out of N packets such that the unfairness is minimized.

Suppose the K packets have (x1, x2, x3,���.xk) candies in them, where xi denotes the number of candies in the ith packet, then we define unfairness as

max(x1,x2,���xk) - min(x1,x2,���xk)

where max denotes the highest value amongst the elements and min denotes the least value amongst the elements. Can you figure out the minimum unfairness and print it?

Input Format
The first line contains an integer N.
The second line contains an integer K. N lines follow each integer containing the candy in the ith packet.

Output Format
A single integer which will be the minimum unfairness.

Constraints
1<=N<=105
1<=K<=N
0<= number of candies in any packet <=109

Sample Input #00

7
3
10
100
300
200
1000
20
30
Sample Output #00

20
Explanation #00
Here K = 3. We can choose packets that contain 10,20,30 candies. The unfairness is

max(10,20,30) - min(10,20,30) = 30 - 10 = 20
Sample Input #01

10
4
1
2
3
4
10
20
30
40
100
200
Sample Output #01

3
Explanation #01
Here K = 4. We can choose the packets that contain 1,2,3,4 candies. The unfairness is

max(1,2,3,4) - min(1,2,3,4) = 4 - 1 = 3

 *
 *Solution: 
 *there are three possibilites
 *if k = 1 then max-min is 0
 *if k = n then max-min is the difference of max and min elements in the array (no sorting required)
 *if 1 < k < n then
 *sort the packets by no of candies (i used the radix sort which is linear for most cases)
 *now our subset would be a continous sequence of k elements becase if we expand this subset in either direction we are either
 *increasing max or decreasing min, both of which increases the unfairness
 *to keep the unfairness minimum we have to keep the max as low as possible and min as high as possible
 *which would only happen when our elements are present in continuation, we cannot go any further because them we wont have k elements
 *Now, given that our subset is a continous stream of numbers one after the another
 *start from A[i] then subset would be (A[i], A[i+1], ... A[i+k-1])
 *unfairness would be A[i+k-1]-A[i]
 *find the min unfairness
 */

import java.io.*;
import java.util.*;


class Solution{
	
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		int k  = in.nextInt();
		
		int a[] = new int[n];
		
		int max = 0;
		int min = a[0];
		
		for(int i=0;i<n;i++){
			a[i] = in.nextInt();
			if(a[i]>max)
				max = a[i];
			else if(a[i]< min)
				min = a[i];
			
		}
		
		if(k==1)
			System.out.println(0);
		else if(k==n)
			System.out.println(max-min);
		else {
			int minDifference = max;
			
			int digits = 0;
			while(max!=0){
				max /=10;
				digits++;
			}
			
			RadixSort.maxPass = (digits%3==0)? digits/3 : digits/3 +1; 
			
			RadixSort.radixSort(a);
			
			for(int i=0;i<a.length;i++){
				if(i+k-1 >= a.length)
					break;
				if((a[i+k-1]-a[i])<minDifference)
					minDifference = a[i+k-1]-a[i]; 
			}
			
			System.out.println(minDifference);
		}
	}
	
}