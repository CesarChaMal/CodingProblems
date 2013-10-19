/*

Given a paragraph of text, write a program to find the first shortest sub-segment that contains each of the given k words at least once. A segment is said to be shorter than other if it contains less number of words.

Ignore characters other than [a-z][A-Z] in the text. Comparison between the strings should be case-insensitive. 

If no sub-segment is found then the program should output “NO SUBSEGMENT FOUND”.

Input format :
First line of the input contains the text.
Next line contains k , the number of  words given to be searched.
Each of the next k lines contains a word.

Output format :
Print first shortest sub-segment that contains given k words , ignore special characters, numbers.If no sub-segment is found it should return “NO SUBSEGMENT FOUND”

Sample Input :
This is a test. This is a programming test. This is a programming test in any language.
4
this
a
test
programming

Sample Output :
a programming test This

Explanation :
In this test case segment "a programming test. This" contains given four words. You have to print without special characters, numbers so output is "a programming test This".  Another segment "This is a programming test." also contains given  four words but have more number of words. 


Constraint :
Total number of character in a paragraph will not be more than 200,000.
0 < k <= no. of words in paragraph.
0 < Each word length < 15

 */

import java.io.*;
import java.util.*;

class Solution {

    public static void main(String args[]) throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String s = br.readLine();
    	StringTokenizer st = new StringTokenizer(s," .");
    	
    	Hashtable<String, Queue> words= new Hashtable<String, Queue>();
    	String nextWord;
    	
    	int noOfWords = st.countTokens();
    	
    	String wordArray[] = new String[noOfWords];
    	
    	for(int i=0;i<noOfWords;i++){
    		nextWord = st.nextToken();
    		wordArray[i] = nextWord;
    		nextWord = nextWord.toLowerCase();
    		
    		if(!words.containsKey(nextWord))    			
    			words.put(nextWord, new Queue());
    		
    		words.get(nextWord).push(i);
    	}
    	
    	String ans = null;
    	s = br.readLine();
    	int k = Integer.parseInt(s);
    	Queue queueArray[] = new Queue[k];
    	
    	Hashtable<String, Integer> searchWords= new Hashtable<String, Integer>();
    	String searchWordsArray[] = new String[k];
    	
    	for(int i=0;i<k;i++){
    		nextWord = br.readLine();
    		searchWordsArray[i] = nextWord;
    		if(!searchWords.containsKey(nextWord)){
    			searchWords.put(nextWord,-1);
    		} else {
    			searchWords.put(nextWord,words.get(nextWord).peek());
    		}
    		if(!words.containsKey(nextWord)){
    			ans = "NO SUBSEGMENT FOUND";
    		} else {
    			queueArray[i] = words.get(nextWord);
    		}
    	}
    	
    	if(ans==null){
    		AVLTrees av = new AVLTrees();
    		
    		for(int i=0;i<k;i++){
    			if(searchWords.get(searchWordsArray[i])!=-1){
    				int num = searchWords.get(searchWordsArray[i]);
    				if(!av.search(num)){
    					if(!queueArray[i].isEmpty())
    						av.insert(queueArray[i].pop(),i);
    				}
    				continue;
    			}
    			if(!queueArray[i].isEmpty())
    				av.insert(queueArray[i].pop(),i);
    		}
    			
    		
    		AVLNode minNode = av.getMinValueNode();
    		AVLNode maxNode = av.getMaxValueNode();
    		
    		int min = minNode.data;
    		int minI = minNode.dataPos;
    		int max = maxNode.data;
    		
    		int size = max - min + 1;
    		int next, nextSize;
    		
    		int nmin, nminI,nmax;
    		nmin = min;
    		nminI = minI;
    		nmax = max;
    		
    		while(!queueArray[nminI].isEmpty()){
    			next = queueArray[nminI].pop();
    			av.delete(nmin);
    			av.insert(next,nminI);
    			minNode = av.getMinValueNode();
    			maxNode = av.getMaxValueNode();
    			nmin = minNode.data;
    			nminI = minNode.dataPos;
    			nmax = maxNode.data;
    			nextSize = nmax - nmin + 1;
    			if(nextSize<size){
    				size = nextSize;
    				min = nmin;
    				minI = nminI;
    				max = nmax;
    			}
    		}
    		for(int i = min;i<=max;i++){
    			System.out.print(wordArray[i]+" ");
    		}
    		System.out.println();
    		return;
    	}
    	System.out.println(ans);
    }
}