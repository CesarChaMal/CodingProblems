import java.io.*;

class StackNode{
	int data;
	StackNode next;
	
	public StackNode(int data, StackNode next){
		this.data = data;
		this.next = next;
	}
}


class Stacks {
	StackNode head;
	int size;

    public Stacks() {
    	head = null;
    	size=0;
    }
    
    public void push(int data){
    	head = new StackNode(data, head);
    	size++;
    }
    
    public int pop(){
    	StackNode temp = head;
    	head = head.next;
    	size--;
    	return temp.data;
    }
    
    public int peek(){
    	return head.data;
    }
    
    public void printStack(){
    	StackNode temp = head;
    	while(temp!=null){
    		System.out.print(temp.data+" ");
    		temp = temp.next;
    	}
    	System.out.println();
    }
    
    public boolean isEmpty(){
    	return (size==0? true:false);
    }
}