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