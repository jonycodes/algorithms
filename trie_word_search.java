import java.io.*;
import java.util.*;
/*
    Problem:
        Given a dictionary list find words that match the dictionary and return true if found
        Catch:
            A '.' character might be in the word to match indicating to ignore the current character
    
    Solution 1 - Trie tree:
        Create a trie tree where every node is a character that points to the next character in the word then find the words that matches.
        http://www.geeksforgeeks.org/overview-of-data-structures-set-3-graph-trie-segment-tree-and-suffix-tree/#code10
        Time complexity: (M log n)
        Space: O (Alphabet_size * m * n)
        
    Solution 2 - Suffix Tree (Much better space efficiency):
        Modify the code to build a suffic tree from the trie and create a new search word method that does linear comparison on each node
    	complexity still (m log n) 
    	Space: O(n)
    	
    Solution 3 - Suffix Array (Pending) 
*/
class searchTrie {
    public static void main (String[] args) {
	String[] dic = {"hello", "chop", "cat", "help", "dog", "dogs", "fish"}; 
	String[] tests = {"", "cha", "cat", "dog", "hel.", "dogs", "d" ,".ogs" ,"dooog", "c.t" , "do.s", "hhmh", "c..p", "helo", "hel" , "elp" , "fi" , "fish", "fishes" };
	Trie trie = new Trie();
	Arrays.stream(dic).forEach(w -> {
	    trie.insertWord(w, null);
	});
    
        // Comment the line below to run the normal Trie Search and uncomment to run Suffix Search
        trie.buildSuffix();
        
        Arrays.stream(tests).forEach(w -> {
            System.out.print("Testing word: \"" + w + "\"   ");
            System.out.println(trie.searchWord(w));
        });  
    
        trie.DPS();
    }
}

class Node {
    String i;
    boolean isLeaf;
    boolean suffix;
    HashMap<String, Node> children; 

    
    public Node(String s){
        i = s;
        children = new HashMap<String, Node>();
        isLeaf = false;
    }
}


class Trie{
    Node root;
    Node suffixTree;
    boolean suffix;
    
    public Trie(){
        root = new Node("");
        suffix = false;
    }
	
    // inserts word into the tree
    public void insertWord(String w, Node n){
        if (n == null) {
            insertWord(w, root);
        } else if (w.isEmpty()) {
            n.isLeaf = true;
        } else {
            String c = w.substring(0, 1);
            if (n.children.containsKey(c)){
                insertWord(w.substring(1), n.children.get(c));
            } else {
                Node nw = new Node(c);
                n.children.put(c, nw);
                insertWord(w.substring(1), nw);
            }
        }    
    }
    
    // calls the suffix tree method
    public void buildSuffix(){
        suffix = true;
        suffixTree(root, root);
    }
    
    // creates suffix Tree with the first character of the suffix as node key.
    public void suffixTree(Node p, Node n) {
        Iterator i = n.children.keySet().iterator();
        while (i.hasNext()){
            String k = (String) i.next();
            Node c = n.children.get(k);
            suffixTree(n, c);
        }
        if (p.children.size() == 1 && !p.isLeaf){
            p.children = n.children;
            p.isLeaf = n.isLeaf;
            p.i += n.i;
        }
    }
   
    // depth first search
    public void DPS(){
        DPSUtil(root);
    }
	
    // depth first search util
    public void DPSUtil(Node n){
        System.out.println(n.i);
        n.children.forEach((k, v) -> {
            DPSUtil(v);
        });
    }
    
    // searchs a word in the tree
    // if the tree is a suffix tree uses searchWordUtilS (suffix search)
    // otherwise uses searchWordUtil (trie search)
    public boolean searchWord(String w) {
        if (suffix)
            return searchWordUtilS(w, root);
        return searchWordUtil(w, root);
    }
    
    // searchs a word in the suffix tree
    // almost the same a trie search
    // except it does a linear comparison at each node to match the word prefix with the node's string
    // if the match is successful then search the rest of the word suffix down the tree until the string is empty
    // once the string is empty return true if the node is a leaf
    public boolean searchWordUtilS(String w, Node n){
        if (n == null)
            return false;
            
        int l = n.i.length();
        
        if (w.length() < l)
            return false;
            
        String pref = w.substring(0, l);
        String suff = w.substring(l);
        
        if (compare(pref, n.i)) {
            if (suff.isEmpty()) 
                return n.isLeaf;
                
            String k = suff.substring(0, 1);
            if (k.compareTo(".") == 0) {
                return n.children.values().stream().anyMatch(v -> {
                    return searchWordUtilS(suff, v);  
                });
            }
            Node c = n.children.get(k); 
            return searchWordUtilS(suff, c);
        }
        return false;
    }
  
    // search a word in the triee tree
    // at each node strip the first character of the word and look for it in the children HashMap
    // if no node is found and the character is a "." then run the search with the suffix in every children node
    public boolean searchWordUtil(String w, Node n){
        if (n == null)
            return false;
            
        if (w.isEmpty())
            return n.isLeaf;
    
        String c = w.substring(0, 1);
        if (c.compareTo(".") == 0){
            return n.children.values().stream().anyMatch(v -> {
                return searchWordUtil(w.substring(1), v); 
            });
        }

        return searchWordUtil(w.substring(1),  n.children.get(c));
    }
	
    // compares two words ignoring "." character
    public boolean compare(String w, String n){
        for (int i = 0; i < w.length(); i++){
            if (w.charAt(i) == '.'){} 
            else if (w.charAt(i) != n.charAt(i)){
                return false;
            }
        }
        return true;
    }
    
}
