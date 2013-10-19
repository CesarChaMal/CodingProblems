import java.io.*;
import java.util.*;

class Solution{

	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int children[][] = new int[n][2]; // rating candies
		
		children[0][0] = in.nextInt();
		children[0][1] = 1;
		for(int i=1;i<n;i++){
			int thisCandidate = in.nextInt();
			int previousCandidate =children[i-1][0];
			children[i][0] = thisCandidate;
			
			if(thisCandidate-previousCandidate>0){
				children[i][1] = children[i-1][1]+1;
			} else if(thisCandidate-previousCandidate==0){
				children[i][1] = 1;
			}else{
				children[i][1] = 1;
				int j = i;
				while(j>0){
					if(children[j-1][0]-children[j][0]>0 && children[j-1][1]<=children[j][1]){
						children[j-1][1] = children[j][1] + 1;
					}else
						break;
					j--;
				}
			}
		}
		int candies=0;
		for(int i=0;i<n;i++){
			candies+=children[i][1];
		}
		
		System.out.println(candies);
	}
}