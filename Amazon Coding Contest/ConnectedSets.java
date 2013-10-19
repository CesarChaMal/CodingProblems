/*

Given a 2–d matrix , which has only 1’s and 0’s in it. Find the total number of connected sets in that matrix.
 
 
Explanation:
Connected set can be defined as group of cell(s) which has 1 mentioned on it and have at least one other cell in that set with which they share the neighbor relationship. A cell with 1 in it and no surrounding neighbor having 1 in it can be considered as a set with one cell in it. Neighbors can be defined as all the cells adjacent to the given cell in 8 possible directions ( i.e N , W , E , S , NE , NW , SE , SW direction ). A cell is not a neighbor of itself.
 
 
Input format :
 
First line of the input contains T , number of test-cases.
Then follow T testcases. Each testcase has given format.
N [ representing the dimension of the matrix N X N ].
Followed by N lines , with N numbers on each line.
 
 
 
Ouput format :
 
For each test case print one line ,  number of connected component it has.
 
Sample Input :
 
4
4
0 0 1 0
1 0 1 0
0 1 0 0
1 1 1 1
4
1 0 0 1
0 0 0 0
0 1 1 0
1 0 0 1
5
1 0 0 1 1
0 0 1 0 0
0 0 0 0 0
1 1 1 1 1
0 0 0 0 0
8
0 0 1 0 0 1 0 0
1 0 0 0 0 0 0 1
0 0 1 0 0 1 0 1
0 1 0 0 0 1 0 0
1 0 0 0 0 0 0 0
0 0 1 1 0 1 1 0
1 0 1 1 0 1 1 0
0 0 0 0 0 0 0 0
 
Sample output :
 
1
3
3
9
 
Constraint :
 
0 < T < 6 
0 < N < 1009 

 */
import java.io.*;
import java.util.*;

class Solution{

    public static void main(String args[]){
    	Scanner in = new Scanner(System.in);
    	int t = in.nextInt();
    	int ans[] = new int[t];
    	
    	for(int i=0;i<t;i++){
    		int n = in.nextInt();
    		int matrix[][] = new int[n][n];
    		
    		for(int j =0;j<n;j++){
    			for(int k=0;k<n;k++){
    				matrix[j][k] = in.nextInt();
    			}
    		}
    		
    		int size =2;
    		for(int j =0;j<n;j++){
    			for(int k=0;k<n;k++){
    				if(matrix[j][k]==1){
    					color(matrix,j,k,size++);
    				}
    			}
    		}
    		ans[i]=size-2;
    	}
    	
    	for(int i=0;i<t;i++)
    		System.out.println(ans[i]);
    }
    
    public static void color(int matrix[][], int x, int y, int clr){
    	matrix[x][y] = clr;
    	
    	if(x>0 && y>0){	//NW
    		if(matrix[x-1][y-1]==1)
    			color(matrix,x-1,y-1,clr);
    	}
    	if(x>0 && y<matrix[0].length-1){	//NE
    		if(matrix[x-1][y+1]==1)
    			color(matrix,x-1,y+1,clr);
    	}
    	if(x<matrix.length-1 && y>0){	//SW
    		if(matrix[x+1][y-1]==1)
    			color(matrix,x+1,y-1,clr);
    	}
    	
    	if(x<matrix.length-1 && y<matrix[0].length-1){	//SE
    		if(matrix[x+1][y+1]==1)
    			color(matrix,x+1,y+1,clr);
    	}
    	
    	if(y<matrix[0].length-1){	//E
    		if(matrix[x][y+1]==1)
    			color(matrix,x,y+1,clr);
    	}
    	
    	if(y>0){	//W
    		if(matrix[x][y-1]==1)
    			color(matrix,x,y-1,clr);
    	}
    	
    	if(x>0){	//N
    		if(matrix[x-1][y]==1)
    			color(matrix,x-1,y,clr);
    	}
    	
    	if(x<matrix.length-1){	//S
    		if(matrix[x+1][y]==1)
    			color(matrix,x+1,y,clr);
    	}
    }
}