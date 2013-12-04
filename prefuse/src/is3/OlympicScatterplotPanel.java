/**
 * 
 */
package is3;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import prefuse.Display;

/**
 * @author Michael
 *
 */
public class OlympicScatterplotPanel extends JPanel implements ActionListener {
	public Display visualisation;
	
	private JComboBox xSelect;
	private JComboBox ySelect;
	private JComboBox sizeSelect;
	private JComboBox filterSelect;
	
	private JLabel rangeSliderLabel1 = new JLabel();
    private JLabel rangeSliderValue1 = new JLabel();
    private JLabel rangeSliderLabel2 = new JLabel();
    private JLabel rangeSliderValue2 = new JLabel();
	
	private RangeSlider rangeFilter = new RangeSlider();

	public OlympicScatterplotPanel(String data){
		setLayout(new BorderLayout());
		
		visualisation = new OlympicScatterplot(data);
		
		String[] fields = {"F2012", "M2012", "TeamSize", "Gold", "Silver", "Bronze",
				"Adult literacy rate (%)", "Population (in thousands) total",
				"Population median age (years)",
				"Community and traditional health workers density (per 10 000 population)",
				"General government expenditure on health as percentage of total expenditure on health",
				"General government expenditure on health as percentage of total government expenditure"};
		
		ySelect = new JComboBox(fields);
		ySelect.setSelectedIndex(3);
		ySelect.addActionListener(this);
		
		xSelect = new JComboBox(fields);
		xSelect.setSelectedIndex(2);
		xSelect.addActionListener(this);
		
		sizeSelect = new JComboBox(fields);
		sizeSelect.setSelectedIndex(7);
		sizeSelect.addActionListener(this);
		
		filterSelect = new JComboBox(fields);
		filterSelect.setSelectedIndex(3);
		filterSelect.addActionListener(this);
		
		rangeSliderLabel1.setText("Lower value:");
        rangeSliderLabel2.setText("Upper value:");
        rangeSliderValue1.setHorizontalAlignment(JLabel.LEFT);
        rangeSliderValue2.setHorizontalAlignment(JLabel.LEFT);
		
		rangeFilter.setPreferredSize(new Dimension(240, rangeFilter.getPreferredSize().height));
	    rangeFilter.setMinimum(0);
	    rangeFilter.setMaximum(10);
	    rangeFilter.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                RangeSlider slider = (RangeSlider) e.getSource();
                rangeSliderValue1.setText(String.valueOf(slider.getValue()));
                rangeSliderValue2.setText(String.valueOf(slider.getUpperValue()));
            }
        });
		
		JLabel xTitle = new JLabel("X-Axis");
		JLabel yTitle = new JLabel("Y-Axis");
		JLabel sizeTitle = new JLabel("Size of points");
		JLabel filterTitle = new JLabel("Filter Data");
		JLabel rangeFilterTitle = new JLabel("Filter Range");
		
		JPanel xControls = new JPanel(new FlowLayout());
		xControls.add(xTitle);
		xControls.add(xSelect);
		JPanel yControls = new JPanel(new FlowLayout());
		yControls.add(yTitle);
		yControls.add(ySelect);
		JPanel sizeControls = new JPanel(new FlowLayout());
		sizeControls.add(sizeTitle);
		sizeControls.add(sizeSelect);
		JPanel filterControls = new JPanel(new FlowLayout());
		filterControls.add(filterTitle);
		filterControls.add(filterSelect);
		JPanel rangeControls = new JPanel(new FlowLayout());
		rangeControls.add(rangeFilterTitle);
		rangeControls.add(rangeFilter);
		
		JPanel axisPanel = new JPanel();
		axisPanel.setLayout(new BoxLayout(axisPanel, BoxLayout.PAGE_AXIS));

		axisPanel.add(sizeControls);
		axisPanel.add(yControls);
		axisPanel.add(xControls);
		axisPanel.add(filterControls);
		axisPanel.add(rangeControls);
		
		add(axisPanel, BorderLayout.PAGE_END);
		
		add(visualisation, BorderLayout.CENTER);

	}
	
	public void actionPerformed(ActionEvent e) {
        JComboBox cb = null;
        cb = (JComboBox)e.getSource();
        String field = "";
        field = (String)cb.getSelectedItem();
        if (visualisation instanceof OlympicScatterplot) {
        	OlympicScatterplot s = (OlympicScatterplot) visualisation;
        	//s.setYField(field);
        	if (cb == ySelect) s.setYField(field);
        	else if(cb == xSelect) s.setXField(field);
        	else if(cb == sizeSelect) s.setDataSizeAction(field);
        	else if(cb == filterSelect) s.changeFilter(field);
        }
    }
	
}
