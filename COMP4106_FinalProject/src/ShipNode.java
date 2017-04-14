import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;


public class ShipNode {

	public static final int size = 6;
	private int[][] state;
	private ShipNode parent;
	private ArrayList<Boat> boats;
	private ArrayList<ShipNode> moves;
	private static Dimension goal;

	
	public ShipNode(int[][] state,ShipNode parent,Dimension goal){
		this.state = state;
		this.parent = parent;
		this.goal = goal;
		getBoats();

	}

	public ArrayList<Boat> getBoats(){
		boats = new ArrayList<Boat>();
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(state[i][j]>0){
					if(!containsId(state[i][j])){
						Boat b = new Boat(state[i][j]);
						b.getArea().add(new Dimension(i,j));
						boats.add(b);
					}
					else{
						getBoat(state[i][j]).getArea().add(new Dimension(i,j));
					}
				}
			}
		}
		for(Boat b: boats){
			b.findOrient();
			b.setFrontBack();
			b.generateFreeSpace(state, size);
		}
		return boats;
	}
	
	public boolean containsId(int id){
		for(Boat b: boats){
			if(b.getId()==id){
				return true;
			}
		}
		return false;
	}
	
	public Boat getBoat(int id){
		for(Boat b: boats){
			if(b.getId() == id){
				return b;
			}
		}
		return null;
	}
	public int[][] getState() {
		return state;
	}

	public void setState(int[][] state) {
		this.state = state;
	}

	public ShipNode getParent() {
		return parent;
	}

	public void setParent(ShipNode parent) {
		this.parent = parent;
	}

	public ArrayList<ShipNode> getMoves(){
		moves = new ArrayList<ShipNode>();
		
		for(Boat b: boats){
			if(b.getOrient()== Boat.HORIZ){
				for(int j=0;j<b.getForward();j++){	//getting forward horizontal moves
					int[][] temp = copyArray(state);
					for(Dimension d: b.getArea()){
						temp[(int)d.getWidth()][(int)d.getHeight()] = 0;
					}
					for(Dimension d: b.getArea()){
						temp[(int)d.getWidth()][(int)d.getHeight()+j+1] = b.getId();
					}
					calibrateBoard(temp);
					if(!hasVisited(temp)){
						moves.add(new ShipNode(temp,this,goal));
					}
				}
				for(int j=0;j<b.getBackward();j++){	//getting backwards horizontal moves
					int[][] temp = copyArray(state);
					for(Dimension d: b.getArea()){
						temp[(int)d.getWidth()][(int)d.getHeight()] = 0;
					}
					for(Dimension d: b.getArea()){
						temp[(int)d.getWidth()][(int)d.getHeight()-j-1] = b.getId();
					}
					calibrateBoard(temp);
					if(!hasVisited(temp)){
						moves.add(new ShipNode(temp,this,goal));
					}
				}
			}
			else{
				for(int i=0;i<b.getForward();i++){	//getting forward vertical moves
					int[][] temp = copyArray(state);
					for(Dimension d: b.getArea()){
						temp[(int)d.getWidth()][(int)d.getHeight()] = 0;
					}
					for(Dimension d: b.getArea()){
						temp[(int)d.getWidth()-i-1][(int)d.getHeight()] = b.getId();
					}
					calibrateBoard(temp);
					if(!hasVisited(temp)){
						moves.add(new ShipNode(temp,this,goal));
					}
				}
				for(int i=0;i<b.getBackward();i++){	//getting backwards vertical moves
					int[][] temp = copyArray(state);
					for(Dimension d: b.getArea()){
						temp[(int)d.getWidth()][(int)d.getHeight()] = 0;
					}
					for(Dimension d: b.getArea()){
						temp[(int)d.getWidth()+i+1][(int)d.getHeight()] = b.getId();
					}
					calibrateBoard(temp);
					if(!hasVisited(temp)){
						moves.add(new ShipNode(temp,this,goal));
					}
				}
			}
		}
		
		return moves;
	}
	
	public boolean hasVisited(int[][] arr){
		ShipNode par = this.parent;
		while(par!=null){
			if(Arrays.deepEquals(arr, par.getState())){
				return true;
			}
			par=par.getParent();
		}
		return false;
	}
	public void printState(){
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				System.out.print(state[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static int[][] copyArray(int[][] arr){
		int[][] temp = new int[size][size];
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				temp[i][j] = arr[i][j];
			}
		}
		return temp;
	}
	public static void calibrateBoard(int[][] board){
		if(board[(int)goal.getWidth()][(int)goal.getHeight()]==0){
			board[(int)goal.getWidth()][(int)goal.getHeight()]=-1;
		}
	}
	public boolean reachedGoal(){
		for(ShipNode s: getMoves()){
			if(s.getState()[(int)goal.getWidth()][(int)goal.getHeight()] == 1){
				return true;
			}
		}
		return false;
	}
	public ShipNode finalPosition(){
		for(ShipNode s: getMoves()){
			if(s.getState()[(int)goal.getWidth()][(int)goal.getHeight()] == 1){
				return s;
			}
		}
		return null;
	}
	public static void main(String args[]){


	}
}
