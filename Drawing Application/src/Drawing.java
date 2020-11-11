/*
 * File: Drawing.java
 * Author: Linden Crandall
 * Purpose: Create a GUI that displays rectangles and ovals 
 * with various characteristics and locations on JFrame within a certain bounds
 */
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

public class Drawing extends JPanel {
	
	//instance variable that contains shape that is currently drawn
	private Shape shape;
	//draws current shape
	//also displays int number of shapes that have been created is upper left corner
	//(use number as titled border)
	@Override
	public void paintComponent(Graphics g) {
		shape.draw(g);
		g.setColor(Color.BLACK);
		g.drawString("" + shape.getNoOfShapes(), 30,30);
		
	}
	
	//specifies dimensions of drawing panel
	@Override
	public Dimension getPreferredSize() {
		Dimension d = new Dimension(200,200);
		return d;
	}
	
	//instance method that checks whether shape will completely fit into panel
	//if not, throws OutsideBounds exception 
	public void drawShape(Shape shape) throws OutsideBounds {
		if(contains(shape.x, shape.y) && contains(shape.x, shape.y + shape.height) && contains(shape.x + shape.width, shape.y) && contains(shape.x + shape.width, shape.y + shape.height)) {
			this.shape = shape;
			repaint();
		} else {
			throw new OutsideBounds("");
			} 
		}
	}
