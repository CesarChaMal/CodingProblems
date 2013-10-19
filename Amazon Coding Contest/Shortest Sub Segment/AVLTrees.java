import java.io.*;

class AVLNode{
	int data;
	int dataPos;
	int height;
	AVLNode left;
	AVLNode right;
	
	public AVLNode(int data, int dataPos){
		this.data = data;
		this.dataPos = dataPos;
		height = 1;
	}
}

public class AVLTrees {
	AVLNode root;
	int size;

    public AVLTrees(){
    	root = null;
    	size = 0;
    }
    
    public void delete(int data){
    	if(root!=null){
    		root = delete(root,data);
    	}
    }
    
    public AVLNode delete(AVLNode node, int data){
    	if(node == null)
    		return node;
    		
    	if(data<node.data)
    		node.left = delete(node.left,data);
    	else if (data>node.data)
    		node.right = delete(node.right,data);
    	else{    		
    			if(node.left==null || node.right==null){
	    			size--;
	    			AVLNode temp = (node.left==null? node.right:node.left);
	    			node = temp;
	    			//return node;
	    		}
	    		else {
	    			AVLNode temp = getMinValueNode(node.right);
	    			node.data = temp.data;
	    			node.dataPos = temp.dataPos;
	    			node.right = delete(node.right,temp.data);
	    		}
    	}
    	
    	if(node==null)
    		return node;
    	int newHeight = getHeight(node);
    	
    	//if(newHeight != node.height){
    		node.height = newHeight;
    		int balance = getBalance(node);
    		
    		if(balance>1 && getBalance(node.left)>=0){	//left left case
    			return rightRotate(node);
    		}
    		
    		if(balance>1 && getBalance(node.left)<0){	//left right case
    			node.left = leftRotate(node.left);
    			return rightRotate(node);
    		}
    		
    		if(balance<-1 && getBalance(node.right)<=0){	//right right case
    			return leftRotate(node);
    		}
    		
    		if(balance<-1 && getBalance(node.right)>0){	//right left case
    			node.right = rightRotate(node.right);
    			return leftRotate(node);
    		}
    	//}
    	return node;
    }
    
    public AVLNode getMinValueNode(){
    	return getMinValueNode(root);
    }
    
    public AVLNode getMinValueNode(AVLNode node){
    	if(node==null)
    		return null;
    		
    	while(node.left!=null)
    		node = node.left;
    	return node;
    }
    
    public AVLNode getMaxValueNode(){
    	return getMaxValueNode(root);
    }
    
    public AVLNode getMaxValueNode(AVLNode node){
    	if(node==null)
    		return null;
    		
    	while(node.right!=null)
    		node = node.right;
    	return node;
    }
    
    public void insert(int data,int dataPos){
    	if(root == null)
    		root = new AVLNode(data,dataPos);
    	else
    		root = insert(root,data,dataPos);
    	size++;
    }
    
    private AVLNode insert(AVLNode node, int data, int dataPos){
    	if(node==null)
    		return (new AVLNode(data,dataPos));
    	if(data<node.data)
    		//if(node.left==null)
    		//	node.left = new AVLNode(data);
    		//else
    			node.left = insert(node.left,data,dataPos);
    	else //if(data>node.data)
    		//if(node.right==null)
    		//	node.right = new AVLNode(data);
    		//else
    			node.right = insert(node.right,data,dataPos);
    	
    	
    	int newHeight = getHeight(node);
    	
    	//if(newHeight != node.height){
    		node.height = newHeight;
    		int balance = getBalance(node);
    		
    		if(balance>1 && data<node.left.data){	//left left case
    			return rightRotate(node);
    		}
    		
    		if(balance>1 && data>node.left.data){	//left right case
    			node.left = leftRotate(node.left);
    			return rightRotate(node);
    		}
    		
    		if(balance<-1 && data>node.right.data){	//right right case
    			return leftRotate(node);
    		}
    		
    		if(balance<-1 && data<node.right.data){	//right left case
    			node.right = rightRotate(node.right);
    			return leftRotate(node);
    		}
    	//}
    	return node;
    }
    
    public AVLNode leftRotate(AVLNode node){
    	AVLNode temp = node.right;
    	node.right = temp.left;
    	temp.left = node;
    	node.height = getHeight(node);
    	temp.height = getHeight(temp);    	
    	return temp;
    }
    
    public AVLNode rightRotate(AVLNode node){
    	AVLNode temp = node.left;
    	node.left = temp.right;
    	temp.right = node;
    	node.height = getHeight(node);
    	temp.height = getHeight(temp);
    	return temp;
    }
    
    public int getBalance(AVLNode node){
    	if(node==null)
    		return 0;
    	return getHeight(node.left)-getHeight(node.right);
    }
    
    
    public int getHeight(AVLNode node){
    	if(node==null)
    		return 0;
    	int leftTreeHeight = (node.left==null? 0:node.left.height);
    	int rightTreeHeight = (node.right==null? 0:node.right.height);
    	return (leftTreeHeight>rightTreeHeight? leftTreeHeight+1:rightTreeHeight+1);
    }
    
    public void preOrder(){
		preOrder(root);
	}
	
	public void preOrder(AVLNode node){		
		if(node != null){
			
			System.out.println(node.data);
			
			if(node.left != null)
				preOrder(node.left);
		
			if(node.right != null)
				preOrder(node.right);
		}
	}
	
	public boolean search(int no){
		AVLNode temp = root;
		while(temp != null){
			if(no<temp.data)
				temp = temp.left;
			else if(no==temp.data)
				return true;
			else 
				temp = temp.right;
		}
		return false;
	}
}

/*class Test{
	public static void main(String args[]){
		AVLTrees avlTree = new AVLTrees(9);
		avlTree.insert(5);
		
		avlTree.insert(10);
		
		avlTree.insert(0);
		
		avlTree.insert(6);
		
		avlTree.insert(11);
		
		avlTree.insert(-1);
		
		avlTree.insert(1);
		
		avlTree.insert(2);
		
		avlTree.preOrder();
		avlTree.delete(10);
		avlTree.preOrder();
	}
}*/