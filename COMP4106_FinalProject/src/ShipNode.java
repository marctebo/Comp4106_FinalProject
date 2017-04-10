import java.awt.Dimension;
import java.util.ArrayList;


public class ShipNode {

	public final int size = 6;
	private int[][] state;
	private ShipNode parent;
	private ArrayList<Boat> boats;
	
	public ShipNode(int[][] state,ShipNode parent){
		this.state = state;
		this.parent = parent;
	}
	
	
	public ArrayList<Boat> getBoats(){
		boats = new ArrayList<Boat>();
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(state[i][j]!=0){
					if(!boats.contains(state[i][j])){
						Boat b = new Boat(state[i][j]);
						b.getArea().add(new Dimension(i,j));
					}
					else{
						
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
}
