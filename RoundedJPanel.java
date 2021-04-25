import java.awt.*;
import javax.swing.*;
import java.awt.Color;

// Class implemented to create stylish Panels with rounded edges and shadows (design purposes only) //
// It's an extension of the JPanel class to design specific types of panels //

public class RoundedJPanel extends JPanel {

	private int cornerRadius = 0;
	private Color colorPanel;
	private Color colorBorderPanel;
	private BasicStroke dashedContour;
	private BasicStroke Contour;
	private boolean shadow = false;
	private boolean dashedBorder = false;
	
	public RoundedJPanel(LayoutManager layout, int r, boolean shadowDisplay, boolean dashedBorderDisplay) {
            super(layout);
            cornerRadius = r;
            shadow = shadowDisplay;
            dashedBorder = dashedBorderDisplay;
        }

    public RoundedJPanel(LayoutManager layout, int r, Color panelColor, Color borderColor, boolean shadowDisplay, boolean dashedBorderDisplay) {
            super(layout);
            cornerRadius = r;
            colorPanel = panelColor;
            colorBorderPanel = borderColor;
            shadow = shadowDisplay;
            dashedBorder = dashedBorderDisplay;
        }

    /*public RoundedJPanel(int radius) {
            super();
            cornerRadius = radius;
        }
	*/
	
    public RoundedJPanel(int r, Color panelColor, Color borderColor, boolean shadowDisplay, boolean dashedBorderDisplay) {
            super();
            cornerRadius = r;
            colorPanel = panelColor;
            colorBorderPanel = borderColor;
            shadow = shadowDisplay;
            dashedBorder = dashedBorderDisplay;
        }

        @Override
        protected void paintComponent(Graphics g) {
            
            super.paintComponent(g);
            Dimension arcs = new Dimension(cornerRadius, cornerRadius);
            
            int width = getWidth();
            int height = getHeight();
            dashedContour = new BasicStroke (5,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,0, new float[]{9}, 0);
            //													if: Basic Stroke.JOIND_MITER,1,...
            Contour = new BasicStroke (4);
            
            Graphics2D graphics = (Graphics2D) g;
            
            if (dashedBorder) {
            graphics.setStroke(dashedContour);
			}
			
			if (!dashedBorder) {
            graphics.setStroke(Contour);
			}
			
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            if(shadow) {
				
				// [...] //
				
			}

            if (colorPanel != null) {
                graphics.setColor(colorPanel);
            } else {
                graphics.setColor(getBackground());
            }
            graphics.fillRoundRect(0, 0, width, height, arcs.width, arcs.height); 
            graphics.setColor(colorBorderPanel);
            graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); 
            
            
        }
    }





