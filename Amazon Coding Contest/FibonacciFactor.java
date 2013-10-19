import java.io.*;
import java.util.*;

class Solution{

    public static void main(String args[]){
    	Scanner in = new Scanner(System.in);
    	long fibonacciNos[] = new long [90];
    	
    	int size =0;
    	long a = 2;
    	long b = 3;
    	while(a < 1000000000000000000L || b < 1000000000000000000L){
    		fibonacciNos[size++] = a;
    		fibonacciNos[size++] = b;
    		a=a+b;
    		b=a+b;
    	}
    		
    	int t = in.nextInt();
    	long k;
    	
    	for(int i=0;i<t;i++){
    		k = in.nextLong();
    		getFibonacciFactor(k,fibonacciNos,size);
    	}
    }
    
    public static void getFibonacciFactor(long k, long fibonacciNos[], int size){
    	long c;
    	for(int i=0;i<size-1;i++){
    		c = HCF(fibonacciNos[i],k);
    		if(c!=1){
    			System.out.println(fibonacciNos[i]+" "+c);
    			break;
    		}
    	}
    }
    
    public static long HCF(long x, long y){
    	if (y == 0)
        	return x;
    	else
        	return HCF(y, x % y);
    }
}