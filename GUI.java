package String_Recognition;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;


public class GUI implements ActionListener{
	
	
	private JLabel lblSR = new JLabel(" STRING RECOGNITION ");
	//private JLabel lblboardsize = new JLabel(" Enter size :");
	
	private JLabel rows = new JLabel(" Enter no. of rows ");
	private JLabel columns = new JLabel(" Enter no. of columns ");
	
	private JSpinner rowsize;
	private JSpinner columnsize;
	private JButton Start = new JButton("START");
	private JFrame Frame; 
	
	GUI(){
		
		Frame = new JFrame("String Recognition");
		Frame.setSize(600,600);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Frame.add(rows);
		Frame.add(columns);
		
		Frame.add(lblSR);
		
		//Frame.add(lblboardsize);
		
		SpinnerNumberModel limit1 = new SpinnerNumberModel(5,1,40,1);
		rowsize = new JSpinner(limit1);
		Frame.add(rowsize);
		
		
		SpinnerNumberModel limit2 = new SpinnerNumberModel(5,1,40,1);
		columnsize = new JSpinner(limit2);
		Frame.add(columnsize);
		
		Frame.add(Start);
		
		lblSR.setBounds(250, 100, 300, 10);
		rows.setBounds(170, 200, 240, 10);
		columns.setBounds(170, 300, 240, 10);
		rowsize.setBounds(320, 200, 50, 20);
		columnsize.setBounds(320, 300, 50, 20);
		Start.setBounds(250,350, 100, 30);
		Start.setFocusable(false);
		
		Start.addActionListener(this);
		Frame.setLayout(null);
		Frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		
		new GUI();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		int row = (int)rowsize.getValue();
		int column = (int)columnsize.getValue();
		
		//new Backtracking_GUI(row,column);
		
		new Board_GUI(row,column);
		
	}

}
