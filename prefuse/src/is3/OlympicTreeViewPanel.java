/**
 * 
 */
package is3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import prefuse.Visualization;
import prefuse.controls.ControlAdapter;
import prefuse.demos.TreeView;
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

	public OlympicTreeViewPanel(String data){
		//setLayout(new BorderLayout());
		//setLayout(new Box(BoxLayout.X_AXIS);
		BoxLayout l = new BoxLayout(this, BoxLayout.X_AXIS);
        Color BACKGROUND = Color.WHITE;
        Color FOREGROUND = Color.BLACK;
        final String label = "name";
        String treeNodes = "tree.nodes";
		visualisation = new OlympicTreeView(data);
		//OlympicTreeView tview = visualisation.getT();
		
	//	add(visualisation, BorderLayout.CENTER);
		
		visualisation.setBackground(BACKGROUND);
        visualisation.setForeground(FOREGROUND);
        
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
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND);
        panel.setForeground(FOREGROUND);
        panel.add(visualisation, BorderLayout.CENTER);
        panel.add(box, BorderLayout.SOUTH);
     //   panel.getPreferredSize();
        System.out.println(panel.getPreferredSize());
        panel.setPreferredSize(new Dimension(1366, 768));
        add(panel);
		//setLayout(l);
		
	//	this.add
	}
}
