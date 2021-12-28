package String_Recognition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


public class Solution {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.print(" Enter no. of rows : ");
		int rows = sc.nextInt();
		
		System.out.print(" Enter no. of columnss : ");
		int columns = sc.nextInt();
		
        
		System.out.println("\n RANDOM MATRIX OF ALPHABETS IS :");
        char[][] table = new char [rows][columns];
     
        
        for (int r = 0; r < rows; r++){
          for (int c = 0; c < columns; c++){
              int number = (int) (Math.random() * 26) + 97;
              table[r][c] = (char) number; 
              System.out.print(" " + table[r][c] + " ");
          }
          System.out.println();
        }
        
        System.out.print(" \n Enter no. of words :");
        int no_of_words = sc.nextInt();
        
        String[] WORDS = new String[no_of_words];
        
        for(int i=0; i<no_of_words; i++){
            System.out.print(" Enter word " + (i+1) + " : ");
            WORDS[i] = sc.next();
        }
        
        List<String> answer=new ArrayList<String>();
        
        answer = findWords(table, WORDS);
        
        System.out.println(" \n Answer : " + answer);
        
        
        System.out.println(" count = " + answer.size());
        System.out.println("boar.0.len = " + WORDS[0]);
        
        // Intialize matrix
        int matrix[][] = new int[rows][columns];
        for(int i=0; i<rows; i++) {
        	for(int j=0; j<columns; j++) {
        		matrix[i][j] = 0;
        	}
        }
       
       sc.close();

	}
	
	public static class TrieNode{
		
        public boolean isWord = false;
        public TrieNode[] child = new TrieNode[26];
        public TrieNode(){
            
        }
    }
    
    public static TrieNode root = new TrieNode();
    
    public static boolean[][] flag;
    
    public static List<String> findWords(char[][] board, String[] words) {
    	
        Set<String> result = new HashSet<>();
        flag = new boolean[board.length][board[0].length];
        
        addToTrie(words);
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(root.child[board[i][j] - 'a'] != null){
                    search(board, i, j, root, "", result);
                }
            }
        }
        
        return new LinkedList<>(result);
    }
    
    private static void addToTrie(String[] words){
    	
        for(String word: words){
            TrieNode node = root;
            
            for(int i = 0; i < word.length(); i++){
                char ch = word.charAt(i);
                if(node.child[ch - 'a'] == null){
                    node.child[ch - 'a'] = new TrieNode();
                }
                node = node.child[ch - 'a'];
            }
            node.isWord = true;
        }
    }
    
    private static void search(char[][] board, int i, int j, TrieNode node, String word, Set<String> result){
    	
    	// Boundary conditions
        if(i >= board.length || i < 0 || j >= board[i].length || j < 0){
            return ;
        }
        
        // Visited condition & child condition
        if(flag[i][j] || node.child[board[i][j] - 'a'] == null) {
        	return ;
        }
        
        flag[i][j] = true;
        node = node.child[board[i][j] - 'a'];
        if(node.isWord){
            result.add(word + board[i][j]);
        }
        
        search(board, i-1, j, node, word + board[i][j], result);	//left
        search(board, i+1, j, node, word + board[i][j], result);	//right
        search(board, i, j-1, node, word + board[i][j], result);	//up
        search(board, i, j+1, node, word + board[i][j], result);	//down
        
        flag[i][j] = false;
        
    }
    
    //A.M.
    private static int[][] print_path(int matrix[][], int path, char[][] board, int i, int j, TrieNode node, String word, Set<String> result){
    	
    	// Boundary conditions
        if(i >= board.length || i < 0 || j >= board[i].length || j < 0){
            return null;
        }
        
        // Visited condition & child condition
        if(flag[i][j] || node.child[board[i][j] - 'a'] == null) {
        	return null;
        }
        
        path++;
        flag[i][j] = true;
        node = node.child[board[i][j] - 'a'];
        if(node.isWord){
            result.add(word + board[i][j]);
            matrix[i][j] = path; 
        }
        
        search(board, i-1, j, node, word + board[i][j], result);	//left
        search(board, i+1, j, node, word + board[i][j], result);	//right
        search(board, i, j-1, node, word + board[i][j], result);	//up
        search(board, i, j+1, node, word + board[i][j], result);	//down
        
        path--;
        flag[i][j] = false;
        
        return matrix;
    }
    
    
}

