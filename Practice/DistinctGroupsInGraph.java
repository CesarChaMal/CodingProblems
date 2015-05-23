/*
Given is an arr of size n
arr[i] tells that node 'i' points to node arr[i]
Find no of distinct groups in a graph
*/

import java.io.*;
import java.util.*;

class Solution {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        
        Scanner in = new Scanner(System.in);
        
        int n = in.nextInt();
        int arr[] = new int[n];
        arr[0] = in.nextInt();
		
		arr[0] = 0;
        
        for(int i=1;i<n;i++){
            int num = in.nextInt();
			num--;
            arr[i] = num;

        }
		
		boolean visited[] = new boolean[n];
		
		for(int i=1;i<n;i++){
			if(!visited[i]){
				bucket(i, arr, visited);
			}
		}
        
        int distinctNums = 0;
        int hashArr[] = new int[n];
        hashArr[0] =1;
        for(int i=1;i<n;i++){
			
            if(hashArr[arr[i]] == 0){
                distinctNums++;
                hashArr[arr[i]] = 1;
            }
        }
        
        System.out.println(distinctNums);
    }
	
	public static int bucket(int i, int arr[], boolean visited[]){
		visited[i] = true;
		
		if(visited[arr[i]]){
			arr[i] = arr[arr[i]];
			return arr[i];
		}
		
		arr[i] = bucket(arr[i], arr, visited);
		
		return arr[i];
	}
}