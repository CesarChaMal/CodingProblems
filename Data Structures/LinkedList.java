import java.io.*;

class Node{
	int data;
	Node next;
	
	public Node (int data, Node next){
		this.data = data;
		this.next = next;
	}
}

public class LinkedList {

	Node head;
	
    public LinkedList() {
    	head = null;
    }
    
    public void reverse(){
    	Node curr = head;
    	Node temp = null, after = null;
    	
    	while(curr != null){
    		after = curr.next;
    		curr.next = temp;
    		temp = curr;
    		curr = after;
    	}
    	
    	head = temp;
    }

    
    public void printList (){
    	Node temp = head;
    	
    	while(temp != null){
    		System.out.println(temp.data);
    		temp = temp.next;
    	}
    	
    	System.out.println();
    }
    
    public void insert(int data){
    	head = new Node (data, head);
    }
}

class Test{
	
	public static void main(String args[]){
		
		LinkedList l1 = new LinkedList();
		l1.insert(4);
		l1.insert(3);
		l1.insert(2);
		l1.insert(1);
		
		l1.printList();
		l1.reverse();
		l1.printList();
	}
}