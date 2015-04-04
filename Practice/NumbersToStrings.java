/*
Question: Given a number, please translate it to a string, following the rules: 1 is translated to 'a', 2 to 'b', …, 12 to 'l', …, 26 to 'z'. For example, the number 12258 can be translated to "abbeh", "aveh", "abyh", "lbeh" and "lyh", so there are 5 different ways to translate 12258. How to write a function/method to count the different ways to translate a number?
*/

import java.io.*;
import java.util.*;
	
class TestClass{
	
	public static void main(String args[]){
		
		int arr[] = {1};
		
		System.out.println("actual = "+countTranslations(arr,0));
		System.out.println("dynamic = "+dynamicCount(arr));
		
	}
	
	public static int countTranslations(int []arr, int pos){
		
		if(pos==arr.length-1){
			if(arr[pos]==0)
				return 0;
			
			return 1;
		}
		
		int c1 = (arr[pos]==0)? 0: countTranslations(arr, pos+1);
		
		int b = arr[pos]*10 + arr[pos+1];
		
		int c2 = 0;
		
		if(b<=26 && b > 0 && arr[pos] != 0 && pos+2 < arr.length){
			c2 = countTranslations(arr, pos+2);
		} else if(b<=26 && b > 0 && arr[pos] != 0 && pos+2 == arr.length) {
			c2 = 1;
		}
		
		return c1+c2;
	}
	
	// dynamic solution
	
	public static int dynamicCount(int []arr){
		
		int counts[] = new int[arr.length];
		
		counts[arr.length-1] = (arr[arr.length-1]==0)? 0: 1;
		
		for(int i=arr.length-2; i>=0;i--){
			if(arr[i]==0){
				counts[i] = 0;
			} else {
				int digit = arr[i]*10+arr[i+1];
				counts[i] = counts[i+1];
				if(digit>0 && digit<=26 && i+2<arr.length){
					counts[i] +=  counts[i+2];
				} else if(digit>0 && digit<=26 && i+2==arr.length){
						counts[i] += 1;
				}
			}
			
		}
		
		return counts[0];
	}
}