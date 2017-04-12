import java.awt.Dimension;
import java.util.ArrayList;


public class ShipNode {

	public static final int size = 6;
	private int[][] state;
	private ShipNode parent;
	private ArrayList<Boat> boats;
	private ArrayList<ShipNode> moves;
	private Dimension goal;
	
	public ShipNode(int[][] state,ShipNode parent){
		this.state = state;
		this.parent = parent;
		getBoats();
		getGoal();
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
	
	public Dimension getGoal(){
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(state[i][j] == -1){
					goal = new Dimension(i,j);
				}
			}
		}
		return goal;
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
					moves.add(new ShipNode(temp,this));
				}
				for(int j=0;j<b.getBackward();j++){	//getting backwards horizontal moves
					int[][] temp = copyArray(state);
					for(Dimension d: b.getArea()){
						temp[(int)d.getWidth()][(int)d.getHeight()] = 0;
					}
					for(Dimension d: b.getArea()){
						temp[(int)d.getWidth()][(int)d.getHeight()-j-1] = b.getId();
					}
					moves.add(new ShipNode(temp,this));
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
					moves.add(new ShipNode(temp,this));
				}
				for(int i=0;i<b.getBackward();i++){	//getting backwards vertical moves
					int[][] temp = copyArray(state);
					for(Dimension d: b.getArea()){
						temp[(int)d.getWidth()][(int)d.getHeight()] = 0;
					}
					for(Dimension d: b.getArea()){
						temp[(int)d.getWidth()+i+1][(int)d.getHeight()] = b.getId();
					}
					moves.add(new ShipNode(temp,this));
				}
			}
		}
		
		return moves;
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
	public static void main(String args[]){
		int[][] temp = {{0,0,3,3,0,0},
						{0,0,2,0,0,0},
						{1,1,2,0,0,-1},
						{0,0,0,4,4,0},
						{0,0,0,0,0,0},
						{0,0,5,5,0,0}};
		ShipNode s = new ShipNode(temp,null);
		Boat b = s.getBoats().get(3);
		System.out.println(b.getId());
		b.generateFreeSpace(temp, 6);
		System.out.println(b.getForward() + "    " + b.getBackward());
		
	}
}
