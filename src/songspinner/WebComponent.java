package songspinner;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;

import egl.math.Vector2i;

public class WebComponent extends JComponent{
	private static Color SPIDER_COLOR = Color.MAGENTA;
	Web web;
	Spider spider;
	
	public WebComponent(Web web){
		super();
		this.web = web;
	}
	
	public WebComponent(){
		this(Web.BuildLinearWeb(5, 5, 1, .7f, 1f));
//		this(Web.BuildPerfectWeb(5, 5, 1, .7f));
	}
	
	public void setWeb(Web w) {
		this.web.set(w);
		this.repaint();
	}
	
	public void clearWeb() {
		this.web.clear();
		this.repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Dimension dim = this.getSize();
		int xMax = (int) dim.getWidth();
		int yMax = (int) dim.getHeight();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, xMax, yMax);
		
		int displaySize = (xMax > yMax) ? yMax : xMax;
		
		for (WebStrand s : this.web.radii) {
			g.setColor(s.color);
			Vector2i start = s.getStartPixel(displaySize);
			Vector2i end = s.getEndPixel(displaySize);
			g.drawLine(start.x, start.y, end.x, end.y);
//			System.out.println("Drawing radius: " + start + " " + end);
		}
		
		for (WebStrand s : this.web.captureSpiral) {
			g.setColor(s.color);
			Vector2i start = s.getStartPixel(displaySize);
			Vector2i end = s.getEndPixel(displaySize);
			g.drawLine(start.x, start.y, end.x, end.y);
//			System.out.println("Drawing radius: " + start + " " + end);
		}
		
//		if (spider != null) {
//		//	System.out.println("Not null");
//			g.setColor(WebComponent.SPIDER_COLOR);
//			Vector2i pos = spider.pos;
//			g.fillOval(pos.x, pos.y, 4, 4);
//		}
		
		
	}
	
	public void playWeb() {
		web.playWeb(new NotePlayer(), 1, 1, 3,this);
	}
	
	public int getDisplaySize() {
		Dimension dim = this.getSize();
		int xMax = (int) dim.getWidth();
		int yMax = (int) dim.getHeight();
		
		return (xMax > yMax) ? yMax : xMax;
	}

	
}
