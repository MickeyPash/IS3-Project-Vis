/**
 * 
 */
package is3;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

import prefuse.Display;

/**
 * @author Michael
 *
 */
public class OlympicScatterplotPanel extends JPanel {
	public Display visualisation;
	public AxisPanel axisPanel;

	public OlympicScatterplotPanel(String data){
		setLayout(new BorderLayout());
		
		visualisation = new OlympicScatterplot(data);
		axisPanel = new AxisPanel(visualisation);
		
		add(visualisation, BorderLayout.CENTER);
		add(axisPanel, BorderLayout.PAGE_END);
	}
}
