/*
Samarpit is the main hero of the Dhoom 4.He is trying to steal from the Code Bank Of Hackers.Samarpit has a key with an integer value printed on it. He also has N other keys with each key having its own specific value.Samarpit is trying to break the Lock for which he is supposed to get to the lock's key value. He can perform one type of operation.Take his own key and one of the other N keys and merge them.During merging Samarpit's Key value changes to product of both the keys modulus 100000.

For example if his key value was X and he took a key with value Y the his new key will be (X*Y)%100000.The other key that was used during the merging process remains along with other N-1 keys.

This entire process of merging takes 1 second.Now since he is in a hurry he asks to you to find the minimum time in which he could reach to the lock's key value.

Input:
The first line contains 2 integers they are Samarpit's Key value and the Lock's key value.
The second line contains N indicating the number of other keys Samarpit has. 
Third line contains N space separated integers indicating the value of N other keys.

Output: 
The minimum time required to get to the Lock's Key.If he is unable to reach that print -1.

Constraints:
1 ≤ N ≤ 1000 
1 ≤ Value of all the keys ≤ 100000


Solution: there are 10^5 possibilities, just keep multiplyinh until you have covered all the posibilities, 
update the solution in hashmap to store result
*/

import java.io.*;
import java.util.*;

class TestClass{
	
	public static void main(String args[]){
		
		Scanner in = new Scanner(System.in);
		
		int ownKey = in.nextInt();
		int lockKey = in.nextInt();
		
		int n = in.nextInt();
		int keys[] = new int[n];
		
		for(int i=0;i<n;i++){
			keys[i] = in.nextInt();
		}
		
		HashMap<Integer, Integer> seconds= new HashMap<Integer, Integer>();
		seconds.put(ownKey, 0);
		
		Queues q = new Queues();
		
		q.push(ownKey);
		while(!q.isEmpty()){
			int element = q.pop();
			
			if(element==lockKey){
				System.out.println(seconds.get(element));
				return;
			}
			
			for(int i=0;i<n;i++){
				long prod = (((long)element)*keys[i])%100000;
				
				if(!seconds.containsKey((int)prod)){
					seconds.put((int)prod, seconds.get(element)+1);
					q.push((int)prod);
				}
			}
				
		}
		
		System.out.println(-1);
	}
}