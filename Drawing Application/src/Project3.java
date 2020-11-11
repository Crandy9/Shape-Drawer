/*
 * File: Project3.java
 * Author: Linden Crandall
 * Purpose: Create a GUI that displays rectangles and ovals 
 * with various characteristics and locations on JFrame within a certain bounds
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.lang.reflect.Field;
import java.awt.*;
public class Project3 extends JFrame {
	
	private JPanel masterPanel, buttonPanel, labelComboTextFieldPanel, shapeDrawingPanel;
	private JButton draw;
	private JLabel shapeType, fillType, colorLabel, width, height, xCoordinate, yCoordinate;
	private JComboBox rectOrOval, hollowOrSolid, colors;
	private JTextField widthText, heightText, xCoordinateText, yCoordinateText;
	private String stringColor;
	private boolean isSolid;
	
	public Project3 () {
		
		
		
		/***********Master Panel***********/
		masterPanel = new JPanel();
		masterPanel.setLayout(new BorderLayout());
		
		
		
		
		
		
		
		/********ShapeDrawingPanel(Right side)********/
		shapeDrawingPanel = new JPanel();
		shapeDrawingPanel.setBorder(BorderFactory.createTitledBorder("Shape Drawing"));
		
	
		
		
		
		
		
		
		
		/*********Labels, JComboBox and Text Fields Panel (left side)********/
		labelComboTextFieldPanel = new JPanel(new GridLayout(7,2, 0,5));
		labelComboTextFieldPanel.setBorder(new EmptyBorder(5,20,0,0));
		shapeType = new JLabel("Shape Type");
		labelComboTextFieldPanel.add(shapeType);
		
		//create Combo Box with rectangle or oval object to be drawn
		rectOrOval = new JComboBox<>();
		rectOrOval.setModel(new DefaultComboBoxModel<>(new String[] {"Oval", "Rectangle"}));
		labelComboTextFieldPanel.add(rectOrOval);
		fillType = new JLabel("Fill Type");
		labelComboTextFieldPanel.add(fillType);
		
		//create Combo Box with hollow or solid shape
		hollowOrSolid = new JComboBox<>();
		hollowOrSolid.setModel(new DefaultComboBoxModel<>(new String[] {"Solid", "Hollow"}));
		hollowOrSolid.setMaximumRowCount(2);
		labelComboTextFieldPanel.add(hollowOrSolid);
		
		//adding JLabel
		colorLabel = new JLabel("Color");
		labelComboTextFieldPanel.add(colorLabel);
		
		
		//create Combo Box with selected colors
		colors = new JComboBox<>();
		colors.setModel(new DefaultComboBoxModel<>(new String[] {"Black","Red","Orange","Yellow","Green","Blue","Magenta"}));
		labelComboTextFieldPanel.add(colors);
		
		//add JLabels/JTextFields
		width = new JLabel("Width");
		labelComboTextFieldPanel.add(width);
		widthText = new JTextField(10);
		labelComboTextFieldPanel.add(widthText);
		height = new JLabel("Height");
		labelComboTextFieldPanel.add(height);
		heightText = new JTextField(10);
		labelComboTextFieldPanel.add(heightText);
		xCoordinate = new JLabel("x coordinate");
		labelComboTextFieldPanel.add(xCoordinate);
		xCoordinateText = new JTextField(10);
		labelComboTextFieldPanel.add(xCoordinateText);
		yCoordinate = new JLabel("y coordinate");
		labelComboTextFieldPanel.add(yCoordinate);
		yCoordinateText = new JTextField(10);
		labelComboTextFieldPanel.add(yCoordinateText);
		
		
		
		
		
		
		
		
		
		
		
		/*************Draw JButton Panel (Bottom)************/
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.setBorder(new EmptyBorder(0,0,15,0));
		draw = new JButton("Draw");
		buttonPanel.add(draw);
		
		
		
		
		
		
		
		
		
		/**********Add panels to masterPanel***********/
		//add to master panel
		masterPanel.add(BorderLayout.CENTER, shapeDrawingPanel);
		//add to master panel
		masterPanel.add(BorderLayout.SOUTH, buttonPanel);
		//add to master panel
		masterPanel.add(BorderLayout.WEST, labelComboTextFieldPanel);
		//add masterPanel to JFrame
		add(masterPanel);
		
		
		
		
		
		
		
		/************Register Listeners************/
		//Draw button
		draw.addActionListener(new Draw());
	}
		
		/*********Nested Class for JButton draw**********/
		class Draw implements ActionListener {
			
			//create Rectangle instance to be displayed on JFrame
			private Rectangle rectObj; 
			private int w, h, xCoord, yCoord;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Error Messages for non-integer values or blank spaces
				try {
					//width textfield
						w = Integer.parseInt(widthText.getText());
					//height textfield
						h = Integer.parseInt(heightText.getText());
					//x coordinate textfield
						xCoord = Integer.parseInt(xCoordinateText.getText());
					//y coordinate textfield
						yCoord = Integer.parseInt(yCoordinateText.getText());
				} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(widthText, "Please enter valid info for each text field", "Input Error",JOptionPane.ERROR_MESSAGE);
						return;
					}
				//Error messages for numbers that are negative
				try {
					if (w < 0|| h < 0 || xCoord < 0 || yCoord <0) {
						throw new NumberFormatException();
					}
				} catch(NumberFormatException format) {
					JOptionPane.showMessageDialog(widthText, "You cannot enter negative values, please try again", "Input Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				//instantiate Rectangle object with user input from GUI
				rectObj = new Rectangle(xCoord,yCoord,w,h);
				
				//get color from user input in GUI
				stringColor = colors.getSelectedItem().toString().toLowerCase();
				Color color;
				
				//convert color String into type Color using Field
				try {
					Field field = Color.class.getField(stringColor);
					color = (Color)field.get(null);
				} catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException excpetions) {
		            color = null; 
		        }
				
				//set shape to be solid by default
				isSolid = true;
				
				//determining a solid or hollow shape to draw
				if (hollowOrSolid.getSelectedItem().toString().equals("Hollow")) {
					isSolid = false;
				}
				
				//create instance of Drawing class to send shape to be drawn
				Drawing d = new Drawing();
				
				//determining whether an oval or rectangle is to be drawn
				if (rectOrOval.getSelectedItem().toString().equals("Rectangle")) {
					Rectangular r = new Rectangular(rectObj, color, isSolid);
					
					//send drawn object to displayShape method to be displayed in JFrame
					displayShape(r);
					
				} else {
					Oval o = new Oval(rectObj, color, isSolid);
					displayShape(o);
				}
			}
			
			//this method sends shape to drawing classes and draws the shape and displays it on the shapeDrawPanel
			public void displayShape(Shape shape) {
				try {
					Drawing d = new Drawing();
					d.setSize(d.getPreferredSize());
					d.drawShape(shape);
					shapeDrawingPanel.removeAll();
					shapeDrawingPanel.add(d);
					shapeDrawingPanel.repaint();
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "The shape size has exceed the boundary. Please create a shape within 200x200 pixels", "Shape Out of Bounds", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		
		
		
	//main method to create gui
	public static void main(String[] args) {
		Project3 p3 = new Project3();
		p3.setTitle("Geometric Drawing");
		p3.setSize(500,275);
		p3.setVisible(true);
		p3.setLocationRelativeTo(null);
		p3.setResizable(false);
		p3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
