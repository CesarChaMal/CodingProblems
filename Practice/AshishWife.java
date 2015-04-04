import java.io.*;
import java.util.*;

class TestClass{
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		
		int t = in.nextInt();
		
		for(int y=0;y<t;y++){
			int n = in.nextInt();
			int x = in.nextInt();
			
			Queues q = new Queues();
			
			int max = 0;
			for(int i=0;i<n;i++){
				int p = in.nextInt();
				q.push(new QueueNode(p, null));
				
				if(p>max)
					max = p;
			}
			
			// no of passes
			int passes=0;
			while(max!=0){
				max=max/10;
				passes++;
			}
			
			radixSort(q,passes);
			
			
			int dup=0;
			int prev = q.pop().data;
			while(!q.isEmpty()){
				int curr = q.pop().data;
				if(prev==curr)
					dup++;
				prev = curr;
			}
			
			if(n-dup == x)
				System.out.println("Perfect husband");
			else if (n-dup > x)
				System.out.println("Lame husband");
			else
				System.out.println("Bad husband");
			
		}
		
	}
	
	public static void radixSort(Queues array, int maxPasses){		
		int passes = 1;
		int key;
		
		while(passes<=maxPasses){
			Queues digitBucket[] = new Queues[10];
			
			while(!array.isEmpty()){
				key = array.peek();
				int digit = nthDigitFromRight(key, passes);
				
				if(digitBucket[digit]==null){
					digitBucket[digit] = new Queues();
				}
				digitBucket[digit].push(array.pop());
			}
			
			//System.out.println("pass="+passes);
			
			for(int i=0;i<10;i++){
				//System.out.println("bucket="+i);
				if(digitBucket[i]!=null){
					//digitBucket[i].printQueue();
					array.merge(digitBucket[i]);
				}
				//System.out.println("merged array now");
				//array.printQueue();
			}
				
			passes++;
		}		
	}
	
	public static int nthDigitFromRight(int digit, int n){
		while(--n>0){
			digit/=10;
		}
		
		return digit%10;
	}
}