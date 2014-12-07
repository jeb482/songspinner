package songspinner;
import java.awt.*;
import javax.swing.JComponent;

public class WebComponent extends JComponent{
	private Web web;
	
	public WebComponent(Web web){
		super();
		this.web = web;
	}
	
	public WebComponent(){
		this(Web.BuildPerfectWeb(10, 10, 1, .7f));
	}

	
}
