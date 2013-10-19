class BinaryTreeNode{
	int data;
	BinaryTreeNode left;
	BinaryTreeNode right;
	BinaryTreeNode next;
	
	public BinaryTreeNode(int data, BinaryTreeNode left, BinaryTreeNode right){
		this.data = data;
		this.left = left;
		this.right=right;
		this.next = null;
	}
}

public class BinaryTree {
	
	BinaryTreeNode root;
	BinaryTreeNode temp;
	
	public void insert(int data){
		if(root == null)
			root = new BinaryTreeNode(data, null, null);
		else{
			BinaryTreeNode temp = root;
			
			while(temp!=null){
				if(temp.data >= data)
					if(temp.left == null)
						break;
					else
						temp = temp.left;
				else if(temp.data < data)
					if(temp.right == null)
						break;
					else
						temp = temp.right;
			}
			
			if(temp.data == data){
				temp.left = new BinaryTreeNode(data, temp.left, null);
			}
			else if(temp.data > data)
				temp.left = new BinaryTreeNode(data, null, null);
			else
				temp.right = new BinaryTreeNode(data, null, null);
		}
	}
	
	public boolean isBST(){
		temp = null;
		return isBST(root);
	}
	
	public boolean isBST(BinaryTreeNode root){
		if(root != null){
			if(root.left != null)
				if(!isBST(root.left))
					return false;
				
			if(temp != null) 
				if(temp.data >  root.data)
					return false;
			temp = root;
			
			if(root.right != null)
				if(!isBST(root.right))
					return false;
		}
		return true;
	}
	
	public void inOrder(){
		inOrder(root);
	}
	
	public void inOrder(BinaryTreeNode root){
		if(root != null){
			if(root.left != null)
				inOrder(root.left);
		
			System.out.println(root.data);
		
			if(root.right != null)
				inOrder(root.right);
		}
	}
	
	public void preOrder(){
		preOrder(root);
	}
	
	public void preOrder(BinaryTreeNode root){		
		if(root != null){
			System.out.println(root.data);
			
			if(root.left != null)
				preOrder(root.left);
		
			if(root.right != null)
				preOrder(root.right);
		}
	}
	
	public void postOrder(){
		postOrder(root);
	}
	
	public void postOrder(BinaryTreeNode root){		
		if(root != null){
			if(root.left != null)
				postOrder(root.left);
		
			if(root.right != null)
				postOrder(root.right);
			
			System.out.println(root.data);
		}
	}
	
	public boolean search(int data){
		BinaryTreeNode temp = root;
		
		while(temp != null){
			if(temp.data == data)
				return true;
			else if (temp.data > data)
				temp = temp.left;
			else
				temp = temp.right;
		}
		
		return false;
	}
	
	public int lowestCommonAncestor(BinaryTreeNode root, int a, int b){
		BinaryTreeNode a1 = null, b1=null;
		a1=createPath(root, a1, a);
		b1=createPath(root, b1,b);
		printPath(a1);
		printPath(b1);
		return compare(a1,b1);
	}
	
	public int compare (BinaryTreeNode a1, BinaryTreeNode b1){
		BinaryTreeNode temp1= a1;
		BinaryTreeNode temp2= b1;
		while(temp1!=null){
			temp2=b1;
			while(temp2!=null){
				if(temp1.data==temp2.data)
					return temp1.data;
				temp2=temp2.left;
			}
			temp1=temp1.left;
		}
		
		return 0;
	}
	
	public void printPath(BinaryTreeNode a1){
		BinaryTreeNode temp = a1;
		while(temp != null){
			System.out.print(temp.data+"-");
			temp=temp.left;
		}
		System.out.println();
	}
	
	public BinaryTreeNode createPath(BinaryTreeNode root, BinaryTreeNode a1, int a){
		if(root != null){
			if(root.data == a){
				//System.out.println("found");
				a1=append(a1,root.data);
				return a1;
			}
			else{
				//System.out.println("searching "+root.data);
				if(root.left != null){
					a1=createPath(root.left,a1,a);
					if(a1!=null){
						append(a1,root.data);
						return a1;
					}
				}
				
				if(root.right != null)
					a1=createPath(root.right,a1,a);
					if(a1!=null)
						a1=append(a1,root.data);
				
				return a1;
			} 
		}
		else
			return null;
	}
	
	
	public BinaryTreeNode append(BinaryTreeNode a1, int a){
		//System.out.println("appending "+a);
		if(a1==null)
			a1= new BinaryTreeNode(a,null,null);
		else {
			BinaryTreeNode temp = a1;
			while(temp.left != null)
				temp=temp.left;
			
			temp.left = new BinaryTreeNode(a, null,null);
		}
		return a1;
	}
	
	public BinaryTreeNode populateNext(BinaryTreeNode root){
		BinaryTreeNode nextNode = null;
		
		return populateNext(root, nextNode);
	}
	
	public BinaryTreeNode populateNext(BinaryTreeNode root, BinaryTreeNode nextNode){
		if(root != null){
			if(root.right != null)
				nextNode = populateNext(root.right,nextNode);
			
			root.next = nextNode;
			nextNode = root;
			
			if(root.left != null)
				nextNode=populateNext(root.left, nextNode);
			
			return nextNode;
		}
		
		return null;
	}
	
	public void nonRecursiveInorder(){
		Stack s = new Stack();
		BinaryTreeNode temp = root;
		
		while(temp != null){
			s.push(temp);
			temp = temp.left;
		}
		
		while(!s.isEmpty()){
			temp = s.pop();
			System.out.println(temp.data);
			temp = temp.right;
			while(temp != null){
				s.push(temp);
				temp = temp.left;
			}
		}
	}
	
	public void nonRecursivePreorder(){
		Stack s = new Stack();
		BinaryTreeNode temp = root;
		
		while(temp != null){
			System.out.println(temp.data);
			s.push(temp);
			temp = temp.left;
		}
		
		while(!s.isEmpty()){
			temp = s.pop();
			temp = temp.right;
			while(temp != null){
				System.out.println(temp.data);
				s.push(temp);
				temp = temp.left;
			}
		}
	}
	
	public void nonRecursivePostorder(){
		
	}
	
	public boolean isLeafNode(BinaryTreeNode node){
		return ((node.left == null && node.right==null)? true: false);
	}
	
}

class Test{
	
	public static void main(String args[]){
		BinaryTree bst = new BinaryTree();
		bst.insert(10);
		bst.insert(8);
		bst.insert(3);
		bst.insert(9);
		bst.insert(12);
		bst.insert(11);
		
		//bst.root=new BinaryTreeNode(5,(new BinaryTreeNode(1,(new BinaryTreeNode(3,null,null)),(new BinaryTreeNode(4,null,null)))),(new BinaryTreeNode(2,(new BinaryTreeNode(5,null,null)),(new BinaryTreeNode(6,null,null)))));
		/*System.out.println("inorder");
		bst.inOrder();
		
		*/System.out.println("preOrder");
		bst.preOrder();/*
		
		System.out.println("postOrder");
		bst.postOrder();
		*/
		
		//System.out.println("is there 5 "+bst.search(5));
		//System.out.println("is there 15 "+bst.search(15));
		
		//System.out.println("is this bst ? "+bst.isBST());
		
		//System.out.println(bst.lowestCommonAncestor(bst.root,3,6));
		
		
		/*BinaryTreeNode temp = bst.populateNext(bst.root);
		
		while(temp != null){
			System.out.println(temp.data);
			temp = temp.next;
		}
		*/
		
		//bst.nonRecursiveInorder();
	}
}