package songspinner;
import java.util.ArrayList;
import java.util.Random;

import egl.math.Vector2;
import egl.math.Vector2i;

public class Web {
	final Vector2 center = new Vector2();
	ArrayList<WebStrand> radii; 
	ArrayList<Float> thetas;
	ArrayList<WebStrand> captureSpiral;


	public Web() {
		this.radii = new ArrayList<WebStrand>();
		this.captureSpiral = new ArrayList<WebStrand>();
		this.thetas = new ArrayList<Float>();
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
		for (float theta : other.thetas) {
			this.thetas.add(theta);
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
		float thisAngle;
		float firstNoise;
		float thisNoise;
		float nextNoise;
		
		// Set up the radii
		for (int i = 0; i < numRadii; i++) {
			thisNoise = noiseLevel*rand.nextFloat();
			thisAngle = (float)((i+thisNoise)*theta);
			Vector2 endpoint = new Vector2((float)Math.cos(thisAngle), (float)Math.sin(thisAngle));
			web.thetas.add(thisAngle);
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
	
	public void playWeb(NotePlayer player, int startCircle, int startRadius, int numRotations, WebComponent parent) {
		int displaySize = parent.getDisplaySize();
		Spider spider = new Spider(this, startCircle, startRadius);
		parent.spider = spider;
		parent.repaint();
		
		for (int i = 0; i < numRotations*this.radii.size(); i++) {
			spider.updatePosition(displaySize);
			parent.repaint();
			player.playNote(40 + spider.circle*captureSpiral.size()/radii.size() + spider.radius, 400);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e){}
		}
		
		//parent.spider = null;
		parent.repaint();
	}
	
	public Vector2 getNodeLocation(int circle, int radius) {
		return this.captureSpiral.get(circle * this.radii.size() + radius).start.clone();
	}
	
	public Vector2i toVector2i(Vector2 p, int displaySize) {
		int x = (int) ((displaySize/2) * (1+p.x));
		int y = (int) ((displaySize/2) * (1+p.y));
		return new Vector2i(x,y);
	}
	
	
	public int nextNode(int circle, int radius) {
		return 0;
	}
	
}
