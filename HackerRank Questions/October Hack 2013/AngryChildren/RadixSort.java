import java.io.*;


public class RadixSort{
	public static int radixQ = 3;
	public static int maxPass = 4;
	
	public static int[] radixSort(int []a){
		Queues q = new Queues();
		
		for(int i=0;i<a.length;i++){
			q.push(new QueueNode(a[i]));
		}
		
		for(int passes=1;passes<=maxPass;passes++){
			Queues digitBucket[] = new Queues[pow(10,radixQ)];
			while(!q.isEmpty()){
				int key = q.peek();
				int digit = nthDigitFromRight(key, passes);
				
				if(digitBucket[digit]==null){
	                digitBucket[digit] = new Queues();
				}
	        
				digitBucket[digit].push(q.pop());
			}
			
			for(int i=0;i<digitBucket.length;i++){
				q.merge(digitBucket[i]);
			}
		}
		
		for(int i=0;i<a.length;i++){
			a[i] = q.peek();
			q.pop();
		}
		return a;
	}
	
	public static int nthDigitFromRight(int digit, int n){ 
		int base = pow(10, radixQ);
		while(--n>0){
                digit/=base;
        }
        return digit%base;
	}
	
	public static int pow(int number, int power){
		int ans = 1;
		while(power>0){
			ans*=number;
			power--;
		}
		return ans;
	}
}