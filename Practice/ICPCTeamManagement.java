import java.io.*;
import java.util.*;

class TestClass{
	
	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		
		int t = in.nextInt();
		
		for(int y=0;y<t;y++){
			int n = in.nextInt();
			int k = in.nextInt();
			
			int lengths[] = new int[101];
			for(int i=0;i<n;i++){
				String name = in.next();
				lengths[name.length()]++;
			}
			
			boolean isPossible = true;
			for(int i=0;i<101;i++){
				if(lengths[i]>0 && lengths[i]%k !=0 ){
					isPossible = false;
					break;
				}
			}
			
			if(isPossible){
				System.out.println("Possible");
			} else {
				System.out.println("Not possible");
			}
		}
	}
}