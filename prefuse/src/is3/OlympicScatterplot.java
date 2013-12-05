package is3;

import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Rectangle2D;

import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.DataColorAction;
import prefuse.action.assignment.DataShapeAction;
import prefuse.action.assignment.DataSizeAction;
import prefuse.action.layout.AxisLabelLayout;
import prefuse.action.layout.AxisLayout;
import prefuse.activity.Activity;
import prefuse.controls.PanControl;
import prefuse.controls.ToolTipControl;
import prefuse.controls.ZoomControl;
import prefuse.data.Schema;
import prefuse.data.Table;
import prefuse.data.expression.Predicate;
import prefuse.data.expression.parser.ExpressionParser;
import prefuse.data.io.CSVTableReader;
import prefuse.data.io.DataIOException;
import prefuse.render.AbstractShapeRenderer;
import prefuse.render.AxisRenderer;
import prefuse.render.Renderer;
import prefuse.render.RendererFactory;
import prefuse.render.ShapeRenderer;
import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;
import prefuse.visual.VisualTable;
import prefuse.visual.expression.VisiblePredicate;
import prefuse.visual.sort.ItemSorter;

@SuppressWarnings("serial")
public class OlympicScatterplot extends Display {

	private Table table;
	private AxisLayout x_axis;
	private AxisLayout y_axis;
	private DataSizeAction size;
	private String filter;

