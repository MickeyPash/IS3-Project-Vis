/**
 * 
 */
package is3;

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
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import prefuse.Constants;
import prefuse.Visualization;
import prefuse.controls.ControlAdapter;
import prefuse.render.EdgeRenderer;
import prefuse.render.LabelRenderer;
import prefuse.util.FontLib;
import prefuse.util.ui.JFastLabel;
import prefuse.util.ui.JSearchPanel;
import prefuse.visual.VisualItem;

@SuppressWarnings("serial")
public class OlympicTreeViewPanel extends JPanel {
	public OlympicTreeView visualisation;

	public OlympicTreeViewPanel(String data) throws IOException {

		final String treeNodes = "tree.nodes";

		setLayout(new BorderLayout());

		Color BACKGROUND = Color.WHITE;
		Color FOREGROUND = Color.BLACK;
		final String label = "name";

		visualisation = new OlympicTreeView(data);

		// create a search panel for the tree map
		JSearchPanel search = new JSearchPanel(
				visualisation.getVisualization(), treeNodes,
				Visualization.SEARCH_ITEMS, label, true, true);
		search.setShowResultCount(true);
		search.setBorder(BorderFactory.createEmptyBorder(5, 5, 4, 0));
		search.setFont(FontLib.getFont("Tahoma", Font.PLAIN, 11));
		search.setBackground(BACKGROUND);
		search.setForeground(FOREGROUND);

		final JFastLabel title = new JFastLabel("                 ");
		title.setPreferredSize(new Dimension(350, 20));
		title.setVerticalAlignment(SwingConstants.BOTTOM);
		title.setBorder(BorderFactory.createEmptyBorder(3, 0, 0, 0));
		title.setFont(FontLib.getFont("Tahoma", Font.PLAIN, 16));
		title.setBackground(BACKGROUND);
		title.setForeground(FOREGROUND);

		visualisation.addControlListener(new ControlAdapter() {
			public void itemEntered(VisualItem item, MouseEvent e) {
				if (item.canGetString(label))
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

		setBackground(BACKGROUND);
		setForeground(FOREGROUND);

		add(visualisation, BorderLayout.CENTER);
		add(box, BorderLayout.SOUTH);
	}
}
