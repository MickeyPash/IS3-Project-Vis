/**
 * 
 */
package is3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Shape;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import prefuse.Display;
import prefuse.util.ColorLib;
import prefuse.render.ShapeRenderer;

/**
 * @author Michael
 * 
 */
@SuppressWarnings("serial")
public class OlympicScatterplotPanel extends JPanel implements ActionListener, ItemListener {
	public Display visualisation;

	private JComboBox<String> xSelect, ySelect, sizeSelect;
	private JCheckBox middleEast, europe, africa, northAmerica, southAmerica, oceania, asia;
	private boolean middleEastFiltered = false;
	private boolean europeFiltered = false;
	private boolean africaFiltered = false;
	private boolean northAmericaFiltered = false;
	private boolean southAmericaFiltered = false;
	private boolean oceaniaFiltered = false;
	private boolean asiaFiltered = false;
	
	private Color[] colours = {
			// Middle East
			new Color(20, 120, 100, 125),
			// Europe
			new Color(105, 245, 40, 125),
			// Africa
			new Color(200, 200, 135, 125),
			// North America
			new Color(35, 50, 225, 125),
			// South America
			new Color(190, 115, 20, 125),
			// Oceania & some Asia
			new Color(95, 175, 210, 125),
			// Asia
			new Color(220, 15, 40, 125),
			// Unselected
			new Color(100, 100, 100)
	};
	private String[] continents = new String[] {
			 "Middle East",
			 "Europe",
			 "Africa",
			 "North America",
			 "South America",
			 "Oceania & some Asia",
			 "Asia"};

	public OlympicScatterplotPanel(String data) {
		setLayout(new BorderLayout());

		visualisation = new OlympicScatterplot(data);
		
		JCheckBox[] colourBoxes = {
				middleEast = new JCheckBox(new ColouredCheckBoxIcon(colours[0]), true),
				europe = new JCheckBox(new ColouredCheckBoxIcon(colours[1]), true),
				africa = new JCheckBox(new ColouredCheckBoxIcon(colours[2]), true),
				northAmerica = new JCheckBox(new ColouredCheckBoxIcon(colours[3]), true),
				southAmerica = new JCheckBox(new ColouredCheckBoxIcon(colours[4]), true),
				oceania = new JCheckBox(new ColouredCheckBoxIcon(colours[5]), true),
				asia = new JCheckBox(new ColouredCheckBoxIcon(colours[6]), true)
		};

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
				"General government expenditure on health as percentage of total government expenditure"};
		
		String [] fields_plus_none = {"None", 
				"F2012", "M2012", "TeamSize", "Gold", "Silver", "Bronze",
				"Adult literacy rate (%)", "Population (in thousands) total",
				"Population median age (years)",
				"Community and traditional health workers density (per 10 000 population)",
				"General government expenditure on health as percentage of total expenditure on health",
				"General government expenditure on health as percentage of total government expenditure"};
		
		ySelect = new JComboBox<String>(fields);
		ySelect.setSelectedIndex(3);
		ySelect.addActionListener(this);

		xSelect = new JComboBox<String>(fields);
		xSelect.setSelectedIndex(2);
		xSelect.addActionListener(this);
		
		sizeSelect = new JComboBox<String>(fields_plus_none);
		sizeSelect.setSelectedIndex(0);
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

		JPanel colourLegend = new JPanel(new FlowLayout());
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Legend");
		colourLegend.setBorder(title);
		title = BorderFactory.createTitledBorder("Drop Down Boxes");
		dropDownMenus.setBorder(title);

		JCheckBox colourBox;
		JLabel label;
		for(int i=0; i<continents.length; i++){
			label = new JLabel(continents[i]);
			// colourLegend.add(box);
			colourBox = colourBoxes[i];
			colourBox.addItemListener(this);
			colourLegend.add(colourBox);
			colourLegend.add(label);
		}

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
	
	private void changeColour(JCheckBox box, int colourPos) {
		if ( box.isSelected() ) 
			((ColouredCheckBoxIcon) box.getIcon()).setColour(colours[colourPos]);
    	else ((ColouredCheckBoxIcon) box.getIcon()).setColour(colours[7]);
    	
		box.getIcon().paintIcon(box, box.getGraphics(), box.getX(), box.getY());
	}
	
	/** Listens to the check boxes. */
    public void itemStateChanged(ItemEvent e) {
    	
        Object source = e.getItemSelectable();
  
        if (source == middleEast) {
        	changeColour(middleEast, 0);
        	if(visualisation instanceof OlympicScatterplot) {
        		OlympicScatterplot s = (OlympicScatterplot) visualisation;
        		if(!middleEastFiltered){ s.Filter("1"); middleEastFiltered = true;}
        		else{ s.deFilter("1"); middleEastFiltered = false;}
        	}
        } else if (source == europe) {
        	changeColour(europe, 1);
        	if(visualisation instanceof OlympicScatterplot) {
        		OlympicScatterplot s = (OlympicScatterplot) visualisation;
        		if(!europeFiltered){ s.Filter("2"); europeFiltered = true;}
        		else {s.deFilter("2"); europeFiltered = false; }
        	}
        } else if (source == africa) {
        	changeColour(africa, 2);
        	if(visualisation instanceof OlympicScatterplot) {
        		OlympicScatterplot s = (OlympicScatterplot) visualisation;
        		if(!africaFiltered){ s.Filter("3"); africaFiltered = true;}
        		else{ s.deFilter("3"); africaFiltered = false;}
        	}
        } else if (source == northAmerica) {
        	changeColour(northAmerica, 3);
        	if(visualisation instanceof OlympicScatterplot) {
        		OlympicScatterplot s = (OlympicScatterplot) visualisation;
        		if(!northAmericaFiltered){ s.Filter("4"); northAmericaFiltered = true;}
        		else{ s.deFilter("4"); northAmericaFiltered = false;}
        	}
        } else if (source == southAmerica) {
        	changeColour(southAmerica, 4);
        	if(visualisation instanceof OlympicScatterplot) {
        		OlympicScatterplot s = (OlympicScatterplot) visualisation;
        		if(!southAmericaFiltered){ s.Filter("5"); southAmericaFiltered = true;}
        		else{ s.deFilter("5"); southAmericaFiltered = false;}
        	}
        } else if (source == oceania) {
        	changeColour(oceania, 5);
        	if(visualisation instanceof OlympicScatterplot) {
        		OlympicScatterplot s = (OlympicScatterplot) visualisation;
        		if(!oceaniaFiltered){ s.Filter("6"); oceaniaFiltered = true;}
        		else{ s.deFilter("6"); oceaniaFiltered = false;}
        	}
        } else if (source == asia) {
        	changeColour(asia, 6);
        	if(visualisation instanceof OlympicScatterplot) {
        		OlympicScatterplot s = (OlympicScatterplot) visualisation;
        		if(!asiaFiltered){ s.Filter("7"); asiaFiltered = true;}
        		else{ s.deFilter("7"); asiaFiltered = false;}
        	}
        }
    }
	
}
