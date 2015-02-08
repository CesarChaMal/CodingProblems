/*
The Hexagon University of India will be hosting its Prom Night tonight.There would be M boys and N girls at the prom tonight. Each boy wants a girl who is strictly shorter than him. A girl can dance with only one boy and vice-versa. Given the heights of all the boys and girls tell whether it is possible for all boys to get a girl.

Input: 
The first line contains T denoting the number of test cases.
Each test case contains three lines.
The first line contains M and N.
The second line contains M integers each denoting the height of boy.
The third contains N integers each denoting the height of girl.

Output:
Print YES if it is possible for each boy to get a girl else print NO.

Constraints: 
1<=T<=10
1<=N, M<=10000 
1<=Height<=200

Solution
Sort girls and boys in order
match the max heigh boy with  a girl and continue (using liner counting sort for sorting)

*/
import java.io.*;
import java.util.*;

class TestClass{
	
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		
		int t = in.nextInt();
		
		for(int y=0;y<t;y++){
			int m = in.nextInt();
			int n = in.nextInt();
			
			int boys[] = new int[m];
			for(int i=0;i<m;i++){
				boys[i] =  in.nextInt();
			}
			
			int girls[] = new int[n];
			for(int i=0;i<n;i++){
				girls[i] = in.nextInt();
			}
			
			if(n<m){
				System.out.println("NO");
			} else {
				boys = doCountSort(boys, 200);
				girls = doCountSort(girls, 200);	
				
				boolean girlForEachBoy = true;
				int girlIndex = girls.length-1;
				for(int i=boys.length-1; i>=0;i--){
					while(girlIndex >=0 && boys[i]<=girls[girlIndex])
						girlIndex--;
					
					if(girlIndex<0 || girlIndex-- < i){
						girlForEachBoy = false;
						break;
					}
				}
				
				if(girlForEachBoy){
					System.out.println("YES");
				} else{
					System.out.println("NO");
				}
			}
		}
		
	}
	
	public static int[] doCountSort(int input[], int maxValue){
		
		int freq[] = new int[maxValue+1];
		
		// calculate the frequency of every element
		for(int i=0; i < input.length; i++){
			freq[input[i]] +=1;
		}
		
		// find the position of the given element based on its position and number of elements smaller that
		// we are doing a stable sort
		int totalCount = 0;
		for(int i=0;i<=maxValue;i++){
			int oldCount = freq[i];
			freq[i] = totalCount; // give it the sum of all frequecies smaller than this index
			totalCount += oldCount;
		}
		
		// input[i] gives the number
		// freq[input[i]] gives the position
		int output[] = new int[input.length];
		for(int i=0;i<input.length;i++){
			output[freq[input[i]]] = input[i];
			freq[input[i]]++;
		}
		
		return output;
	}
	
}