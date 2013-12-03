/**
 * 
 */
package is3;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import prefuse.Display;

/**
 * @author Michael
 *
 */
public class OlympicScatterplotPanel extends JPanel implements ActionListener {
	public Display visualisation;
	
	private JComboBox xSelect;
	private JComboBox ySelect;

	public OlympicScatterplotPanel(String data){
		setLayout(new BorderLayout());
		
		visualisation = new OlympicScatterplot(data);
		
		String[] fields = {"F2012", "M2012", "TeamSize", "Gold", "Silver", "Bronze"};
		
		ySelect = new JComboBox(fields);
		ySelect.setSelectedIndex(3);
		ySelect.addActionListener(this);
		
		xSelect = new JComboBox(fields);
		xSelect.setSelectedIndex(2);
		xSelect.addActionListener(this);
		
		JLabel xTitle = new JLabel("X-Axis");
		JLabel yTitle = new JLabel("Y-Axis");
		
		JPanel xControls = new JPanel(new FlowLayout());
		xControls.add(xTitle);
		xControls.add(xSelect);
		JPanel yControls = new JPanel(new FlowLayout());
		yControls.add(yTitle);
		yControls.add(ySelect);
		
		add(xControls, BorderLayout.PAGE_END);
		add(yControls, BorderLayout.WEST);
		
		add(visualisation, BorderLayout.CENTER);

	}
	
	public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String field = (String)cb.getSelectedItem();
        if (visualisation instanceof OlympicScatterplot) {
        	OlympicScatterplot s = (OlympicScatterplot) visualisation;
        	//s.setYField(field);
        	if (cb == ySelect) s.setYField(field);
        	else s.setXField(field);
        }
    }
}
