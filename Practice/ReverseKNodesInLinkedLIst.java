/*
 * problem to reverse K nodes in a linked list
*/

import java.io.*;
import java.util.*;

class Node{
	int data;
	Node next;
	
	public Node(int data, Node next){
		this.data = data;
		this.next = next;
	}
}

class Solution {

    public static void main(String[] args) {
        
	
		Node head = new Node(1, new Node(2, new Node(3, new Node(4, new Node(5, new Node(6, null))))));
    
		int k = 4;
		printListWithHead(head);
		Node newHead = reverseHead(head, k);
		printListWithHead(newHead);
        
    }
	
	public static Node reverseHead(Node head, int k){
		
		Node temp = head;
		
		if(temp == null){
			return null;
		}
		
		int counter = 0;
		
		while(temp!=null && counter < k){
			temp = temp.next;
			counter++;
		}
		
		Node tail = reverseHead(temp, k);
		
		Node prev = tail;
		Node curr = head;
		Node next;
		
		counter = 0;
		
		while(curr != null && counter < k){
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
			counter++;
		}
		
		return prev;
	}
	
	public static void printListWithHead(Node head){
		Node temp = head;
		while(temp!=null){
			System.out.print(temp.data+" -> ");
			temp = temp.next;
		}
		System.out.println("null");
	}
    
    
}