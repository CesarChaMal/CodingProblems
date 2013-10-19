/*
 
Given M busy-time slots of N people, You need to print all the available time slots when all the N people can schedule a meeting for a duration of K minutes.

Event time will be of form HH MM ( where 0 <= HH <= 23 and 0 <= MM <= 59 ), K will be in the form minutes.

Input Format:
M K [ M number of busy time slots , K is the duration in minutes ]

Followed by M lines with 4 numbers on each line.

Each line will be of form StartHH StartMM EndHH EndMM  [ Example 9Am-11Am time slot will be given as 9 00 11 00 ]

An event time slot is of form [Start Time, End Time ) . Which means it inclusive at start time but doesn’t include the end time. 

So an event of form 10 00  11 00 => implies that the meeting start at 10:00 and ends at 11:00, so another meeting can start at 11:00.

Sample Input:
5 120
16 00 17 00
10 30 14 30
20 45 22 15
10 00 13 15
09 00 11 00

Sample Output:
00 00 09 00
17 00 20 45

Sample Input:
8 60
08 00 10 15
22 00 23 15
17 00 19 00
07 00 09 45
09 00 13 00
16 00 17 45
12 00 13 30
11 30 12 30

Sample Output:
00 00 07 00
13 30 16 00
19 00 22 00

Constraints :
1 <= M <= 100

Note: 24 00 has to be presented as 00 00.

 

 */
import java.io.*;
import java.util.*;

class Solution{
	
	public static int getMinID(int hh, int mm){
		return (hh*60)+mm;
	}
	
	public static String getTime(int minID){
		int hh = minID/60;
		int mm = minID%60;
		
		String time;
		if(hh<10)
			time = "0"+hh;
		else
			time = hh+"";
		if(mm<10)
			time+=" 0"+mm;
		else
			time+=" "+mm;
		
		return time;
	}

    public static void main(String args[]){
    	Scanner in = new Scanner(System.in);
    	int m = in.nextInt();
    	int k = in.nextInt();
    	
    	int busySlot[][] = new int [m][2];	//start	//end
    	
    	int hh;
    	int mm;
    	for(int i =0;i<m;i++){
    		hh = in.nextInt();
    		mm = in.nextInt();
    		busySlot[i][0] = getMinID(hh,mm);
    		hh = in.nextInt();
    		mm = in.nextInt();
    		busySlot[i][1] = getMinID(hh,mm);
    	}
    	
    	mergeSort(busySlot,0,m-1);
    	
    	int mergedBusySlot[][]= new int[m][2];
    	int size =0;
    	mergedBusySlot[0][0] = busySlot[0][0];
    	mergedBusySlot[0][1] = busySlot[0][1];
    	
    	for(int i=1;i<m;i++){
    		if(busySlot[i][0]<=mergedBusySlot[size][1]){
    			if(busySlot[i][1]>mergedBusySlot[size][1])
    				mergedBusySlot[size][1] = busySlot[i][1];
    		}else{
    			size++;
    			mergedBusySlot[size][0] = busySlot[i][0];
    			mergedBusySlot[size][1] = busySlot[i][1];
    		}
    	}
    	
    	if(k<=mergedBusySlot[0][0]){
    		System.out.println("00 00 "+getTime(mergedBusySlot[0][0]));
    	}
    	for(int i=1;i<=size;i++){
    		if(k<=(mergedBusySlot[i][0]-mergedBusySlot[i-1][1])){
    			System.out.println(getTime(mergedBusySlot[i-1][1])+" "+getTime(mergedBusySlot[i][0]));
    		}
    	}
    	
    	if(mergedBusySlot[size][1]!=0){
    		if(k<=(1440-mergedBusySlot[size][1])){
    			System.out.println(getTime(mergedBusySlot[size][1])+" 00 00");
    		}
    	}
    }
    
    public static void mergeSort(int array[][], int low, int high){
    	if(low >=high)
    		return;
    	int mid = (low+high)/2;
    	mergeSort(array,low,mid);
    	mergeSort(array,mid+1,high);
    	int end_low = mid;
    	int start_high = mid+1;
    	
    	while((low <= end_low) && (start_high <= high)){
    		if (array[low][0] < array[start_high][0]) {
  				low++;
  			} else {
  				int Temp1 = array[start_high][0];
  				int Temp2 = array[start_high][1];
  				
  				for (int k = start_high- 1; k >= low; k--){
  					array[k+1][0] = array[k][0];
  					array[k+1][1] = array[k][1];
  				}
  				
  				array[low][0] = Temp1;
  				array[low][1] = Temp2;
  				low++;
  				end_low++;
  				start_high++;
    		}
   		}
    }
    
}