 import java.io.*;
import java.util.*;
import java.lang.Math;

class Solution {

    public static void main(String args[]){
    	Scanner in = new Scanner(System.in);
    	double a = in.nextDouble();
    	double b = in.nextDouble();
    	double x0 = in.nextDouble();
    	double y0 = in.nextDouble();
    	double x1 = in.nextDouble();
    	double y1 = in.nextDouble();
    	double x2 = in.nextDouble();
    	double y2 = in.nextDouble();
    	double x3 = in.nextDouble();
    	double y3 = in.nextDouble();
    	
		double x = 0;
		double y = 0;
		double c1 = 2*(x0-a*a*x1)/(a*a-1);
		double c2 = 2*(y0-a*a*y1)/(a*a-1);
		double c3 = (a*a*x1*x1 + a*a*y1*y1-x0*x0-y0*y0)/(a*a-1);
		double c4 = 2*(x2-b*b*x3)/(b*b-1);
		double c5 = 2*(y2-b*b*y3)/(b*b-1);
		double c6 = (b*b*x3*x3 + b*b*y3*y3-x2*x2-y2*y2)/(b*b-1);
		
		if(c2!=c5){
			double c7 = (c6-c3)/(c2-c5);
			double c8 = (c1-c4)/(c2-c5);
			double c9 = c7*c7+c2*c7+c3;
			double c10 = c1-c2*c8-2*c7*c8;
			double c11 = 1 + c8*c8;
			
			double determinant = c10*c10 - 4*c9*c11;
			if(determinant>=0){
				double x11 = (-c10 - Math.sqrt(determinant))/(2*c11);
				double x22 = (-c10 + Math.sqrt(determinant))/(2*c11);
				x = x11;
				if(x22<x11)
					x = x22;
				y = c7 - x*c8;
				//System.out.println("c2!=c5");
			}else{
				System.out.println("Impossible!");	
				return;
			}
		} else if(c1!=c4){
			double c12 = (c6-c3)/(c1-c4);
			double c13 = c12*c12 + c3 + c1*c12;
			double determinant = c2*c2 - 4*c13;
			
			if(determinant>=0){
				double y11 = (-c2 - Math.sqrt(determinant))/2;
				double y22 = (-c2 + Math.sqrt(determinant))/2;
				y = y11;
				if(y22<y11)
					y = y22;
				x = c12;
			} else {
				System.out.println("Impossible!");	
				return;
			}
		} else if(c3==c6){
			//overlapping circles
			double a1 = -c1/2;
			double b1 = -c2/2;
			double r1 = Math.sqrt(-c3+a1*a1+b1*b1);
			
			x=a1-r1;
			y=b1;
			//System.out.println("overlapping circes");
		} else {
			System.out.println("Impossible!");	
			return;
		}
		
		
		x = Math.round(x*100);
		y = Math.round(y*100);
		
		System.out.format("%.2f %.2f\n",x/100,y/100);
    }
}