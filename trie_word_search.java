import java.io.*;
import java.util.*;
/*
    Problem:
        Given a dictionary list find words that match the dictionary and return true if found
        Catch:
            A '.' character might be in the word to match indicating to ignore the current character
    Possible Solution:
        trie tree:
        Create a trie tree where every node is a character that points to the next character in the word then find the words that matches.
        http://www.geeksforgeeks.org/overview-of-data-structures-set-3-graph-trie-segment-tree-and-suffix-tree/#code10
*/
class searchTrie {
	public static void main (String[] args) {
	String[] dic = {"hello", "chat", "cat", "dog", "fish"}; 
	String[] tests = {"", "cha", "cat", "dog", "dooog", "d", "c.t" , "do." };
	Trie trie = new Trie();
	Arrays.stream(dic).forEach(w -> {
	    trie.insertWord(w, null);
	});
    
    Arrays.stream(tests).forEach(w -> {
        System.out.print("Testing word: \"" + w + "\"   ");
        System.out.println(trie.searchWord(w));
    });  
		
	}
}

class Node {
    char i;
    public boolean leaf = false;
    public HashMap<Character, Node> set = new HashMap<Character, Node>();
    
    public Node(char s){
        this.i = s;
    }
}


class Trie{
    Node root;
    public Trie(){
        root = new Node(' ');
    }
    public void insertWord(String w, Node child){
        if (child == null) {
            insertWord(w, root);
        } else if (w.isEmpty()) {
            child.leaf = true;
        } else {
            char c = w.charAt(0);
            if (child.set.containsKey(c)){
                insertWord(w.substring(1), child.set.get(c));
            } else {
                Node n = new Node(c);
                child.set.put(c, n);
                insertWord(w.substring(1), n);
            }
        }
        
    }
	
public void buildSuffix(){
        suffixTree = suffixTree(root);
    }
    public Node suffixTree(Node n){
        // System.out.println(n.i);
       if (n.set.isEmpty()) {
            n.suffix = String.valueOf(n.i);
            return n;
        }
        List remove = new LinkedList();
        Iterator i = n.set.keySet().iterator();
        while(i.hasNext()) {
            char k = (Character) i.next();
            Node current = n.set.get(k);
            Node nxt = suffixTree(current);
          
            if (nxt.set.isEmpty()) {
                n.suffix += nxt.suffix;
                remove.add(nxt.i);
            }
            
        }
        
        // java does not allow to remove elements inside the loop
        remove.stream().forEach(k -> {
            n.set.remove(k);
        });
        
         System.out.println(n.suffix);
        return n;
    }
    public void DPS(){
        DPSUtil(root);
    }
    
    public void DPSUtil(Node n){
        System.out.print(n.i);
        n.set.forEach((k, v) -> {
            DPSUtil(v);
        });
            
    }
    
    public boolean searchWord(String w){
        return searchWordUtil(w, root);
    }
    public boolean searchWordUtil(String w, Node child){
        if (w.isEmpty()){
            if (child.leaf)
                return true;
            return false;
        }
        char c = w.charAt(0);
        Node n = child.set.get(c);
        if (n == null) {
            if (c == '.'){
                return child.set.values().stream().anyMatch(v -> {
                   return searchWordUtil(w.substring(1), v); 
                });      
            }
            return false; 
        }
        return searchWordUtil(w.substring(1), n);
    }
}
