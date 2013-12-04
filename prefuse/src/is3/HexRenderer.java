package is3;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import prefuse.render.AbstractShapeRenderer;
import prefuse.visual.VisualItem;

public class HexRenderer extends AbstractShapeRenderer {

	// protected RectangularShape m_box = new Rectangle2D.Double();
	protected Ellipse2D m_box = new Ellipse2D.Double();

	@Override
	protected Shape getRawShape(VisualItem item) {
		m_box.setFrame(
				item.getX(),
				item.getY(),
				Math.sqrt((Double) item.get("Population (in thousands) total")),
				Math.sqrt((Double) item.get("Population (in thousands) total")));

		return m_box;
	}

}
