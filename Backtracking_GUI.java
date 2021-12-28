package String_Recognition;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;


public class Backtracking_GUI implements ActionListener{
	
	private JFrame board;
	private JButton[][] cell;

	public static Scanner sc = new Scanner(System.in);
	
	public Backtracking_GUI(int row, int column) {
		
		cell = new JButton[row][column];
		
		char[][] table = new char [row][column];
		
		board = new JFrame(" STRING RECOGNITION");
		board.setSize(650, 650);
		board.setLayout(new GridLayout(row, column));
		
		for(int i=0; i<row; i++) {
			
			for(int j=0; j<column; j++) {
				
				cell[i][j] = new JButton();
				
				int number = (int)(Math.random() * 26)+97;
				table[i][j] = (char)number;
				cell[i][j].setText("" + (char)number);
				cell[i][j].setFocusable(false);
				cell[i][j].setFont(new Font("Comic sans",Font.BOLD,35));
				board.add(cell[i][j]);
				cell[i][j].addActionListener(this);
				
				cell[i][j].setBackground(Color.MAGENTA);
			}
		}
		//new String_Input_GUI();
		board.setVisible(true);
		String_Input_GUI(table);
		
		
	}

	private void String_Input_GUI(char[][] table) {
		for(int i=0; i<table.length; i++) {
			for(int j=0; j<table.length; j++) {
				System.out.print(" " + table[i][j] + "");
			}System.out.println();
		}
		
		board = new JFrame(" STRING RECOGNITION");
		board.setSize(350,350);
		
		//System.out.print(" \n Enter no. of words :");
        //int no_of_words = sc.nextInt();
        int no_of_words = 1;
        
        String[] WORDS = new String[no_of_words];
        
        for(int i=0; i<no_of_words; i++){
            System.out.print(" Enter word " + (i+1) + " : ");
            WORDS[i] = sc.next();
        }
        
        List<String> answer=new ArrayList<String>();
        
        answer = findWords(table, WORDS);
        
        System.out.println(" \n Answer : " + answer);
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
}
