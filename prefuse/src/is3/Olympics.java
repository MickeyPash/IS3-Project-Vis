package is3;

import javax.swing.JFrame;

public class Olympics {

    public static void main(String[] argv) {
    	
    	OlympicScatterplot scatterplot = new OlympicScatterplot("/WrangledData.csv");
    	
		// create a new window to hold the visualization
        JFrame frame = new JFrame("Olympics Graph");
        // ensure application exits when window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(scatterplot);
        frame.pack();           // layout components in window
        frame.setVisible(true); // show the window
		
    }
    
}
