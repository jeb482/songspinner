package songspinner;
import egl.math.Vector2;
import egl.math.Colorf;

public class WebStrand {
	final Vector2 start = new Vector2();
	final Vector2 end = new Vector2();
	final Colorf color = new Colorf();
	
	public WebStrand(Vector2 start, Vector2 end) {
		this.start.set(start);
		this.end.set(end);
	}
}
