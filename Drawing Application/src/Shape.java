/*
 * File: Shape.java
 * Author: Linden Crandall
 * Purpose: Create a GUI that displays rectangles and ovals 
 * with various characteristics and locations on JFrame within a certain bounds
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Shape extends Rectangle {
	//instance variable color of shape
	private Color color;
	//instance variable hollow or solid variable
	private boolean isSolid;
	//static variable that keeps track of how many shapes have been created
	private static int shapeTracker;
	
	public Shape(Rectangle r, Color color, boolean isSolid) {
		this.color = color;
		this.isSolid = isSolid;
		this.setRect(r.x, r.y, r.width, r.height);
		shapeTracker++;
	}
	
	public void setColor(Graphics g) {
		g.setColor(this.color);
	}
	
	public boolean getSolid() {
		return this.isSolid;
	}
	
	public static int getNoOfShapes() {
		return shapeTracker;
	}
	
	public abstract void draw(Graphics g);

}
