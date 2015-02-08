import java.io.*;
import java.util.*;

public class CountSort{
	
	public static void main(String args[]){
		
		int input[] = {1, 4, 1, 2, 7, 5, 2};
		
		int output[] = doCountSort(input, 7);
		
		System.out.println("here is the input");
		for(int i=0;i<input.length;i++){
			System.out.print(input[i] + " ");
		}
		System.out.println();
		
		System.out.println("here is the output");
		for(int i=0;i<input.length;i++){
			System.out.print(output[i] + " ");
		}
		System.out.println();
	};
	
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