package String_Recognition;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;


public class Board_GUI implements ActionListener{

	
	private JFrame board;
	private JButton[][] cell;

	public static Scanner sc = new Scanner(System.in);
	
	public Board_GUI(int row, int column) {
		
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
		board.setVisible(true);
		new String_Input_GUI(table);
		
	}

	public static void main(String[] args) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
