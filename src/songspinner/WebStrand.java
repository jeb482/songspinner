package songspinner;
import egl.math.Vector2;
import egl.math.Vector2i;
import java.awt.Color;

public class WebStrand {
	final Vector2 start = new Vector2();
	final Vector2 end = new Vector2();
	Color color;
	
	public WebStrand(Vector2 start, Vector2 end, Color color) {
		this.start.set(start);
		this.end.set(end);
		this.color = Color.BLACK;
	}
	
	public WebStrand(Vector2 start, Vector2 end) {
		this(start,end, new Color(0,0,0));
	}
	
	public Vector2i getStartPixel(int displaySize) {
		int x = (int) ((displaySize/2) * (1+start.x));
		int y = (int) ((displaySize/2) * (1+start.y));
		return new Vector2i(x,y);
	}
	
	public Vector2i getEndPixel(int displaySize) {
		int x = (int) ((displaySize/2) * (1+end.x));
		int y = (int) ((displaySize/2) * (1+end.y));
		return new Vector2i(x,y);		
	}
	
	public WebStrand clone() {
		return new WebStrand(this.start, this.end, this.color);
	}
	
	public float getLength() {
		return this.start.dist(end);
	}
}
