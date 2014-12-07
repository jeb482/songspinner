package songspinner;
import java.util.ArrayList;
import java.util.Random;
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
		return BuildLinearWeb(numRadii, numCircles, radiusLength, lastCircleDistance, 0);
	}
	
	public static Web BuildLinearWeb(int numRadii, int numCircles, float radiusLength, float lastCircleDistance, float noiseLevel){
		// Repeated use values
		Web web = new Web();
		double theta = 2*Math.PI/numRadii;
		Random rand = new Random();
		float firstNoise;
		float thisNoise;
		float nextNoise;
		
		// Set up the radii
		for (int i = 0; i < numRadii; i++) {
			thisNoise = noiseLevel*rand.nextFloat();
			Vector2 endpoint = new Vector2((float)Math.cos((i+thisNoise)*theta), (float)Math.sin((i+thisNoise)*theta));
			web.radii.add(new WebStrand(web.center, endpoint));
		}
		
		 
		// Write the circles
		nextNoise = (rand.nextFloat()-.5f) * noiseLevel;
		float radialDistance = lastCircleDistance/(numCircles);
		
		
		for (int circle = 1; circle <= numCircles; circle++) {
			firstNoise = (rand.nextFloat()-.5f) * noiseLevel;
			nextNoise = firstNoise;
			for (int i = 0; i < web.radii.size(); i++) {
				Vector2 thisRad = web.radii.get(i).end;
				Vector2 nextRad = web.radii.get((i+1) % web.radii.size()).end;
				thisNoise = nextNoise;
				if (i == web.radii.size()-1) {
					nextNoise = firstNoise;
				} else {
					nextNoise = (rand.nextFloat()-.5f) * noiseLevel;
				}
				web.captureSpiral.add(new WebStrand(thisRad.clone().mul((circle + thisNoise) * radialDistance),
													nextRad.clone().mul((circle + nextNoise) * radialDistance)));
			}
		}
		return web;
	}	
	
}
