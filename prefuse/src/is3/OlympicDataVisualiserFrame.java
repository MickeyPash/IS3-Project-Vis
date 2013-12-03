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

	// This is the location of the data, This is passed into the constructor of
	// every visualisation
	private String data = "/WrangledData.csv";
	// This is a String array of the names of visualisations. These names must
	// match the name of the visualisation class (whitespace is allowed)
	private String[] visualisationOptions = { "Olympic Scatterplot", "Olympic Tree View" };
	// This is the array the visualisations will be added to when the program
	// runs. Each visualisation will then be hidden/shown
	private ArrayList<Display> visualisations = new ArrayList<Display>();

	/**
	 * 
	 */
	public OlympicDataVisualiserFrame() {
		
		// TODO Add more views
		// Map view (copy from demo?)
		
		// TODO filter data (sliders, checkboxes)

		// This instantiates the classes based of the name in the
		// visualisationOptions array
		// Each of these classes must have a constructor which takes one
		// string(data) to open the csv file, else the program will exit
		for (String visualisation : visualisationOptions) {
			Class<?> clazz;
			Constructor<?> constructor;
			try {
				clazz = Class.forName("is3."
						+ visualisation.replaceAll("\\s+", ""));
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

		// Setting up the combo box and panel
		setTitle("Olympic Data Visualiser");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JComboBox<String> visualisationSelector = new JComboBox<String>(
				visualisationOptions);
		visualisationSelector.addActionListener(new ComboBoxListener());

		getContentPane().add(visualisationSelector, BorderLayout.PAGE_START);

		// This was just to test that a display could be shown/hidden in the
		// right spot
//		getContentPane().add(
//				ScatterPlot.demo("/fisher.iris.txt", "SepalLength",
//						"PetalLength"), BorderLayout.CENTER);
		getContentPane().add(visualisations.get(0));
		getContentPane().add(new AxisPanel(visualisations.get(0)), BorderLayout.PAGE_END);
		pack();
		setVisible(true);
	}

	private class ComboBoxListener implements ActionListener {

		// This inner class will receive events triggered by the combo box
		// changing and will hide/show the visualisations appropriately
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
