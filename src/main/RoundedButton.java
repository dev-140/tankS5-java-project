package main;

import java.awt.*;
import javax.swing.*;

public class RoundedButton extends JButton {

    private static final long serialVersionUID = 1L;

	public RoundedButton(String text) {
        super(text);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        g2.setColor(getForeground());
        g2.drawString(getText(), getWidth() / 2 - g2.getFontMetrics().stringWidth(getText()) / 2, getHeight() / 2 + g2.getFontMetrics().getAscent() / 2);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(1)); // set the border thickness to 2 pixels
        g2.setColor(getForeground());
        g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 20, 20); // adjust the border position
        g2.dispose();
    }
}