import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TicTacToe extends JFrame implements ActionListener{
	public static int BOARD_SIZE = 3 ; 


	public static enum GameStatus{
		XWins,ZWins,Incomplete,Tie;
	}

	private JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE] ; 
	boolean crossTurn = true ; 


	public TicTacToe() {

		super.setTitle("Tic Tac Toe");

		GridLayout grid = new GridLayout(BOARD_SIZE,BOARD_SIZE);
		super.setLayout(grid);
		super.setResizable(false);
		Font font = new Font("Comic Sans",1,150) ;
		for(int row = 0 ; row < BOARD_SIZE ; row++) {
			for(int col = 0 ; col < BOARD_SIZE ; col++) {
				JButton button = new JButton();
				buttons[row][col] = button ; 
				button.addActionListener(this);
				button.setFont(font);
				super.add(button);
			}
		}
		super.setSize(800,800);
		super.setVisible(true) ; 



	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		JButton clickedButton =(JButton) e.getSource();
		makemove(clickedButton);
		GameStatus gs = this.getGameStatus();
		if(gs== GameStatus.Incomplete) {
			return ;
		}
	
		declareWinner(gs);

		int choice = JOptionPane.showConfirmDialog(this, "Do you wan to restart the Game?");
		if(choice == JOptionPane.YES_OPTION) {
			for(int row = 0 ; row < BOARD_SIZE ; row++) {
				for(int col = 0 ; col < BOARD_SIZE ; col++) {
					buttons[row][col].setText("");				
				}
			}
			crossTurn = true ; 

		}else {
			super.dispose();
		}



	}


	private void declareWinner(GameStatus gs) {
		if(gs == GameStatus.XWins) {
			
			JOptionPane.showMessageDialog(this,"X Wins");
		}else if(gs == GameStatus.ZWins) {
			JOptionPane.showMessageDialog(this,"Z Wins");
		}else {
			JOptionPane.showMessageDialog(this,"It is a Tie");
		}

	}


	private GameStatus getGameStatus() {
		String text1="";
		String text2 = "";
		int row = 0 ;  
		int col = 0 ;

		//text inside rows
		while(row < BOARD_SIZE) {
			col = 0 ; 
			while(col < BOARD_SIZE-1) {
				text1 = buttons[row][col].getText();
				text2 = buttons[row][col +1].getText();
				if(!text1.equals(text2)||text1.length()==0) {
					break ; 
				}
				col++;
			}
			if(col == BOARD_SIZE-1) {
				if(text1.equals("X")) {
					return GameStatus.XWins;
				}
				if(text1.equals("O")) {
					return GameStatus.ZWins;
				}

			}
			row++;
		}



		// text inside columns
		col = 0 ;
		while(col < BOARD_SIZE) {
			row = 0 ; 
			while(row < BOARD_SIZE-1) {
				text1 = buttons[row][col].getText();
				text2 = buttons[row+1][col].getText();
				if(!text1.equals(text2)||text1.length()==0) {
					break ; 
				}
				row++;
			}
			if(row == BOARD_SIZE-1) {
				if(text1.equals("X")) {
					return GameStatus.XWins;
				}
				if(text1.equals("O")) {
					return GameStatus.ZWins;
				}

			}
			col++;
		}

		//text inside diagonal 1
		row =0 ;
		col = 0 ; 
		while(row < BOARD_SIZE-1 && col<BOARD_SIZE-1) {
			text1 = buttons[row][col].getText();
			text2 = buttons[row+1][col+1].getText();
			if(!text1.equals(text2)||text1.length()==0) {
				break ; 
			}
			row++;
			col++;
		}

		if(row == BOARD_SIZE-1 && col == BOARD_SIZE-1) {
			if(text1.equals("X")) {
				return GameStatus.XWins;
			}
			if(text1.equals("O")) {
				return GameStatus.ZWins;
			}
		}



		//text inside diagonal 2
		row =0 ;
		col = BOARD_SIZE-1 ; 
		while(row < BOARD_SIZE-1 && col > 0) {
			text1 = buttons[row][col].getText();
			text2 = buttons[row+1][col-1].getText();
			if(!text1.equals(text2)||text1.length()==0) {
				break ; 
			}
			row++;
			col--;
		}

		if(row == BOARD_SIZE-1 && col == 0) {
			if(text1.equals("X")) {
				return GameStatus.XWins;
			}
			if(text1.equals("O")) {
				return GameStatus.ZWins;
			}
		}

		String txt = "";
		for(row =0 ; row < BOARD_SIZE ; row++) {
			for(col = 0 ; col < BOARD_SIZE ; col++) {
				txt = buttons[row][col].getText();
				if(txt.length()==0) {
					return GameStatus.Incomplete;
				}
			}
		}


		return GameStatus.Tie;
	}


	private void makemove(JButton clickedButton) {
		// TODO Auto-generated method stub
		String btntext = clickedButton.getText();
		if(btntext.length() > 0) {
			JOptionPane.showMessageDialog(this,"Invalid Move");
			return;

		}else {
			if(crossTurn) {
				clickedButton.setText("X");

			}else {
				clickedButton.setText("O");
			}
			crossTurn = !crossTurn;
		}

	}





}
