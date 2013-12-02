/**
 * 
 */
package is3;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import prefuse.Display;
import prefuse.demos.ScatterPlot;

/**
 * @author Michael
 * 
 */
public class OlympicDataVisualiserFrame extends JFrame {

	private String data = "/WrangledData.csv";
	private String[] visualisationOptions = { "Olympics" };
	private ArrayList<Display> visualisations = new ArrayList<Display>();

	/**
	 * 
	 */
	public OlympicDataVisualiserFrame() {

		for (String visualisation : visualisationOptions) {
			Class<?> clazz;
			Constructor<?> constructor;
			try {
				clazz = Class.forName("is3."+visualisation.replaceAll("\\s+", ""));
				constructor = clazz.getConstructor(String.class);
				visualisations.add((Display) constructor.newInstance(data));
			} catch (ClassNotFoundException | NoSuchMethodException
					| SecurityException | InstantiationException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}

		setTitle("Olympic Data Visualiser");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JComboBox<String> visualisationSelector = new JComboBox<String>(
				visualisationOptions);

		getContentPane().add(visualisationSelector, BorderLayout.PAGE_START);
		getContentPane().add(
				ScatterPlot.demo("/fisher.iris.txt", "SepalLength",
						"PetalLength"), BorderLayout.CENTER);

		pack();
		setVisible(true);	
	}

	private class ComboBoxListener implements ActionListener {

		@SuppressWarnings("rawtypes")
		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox source = (JComboBox) e.getSource();
			String value = (String) source.getSelectedItem();
			for (int i = 0; i < visualisationOptions.length; i++) {
				if (value.equalsIgnoreCase(visualisationOptions[i])) {
					getContentPane().remove(1);
					getContentPane().add(visualisations.get(i));
					pack();
				}
			}
		}

	}
	
}
