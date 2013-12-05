package is3;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;

public class Box extends Component{
	
	int width = 0;
	int height = 0;
	Rectangle s;
	Color colour;
	
	 /**
     * Create a new Box object.
     * @param i the width of the box
     * @param j the height of the box
     * @param r the red value of the colour of the box
     * @param g the green value of the colour of the box
     * @param b the blue value of the colour of the box
     * @param a the alpha value of the colour of the box
     */
	public Box (int i, int j, int r, int g, int b, int a){
		width = i;
		height = j;
		s = new Rectangle(i, j);
		colour = new Color(r, g, b, a);
	}
	
	/**
	 * Create a new Box object. 
     * @param i the width of the box
     * @param j the height of the box
	 * @param c the colour code of the box with alpha
	 */
	public Box (int i, int j, int c){
		width = i;
		height = j;
		s = new Rectangle(i, j);
		colour = new Color(c, true);
	}
	
    public Dimension getPreferredSize(){
        return new Dimension(width + 1, height);
    }
    
	public void paint (Graphics g) {
	    Graphics2D g2 = (Graphics2D) g;
	    g2.setStroke((Stroke) new BasicStroke(0.5f,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND) );
	    g2.setPaint(colour);
	    g2.fill(s);
	}
}
