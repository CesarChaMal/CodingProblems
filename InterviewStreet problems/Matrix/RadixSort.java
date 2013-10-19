import java.io.*;
import java.util.*;

public class RadixSort {
	
	public static void main(String args[]){
		
		QueueNode n1 = new QueueNode(9, null);
		QueueNode n2 = new QueueNode(7, null);
		QueueNode n3 = new QueueNode(16, null);
		QueueNode n4 = new QueueNode(2, null);
		QueueNode n5 = new QueueNode(2221, null);
		QueueNode n6 = new QueueNode(4, null);
		QueueNode n7 = new QueueNode(100, null);
		QueueNode n8 = new QueueNode(102, null);
		QueueNode n9 = new QueueNode(65, null);
		QueueNode n10 = new QueueNode(333, null);
		
		Queues q = new Queues();
		q.push(n1);
		q.push(n2);
		q.push(n3);
		q.push(n4);
		q.push(n5);
		q.push(n6);
		q.push(n7);
		q.push(n8);
		q.push(n9);
		q.push(n10);
		
		q.printQueue();
		
		radixSort(q,4);
		
		System.out.println();
		
		q.printQueue();
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