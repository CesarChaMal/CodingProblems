import java.io.*;
import java.util.*;

/*
 * 

Given N numbers , [N<=10^5] we need to count the total pairs of numbers that have a difference of K. [K>0 and K<1e9]

Input Format:
1st line contains N & K (integers).
2nd line contains N numbers of the set. All the N numbers are assured to be distinct.
Output Format:
One integer saying the no of pairs of numbers that have a diff K.

Sample Input #00:
5 2
1 5 3 4 2
Sample Output #00:
3

Sample Input #01:

0 1

363374326 364147530 61825163 1073065718 1281246024 1399469912 428047635 491595254 879792181 1069262793

Sample Output #01:
0


Solution:
Sort them by a good sorting algorithm - i am using merge sort O(N Log n)
Now Start from the left
pick a number a[i]
add the difference to it i.e. a[i] + k
check is this is not bigger than the greatest number (break otherwise)
do a binary search for a[i] + k in the array O(log N)
if found increment the counter

Complexity: O(N Log N)

 */

class Solution{
	public static void main( String args[] ){

		Scanner in = new Scanner(System.in);
			
		int n = in.nextInt();
		long k = in.nextLong();
		long no[] = new long[n];
		
		for(int i=0; i<n; i++){
			no[i]=in.nextLong();
		}
		
		mergeSort(no,0,n-1);
		
		int counter=0;
		
		long n1;
		for(int i=0; i<n; i++){
			n1 = no[i]+k;
			if(n1>no[n-1])
				break;
			if(binarySearch(no,i,n-1,n1))
				counter++;
		}
		
		System.out.println(counter);
	}
	
	public static void mergeSort(long array[], int low, int high){
    	if(low >=high)
    		return;
    	int mid = (low+high)/2;
    	mergeSort(array,low,mid);
    	mergeSort(array,mid+1,high);
    	int end_low = mid;
    	int start_high = mid+1;
    	
    	while((low <= end_low) && (start_high <= high)){
    		if (array[low] < array[start_high]) {
  				low++;
  			} else {
  				long Temp = array[start_high];
  				
  				for (int k = start_high- 1; k >= low; k--) 
  					array[k+1] = array[k];
  				
  				
  				array[low] = Temp;
  				low++;
  				end_low++;
  				start_high++;
    		}
   		}
    }
    
    public static boolean binarySearch(long []array, int low, int high, long no){
    	int mid;
    	while(low<=high){
    		mid = (low + high)/2;
    		if(no == array[mid])
    			return true;
    		else if(no < array[mid])
    			high = mid-1;
    		else
    			low = mid + 1;
    	}
    	return false;
    }
}