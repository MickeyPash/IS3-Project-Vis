/**
 * 
 */
package is3;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Shape;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import prefuse.Display;
import prefuse.util.ColorLib;
import prefuse.render.ShapeRenderer;

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

	private int[] palette = new int[] {
			// Middle East
			ColorLib.rgba(20, 120, 100, 125),
			// Europe
			ColorLib.rgba(105, 245, 40, 125),
			// Africa
			ColorLib.rgba(200, 200, 135, 125),
			// North America
			ColorLib.rgba(35, 50, 225, 125),
			// South America
			ColorLib.rgba(190, 115, 20, 125),
			// Oceania & some Asia
			ColorLib.rgba(95, 175, 210, 125),
			// Asia
			ColorLib.rgba(220, 15, 40, 125) };

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

		JPanel dropDownMenus = new JPanel(new GridLayout(0, 1));
		dropDownMenus.add(yControls);
		dropDownMenus.add(xControls);
		dropDownMenus.add(sizeControls);

		// ShapeRenderer shape = new ShapeRenderer();
		// dropDownMenus.add((Component)shape.rectangle(0.0, 0.0, 2, 2));

		JPanel colourLegend = new JPanel(new FlowLayout());

		JPanel axisPanel = new JPanel(new BorderLayout());
		axisPanel.add(dropDownMenus, BorderLayout.PAGE_END);
		axisPanel.add(colourLegend, BorderLayout.PAGE_START);

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
