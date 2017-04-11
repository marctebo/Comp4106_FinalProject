import java.awt.Dimension;
import java.util.ArrayList;


public class Boat {
	public final int HORIZ = 1;
	public final int VERT = 2;
	private ArrayList<Dimension> area;
	private int id;
	private int orient;
	
	public Boat(int id){
		this.id = id;
		area = new ArrayList<Dimension>();
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
	public void findOrient(){
		if(area.get(0).getWidth()==area.get(1).getWidth()){
			orient = HORIZ;
		}
		else{
			orient = VERT;
		}
	}
	public int getOrient() {
		return orient;
	}
	public void setOrient(int orient) {
		this.orient = orient;
	}
	public static void main(String args[]){
		Boat b = new Boat(1);
		b.getArea().add(new Dimension(1,1));
		b.getArea().add(new Dimension(2,1));
		b.findOrient();
		System.out.println(b.getOrient());
	}
}
