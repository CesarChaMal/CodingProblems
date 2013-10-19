/*
 * this would convert a bracketed expression like (5+2*(3+2)) into a binary tree that can be then calculated
 * 
 * This problem was asked in one the amazon written coding tests
 */

import java.io.*;
import java.util.*;


class BinaryTreeNode{
	public String token;
	public BinaryTreeNode left;
	public BinaryTreeNode right;
	
	public BinaryTreeNode(){
		this.token = null;
		this.left = null;
		this.right = null;
	}
}

class Calculator {

	public static void main(String args[]){
		Scanner in = new Scanner(System.in);
		String expressionString = "("+in.next()+")";
		char expression[]=expressionString.toCharArray();
		
		//process expression
		Queues token = new Queues();
		
		int number = 0;
		boolean numberFlag = false;
		int openBracesCounter=0;
		for(int i=0; i<expression.length;i++){
			switch (expression[i]){
				case '(':
				case '+':
				case '-':
				case '*':
				case '/':
				case '%':
				case ')': 	
							if(numberFlag){
								numberFlag = false;
								token.push(number+"");
								number=0;
							}
							
							token.push(expression[i]+"");
							break;
				default: 
						numberFlag = true;
						number = number*10 + Integer.parseInt(expression[i]+"");
			}
			
		}
		
		token.printQueue();
		System.out.println("that was token queue.. lets see how the tree looks like");
		BinaryTreeNode root = createTokenTree(token);
		showPreOrder(root);
	}
	
	public static BinaryTreeNode createTokenTree(Queues token){
		if(token.isEmpty())
			return null;
		
		String t = token.pop();
		
		BinaryTreeNode root = new BinaryTreeNode();
		root.token = t;
		
		if(t.equals("(")){
			root.left = createTokenTree(token);
			
			if(!token.isEmpty()){
				t = token.pop();
				while(t.equals(")") && !token.isEmpty())
					t = token.pop();
				
				if(token.isEmpty())
					return root.left;
				else {
					root.token = t;
					root.right = createTokenTree(token);
				}
			} else
				return null;
		}else if(t.equals(")"))
			return null;
		
		return root;
	}
	
	public static void showInorder(BinaryTreeNode root){
		if(root.left!=null)
			showInorder(root.left);
		
		System.out.print(root.token+" " );
		
		if(root.right!=null)
			showInorder(root.right);
	}
    
    public static void showPreOrder(BinaryTreeNode root){
        
        System.out.print(root.token+" " );
        
		if(root.left!=null)
			showPreOrder(root.left);
		
		if(root.right!=null)
			showPreOrder(root.right);
	}
}