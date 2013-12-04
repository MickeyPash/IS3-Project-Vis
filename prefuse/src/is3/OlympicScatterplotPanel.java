/**
 * 
 */
package is3;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
@SuppressWarnings("serial")
public class OlympicScatterplotPanel extends JPanel implements ActionListener {
	public Display visualisation;

	private JComboBox<String> xSelect;
	private JComboBox<String> ySelect;
	private JComboBox<String> sizeSelect;

	public OlympicScatterplotPanel(String data) {
		setLayout(new BorderLayout());

		visualisation = new OlympicScatterplot(data);

		String[] fields = {
				"F2012",
				"M2012",
				"TeamSize",
				"Gold",
				"Silver",
				"Bronze",
				"Adult literacy rate (%)",
				"Population (in thousands) total",
				"Population median age (years)",
				"Community and traditional health workers density (per 10 000 population)",
				"General government expenditure on health as percentage of total expenditure on health",
				"General government expenditure on health as percentage of total government expenditure" };

		ySelect = new JComboBox<String>(fields);
		ySelect.setSelectedIndex(3);
		ySelect.addActionListener(this);

		xSelect = new JComboBox<String>(fields);
		xSelect.setSelectedIndex(2);
		xSelect.addActionListener(this);

		sizeSelect = new JComboBox<String>(fields);
		sizeSelect.setSelectedIndex(7);
		sizeSelect.addActionListener(this);

		JLabel xTitle = new JLabel("X-Axis");
		JLabel yTitle = new JLabel("Y-Axis");
		JLabel sizeTitle = new JLabel("Size of points");

		JPanel xControls = new JPanel(new FlowLayout());
		xControls.add(xTitle);
		xControls.add(xSelect);
		JPanel yControls = new JPanel(new FlowLayout());
		yControls.add(yTitle);
		yControls.add(ySelect);
		JPanel sizeControls = new JPanel(new FlowLayout());
		sizeControls.add(sizeTitle);
		sizeControls.add(sizeSelect);

		JPanel axisPanel = new JPanel(new BorderLayout());
		axisPanel.add(xControls, BorderLayout.PAGE_END);
		axisPanel.add(yControls, BorderLayout.CENTER);
		axisPanel.add(sizeControls, BorderLayout.PAGE_START);

		add(axisPanel, BorderLayout.PAGE_END);

		add(visualisation, BorderLayout.CENTER);

	}

	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e) {
		JComboBox<String> cb = (JComboBox<String>) e.getSource();
		String field = (String) cb.getSelectedItem();
		if (visualisation instanceof OlympicScatterplot) {
			OlympicScatterplot s = (OlympicScatterplot) visualisation;
			// s.setYField(field);
			if (cb == ySelect)
				s.setYField(field);
			else if (cb == xSelect)
				s.setXField(field);
			else if (cb == sizeSelect)
				s.setDataSizeAction(field);
		}
	}
}
