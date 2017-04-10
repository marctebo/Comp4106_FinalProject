import java.awt.Dimension;
import java.util.ArrayList;


public class Boat {
	private ArrayList<Dimension> area;
	private int id;
	
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
	
	public static void main(String args[]){
		Boat b = new Boat(1);
		b.getArea().add(new Dimension(1,1));
		Dimension test = new Dimension(1,1);
		System.out.println(b.getArea().contains(test));
	}
}
