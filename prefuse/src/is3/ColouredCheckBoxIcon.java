package is3;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.UIManager;

public class ColouredCheckBoxIcon implements Icon {
	
	private Color colour;
	private int width, height;
	
	ColouredCheckBoxIcon(Color iconColour) {

		colour = iconColour;

		Icon icon = UIManager.getIcon("CheckBox.icon");

		width = icon.getIconWidth();

		height = icon.getIconHeight();

		}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		
		g.setColor(colour);
		g.fillRect(x, y, width, height);
		
	}

	@Override
	public int getIconWidth() {
		return width;
	}

	@Override
	public int getIconHeight() {
		return height;
	}
	
	public void setColour(Color c) {
		colour = c;
	}

}
