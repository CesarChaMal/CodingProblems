import java.io.*;


class QueueNode {
	int data;
	QueueNode next;
	
	public QueueNode(int data, QueueNode next) {
		this.data=data;
		this.next=next;
	}
	
	public QueueNode(int data){
		this.data = data;
		this.next = null;
	}
}

class Queues {
	QueueNode first;
	QueueNode last;
	int size;

    public Queues() {
    	first = null;
    	last = null;
    	size = 0;
    }
    
    public void push(QueueNode queueNode){
    	queueNode.next = null;
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
    
    public QueueNode pop(){
    	if(first !=null){
    		QueueNode temp = first;
	    	first = first.next;
	    	size--;
	    	return  temp;
    	} else
    		return null;
    }
    
    public int peek(){
    	if(first != null)
    		return first.data;
    	else
    		return -1;
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
    
    public void merge(Queues queue2){
    	if(queue2!=null){
    		if(!queue2.isEmpty()){
    			if(first!=null){
    				last.next = queue2.first;
    			} else {
    				first = queue2.first;
    			}
    			last = queue2.last;
    			size+= queue2.size;
    		}
    	}
    }
    
    public void reverse(){
    	QueueNode curr = first;
    	QueueNode temp = null, after = null;
    	
    	while(curr != null){
    		after = curr.next;
    		curr.next = temp;
    		temp = curr;
    		curr = after;
    	}
    	last = first;
    	first = temp;
    }
}