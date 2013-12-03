/**
 * 
 */
package is3;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * @author Michael
 *
 */
public class OlympicTreeViewPanel extends JPanel {
	public OlympicTreeView visualisation;

	public OlympicTreeViewPanel(String data){
		setLayout(new BorderLayout());
		
		visualisation = new OlympicTreeView(data);
		
		add(visualisation, BorderLayout.CENTER);
	}
}
