import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ShipYardGUI extends JFrame {
	
	ShipYard game;
	private JLabel labels[];
	private JLabel changedLabel;
	private JButton buttons[];
	JPanel panel,panel2,panel3;
	private JFrame window = new JFrame();
	
	public ShipYardGUI(ShipYard game){
		this.game = game;
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
	    changedLabel = new JLabel("Initial State");
	    panel3.add(changedLabel);
		panel2.add(buttons[0]);
		panel2.add(buttons[1]);
		
        window.add(panel, BorderLayout.CENTER);
        window.add(panel2, BorderLayout.SOUTH);
        window.add(panel3, BorderLayout.NORTH);
        int count = 0;
        for(int i = 0; i < game.getSize(); i++){
        	for(int j = 0; j< game.getSize(); j++){
        		labels[count] = new JLabel("" + game.getPath().get(0).getState()[i][j]);
            	panel.add(labels[count]);
            	count++;
        	}
        }
        window.setVisible(true);
        System.out.println(game.getPath().size());
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
        		labels[count].setText("" + game.getPath().get(index).getState()[i][j]);
        		if(index<game.getPath().size()-1){
        			changedLabel.setText("Boat " + game.boatsMoved.get(index).getId() + " has moved");
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
		ShipYardGUI gui = new ShipYardGUI(new ShipYard(temp, new Dimension(2,5)));
		
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
