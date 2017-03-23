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
    Possible Second Solution:
    	Suffix Tree.
*/
class searchTrie {
	public static void main (String[] args) {
		String[] dic = {"hello", "chop", "cat", "help", "dog", "fish"}; 
		String[] tests = {"", "cha", "cat", "dog", "dooog", "d", "c.t" , "do." };
		Trie trie = new Trie();
		Arrays.stream(dic).forEach(w -> {
	    		trie.insertWord(w, null);
		});
    
    		Arrays.stream(tests).forEach(w -> {
        		System.out.print("Testing word: \"" + w + "\"   ");
        		System.out.println(trie.searchWord(w));
    		});  
    
        	trie.buildSuffix();
        	trie.DPS();
	}
}

class Node {
    String i;
    public boolean leaf;
    public boolean suffix;
    public HashMap<String, Node> set; 

    
    public Node(String s){
        i = s;
        set = new HashMap<String, Node>();
        leaf = false;
        suffix = false;
    }
}


class Trie{
    Node root;
    Node suffixTree;
    
    public Trie(){
        root = new Node("");
    }
    public void insertWord(String w, Node child){
        if (child == null) {
            insertWord(w, root);
        } else if (w.isEmpty()) {
            child.leaf = true;
        } else {
            String c = String.valueOf(w.charAt(0));
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
        root.suffix = true;
        suffixTree(root, root);
    }
    
    public void suffixTree(Node p, Node n) {
        Iterator i = n.set.keySet().iterator();
        while (i.hasNext()){
            String k = (String) i.next();
            Node c = n.set.get(k);
            suffixTree(n, c);
        }
        if (p.set.size() == 1){
            if (n.leaf==true){
                p.i += n.i;
                p.set.clear();
                p.leaf = true;
            } else if (!n.set.isEmpty()) {
                p.i += n.i;
                p.set = n.set;
            }
            
        }
    }
   
    public void DPS(){
        DPSUtil(root);
    }
    
    public void DPSUtil(Node n){
        System.out.println(n.i);
        n.set.forEach((k, v) -> {
            DPSUtil(v);
        });
            
    }
    public boolean searchWord(String w) {
        if (root.suffix)
            return searchWordUtilS(w, root);
        return searchWordUtil(w, root);
    }
    
    public boolean searchWordUtilS(String w, Node child) {
        
    }
    public boolean searchWordUtil(String w, Node child){
        if (w.isEmpty()){
            if (child.leaf)
                return true;
            return false;
        }
        String c = String.valueOf(w.charAt(0));
        Node n = child.set.get(c);
        if (n == null) {
            boolean f = false;
            int counter = 0;
            if (c.compareTo(".") == 0){
                return child.set.values().stream().anyMatch(v -> {
                   return searchWordUtil(w.substring(1), v); 
                });
                
            }
            return false; 
        }
        return searchWordUtil(w.substring(1), n);
    }
    
}
