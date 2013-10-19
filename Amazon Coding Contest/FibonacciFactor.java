/*

Given a number K, find the smallest Fibonacci number that shares a common factor( other than 1 ) with it. A number is said to be a common factor of two numbers if it exactly divides both of them. 
Output two separate numbers, F and D, where F is the smallest fibonacci number and D is the smallest number other than 1 which divides K and F.

Input Format 
First line of the input contains an integer T, the number of testcases.
Then follows T lines, each containing an integer K.

Output Format
Output T lines, each containing the required answer for each corresponding testcase.

Sample Input 
3
3
5
161

Sample Output
3 3
5 5
21 7

Explanation
There are three testcases. The first test case is 3, the smallest required fibonacci number  3. The second testcase is 5 and the third is 161. For 161 the smallest fibonacci numer sharing a common divisor with it is 21 and the smallest number other than 1 dividing 161 and 7 is 7.


Constraints :
1 <= T <= 5
2 <= K <= 1000,000

The required fibonacci number is guranteed to be less than 10^18.

 */
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