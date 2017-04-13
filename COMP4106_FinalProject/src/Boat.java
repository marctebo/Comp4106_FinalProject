import java.awt.Dimension;
import java.util.ArrayList;


public class Boat {
	public static final int HORIZ = 1;
	public static final int VERT = 2;
	private ArrayList<Dimension> area;
	private int id, orient, forward, backward;
	private Dimension front,back;
	private boolean goalBoat = false;

	public Boat(int id){
		this.id = id;
		area = new ArrayList<Dimension>();
		if(id==1){
			goalBoat = true;
		}
	}

	public ArrayList<Dimension> getArea() {
		return area;
	}
	public void setArea(ArrayList<Dimension> area) {
		this.area = area;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrient() {
		return orient;
	}
	public void setOrient(int orient) {
		this.orient = orient;
	}	
	public int getForward() {
		return forward;
	}
	public void setForward(int forward) {
		this.forward = forward;
	}
	public int getBackward() {
		return backward;
	}
	public void setBackward(int backward) {
		this.backward = backward;
	}
	public Dimension getFront() {
		return front;
	}
	public void setFront(Dimension front) {
		this.front = front;
	}
	public Dimension getBack() {
		return back;
	}
	public void setBack(Dimension back) {
		this.back = back;
	}
	public boolean isGoalBoat() {
		return goalBoat;
	}

	public void setGoalBoat(boolean goalBoat) {
		this.goalBoat = goalBoat;
	}

	public void findOrient(){
		if(area.get(0).getWidth()==area.get(1).getWidth()){
			orient = HORIZ;
		}
		else{
			orient = VERT;
		}
	}
	public void setFrontBack(){
		if(orient == HORIZ){
			back = area.get(0);
			front = area.get(area.size()-1);
		}
		else{
			front = area.get(0);
			back = area.get(area.size()-1);
		}
	}
	public void generateFreeSpace(int[][] state,int size){
		forward = 0;
		backward = 0;
		if(orient == HORIZ){
			for(int j=(int)front.getHeight()+1;j<size;j++){
				int i = (int)front.getWidth();
				if(state[i][j]==0 || state[i][j]==-1){
					forward++;
				}
				else{
					break;
				}
			}
			for(int j=(int)back.getHeight()-1;j>=0;j--){
				int i = (int)back.getWidth();
				if(state[i][j]==0 || state[i][j]==-1){
					backward++;
				}
				else{
					break;
				}
			}
		}
		
		else{
			for(int i=(int)front.getWidth()-1;i>=0;i--){
				int j = (int)front.getHeight();
				if(state[i][j]==0 || state[i][j]==-1){
					forward++;
				}
				else{
					break;
				}
			}
			for(int i=(int)back.getWidth()+1;i<size;i++){
				int j = (int)back.getHeight();
				if(state[i][j]==0 || state[i][j]==-1){
					backward++;
				}
				else{
					break;
				}
			}
		}
	}

	public static void main(String args[]){
		Boat b = new Boat(1);
		b.getArea().add(new Dimension(1,1));
		b.getArea().add(new Dimension(2,1));
		b.findOrient();
		b.setFrontBack();
		System.out.println(b.getFront() + "    " + b.getBack());
	}
}
