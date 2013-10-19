import java.io.*;
import java.util.*;


class Solution {

    public static void main(String args[]){
    	Scanner in = new Scanner(System.in);
    	int t = in.nextInt();
    	
    	String s;
    	
    	int ans[] = new int[t];
    	
    	for(int i=0;i<t;i++){
    		s = in.next();
    		char charArray[] = s.toCharArray();
    		int a=0,b=0,c=0;
    		
    		for(int j=0;j<charArray.length;j++){
    			if(charArray[j]=='a')
    				a++;
    			else if(charArray[j]=='b')
    				b++;
    			else
    				c++;
    		}
    		
    		if((a==0 && b==0) || (a==0 && c==0) || (b==0 && c==0))
    			ans[i] = a+b+c;
    		else if ((a%2==0 && b%2==0 && c%2==0) || (a%2==1 && b%2==1 && c%2==1))
    			ans[i] = 2;
    		else
    			ans[i] = 1;
    	}
    	
    	for(int i=0;i<t;i++)
    		System.out.println(ans[i]);
    }
}