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