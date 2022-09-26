package tic_tac_t;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe extends JPanel
{
    JButton buttons[] = new JButton[9]; 
    int alternate = 0;    
    public TicTacToe()
    {
      setLayout(new GridLayout(4,3,1,1));
      initializebuttons(); 
    }
    
    public void initializebuttons()
    {
        for(int i = 0; i <= 8; i++)
        {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial",Font.PLAIN,40));
            buttons[i].setText("");
            buttons[i].addActionListener(new buttonListener());
            
            add(buttons[i]); //adds this button to JPanel (note: no need for JPanel.add(...)
                                //because this whole class is a JPanel already           
        }
        JLabel ins1=new JLabel("PLAYER 1- X");
        ins1.setFont(new Font("Verdana",Font.BOLD,15));
        JLabel ins2=new JLabel("PLAYER 2- O");
        ins2.setFont(new Font("Verdana",Font.BOLD,15));


        
        add(ins1);
        add(ins2);
        
    }
    public void resetButtons()
    {
        for(int i = 0; i <= 8; i++)
        {
            buttons[i].setText("");
        }
    }
    
// when a button is clicked, it generates an ActionEvent. Thus, each button needs an ActionListener. When it is clicked, it goes to this listener class that I have created and goes to the actionPerformed method. There (and in this class), we decide what we want to do.
    private class buttonListener implements ActionListener
    {
       
        public void actionPerformed(ActionEvent e) 
        {
            
            JButton buttonClicked = (JButton)e.getSource(); //get the particular button that was clicked
            if(alternate%2 == 0)
                buttonClicked.setText("X");
            else
                buttonClicked.setText("O");
            
            if(checkForWin() == true)
            {   String var=alternate%2==0?"1":"2";
                JOptionPane.showConfirmDialog(null, "Game Over.\n The Winner is Player "+var);
                resetButtons();
            }
                
            alternate++;
            
        }
        
        public boolean checkForWin()
        {
            /*
                Arrangement of buttons
                
                   0 | 1 | 2
                   3 | 4 | 5
                   6 | 7 | 8
             */
        	
            //horizontal
            if( checkAdjacent(0,1) && checkAdjacent(1,2) ) 
                return true;
            else if( checkAdjacent(3,4) && checkAdjacent(4,5) )
                return true;
            else if ( checkAdjacent(6,7) && checkAdjacent(7,8))
                return true;
            
            
            //vertical
            else if ( checkAdjacent(0,3) && checkAdjacent(3,6))
                return true;  
            else if ( checkAdjacent(1,4) && checkAdjacent(4,7))
                return true;
            else if ( checkAdjacent(2,5) && checkAdjacent(5,8))
                return true;
            
            
            //diagonal
            else if ( checkAdjacent(0,4) && checkAdjacent(4,8))
                return true;  
            else if ( checkAdjacent(2,4) && checkAdjacent(4,6))
                return true;
            else 
                return false;
            
            
        }
        
        public boolean checkAdjacent(int a, int b)
        {
            if ( buttons[a].getText().equals(buttons[b].getText()) && !buttons[a].getText().equals("") )
                return true;
            else
                return false;
        }
        
    }
    
    public static void main(String[] args) 
    {
        JFrame window = new JFrame(" Tic-Tac-Toe ");
        window.getContentPane().add(new TicTacToe());

        window.setBounds(400,300,400,400);
        window.setVisible(true);
    }
}
