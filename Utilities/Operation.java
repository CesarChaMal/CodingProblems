import java.io.*;

public class Operation {

    public static int HCF(int a, int b){
    	while(a != b){
    		if(a>b)
    			a=a-b;
    		else
    			b=b-a;
    	}
    	return a;
    }
    
    public static int LCM(int a, int b){
    	return (a/HCF(a,b))*b;
    }
    
    public static void mergeSort(int array[], int low, int high){
    	if(low >=high)
    		return;
    	int mid = (low+high)/2;
    	mergeSort(array,low,mid);
    	mergeSort(array,mid+1,high);
    	int end_low = mid;
    	int start_high = mid+1;
    	
    	while((low <= end_low) && (start_high <= high)){
    		if (array[low] < array[start_high]) {
  				low++;
  			} else {
  				int Temp = array[start_high];
  				
  				for (int k = start_high- 1; k >= low; k--) 
  					array[k+1] = array[k];
  				
  				
  				array[low] = Temp;
  				low++;
  				end_low++;
  				start_high++;
    		}
   		}
    }
    
    public static void permute(String s){
    	permute("",s);
    }
    
    public static void permute(String prefix, String suffix){
    	//System.out.println("===="+prefix+"    "+suffix);
    	int n = suffix.length();
    	if(n==0)
    		System.out.println(prefix);
    	else {
    		for(int i =0; i<n;i++)
    			permute(prefix+suffix.charAt(i),suffix.substring(0,i)+suffix.substring(i+1,n));
    	}
    }
    
    public static void main(String args[]){
    	
    	//System.out.println("HCF 1122, 867 is "+HCF(1122,867));
    	//System.out.println("LCM 21, 6 is "+LCM(21,6));
    	
    	/*int a[] = {1,10,12,33,8,6,2,91,19,5,3};
    	mergeSort(a,0,10);
    	
    	for(int i=0;i<=10;i++)
    		System.out.print(a[i]+" ");
    	*/
    	
    	permute("123");
    }
}