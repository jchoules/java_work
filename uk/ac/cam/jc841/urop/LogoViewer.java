package uk.ac.cam.jc841.urop;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 * Logo viewer
 *
 * Download from http://www.cl.cam.ac.uk/~acr31/LogoViewer.java
 *
 * @author acr31
 *
 */
public class LogoViewer {

	public static final int SIZE = 500;

	private JFrame window;
	private BufferedImage buf;

	public LogoViewer() {
		buf = new BufferedImage(SIZE,SIZE, BufferedImage.TYPE_INT_RGB);
		clear();
		window = new JFrame() {
			@Override
			public void paint(Graphics g) {
				g.drawImage(buf,0,0,null);
			}
		};
		window.setSize(SIZE,SIZE);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public void clear() {
		Graphics2D g = buf.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0,0,SIZE,SIZE);
		g.dispose();
	}

	public void drawPoint(int x, int y) {
		Graphics2D g = buf.createGraphics();
		g.setColor(Color.BLACK);
		g.drawLine(x, y,x,y);
		g.dispose();
		window.repaint();
	}
}
