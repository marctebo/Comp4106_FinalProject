import java.awt.Dimension;
import java.util.ArrayList;


public class ShipNode {

	public final int size = 6;
	private int[][] state;
	private ShipNode parent;
	private ArrayList<Boat> boats;
	private ArrayList<ShipNode> moves;
	
	public ShipNode(int[][] state,ShipNode parent){
		this.state = state;
		this.parent = parent;
		getBoats();
	}
	
	
	public ArrayList<Boat> getBoats(){
		boats = new ArrayList<Boat>();
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(state[i][j]!=0){
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
	
	public ArrayList<ShipNode> getMoves(){
		moves = new ArrayList<ShipNode>();
		
		for(Boat b: boats){
			
		}
		
		return moves;
	}
	public static void main(String args[]){
		int[][] temp = {{0,0,3,3,0,0},
						{0,0,2,0,0,0},
						{1,1,2,0,0,0},
						{0,4,4,4,4,0},
						{0,0,0,0,0,0},
						{0,0,5,5,0,0}};
		ShipNode s = new ShipNode(temp,null);
	}
}