	public OlympicScatterplot(String csvfile) {

		// Help mainly found at:
		// http://www.ifs.tuwien.ac.at/~rind/w/doku.php/java/prefuse-scatterplot

		super(new Visualization());

		table = new Table();
		Schema schema = new Schema();

		// Schema allows specifying type of data being stored

		schema.addColumn("Country", String.class);
		schema.addColumn("Continent", int.class);
		schema.addColumn("Adult literacy rate (%)", Double.class);
		schema.addColumn("Population (in thousands) total", int.class);
		schema.addColumn("Population median age (years)", int.class);
		schema.addColumn(
				"Community and traditional health workers density (per 10 000 population)",
				Double.class);
		schema.addColumn(
				"General government expenditure on health as percentage of total expenditure on health",
				int.class);
		schema.addColumn(
				"General government expenditure on health as percentage of total government expenditure",
				int.class);
		schema.addColumn("F2012", int.class);
		schema.addColumn("M2012", int.class);
		schema.addColumn("TeamSize", int.class);
		schema.addColumn("Gold", int.class);
		schema.addColumn("Silver", int.class);
		schema.addColumn("Bronze", int.class);

		table.addColumns(schema);

		/*
		 * this is me just randomly putting 200 since I know there isn't more
		 * than 200 countries, need to change this
		 */
		table.addRows(200);

		// Read table from csv file
		try {
			table = new CSVTableReader().readTable(csvfile);
		} catch (DataIOException e) {
			e.printStackTrace();
			System.err.println("Error loading table. Exiting...");
			System.exit(1);
		}

		final Rectangle2D boundsData = new Rectangle2D.Double();
		final Rectangle2D boundsLabelsX = new Rectangle2D.Double();
		final Rectangle2D boundsLabelsY = new Rectangle2D.Double();

		// TODO Consider logarithmic scale for graph
		// This would be a user option
		// Look at this for possible implementation
		// https://github.com/prefuse/Flare/blob/master/flare/src/flare/scale/LogScale.as

		// ------------------------------------------------------------------
		// Step 1: setup the visualised data

		VisualTable vt = m_vis.addTable("data", table);

		// Column that will hold data for tooltip to use
		vt.addColumn(
				"label",
				"CONCAT([Country],': ', ' Gold: ', [Gold], ';  Silver: ', [Silver], ';  Bronze: ', [Bronze])");

		// ------------------------------------------------------------------
		// Step 2: setup renderers for visualised data

		m_vis.setRendererFactory(new RendererFactory() {
			AbstractShapeRenderer sr = new ShapeRenderer();
			Renderer arY = new AxisRenderer(Constants.FAR_LEFT,
					Constants.CENTER);
			Renderer arX = new AxisRenderer(Constants.CENTER,
					Constants.FAR_BOTTOM);

			public Renderer getRenderer(VisualItem item) {
				return item.isInGroup("ylab") ? arY
						: item.isInGroup("xlab") ? arX : sr;
			}
		});

		// ------------------------------------------------------------------
		// Step 3: create actions to process the visual data

		x_axis = new AxisLayout("data", "TeamSize", Constants.X_AXIS,
				VisiblePredicate.TRUE);
		y_axis = new AxisLayout("data", "Gold", Constants.Y_AXIS,
				VisiblePredicate.TRUE);

		x_axis.setLayoutBounds(boundsData);
		y_axis.setLayoutBounds(boundsData);

		AxisLabelLayout x_labels = new AxisLabelLayout("xlab", x_axis,
				boundsLabelsX);
		AxisLabelLayout y_labels = new AxisLabelLayout("ylab", y_axis,
				boundsLabelsY);

		// Colour palette for continents
		int[] palette = new int[] {
				// Middle East
				ColorLib.rgba(20, 120, 100, 125),
				// Europe
				ColorLib.rgba(105, 245, 40, 125),
				// Africa
				ColorLib.rgba(200, 200, 135, 125),
				// North America
				ColorLib.rgba(35, 50, 225, 125),
				// South America
				ColorLib.rgba(190, 115, 20, 125),
				// Oceania & some Asia
				ColorLib.rgba(95, 175, 210, 125),
				// Asia
				ColorLib.rgba(220, 15, 40, 125) };

		DataColorAction fill = new DataColorAction("data", "Continent",
				Constants.NOMINAL, VisualItem.FILLCOLOR, palette);

		// Make points hexagons
		int[] shapes = new int[] { Constants.SHAPE_HEXAGON };
		DataShapeAction shape = new DataShapeAction("data", "Country", shapes);

		// Size of points determined by Population
		// Makes points HUGE

		size = new DataSizeAction("data", "Population (in thousands) total", 0,
				Constants.LINEAR_SCALE);
		size.setMinimumSize(1.0);
		size.setMaximumSize(1.0);

		// TODO Background Image?

		ActionList draw = new ActionList(Activity.INFINITY);
		draw.add(x_axis);
		draw.add(y_axis);
		draw.add(x_labels);
		draw.add(y_labels);
		draw.add(size);
		draw.add(shape);
		draw.add(fill);
		draw.add(new RepaintAction());
		m_vis.putAction("draw", draw);

		// ------------------------------------------------------------------
		// Step 4: Setup a display and controls

		setHighQuality(true);
		// set display size
		setSize(720, 500);
		// pan with left-click drag on background
		addControlListener(new PanControl());
		// zoom with right-click drag
		addControlListener(new ZoomControl());

		// Update visuals when user resizes display
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				updateBounds(boundsData, boundsLabelsX, boundsLabelsY);
				m_vis.run("update");
			}
		});

		setItemSorter(new ItemSorter() {
			public int score(VisualItem item) {
				int score = super.score(item);
				if (item.isInGroup("data"))
					score++;
				return score;
			}
		});

		// Display specific information when hovering over point
		ToolTipControl ttc = new ToolTipControl("label");
		addControlListener(ttc);

		// ------------------------------------------------------------------
		// Step 5: Launching the visualisation

		updateBounds(boundsData, boundsLabelsX, boundsLabelsY);
		m_vis.run("draw");

	}

	/**
	 * Taken from
	 * http://www.ifs.tuwien.ac.at/~rind/w/doku.php/java/prefuse-scatterplot
	 * 
	 * @param boundsData
	 * @param boundsLabelsX
	 * @param boundsLabelsY
	 */
	private void updateBounds(Rectangle2D boundsData,
			Rectangle2D boundsLabelsX, Rectangle2D boundsLabelsY) {

		int paddingLeft = 30;
		int paddingTop = 15;
		int paddingRight = 30;
		int paddingBottom = 15;

		int axisWidth = 20;
		int axisHeight = 10;

		Insets i = getInsets();

		int left = i.left + paddingLeft;
		int top = i.top + paddingTop;
		int innerWidth = getWidth() - i.left - i.right - paddingLeft
				- paddingRight;
		int innerHeight = getHeight() - i.top - i.bottom - paddingTop
				- paddingBottom;

		boundsData.setRect(left + axisWidth, top, innerWidth - axisWidth,
				innerHeight - axisHeight);
		boundsLabelsX.setRect(left + axisWidth, top + innerHeight - axisHeight,
				innerWidth - axisWidth, axisHeight);
		boundsLabelsY.setRect(left, top, innerWidth + paddingRight, innerHeight
				- axisHeight);
	}

	/**
	 * Change what x_axis tracks
	 * 
	 * @param field
	 */
	public void setXField(String field) {
		x_axis.setDataField(field);
		y_axis.setDataField(y_axis.getDataField());
	}

	/**
	 * Change what y_axis tracks
	 * 
	 * @param field
	 */
	public void setYField(String field) {
		y_axis.setDataField(field);
		x_axis.setDataField(x_axis.getDataField());
	}

	
	public void setDataSizeAction(String field){
		if (field.equals("None")){
			size.setMinimumSize(1.0);
			size.setMaximumSize(1.0);
		}
		else{
			size.setMinimumSize(0.2);
			size.setMaximumSize(5.0);
			size.setDataField(field);
		}
	}
	
	public void Filter(String field) {
		filter = "Continent="+field;
		Predicate filter1 = (Predicate)ExpressionParser.parse(filter);
		m_vis.setVisible("data", filter1, false);
	}
	
	public void deFilter(String field) {
		filter = "Continent="+field;
		Predicate filter1 = (Predicate)ExpressionParser.parse(filter);
		m_vis.setVisible("data", filter1, true);
	}
}