import java.awt.Dimension;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;


public class ShipYard {

	public ShipNode start;
	private int size = 6;
	private Dimension goal;
	private ArrayList<ShipNode> visitedNodes;
	private ShipNode end;
	private ArrayList<ShipNode> path;
	public ArrayList<Boat> boatsMoved;
	
	public ShipYard(int[][] board){
		goal = getGoal(board);
		start = new ShipNode(board,null,goal);
		
	}
	public ShipYard(int[][] board, Dimension goal){
		this.goal = goal;
		start = new ShipNode(board,null,goal);
	}
	
	public ShipNode breadthFirstSearch(){
		visitedNodes = new ArrayList<>();
		visitedNodes.add(start);
		Queue<ShipNode> q = new ArrayDeque<ShipNode>();
		for(ShipNode s: start.getMoves()){
			q.add(s);
			visitedNodes.add(s);
		}
		ShipNode temp = q.remove();
		while(!temp.reachedGoal()){
			//temp.printState();
			//System.out.println();
			for(ShipNode s: temp.getMoves()){
				if(!checkVisited(s)){
					q.add(s);
					visitedNodes.add(s);
				}
			}
			temp = q.remove();
		}
		end = temp;
		setEndPath(end);
		boatsMoved = new ArrayList<Boat>();
		for(int i=0;i<path.size()-1;i++){
			getChangedBoats(path.get(i),path.get(i+1));
		}
		return temp;
	}
	public ShipNode depthFirstSearch(){
		visitedNodes = new ArrayList<>();
		visitedNodes.add(start);
		Stack<ShipNode> st = new Stack<ShipNode>();
		for(ShipNode s: start.getMoves()){
			st.push(s);
			visitedNodes.add(s);
		}
		ShipNode temp = st.pop();
		while(!temp.reachedGoal()){
			//temp.printState();
			//System.out.println();
			for(ShipNode s: temp.getMoves()){
				if(!checkVisited(s)){
					st.push(s);
					visitedNodes.add(s);
				}
			}
			temp = st.pop();
		}
		end = temp;
		setEndPath(end);
		boatsMoved = new ArrayList<Boat>();
		for(int i=0;i<path.size()-1;i++){
			getChangedBoats(path.get(i),path.get(i+1));
		}
		return temp;
	}
	public Dimension getGoal(int[][] state){
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(state[i][j] == -1){
					goal = new Dimension(i,j);
				}
			}
		}
		return goal;
	}
	public void setEndPath(ShipNode end){
		path = new ArrayList<>();
		path.add(0,end);
		
		while(end.getParent()!=null){
			path.add(0,end.getParent());
			end = end.getParent();
		}
	}
	public void printEndPath(ShipNode end){
		setEndPath(end);
		for(ShipNode s: path){
			s.printState();
			System.out.println();
		}
	}
	public ArrayList<ShipNode> getPath() {
		return path;
	}
	public void setPath(ArrayList<ShipNode> path) {
		this.path = path;
	}
	public boolean checkVisited(ShipNode move){
		for(ShipNode s: visitedNodes){
			if(Arrays.deepEquals(s.getState(), move.getState())){
				return true;
			}
		}
		return false;
	}
	public ShipNode getEnd() {
		return end;
	}
	public void setEnd(ShipNode end) {
		this.end = end;
	}
	public void getChangedBoats(ShipNode ship1, ShipNode ship2){
		for(Boat b: ship1.getBoats()){
			if(!ship2.getBoat(b.getId()).getArea().equals(b.getArea())){
				boatsMoved.add(b);
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
		ShipYard game = new ShipYard(temp,new Dimension(2,5));
		ShipNode end = game.breadthFirstSearch();
		//end = game.depthFirstSearch();
		game.printEndPath(end);
		for(Boat b: game.boatsMoved){
			System.out.print(b.getId() + " ");
		}

	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public ShipNode getStart() {
		return start;
	}
	public void setStart(ShipNode start) {
		this.start = start;
	}
}
