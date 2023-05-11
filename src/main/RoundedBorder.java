package main;

import java.awt.*;
import javax.swing.border.*;

public class RoundedBorder implements Border {
    private int radius;
    private int thickness;

    public RoundedBorder(int radius, int thickness) {
        this.radius = radius;
        this.thickness = thickness;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius + this.thickness, this.radius + this.thickness, this.radius + this.thickness + 1, this.radius + this.thickness);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(c.getBackground());
        g.fillRoundRect(x, y, width, height, this.radius, this.radius);
        g.setColor(Color.BLACK);
        ((Graphics2D) g).setStroke(new BasicStroke(this.thickness));
        g.drawRoundRect(x + (this.thickness / 2), y + (this.thickness / 2), width - this.thickness, height - this.thickness, this.radius, this.radius);
    }
}