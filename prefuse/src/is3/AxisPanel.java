package is3;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import prefuse.Display;

public class AxisPanel extends JPanel implements ActionListener {
	
	private Display d;
	private JComboBox xSelect;
	private JComboBox ySelect;
	
	public AxisPanel(Display d) {
		super(new BorderLayout());
		this.d = d;

		String[] fields = {"F2012", "M2012", "TeamSize", "Gold", "Silver", "Bronze"};
				
		ySelect = new JComboBox(fields);
		ySelect.setSelectedIndex(3);
		ySelect.addActionListener(this);
		
		xSelect = new JComboBox(fields);
		xSelect.setSelectedIndex(2);
		xSelect.addActionListener(this);
		
		JLabel xTitle = new JLabel("X-Axis");
		JLabel yTitle = new JLabel("Y-Axis");
		
		JPanel xControls = new JPanel(new BorderLayout());
		xControls.add(xTitle, BorderLayout.EAST);
		xControls.add(xSelect, BorderLayout.WEST);
		JPanel yControls = new JPanel(new BorderLayout());
		yControls.add(yTitle, BorderLayout.EAST);
		yControls.add(ySelect, BorderLayout.WEST);
		
		add(xControls, BorderLayout.PAGE_START);
		add(yControls, BorderLayout.PAGE_END);
	}
	
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String field = (String)cb.getSelectedItem();
        if (d instanceof OlympicScatterplot) {
        	OlympicScatterplot s = (OlympicScatterplot) d;
        	//s.setYField(field);
        	if (cb == ySelect) s.setYField(field);
        	else s.setXField(field);
        }
    }

}
