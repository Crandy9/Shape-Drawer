/*
 * File: Oval.java
 * Author: Linden Crandall
 * Purpose: Create a GUI that displays rectangles and ovals 
 * with various characteristics and locations on JFrame within a certain bounds
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Oval extends Shape {
	
	public Oval(Rectangle r, Color color, boolean isSolid) {
		super(r,color, isSolid);
	}

	//draw oval
	@Override
	public void draw(Graphics g) {
		setColor(g);
		
		if (getSolid()) {
		g.fillOval(this.x, this.y, this.width, this.height);
		} else if (super.getSolid() == false) {
			g.drawOval(this.x, this.y, this.width, this.height);
		}
	}
}