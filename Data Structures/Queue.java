import java.io.*;


class QueueNode {
	int data;
	QueueNode next;
	
	public QueueNode(int data, QueueNode next) {
		this.data=data;
		this.next=next;
	}
}
public class Queue {
	QueueNode first;
	QueueNode last;
	int size;

    public Queue() {
    	first = null;
    	last = null;
    	size =0;
    }
    
    public void push(int data){
    	QueueNode queueNode = new QueueNode(data,null);
    	if(last==null){
    		last = queueNode;
    		if(first == null)
    			first = last;
    	} else {
	    	last.next=queueNode;
	    	last = queueNode;
    	}
    	size++;
    }
    
    public int pop(){
    	if(first !=null){
    		QueueNode temp = first;
	    	first = first.next;
	    	size--;
	    	return  temp.data;
    	} else
    		return -10;
    }
    
    public int peek(){
    	if(first != null)
    		return first.data;
    	else
    		return -10;
    }
    
    public void printQueue(){
    	if(first==null)
    		System.out.println("null");
    	else {
    		QueueNode temp = first;
    		while(temp!=null){
    			System.out.println(temp.data);
    			temp=temp.next;
    		}
    	}
    }
    
    public boolean isEmpty(){
    	return (size==0? true:false);
    }
}