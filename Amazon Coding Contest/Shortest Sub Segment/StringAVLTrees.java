import java.io.*;

class StringAVLNode{
	String data;
	int height;
	int children;
	int instance;
	Queue occurance;
	StringAVLNode left;
	StringAVLNode right;
	
	public StringAVLNode(String data, int pos){
		this.data = data;
		height = 1;
		children = 0;
		instance=1;
		occurance = new Queue();
		occurance.push(pos);
	}
}

public class StringAVLTrees {
	StringAVLNode root;
	int size;

    public StringAVLTrees(){
    	root = null;
    	size = 0;
    }
    
    public StringAVLTrees(String data, int pos) {
    	root = new StringAVLNode(data, pos);
    	size = 1;
    }
    
    public int getLeftTreeSize(){
    	return getLeftTreeSize(root);
    }
    
    public int getLeftTreeSize(StringAVLNode root){
    	if(root==null)
    		return 0;
    	return (root.left==null? 0:root.left.children+root.left.instance);
    }
    
    public int getRightTreeSize(){
    	return getRightTreeSize(root);
    }
    
    public int getRightTreeSize(StringAVLNode root){
    	if(root==null)
    		return 0;
    	return (root.right==null? 0:root.right.children+root.right.instance);
    }
    
     public StringAVLNode getMinValueNode(StringAVLNode node){
    	if(node==null)
    		return null;
    		
    	while(node.left!=null)
    		node = node.left;
    	return node;
    }
    
    public StringAVLNode getMaxValueNode(StringAVLNode node){
    	if(node==null)
    		return null;
    		
    	while(node.right!=null)
    		node = node.right;
    	return node;
    }
    
    public void insert(String data, int pos){
    	if(root == null)
    		root = new StringAVLNode(data,pos);
    	else
    		root = insert(root,data,pos);
    	size++;
    }
    
    private StringAVLNode insert(StringAVLNode node, String data, int pos){
    	if(node==null)
    		return (new StringAVLNode(data,pos));
    	int comparisonResult = data.compareToIgnoreCase(node.data);
    	if(comparisonResult<0)
    		//if(node.left==null)
    		//	node.left = new AVLNode(data);
    		//else
    			node.left = insert(node.left,data,pos);
    	else if(comparisonResult>0)
    		//if(node.right==null)
    		//	node.right = new AVLNode(data);
    		//else
    			node.right = insert(node.right,data,pos);
    	else{
    		node.instance++;
    		node.occurance.push(pos);
    		return node;
    	}
    	
    	node.children = getChildren(node);
    	int newHeight = getHeight(node);
    	
    	//if(newHeight != node.height){
    		node.height = newHeight;
    		int balance = getBalance(node);
    		
    		if(balance>1 && data.compareToIgnoreCase(node.left.data)<0){	//left left case
    			return rightRotate(node);
    		}
    		
    		if(balance>1 && data.compareToIgnoreCase(node.left.data)>0){	//left right case
    			node.left = leftRotate(node.left);
    			return rightRotate(node);
    		}
    		
    		if(balance<-1 && data.compareToIgnoreCase(node.right.data)>0){	//right right case
    			return leftRotate(node);
    		}
    		
    		if(balance<-1 && data.compareToIgnoreCase(node.right.data)<0){	//right left case
    			node.right = rightRotate(node.right);
    			return leftRotate(node);
    		}
    	//}
    	return node;
    }
    
    public StringAVLNode leftRotate(StringAVLNode node){
    	StringAVLNode temp = node.right;
    	node.right = temp.left;
    	temp.left = node;
    	node.height = getHeight(node);
    	node.children = getChildren(node);
    	temp.height = getHeight(temp);
    	temp.children = getChildren(temp);
    	return temp;
    }
    
    public StringAVLNode rightRotate(StringAVLNode node){
    	StringAVLNode temp = node.left;
    	node.left = temp.right;
    	temp.right = node;
    	node.height = getHeight(node);
    	node.children = getChildren(node);
    	temp.height = getHeight(temp);
    	temp.children = getChildren(temp);
    	return temp;
    }
    
    public int getBalance(StringAVLNode node){
    	if(node==null)
    		return 0;
    	return getHeight(node.left)-getHeight(node.right);
    }
    
    public int getChildren(StringAVLNode node){
    	if(node==null)
    		return 0;
    	int leftTreeChildren = (node.left==null? 0:node.left.children+node.left.instance);
    	int rightTreeChildren = (node.right==null? 0:node.right.children+node.right.instance);
    	return (leftTreeChildren+rightTreeChildren);
    }
    
    public int getHeight(StringAVLNode node){
    	if(node==null)
    		return 0;
    	int leftTreeHeight = (node.left==null? 0:node.left.height);
    	int rightTreeHeight = (node.right==null? 0:node.right.height);
    	return (leftTreeHeight>rightTreeHeight? leftTreeHeight+1:rightTreeHeight+1);
    }
    
    public void preOrder(){
		preOrder(root);
	}
	
	public void preOrder(StringAVLNode node){		
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
	
	public String getStringNo(int k){	
		/*if(k>size){
			System.out.println("Invalid k="+k);
			return k;
		}*/
		StringAVLNode node = root;
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
}

/*class Test{
	public static void main(String args[]){
		StringAVLTrees avlTree = new StringAVLTrees("a");
		avlTree.insert("b");
		avlTree.insert("b");
		
		avlTree.insert("c");
		
		avlTree.insert("d");
		
		avlTree.insert("e");
		
		avlTree.insert("f");
		
		avlTree.insert("g");
		avlTree.insert("g");

		avlTree.preOrder();
		System.out.println(avlTree.size);
	}
}*/