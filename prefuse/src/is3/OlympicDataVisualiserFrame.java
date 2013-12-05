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

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class OlympicDataVisualiserFrame extends JFrame {

	// This is the location of the data, This is passed into the constructor of
	// every visualisation
	private String data = "/WrangledData.csv";

	// This is a String array of the names of visualisations. These names must
	// match the name of the visualisation class (whitespace is allowed)
	private String[] visualisationOptions = { "Olympic Scatterplot",
			"Olympic Tree View" };

	// This is the array the visualisations will be added to when the program
	// runs. Each visualisation will then be hidden/shown
	private ArrayList<JPanel> visualisations = new ArrayList<JPanel>();

	/**
	 * 
	 */
	public OlympicDataVisualiserFrame() {
		TitledBorder title;
		// TODO Add more views

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
						+ visualisation.replaceAll("\\s+", "") + "Panel");
				constructor = clazz.getConstructor(String.class);
				JPanel temp = (JPanel) constructor.newInstance(data);
				title = BorderFactory.createTitledBorder("Visualisation");
				title.setTitleJustification(TitledBorder.CENTER);
				temp.setBorder(title);
				visualisations.add(temp);
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

		JList<String> visualisationSelector = new JList<String>(
				visualisationOptions);

		visualisationSelector.addListSelectionListener(new ComboBoxListener());

		JPanel visualisationSelectorPanel = new JPanel(new BorderLayout());
		visualisationSelectorPanel.add(visualisationSelector,
				BorderLayout.LINE_START);
		title = BorderFactory.createTitledBorder("Choose");
		visualisationSelectorPanel.setBorder(title);

		getContentPane().add(visualisationSelectorPanel,
				BorderLayout.LINE_START);
		getContentPane().add(visualisations.get(0));

		pack();
		setVisible(true);
	}

	private class ComboBoxListener implements ActionListener,
			ListSelectionListener {

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

		@Override
		public void valueChanged(ListSelectionEvent e) {
			String value = ((JList<String>) e.getSource()).getSelectedValue();
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
