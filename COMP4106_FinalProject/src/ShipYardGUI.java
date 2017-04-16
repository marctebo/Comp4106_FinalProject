import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;



public class ShipYardGUI extends JFrame {
	
	private ShipYard game;
	private Color[] colours;
	private JLabel labels[];
	private JLabel changedLabel;
	private JButton buttons[];
	JPanel panel,panel2,panel3,panel4;
	private JFrame window = new JFrame();
	
	public ShipYardGUI(ShipYard game){
		this.game = game;
		generateColours();
		game.breadthFirstSearch();
		labels = new JLabel[game.getSize()*game.getSize()];
		buttons = new JButton[2];
		ButtonListener bl = new ButtonListener(game,this);
		buttons[0] = new JButton("Previous");
		buttons[1] = new JButton("Next");
		buttons[0].addActionListener(bl);
		buttons[1].addActionListener(bl);
		buttons[0].setActionCommand("Previous");
		buttons[1].setActionCommand("Next");
		
		window.setSize(400,500);
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    panel = new JPanel( new GridLayout(6, 6) );
	    panel2 = new JPanel();
	    panel3 = new JPanel();
	    panel4 = new JPanel(new GridLayout(6,1));
	    
	    changedLabel = new JLabel("Initial State");
	    panel3.add(changedLabel);
		panel2.add(buttons[0]);
		panel2.add(buttons[1]);
		
        window.add(panel, BorderLayout.CENTER);
        window.add(panel2, BorderLayout.SOUTH);
        window.add(panel3, BorderLayout.NORTH);
        window.add(panel4,BorderLayout.EAST);
        int count = 0;
        for(int i = 0; i < game.getSize(); i++){
        	for(int j = 0; j< game.getSize(); j++){
        		int num = game.getPath().get(0).getState()[i][j];
        		labels[count] = new JLabel("" + num,SwingConstants.CENTER);
        		labels[count].setOpaque(true);
        		if(num>=0){
        			labels[count].setBackground(colours[num]);
        		}
        		if(num==-1){
        			labels[count].setBackground(colours[0]);
        		}
            	panel.add(labels[count]);
            	count++;
        	}
        }
        for(int i=0;i<game.getSize();i++){
        	if(i ==2){
        		JLabel j = new JLabel(" GOAL ",SwingConstants.CENTER);
        		j.setOpaque(true);
        		j.setBackground(colours[1]);
        		panel4.add(j);
        	}
        	else{
        		JLabel j = new JLabel("",SwingConstants.CENTER);
        		j.setOpaque(true);
        		j.setBackground(new Color(0,0,0));
        		panel4.add(j);
        	}
        }
        window.setVisible(true);
	}
	public void generateColours(){
		colours = new Color[14];
		colours[0] = new Color(255,255,255);
		colours[1] = new Color(0,255,0);
		colours[2] = new Color(175,175,0);
		colours[3] = new Color(255,50,50);
		colours[4] = new Color(0,0,255);
		colours[5] = new Color(0,153,153);
		colours[6] = new Color(255,0,255);
		colours[7] = new Color(255,128,0);
		colours[8] = new Color(21,55,0);
		colours[9] = new Color(0,128,255);
		colours[10] = new Color(128,0,255);
		colours[11] = new Color(51,102,0);
		colours[12] = new Color(128,128,128);
		colours[13] = new Color(51,25,0);
		
	}
	public JPanel getPanel() {
		return panel;
	}
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	public void updateGUI(int index){
        int count = 0;
        for(int i = 0; i < game.getSize(); i++){
        	for(int j = 0; j< game.getSize(); j++){
        		int num = game.getPath().get(index).getState()[i][j];
        		labels[count].setText("" + num);
        		if(num>=0){
        			labels[count].setBackground(colours[num]);
        		}
        		if(num==-1){
        			labels[count].setBackground(colours[0]);
        		}
        		if(index<game.getPath().size()){
        			changedLabel.setText("Boat " + game.boatsMoved.get(index).getId() + " has moved");
        		}
        		if(index==0){
        			changedLabel.setText("Initial State");
        		}
            	count++;
        	}
        }
        
	}
	public static void main(String args[]){
		int[][] temp = {{6,6,3,3,3,7},
				{0,0,2,0,0,7},
				{1,1,2,0,0,-1},
				{0,4,4,4,4,0},
				{0,0,0,0,0,0},
				{0,0,0,0,0,0}};
		int[][] temp2 ={{7, 6, 6, 0, 3, 0},
				{7, 8, 9, 0, 3, 2},
				{7, 8, 9, 1, 1, 2},
				{5, 5, 5 ,4 ,0, 2},
				{0, 0, 12,4,10,10},
				{13,13,12,11,11,0}};
		int[][] temp3 ={{11,11, 2, 0, 5, 5},
				{10,10, 2, 0, 0, 4},
				{9 , 1, 1, 0, 0, 4},
				{9 , 7, 7, 7, 0, 4},
				{9 , 0, 0, 3,12,12},
				{8 , 8, 0, 3, 6, 6}};
		ShipYardGUI gui = new ShipYardGUI(new ShipYard(temp2, new Dimension(2,5)));
		
	}
	
	public class ButtonListener implements ActionListener{
		private ShipYard game;
		private ShipYardGUI gui;
		private int count = 0;
		public ButtonListener(ShipYard game,ShipYardGUI gui){
			this.game = game;
			this.gui = gui;
		}
		public void actionPerformed(ActionEvent e){
			if(e.getActionCommand().equals("Next")){
				if(count<game.getPath().size()){
					gui.updateGUI(count);
					count++;
				}
				if(count == game.getPath().size()){
					JOptionPane.showMessageDialog(gui, "Solution Reached");
				}
			}
			if(e.getActionCommand().equals("Previous")){
				if(count>0){
					count--;
					gui.updateGUI(count);
				}
			}
		}
	}
}
