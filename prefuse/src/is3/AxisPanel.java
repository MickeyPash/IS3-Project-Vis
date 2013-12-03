package is3;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import prefuse.Display;

public class AxisPanel extends JPanel implements ActionListener {
	
	OlympicScatterplot d;
	
	public AxisPanel(OlympicScatterplot d) {
		super(new BorderLayout());
		this.d = d;
		
		String[] medals = {"Gold", "Silver", "Bronze"};
		JComboBox ySelect = new JComboBox(medals);
		ySelect.setSelectedIndex(0);
		ySelect.addActionListener(this);
		
		add(ySelect);
	}
	
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String yField = (String)cb.getSelectedItem();
        d.setYField(yField);
    }

}
