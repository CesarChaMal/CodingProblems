/*
 * Service Lane
 * 
 * Calvin was driving his favorite vehicle on the 101 freeway. 
 * He noticed the check engine light was on and wants to service it immediately to avoid any risks. Luckily, a service lane runs parallel to the highway. 
 * The length of the highway and service lane is N units. The service lane constitutes of N segments of unit length, where each segment can have different widths. 
 * Calvin can enter into and exit from any segment. Let’s call the entry segment as index i and the exit segment as index j. 
 * Assume that the exit segment lies after the enter segment(j>i) and i ≥ 0.
 * 
 * Calvin has three types of vehicles - bike, car and truck, represented by 1, 2 and 3 respectively, also implying the width of the vehicle. We are given an array width[] of length N, where width[k] represents the width of kth segment of our service lane. It is guaranteed that while servicing he can pass through at most 1000 segments, including entry and exit segments.

If width[k] is 1, only the bike can pass through kth segment.
If width[k] is 2, the bike and car can pass through kth segment.
If width[k] is 3, any of the bike, car or truck can pass through kth segment.
Given the entry and exit point of the Calvin’s vehicle in service lane, output the type of largest vehicle which can pass through the service lane (including the entry & exit segment)

Input Format 
The first line of input contains two integers - N & T, where N is the length of the freeway and T is the number of test cases. The next line has N space separated integers which represents the width array.

T test cases follow. Each test case containts two integers - i & j, where i is the index of segment through which Calvin enters the service lane and j is the index of the lane segment where he exits.

Output Format 
For each test case, print the vehicle type of the largest vehicle which can pass through.

Constraints
1 <= N <= 100000
1 <= T <= 1000
0 <= i < j < N
2 <= j-i+1 <= min(N,1000)
1 <= width[k] <= 3, where 0 <= k < N

Sample Input #00

8 5
2 3 1 2 3 2 3 3
0 2
0 1
6 7
3 5
0 7
Sample Output #00

1
2
3
2
1
 *
 * 
 * 
 * Solution
 * 

 * Store the index positions of occurance of 1, 2, 3 in an array
 * Perform a binary search to see if there is occurance of 1 in between i and j
 * then if 1 is not there check for 2 otherwise 1 is the ans
 * if 2 is there then 2 is the ans
 * if 1 and 2 are not there then only 3 is possible 
 */

import java.io.*;
import java.util.*;

class Solution{
	
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		int t = in.nextInt();
		
		int a1[] = new int[n];
		int a2[] = new int[n];
		
		
		int min1 = 0;
		int min2 = 0;
		
		
		int val;
		for(int i = 0; i< n; i++){
			val = in.nextInt();
		
			if(val == 1){
				a1[min1++] = i;
			} else if(val == 2){
				a2[min2++] = i;
			}
		}
		
		int start, stop;
		
		for(int i=0;i<t;i++){
			start = in.nextInt();
			stop = in.nextInt();
			
			if(find(a1,min1,start,stop) == 1)
				System.out.println(1);
			else if(find(a2,min2,start,stop) == 1)
				System.out.println(2);
			else
				System.out.println(3);
		}
	}
	
	public static int find(int [] a, int max, int start, int stop){
		if(max<=0)
			return 0;
		
		int low = 0;
		int high = max-1;
		
		int mid;
		int possibleAns = -1;
		
		while(low<=high){
			mid = (low+high)/2; 
			if((a[mid] == stop) || (a[mid]>=start && a[mid]<stop)){
				return 1;
			} else if (a[mid] < start){
				low = mid+1;
			} else if (a[mid] > stop){
				high = mid-1;
			}
			
		}
		
		return 0;
	}
}