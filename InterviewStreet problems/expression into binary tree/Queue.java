import java.io.*;


class QueueNode {
	String data;
	QueueNode next;
	
	public QueueNode(String data, QueueNode next) {
		this.data=data;
		this.next=next;
	}
}
class Queues {
	QueueNode first;
	QueueNode last;
	int size;

    public Queues() {
    	first = null;
    	last = null;
    	size =0;
    }
    
    public void push(String data){
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
    
    public String pop(){
    	if(first !=null){
    		QueueNode temp = first;
	    	first = first.next;
	    	size--;
	    	return  temp.data;
    	} else
    		return null;
    }
    
    public String peek(){
    	if(first != null)
    		return first.data;
    	else
    		return null;
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