/*
 * Find the median at any given point from a running stream of integers entered dynamically at the runtime
 */
import java.io.*;
import java.util.*;

class Solution{
	
	public static void main(String args[]) throws Exception{
		//System.setOut(new PrintStream(new FileOutputStream("b.txt")));
		//Scanner in = new Scanner(new FileInputStream("a.txt"));
		Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String ans[] = new String[n];
        AVLTrees avl = new AVLTrees();

        for(int i=0;i<n;i++){
        	char inst = in.next().toCharArray()[0];
        	int x = in.nextInt();
        	
        	if(inst=='a'){
        		avl.insert(x);
        		ans[i]=processAns(avl.getMedian());
        	} else {
        		if(avl.size==0){
        			ans[i]="Wrong!";
        		}else{
        			int sizeBefore = avl.size;
        			avl.delete(x);
        			int sizeAfter = avl.size;
        			if(sizeBefore == sizeAfter || sizeAfter==0)
        				ans[i]="Wrong!";
        			else
        				ans[i]=processAns(avl.getMedian());
        		}
        	}
        	//System.out.println(ans[i]);
        }
        
        for(int i=0;i<n;i++)
        	System.out.println(ans[i]);
		
	}
	
	public static String processAns(double no){
		int n = (int)no;
		double n1 = no - (double)n;
		if(no==-0.5)
			return "-0.5";
		if(n1==0)
			return n+"";
		else 
			return n+".5";
	}
}