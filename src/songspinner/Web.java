package songspinner;
import java.util.ArrayList;
import egl.math.Vector2;

public class Web {
	final Vector2 center = new Vector2();
	ArrayList<WebStrand> radii; 
	ArrayList<WebStrand> captureSpiral;


	public Web() {
		this.radii = new ArrayList<WebStrand>();
		this.captureSpiral = new ArrayList<WebStrand>();
	}

	public static Web BuildPerfectWeb(int numRadii, int numCircles, float radiusLength, float lastCircleDistance){
		Web web = new Web();
		double theta = Math.PI/numRadii;
		
		// Set up the radii
		for (int i = 0; i < numRadii; i++) {
			Vector2 endpoint = new Vector2((float)Math.cos(i*theta), (float)Math.sin(i*theta));
			web.radii.add(new WebStrand(web.center, endpoint));
		}
		
		// Write the circles
		for (WebStrand r : web.radii) {
			for (int i = 0; i < numCircles; i++) {
				
			}
			
		}
		
		return web;
	}
	
}
