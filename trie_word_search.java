import java.io.*;
import java.util.*;
/*
    Problem:
        Given a dictionary list find words that match the dictionary and return true if found
        Catch:
            A '.' character might be in the word to match indicating to ignore the current character
    Solution 1:
        trie tree:
        Create a trie tree where every node is a character that points to the next character in the word then find the words that matches.
        http://www.geeksforgeeks.org/overview-of-data-structures-set-3-graph-trie-segment-tree-and-suffix-tree/#code10
    
    Solution 2 (less run time efficiency, better space efficiency):
    	Suffix Tree.
    	Modify the code to build a suffic tree from the trie and create a new search word method that does linear comparison on each node
*/
class searchTrie {
	public static void main (String[] args) {
	    String[] dic = {"hello", "chop", "cat", "help", "dog", "fish"}; 
	    String[] tests = {"", "cha", "cat", "dog", "dooog", "d", "c.t" , "do." };
	    Trie trie = new Trie();
	    Arrays.stream(dic).forEach(w -> {
	        trie.insertWord(w, null);
	    });
    
        // Uncomment the line below to run the suffix Search instead
        // trie.buildSuffix();
        
        Arrays.stream(tests).forEach(w -> {
            System.out.print("Testing word: \"" + w + "\"   ");
            System.out.println(trie.searchWord(w));
        });  
    
        // trie.DPS();
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
	
    // inserts word into the tree
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
    
    // calls the build suffix tree method
    public void buildSuffix(){
        root.suffix = true;
        suffixTree(root, root);
    }
    
    // creates suffix Tree with the first character of the suffix as node key.
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
   
    // depth first search the tree;
    public void DPS(){
        DPSUtil(root);
    }
	
    // depth first search util
    public void DPSUtil(Node n){
        System.out.println(n.i);
        n.set.forEach((k, v) -> {
            DPSUtil(v);
        });
            
    }
    // searchs a word in the tree
    // if the tree is a suffix tree using searchWordUtilS
    // otherwise uses searchWordUtil
    public boolean searchWord(String w) {
        if (root.suffix)
            return searchWordUtilS(w, root);
        return searchWordUtil(w, root);
    }
    
    // searchs a word in the suffix tree
    public boolean searchWordUtilS(String w, Node n){
        int l = n.i.length();
        if (w.length() < l)
            return false;
        String pref = w.substring(0, l);
        String suff = w.substring(l);
        if (compare(pref, n.i)) {
            if (suff.isEmpty()) {
                if (n.leaf)
                    return true;
                return false;
            }
            String k = suff.substring(0, 1);
            Node c = n.set.get(k); 
            if (c == null && k.compareTo(".") == 0) {
                return n.set.values().stream().anyMatch(v -> {
                    return searchWordUtilS(w.substring(1), v);  
                });
            } else 
                return searchWordUtilS(suff, c);
        }
        return false;
    }
  
    // search a word in the triee tree
    public boolean searchWordUtil(String w, Node child){
        if (w.isEmpty()){
            if (child.leaf)
                return true;
            return false;
        }
        String c = String.valueOf(w.charAt(0));
        Node n = child.set.get(c);
        if (n == null) {
            if (c.compareTo(".") == 0){
                return child.set.values().stream().anyMatch(v -> {
                   return searchWordUtil(w.substring(1), v); 
                });
                
            }
            return false; 
        }
        return searchWordUtil(w.substring(1), n);
    }
	
    // compares two words ignoring "." character
    public boolean compare(String w, String n){
        for (int i = 0; i < w.length(); i++){
            if (w.charAt(i) == '.'){
                // System.out.print("skip");
            } else if (w.charAt(i) != n.charAt(i)){
                return false;
            }
        }
        return true;
    }
    
}
