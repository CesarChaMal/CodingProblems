/*
www.geeksforgeeks.org/snake-ladder-problem-2/
*/

import java.io.*;
import java.util.*;

class TestClass{
	
	public static void main (String[] args){
		
		int n = 30;
		int snakeNLadder[] = new int[n+1];
		
		int moves[] = new int[n+1];
		
		for(int i=0;i<moves.length;i++){
			snakeNLadder[i] = -1;
			moves[i] = Integer.MAX_VALUE;
		}
		
		snakeNLadder[3] = 22;
		snakeNLadder[5] = 8;
		snakeNLadder[11] = 26;
		snakeNLadder[20] = 29;
		snakeNLadder[27] = 1;
		snakeNLadder[21] = 9;
		snakeNLadder[17] = 4;
		snakeNLadder[19] = 7;
		
		moves[1] = 0;
		
		Queues q = new Queues();
		
		// we start with 1
		q.push(1);
		
		while(!q.isEmpty()){
			int pos = q.pop();
			
			System.out.println("evaluating pos="+pos);
			
			for(int i=pos+1; i<=n && i<=pos+6; i++){
				if((snakeNLadder[i]==-1 && moves[pos]+1 < moves[i])){
					
					moves[i] = moves[pos]+1;
					q.push(i);
					
					System.out.println("setting pos="+i+" with value="+moves[i]);
					
				} else if(snakeNLadder[i]>0 && moves[pos]+1 < moves[i]){
					moves[i] = moves[pos]+1;
					
					if(moves[i]<moves[snakeNLadder[i]]){
						moves[snakeNLadder[i]] = moves[i];
						q.push(snakeNLadder[i]);
					}
					
					System.out.println("setting pos="+i+" with value="+moves[i]);
					System.out.println("and likewise also setting pos="+snakeNLadder[i]+" with value="+moves[snakeNLadder[i]]);
				}
				
			}
			
			System.out.println("positions left to evaluate");
			q.printQueue();
		}
		
		for(int i=1;i<n+1;i++)
			System.out.println("min moves for pos= "+i+" is: "+moves[i]);
		
	}
}