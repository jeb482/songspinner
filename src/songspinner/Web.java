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

	
	public void clear() {
		this.radii.clear();
		this.captureSpiral.clear();
	}
	
	public void set(Web other) {
		this.clear();
		for (WebStrand s : other.radii) {
			this.radii.add(s.clone());
		}
		for (WebStrand s : other.captureSpiral) {
			this.captureSpiral.add(s.clone());
		}
	}
	
	
	
	public static Web BuildPerfectWeb(int numRadii, int numCircles, float radiusLength, float lastCircleDistance){
		Web web = new Web();
		double theta = 2*Math.PI/numRadii;
		
		// Set up the radii
		for (int i = 0; i < numRadii; i++) {
			Vector2 endpoint = new Vector2((float)Math.cos(i*theta), (float)Math.sin(i*theta));
			web.radii.add(new WebStrand(web.center, endpoint));
		}
		
		// Write the circles
		float radialDistance = lastCircleDistance/(numCircles); 
		
		for (int i = 0; i < web.radii.size(); i++) {
			Vector2 thisRad = web.radii.get(i).end;
			Vector2 nextRad = web.radii.get((i+1) % web.radii.size()).end;
			for (int circle = 1; circle <= numCircles; circle++) {
				web.captureSpiral.add(new WebStrand(thisRad.clone().mul(circle*radialDistance),
													nextRad.clone().mul(circle*radialDistance)));
			}
		}
		
		return web;
	}
	
	
	
}
