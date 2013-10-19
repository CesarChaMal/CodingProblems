import java.io.*;
import java.util.*;

class Solution{
	
	public static void main(String args[]) throws Exception{
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		
		int direction[][] = new int[n][n];
		boolean marked[] = new boolean[n];
		int pathLength[][] = new int[n][n];
		
		int v1, v2;
		for(int i=0;i<n-1;i++){
			v1 = in.nextInt();
			v2 = in.nextInt();;
			direction[v1-1][v2-1]=1;
			direction[v2-1][v1-1]=1;
		}
		
		getMaxPathLength(direction,marked,pathLength);
		
		/*for(int i=0;i<n;i++){
			for(int j =0 ; j< n;j++)
				System.out.print(pathLength[i][j]+" ");
			System.out.println();
		}*/
		
		int i,j;
		int objectSize=0;
		Stacks s[] = new Stacks[n];
		int objectArray[] = new int[n];

		
		for(i=0;i<n;i++){
			for(j=i+1;j<n;j++){
				if(pathLength[i][j]>k){
					objectSize+=2;
					objectArray[i]++;
					objectArray[j]++;
					if(s[i]==null)
						s[i] = new Stacks();
					s[i].push(j);
					if(s[j]==null)
						s[j] = new Stacks();
					s[j].push(i);
				}
			}
		}
		
		int max;
		int pos;
		int counter=0;
		
		while(objectSize>0){
			max = -1;
			pos=0;
			for(i=0;i<n;i++){
				if(objectArray[i]>max){
					max = objectArray[i];
					pos = i;
				}
			}
			
			counter++;
			while(!s[pos].isEmpty()){
				int val = s[pos].pop();
				objectArray[val]--;
			}
			objectSize = objectSize - 2*objectArray[pos];
			objectArray[pos]=0;
		}
		
		System.out.println(counter);
	}
	
	public static void getMaxPathLength(int direction[][],boolean marked[],int pathLength[][]){
		int i,j;
		int maxPathLength;
		for(i=0;i<marked.length;i++){
			marked[i] = true;
			for(j=i+1;j<marked.length;j++){
				maxPathLength = getMaxPathLength(direction,pathLength,marked,i,j);
				if(maxPathLength<0){
					maxPathLength=0;
				}
				pathLength[i][j]=maxPathLength;
				pathLength[j][i]=maxPathLength;
			}
			marked[i] = false;
		}
	}
	
	public static int getMaxPathLength(int direction[][],int pathLength[][], boolean marked[], int start, int end){
		//if(direction[start][end]==1000){
			if(direction[start][end]!=0){
					return 1;
			}
			int i, max, ans= -1000;
			for(i=0;i<marked.length;i++){
				if(!marked[i]){
					if(direction[start][i]>0){
						marked[i] = true;
						max = getMaxPathLength(direction,pathLength,marked,i,end);
						marked[i] = false;
						if(max>ans)
							ans = max;
					}
				}
			}
			return ans + 1;
		//}
		//return direction[start][end];
	}
}