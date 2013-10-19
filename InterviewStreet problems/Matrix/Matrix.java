import java.io.*;
import java.util.*;

class Solution{

    public static void main(String args[]){
    	Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		
		Queues connection = new Queues();
		boolean isMachined[] = new boolean[n];
		int vertices[] = new int[n];
		
		int vertex1, vertex2, time, maxTime=0;
		for(int i=0;i<n-1;i++){
			vertex1 = in.nextInt();
			vertex2 = in.nextInt();
			time = in.nextInt();
			if(time>maxTime)
				maxTime = time;
			
			QueueNode q = new QueueNode(time, vertex1, vertex2, null);
			connection.push(q);
		}
		
		int maxPasses =0;
		
		while(maxTime!=0){
			maxTime/=10;
			maxPasses++;
		}
		
		int city;
		for(int i=0;i<k;i++){
			city = in.nextInt();
			isMachined[city] = true;
		}
		
		for(int i=0; i<n;i++)
			vertices[i]=-1;
		
		radixSort(connection, maxPasses);
		
		connection.reverse();
		
		int city1, city2,sum=0;
		QueueNode currentNode;
		for(int i=0;i<n-1;i++){
			currentNode = connection.pop();
			
			city1 = find(currentNode.vertex1,vertices);
			city2 = find(currentNode.vertex2,vertices);
			
			if(isMachined[city1] && isMachined[city2]){
				sum+=currentNode.data;
			}else if(isMachined[city1] || isMachined[city2]){
					union(city1, city2, vertices);
					isMachined[city1] = true;
					isMachined[city2] = true;
			} else {
					union(city1, city2, vertices);
			}
		}
		
		System.out.println(sum);
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
	
	public static void union(int root1, int root2, int array[]){
		if(array[root2]<array[root1]){
			array[root2]+=array[root1];
			array[root1]=root2;
		}else{
			array[root1]+=array[root2];
			array[root2]=root1;
		}
	}
	
	public static int find(int x, int array[]){
		if(array[x]<0)
			return x;
		else {
			array[x] = find(array[x], array);
			return array[x];
		}
	}
}