package is3;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Olympics {

	public static void main(String[] argv) {

		OlympicScatterplot scatterplot = new OlympicScatterplot(
				"/WrangledData.csv");
		// AxisPanel axisPanel = new AxisPanel(scatterplot);

		JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.add(scatterplot, BorderLayout.PAGE_START);
		// contentPanel.add(axisPanel, BorderLayout.PAGE_END);

		// create a new window to hold the visualization
		JFrame frame = new JFrame("Olympics Graph");
		// ensure application exits when window is closed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(contentPanel);
		frame.pack(); // layout components in window
		frame.setVisible(true); // show the window

	}

}
