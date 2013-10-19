import java.io.*;
import java.util.*;

class Solution{
	
	static int treeSize;
	static int tuncatedEdges = 0;

    public static void main(String args[]) throws Exception{
    	Scanner in = new Scanner(System.in);
    	//System.setOut(new PrintStream(new FileOutputStream("b.txt")));
    	int n = in.nextInt();
    	int m = in.nextInt();
    	
    	treeSize=n;
    	
    	int adjMatrix[][] = new int[n][n];
    	boolean visited[] = new boolean[n];
    	
    	int edge1, edge2;
    	for(int i=0;i<m;i++){
    		edge1 = in.nextInt() - 1;
    		edge2 = in.nextInt() - 1;
    		
    		adjMatrix[edge1][edge2] = 1;
    		adjMatrix[edge2][edge1] = 1;
    	}    		
    	
    	getSize(adjMatrix, visited, 0);
    	
    	System.out.println(tuncatedEdges);
    }
    
    public static int getSize(int adjMatrix[][], boolean visited[], int vertex){
    	visited[vertex] = true;
    	int vertexSize = 1;
    	for(int i=0;i<adjMatrix.length;i++){
    		if(adjMatrix[vertex][i]==1 && !visited[i]){
    			int size = getSize(adjMatrix, visited,i);
    			vertexSize+=size;
    			if(size%2==0){
    				if((treeSize-size)%2==0){
    					tuncatedEdges++;
    					treeSize-= size;
    					vertexSize-=size;
    				}
    			}
    		}
    	}
    	visited[vertex]=false;
    	return vertexSize;
    }
}