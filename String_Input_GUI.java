package String_Recognition;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.JFrame;

public class String_Input_GUI implements ActionListener {

	public static Scanner sc = new Scanner(System.in);
	
	private JFrame ANSWER = new JFrame();

	private JLabel found = new JLabel();
	private JLabel input = new JLabel();
	
	//JButton button = new JButton("ENTER");
	//JTextField jt = new JTextField();
	
	String_Input_GUI(char[][] table){
		
		System.out.println("\n RANDOM MATRIX OF ALPHABETS IS :");
        for(int i=0; i<table.length; i++) {
			for(int j=0; j<table[0].length; j++) {
				System.out.print(" " + table[i][j] + "");
			}System.out.println();
		}
		
		ANSWER = new JFrame("ANSWER STRING RECOGNITION");
		ANSWER.setSize(300,300);
		
		System.out.print(" \n Enter no. of words :");
        int no_of_words = sc.nextInt();
        
        //int no_of_words = 1;
        
        String[] WORDS = new String[no_of_words];
        
        for(int i=0; i<no_of_words; i++){
            System.out.print(" Enter word " + (i+1) + " : ");
            WORDS[i] = sc.next();
        }
        
        List<String> answer=new ArrayList<String>();
        
        answer = findWords(table, WORDS);
        
        System.out.println(" \n Answer : " + answer);
        
		//board.add(button);
		//button.setBounds(20,20, 100, 30);
		//button.addActionListener(this);
		//jt.setPreferredSize(new Dimension(250,40));
		
		//board.add(jt);
        List<String> al = new ArrayList<String>();
        al = Arrays.asList(WORDS);
        input.setText("Input text are : " + al);
		found.setText("Found words are : " + answer);
		ANSWER.add(input);
		ANSWER.add(found);
		
		input.setBounds(20, 20, 240, 100);
		found.setBounds(20, 25, 240, 100);
		
		ANSWER.setVisible(true);
	}
	
	// table
	/*
	String_Input_GUI(char[][] board) {
		
		
	}*/

	

	public static void main(String[] args) {
		

	}

public static class TrieNode{
		
        public boolean isWord = false;
        public TrieNode[] child = new TrieNode[26];
        public TrieNode(){
            
        }
    }
    
    public static TrieNode root = new TrieNode();
    
    public static boolean[][] isVisited;
    
    public static List<String> findWords(char[][] board, String[] words) {
    	
        Set<String> result = new HashSet<>();
        
        isVisited = new boolean[board.length][board[0].length];
        
        // Insertion into trie
        addToTrie(words);
        
        // Iterating through board
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
    
    // DFS SEARCHING
    private static void search(char[][] board, int i, int j, TrieNode node, String word, Set<String> result){
    	
    	// Boundary conditions
        if(i >= board.length || i < 0 || j >= board[i].length || j < 0){
            return ;
        }
        
        // Visited condition & child condition
        if(isVisited[i][j] || node.child[board[i][j] - 'a'] == null) {
        	return ;
        }
        
        isVisited[i][j] = true;		// MARK AS VISITED
        
        node = node.child[board[i][j] - 'a'];
        
        if(node.isWord){		// WORD ENDED
            result.add(word + board[i][j]);		//ADDING WORD INTO RESULT
        }
        
        search(board, i-1, j, node, word + board[i][j], result);	//left
        search(board, i+1, j, node, word + board[i][j], result);	//right
        search(board, i, j-1, node, word + board[i][j], result);	//up
        search(board, i, j+1, node, word + board[i][j], result);	//down
        
        isVisited[i][j] = false;		//MARK AS UNVISITED
        
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		
		
//		if(e.getSource()==button) {
//			System.out.println("\n Input text is : " + jt.getText());
//			
//		}
	}
		

}
