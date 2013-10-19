import java.io.*;

class AVLNode{
	int data;
	int height;
	int children;
	int instance;
	AVLNode left;
	AVLNode right;
	
	public AVLNode(int data){
		this.data = data;
		height = 1;
		children = 0;
		instance=1;
	}
}

public class AVLTrees {
	AVLNode root;
	int size;

    public AVLTrees(){
    	root = null;
    	size = 0;
    }
    
    public AVLTrees(int data) {
    	root = new AVLNode(data);
    	size = 1;
    }
    
    public int getLeftTreeSize(){
    	return getLeftTreeSize(root);
    }
    
    public int getLeftTreeSize(AVLNode root){
    	if(root==null)
    		return 0;
    	return (root.left==null? 0:root.left.children+root.left.instance);
    }
    
    public int getRightTreeSize(){
    	return getRightTreeSize(root);
    }
    
    public int getRightTreeSize(AVLNode root){
    	if(root==null)
    		return 0;
    	return (root.right==null? 0:root.right.children+root.right.instance);
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
    		int inst = node.instance;
    		if(inst>1){
    			node.instance--;
    			size--;
    			return node;
    		}else {
    			if(node.left==null || node.right==null){
	    			size--;
	    			AVLNode temp = (node.left==null? node.right:node.left);
	    			node = temp;
	    			//return node;
	    		}
	    		else {
	    			AVLNode temp = getMinValueNode(node.right);
	    			node.data = temp.data;
	    			node.instance = temp.instance;
	    			temp.instance=1;
	    			node.right = delete(node.right,temp.data);
	    		}
    		}
    	}
    	
    	if(node==null)
    		return node;
    	node.children = getChildren(node);
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
    
    public AVLNode getMinValueNode(AVLNode node){
    	if(node==null)
    		return null;
    		
    	while(node.left!=null)
    		node = node.left;
    	return node;
    }
    
    public AVLNode getMaxValueNode(AVLNode node){
    	if(node==null)
    		return null;
    		
    	while(node.right!=null)
    		node = node.right;
    	return node;
    }
    
    public void insert(int data){
    	if(root == null)
    		root = new AVLNode(data);
    	else
    		root = insert(root,data);
    	size++;
    }
    
    private AVLNode insert(AVLNode node, int data){
    	if(node==null)
    		return (new AVLNode(data));
    	if(data<node.data)
    		//if(node.left==null)
    		//	node.left = new AVLNode(data);
    		//else
    			node.left = insert(node.left,data);
    	else if(data>node.data)
    		//if(node.right==null)
    		//	node.right = new AVLNode(data);
    		//else
    			node.right = insert(node.right,data);
    	else{
    		node.instance++;
    		return node;
    	}
    	
    	node.children = getChildren(node);
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
    	node.children = getChildren(node);
    	temp.height = getHeight(temp);
    	temp.children = getChildren(temp);
    	return temp;
    }
    
    public AVLNode rightRotate(AVLNode node){
    	AVLNode temp = node.left;
    	node.left = temp.right;
    	temp.right = node;
    	node.height = getHeight(node);
    	node.children = getChildren(node);
    	temp.height = getHeight(temp);
    	temp.children = getChildren(temp);
    	return temp;
    }
    
    public int getBalance(AVLNode node){
    	if(node==null)
    		return 0;
    	return getHeight(node.left)-getHeight(node.right);
    }
    
    public int getChildren(AVLNode node){
    	if(node==null)
    		return 0;
    	int leftTreeChildren = (node.left==null? 0:node.left.children+node.left.instance);
    	int rightTreeChildren = (node.right==null? 0:node.right.children+node.right.instance);
    	return (leftTreeChildren+rightTreeChildren);
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
			int inst = node.instance;
			while(inst>=1){
				System.out.println(node.data);
				inst--;
			}
			
			if(node.left != null)
				preOrder(node.left);
		
			if(node.right != null)
				preOrder(node.right);
		}
	}
	
	public int getIntegerNo(int k){	
		/*if(k>size){
			System.out.println("Invalid k="+k);
			return k;
		}*/
		AVLNode node = root;
		while(node!=null){
			int leftTreeSize = getLeftTreeSize(node);
			int rightTreeSize = getRightTreeSize(node);
			if(k>leftTreeSize && k<=leftTreeSize+node.instance)
				break;
			if(k<=leftTreeSize){
				node = node.left;
			} else {
				k = k-leftTreeSize-node.instance;
				node = node.right;
			}
		}
		return node.data;
	}
	
	public double getMedian(){
		return getMedian(root);
	}
	
	public double getMedian(AVLNode node){
		if(size%2!=0){
			int pos = size/2+1;
			return getIntegerNo(pos);
		}else{
			int pos1 = size/2;
			int pos2 = pos1+1;
			double n1 = getIntegerNo(pos1);
			double n2 = getIntegerNo(pos2);
			n2 = n1 + n2;
			n2=n2/2;
			//return (double)((double)getIntegerNo(pos1)+(double)getIntegerNo(pos2))/(double)2;
			return n2;
		}
			
		
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
		avlTree.insert(5);
		
		avlTree.insert(2);
		avlTree.insert(2);
		
		System.out.println();
		avlTree.preOrder();
		System.out.println("size = "+avlTree.size+" left tree = "+avlTree.getLeftTreeSize()+" right = "+avlTree.getRightTreeSize());
		avlTree.delete(1);
		avlTree.preOrder();
		System.out.println("size = "+avlTree.size+" left tree = "+avlTree.getLeftTreeSize()+" right = "+avlTree.getRightTreeSize());
		
	}
}*/