package songspinner;

import egl.math.Vector2i;
import java.util.Random;

public class Spider {
	public static final float CHANCE_TO_STAY = .5f;
	
	Web web;
	int circle;
	int radius;
	final Vector2i pos = new Vector2i();
	Random rand;
	
	Spider(Web w, int startCir, int startRad) {
		this.web = w;
		this.circle = startCir;
		this.radius = startRad;
		this.rand = new Random();
		
	}
	
	public void updatePosition(int displaySize){
		this.radius = (this.radius + 1) % this.web.radii.size();
		float webChooser = this.rand.nextFloat();
		// Calculate if spider moves in or out a circle or stays on circle.
		if (webChooser > CHANCE_TO_STAY) {
			if (circle == 0) {
				circle++;
			} else if (circle == (web.captureSpiral.size()/web.radii.size())-1) {
				circle--;
			} else {
				webChooser = this.rand.nextFloat();
				float distanceOut = web.getNodeLocation(circle, radius).dist(web.getNodeLocation(circle+1, radius));
				float distanceIn = web.getNodeLocation(circle, radius).dist(web.getNodeLocation(circle-1, radius));
				if (webChooser < distanceIn/(distanceOut+distanceIn)) {
					circle--;
				} else { 
					circle++;
				}
			}
		}
		this.pos.set(web.toVector2i(web.getNodeLocation(circle, radius), displaySize));
	}
	
	
	
	
}
