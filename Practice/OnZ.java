import java.io.*;
import java.util.*;

class TestClass{
	public static void main(String args[]) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int t = Integer.parseInt(line);
		
		for(int y=0;y<t;y++){
			String s = br.readLine();
			
			// right to left
			long noOfZ = 0;
			long noOfMovesRToL = 0;
			for(long i=s.length()-1; i>=0;i--){
				if(s.charAt((int)i)=='Z'){
					noOfMovesRToL += s.length()-1-noOfZ-i;
					noOfZ++;
				}
			}
			// left to right
			noOfZ = 0;
			long noOfMovesLToR = 0;
			for(long i=0; i<s.length();i++){
				if(s.charAt((int)i)=='Z'){
					noOfMovesLToR += i - noOfZ;
					noOfZ++;
				}
			}
			
			System.out.println((noOfMovesRToL<noOfMovesLToR)? noOfMovesRToL: noOfMovesLToR);
			
		}
	}
}