/*
 * File: Rectangular.java
 * Author: Linden Crandall
 * Purpose: Create a GUI that displays rectangles and ovals 
 * with various characteristics and locations on JFrame within a certain bounds
 */
import java.awt.*;
import java.awt.event.*;

public class Rectangular extends Shape {
	
	public Rectangular(Rectangle r, Color c, boolean isSolid) {
		super(r, c, isSolid);
	}

	//draw rectangle
	@Override
	public void draw(Graphics g) {
		setColor(g);
		if (getSolid()) {
			g.fillRect(this.x, this.y, this.width, this.height);
		} else if (super.getSolid() == false) {
			g.drawRect(this.x, this.y, this.width, this.height);
		}
	}

}
