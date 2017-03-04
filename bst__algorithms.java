import java.io.*;
import java.util.*;
class Node {
    int data;
    Node left;
    Node right;
    Node(int i){
        data = i;
        left = right = null;
    }
}
class BinarySearchTree {
    Node root;
    
    BinarySearchTree(){
        root = null;
    }
    
    BinarySearchTree(int s){
        root = new Node(s);
    }
    
    void addNode(int s){
        if (root == null){
            root.data = s;
        }
        if (s == root.data){
           System.out.println("key already inserted");
        } else
        addNodeUtil(s, root);
        
    }
    
    Node addNodeUtil(int s, Node tree){
        if(tree == null){
            tree = new Node(s);
        } else if (tree.data == s){
            System.out.println("key already added");
        } else { 
            if (s > tree.data)
                tree.right = addNodeUtil(s, tree.right);
            if (s < tree.data)
                tree.left = addNodeUtil(s, tree.left);
        }
        return tree;
    }
    
    void inOrderTraversal(Node tree){
        if (tree == null) {
            return;
        }
        else {
            inOrderTraversal(tree.left);
            System.out.print(tree.data + " ");
            inOrderTraversal(tree.right);
        }
    }
    
    void preOrderTraversal(Node tree){
        if (tree == null){
            return;
        }else{
            System.out.print(tree.data + " ");
            preOrderTraversal(tree.left);
            preOrderTraversal(tree.right);
        }
    }
    
       void postOrderTraversal(Node tree){
        if (tree == null){
            return;
        }else{
            preOrderTraversal(tree.left);
            preOrderTraversal(tree.right);
            System.out.print(tree.data + " ");
        }
    }
    
	public static void main (String[] args) {
		BinarySearchTree tree = new BinarySearchTree(10);
		int keys[] = {67, 98, 99, 5, 7, 8, 8, 10 ,12 ,15, 67, 40, 30, 20, 50};
		Arrays.stream(keys).forEach(tree::addNode);
		System.out.println("In order:");
		tree.inOrderTraversal(tree.root);
		System.out.println("\nPre order:");
		tree.preOrderTraversal(tree.root);
		System.out.println("\nPost order:");
		tree.postOrderTraversal(tree.root);
	}
}