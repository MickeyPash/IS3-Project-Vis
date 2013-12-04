/**
 * 
 */
package is3;

import is3.OlympicTreeView.OrientAction;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import prefuse.Constants;
import prefuse.Visualization;
import prefuse.controls.ControlAdapter;
import prefuse.demos.TreeView;
import prefuse.render.EdgeRenderer;
import prefuse.render.LabelRenderer;
import prefuse.util.FontLib;
import prefuse.util.ui.JFastLabel;
import prefuse.util.ui.JSearchPanel;
import prefuse.visual.VisualItem;

/**
 * @author Michael
 *
 */


public class OlympicTreeViewPanel extends JPanel {
	public OlympicTreeView visualisation;

	public OlympicTreeViewPanel(String data) throws IOException{
		
		
	   
	    
	    final String tree = "tree";
	    final String treeNodes = "tree.nodes";
	    final String treeEdges = "tree.edges";
	    
	    LabelRenderer m_nodeRenderer;
	    EdgeRenderer m_edgeRenderer;
	    
	    String m_label = "label";
	    int m_orientation = Constants.ORIENT_LEFT_RIGHT;
		
		//setLayout(new BorderLayout());
		//setLayout(new Box(BoxLayout.X_AXIS);
		BoxLayout l = new BoxLayout(this, BoxLayout.X_AXIS);
        Color BACKGROUND = Color.WHITE;
        Color FOREGROUND = Color.BLACK;
        final String label = "name";
        
		visualisation = new OlympicTreeView(data);
		//OlympicTreeView tview = visualisation.getT();
		
	//	add(visualisation, BorderLayout.CENTER);
		
//		visualisation.registerKeyboardAction(
//	            visualisation.new OrientAction(Constants.ORIENT_LEFT_RIGHT),
//	            "left-to-right", KeyStroke.getKeyStroke("ctrl 1"), WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
//	    visualisation.registerKeyboardAction(
//	            visualisation.new OrientAction(Constants.ORIENT_TOP_BOTTOM),
//	            "top-to-bottom", KeyStroke.getKeyStroke("ctrl 2"), WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
//	    visualisation.registerKeyboardAction(
//	            visualisation.new OrientAction(Constants.ORIENT_RIGHT_LEFT),
//	            "right-to-left", KeyStroke.getKeyStroke("ctrl 3"), WHEN_FOCUSED);
//	    visualisation.registerKeyboardAction(
//	            visualisation.new OrientAction(Constants.ORIENT_BOTTOM_TOP),
//	            "bottom-to-top", KeyStroke.getKeyStroke("ctrl 4"), WHEN_FOCUSED);
	        

		
		//visualisation.setBackground(BACKGROUND);
      //  visualisation.setForeground(FOREGROUND);
        
        // create a search panel for the tree map
        JSearchPanel search = new JSearchPanel(visualisation.getVisualization(),
            treeNodes, Visualization.SEARCH_ITEMS, label, true, true);
        search.setShowResultCount(true);
        search.setBorder(BorderFactory.createEmptyBorder(5,5,4,0));
        search.setFont(FontLib.getFont("Tahoma", Font.PLAIN, 11));
        search.setBackground(BACKGROUND);
        search.setForeground(FOREGROUND);
        
        final JFastLabel title = new JFastLabel("                 ");
        title.setPreferredSize(new Dimension(350, 20));
        title.setVerticalAlignment(SwingConstants.BOTTOM);
        title.setBorder(BorderFactory.createEmptyBorder(3,0,0,0));
        title.setFont(FontLib.getFont("Tahoma", Font.PLAIN, 16));
        title.setBackground(BACKGROUND);
        title.setForeground(FOREGROUND);
        
        visualisation.addControlListener(new ControlAdapter() {
            public void itemEntered(VisualItem item, MouseEvent e) {
                if ( item.canGetString(label) )
                    title.setText(item.getString(label));
            }
            public void itemExited(VisualItem item, MouseEvent e) {
                title.setText(null);
            }
        });
        
        Box box = new Box(BoxLayout.X_AXIS);
        box.add(Box.createHorizontalStrut(10));
        box.add(title);
        box.add(Box.createHorizontalGlue());
        box.add(search);
        box.add(Box.createHorizontalStrut(3));
        box.setBackground(BACKGROUND);
        //this.add
        
        Image image = ImageIO.read(new File(System.getProperty("user.dir") + "/data/freedom1.jpg"));
        
//        BackgroundPanel panel =
//        	    new BackgroundPanel(image, 0);
//        panel.setLayout(new BorderLayout());
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(1280, 720));
        panel.setBackground(BACKGROUND);
        panel.setForeground(FOREGROUND);
        visualisation.setBackgroundImage(image, true, false);
        panel.add(visualisation, BorderLayout.CENTER);
        panel.add(box, BorderLayout.SOUTH);
     //   panel.getPreferredSize();
        System.out.println(panel.getPreferredSize());
        
        
//		JLabel label1 = new JLabel();  
//		System.out.println("ASDASDASDASDAS");
//		ImageIcon i = new ImageIcon("/freedsdfsom.jpg");
//        label1.setIcon(i);// your image here  
//        panel.add(label1);
          
        add(panel);
		//setLayout(l);
		
	//	this.add
	}
}
