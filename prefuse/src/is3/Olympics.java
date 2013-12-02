package is3;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.assignment.DataColorAction;
import prefuse.action.layout.AxisLabelLayout;
import prefuse.action.layout.AxisLayout;
import prefuse.controls.PanControl;
import prefuse.controls.ToolTipControl;
import prefuse.controls.ZoomControl;
import prefuse.data.Schema;
import prefuse.data.Table;
import prefuse.data.io.CSVTableReader;
import prefuse.data.io.DataIOException;
import prefuse.render.AxisRenderer;
import prefuse.render.DefaultRendererFactory;
import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;
import prefuse.visual.expression.InGroupPredicate;
import prefuse.visual.expression.VisiblePredicate;
import prefuse.visual.sort.ItemSorter;

public class Olympics {

    public static void main(String[] argv) {
    	
    	// Help mainly found at:
    	// http://www.ifs.tuwien.ac.at/~rind/w/doku.php/java/prefuse-scatterplot
        
    	Table table = new Table();
    	Schema schema = new Schema();
    	
    	// Schema allows specifying type of data being stored
    	// Need to look into how to store appropriate types when reading from CSV
    	
    	schema.addColumn("Country", String.class);
    	schema.addColumn("Continent", int.class);
    	schema.addColumn("Adult literacy rate (%)", Double.class);
    	schema.addColumn("Population (in thousands) total", int.class);
    	schema.addColumn("Population median age (years)", int.class);
    	schema.addColumn("Community and traditional health workers density (per 10 000 population)", Double.class);
    	schema.addColumn("General government expenditure on health as percentage of total expenditure on health", int.class);
    	schema.addColumn("General government expenditure on health as percentage of total government expenditure", int.class);
    	schema.addColumn("F2012", int.class);
    	schema.addColumn("M2012", int.class);
    	schema.addColumn("TeamSize", int.class);
    	schema.addColumn("Gold", int.class);
    	schema.addColumn("Silver", int.class);
    	schema.addColumn("Bronze", int.class);
    	
    	table.addColumns(schema);
    	
    	////// Test data //////
    	
    	table.addRows(200); /* this is me just randomly putting 200 ssince I know there isnt more than 200 countries, need to change this */
    	
//		table.set(0, 0, "United States");
//		table.set(0, 1, 1);
//		table.set(0, 2, 80.0);
//		table.set(0, 3, 12000.0);
//		table.set(0, 4, 50);
//		table.set(0, 5, 10.0);
//		table.set(0, 6, 10);
//		table.set(0, 7, 10);
//		table.set(0, 8, 200);
//		table.set(0, 9, 300);
//		table.set(0, 10, 500);
//		table.set(0, 11, 20);
//		table.set(0, 12, 30);
//		table.set(0, 13, 45);
//
// 
//		table.set(1, 0, "New Zealand");
//		table.set(1, 1, 4);
//		table.set(1, 2, 85.0);
//		table.set(1, 3, 1000.0);
//		table.set(1, 4, 52);
//		table.set(1, 5, 15.0);
//		table.set(1, 6, 15);
//		table.set(1, 7, 15);
//		table.set(1, 8, 100);
//		table.set(1, 9, 150);
//		table.set(1, 10, 250);
//		table.set(1, 11, 5);
//		table.set(1, 12, 4);
//		table.set(1, 13, 11);
		
		
    /* PLEASE IGNORE FOLLOWING CODE */
    	
//		BufferedReader br = null;
//    	File file = null;
//    	Scanner newS = null;
//    	int row = 1;
//    	int collumn = 0;
//    	try{
//
//    	   // br = new BufferedReader(new FileReader("/prefuse/data/WrangledData.csv"));
//    	   // file = new File("amazon.txt");
//    	    file = new File(System.getProperty("user.dir") + "/data/WrangledData2.csv");
//    	    String test = file.getAbsolutePath();
//    	    System.out.println(test);
//    	    newS = new Scanner(file);
//    	    String strLine;
//    	    strLine = newS.nextLine();
//    	    //Read File Line By Line
//    	    while ((strLine = newS.nextLine()) != null)   {
//    	      // Print the content on the console
//    	      Scanner lineScanner = new Scanner(strLine);
//    	      System.out.println(strLine);
//    	      Object[] g = strLine.split(",");
//    	      collumn = 0;
//    	      for (Object s: g){
//    	    	System.out.println(s);
//      	    	table.set(row, collumn, s);
//      	    	collumn++;
//    	      }

		////// Test data //////
    	
    	
    	// Not used yet, need to look into how to store appropriate types
    	 
    	try {
    		table = new CSVTableReader().readTable("/WrangledData.csv");
    	}
    	catch (DataIOException e) {
    		e.printStackTrace();
            System.err.println("Error loading table. Exiting...");
            System.exit(1);
    	}
    	
    	
    	final Visualization vis = new Visualization();
		final Display display = new Display(vis);
        
		// ------------------------------------------------------------------
		// Step 1: setup the visualised data
		
		vis.add("data", table);
		
		// ------------------------------------------------------------------
		// Step 2: setup renderers for visualised data
		
		DefaultRendererFactory rf = new DefaultRendererFactory(); 
		
		rf.add(new InGroupPredicate("ylab"), 
				new AxisRenderer(Constants.FAR_LEFT, Constants.CENTER));
		rf.add(new InGroupPredicate("xlab"), 
				new AxisRenderer(Constants.CENTER, Constants.FAR_BOTTOM));
		vis.setRendererFactory(rf);
		
		// ------------------------------------------------------------------
		// Step 3: create actions to process the visual data
		
		AxisLayout x_axis = new AxisLayout("data", "TeamSize", Constants.X_AXIS, VisiblePredicate.TRUE);
		AxisLayout y_axis = new AxisLayout("data", "Gold", Constants.Y_AXIS, VisiblePredicate.TRUE);
		
		AxisLabelLayout x_labels = new AxisLabelLayout("xlab", x_axis);
		AxisLabelLayout y_labels = new AxisLabelLayout("ylab", y_axis);
		
		// Colour palette for continents
		int[] palette = new int[] {
				// Middle East
	            ColorLib.rgb(20,120,100),
	            // Europe
	            ColorLib.rgb(105,245,40),
	            // Africa
	            ColorLib.rgb(200,200,135),
	            // North America
	            ColorLib.rgb(35,50,225),
	            // South America
	            ColorLib.rgb(190,115,20),
	            // Oceania
	            ColorLib.rgb(95,175,210),
	            // Asia
	            ColorLib.rgb(220,15,40)
	        };
		
		DataColorAction fill = new DataColorAction("data", "Continent", Constants.NOMINAL, VisualItem.FILLCOLOR, palette);
    	
		ActionList draw = new ActionList();
		draw.add(x_axis);
		draw.add(y_axis);
		draw.add(x_labels);
		draw.add(y_labels);
		draw.add(fill);
		// draw.add(new RepaintAction());
		vis.putAction("draw", draw);
		
		ActionList update = new ActionList();
		update.add(x_axis);
		update.add(y_axis);
		update.add(x_labels);
		update.add(y_labels);
		draw.add(fill);
		// draw.add(new RepaintAction());
		vis.putAction("update", update);

		// ------------------------------------------------------------------
		// Step 4: Setup a display and controls

		// set display size
		display.setSize(720, 500);
        // pan with left-click drag on background
        display.addControlListener(new PanControl()); 
        // zoom with right-click drag
        display.addControlListener(new ZoomControl());
        
        // Update visuals when user resizes display
        display.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				vis.run("update");
			}
		});
		
		// Make graph fit in window
		display.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
		
		display.setItemSorter(new ItemSorter() {
			public int score(VisualItem item) {
				int score = super.score(item);
				if (item.isInGroup("data"))
					score++;
				return score;
			}
		});
		 
        // Display specific information when hovering over point
        String[] tooltipparams = { "Country", "Gold", "Silver", "Bronze" };
		ToolTipControl ttc = new ToolTipControl(tooltipparams);
		display.addControlListener(ttc);
		
		// ------------------------------------------------------------------
		// Step 5: Launching the visualisation
		
		
		// create a new window to hold the visualization
        JFrame frame = new JFrame("Olympics Graph");
        // ensure application exits when window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(display);
        frame.pack();           // layout components in window
        frame.setVisible(true); // show the window
        

		vis.run("draw");
		
    }
    
}
