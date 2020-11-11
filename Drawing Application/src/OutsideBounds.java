/*
 * File: OutsideBounds.java
 * Author: Linden Crandall
 * Purpose: Create a GUI that displays rectangles and ovals 
 * with various characteristics and locations on JFrame within a certain bounds
 */
//define checked exception
public class OutsideBounds extends Exception {
	public OutsideBounds(String s) {
		super(s);
	}

}
