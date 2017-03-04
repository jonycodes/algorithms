import java.io.*;
import java.util.*;
class Node {
    int data;
    int children;
    Node left;
    Node right;
    Node(int s){
        data = s;
        children = 0;
        left = right = null;
    }
}
class BinaryTree {
    Node root;
    
    BinaryTree(){
        root = null;
    }
    
    BinaryTree(int s){
        root = new Node(s);
    }
    
    Node addNodeUtil(int s, Node tree){
        if (tree == null){
            tree = new Node(s);
        }
        if (tree.left == null) {
            tree.left = new Node(s);
        } else if (tree.right == null){
            tree.right = new Node(s);
        } else {
            if (tree.right.children < tree.left.children){
                tree.right.children++;
                addNodeUtil(s,  tree.right);
            } else {
                tree.left.children++;
                addNodeUtil(s, tree.left);
            }
        }
        return tree;
    }
    void addNode(int s){
        root = addNodeUtil(s, root);
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
	public static void main (String[] args) {
	    BinaryTree tree = new BinaryTree(0);
		int keys[] = {1, 2, 3, 4, 5, 6};
		Arrays.stream(keys).forEach(tree::addNode);
		System.out.println("In order:");
		tree.inOrderTraversal(tree.root);
		
	}
}