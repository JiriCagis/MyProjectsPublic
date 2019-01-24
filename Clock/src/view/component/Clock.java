package view.component;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Date;

import javax.swing.JPanel;

/**
 * This class is panel show analog clock.
 * @author adminuser
 */
public class Clock extends JPanel implements Runnable {

    //constants
    private final Color BACKGROUND = Color.LIGHT_GRAY;
    private final Color CONTOUR = Color.BLACK;
    private final Color HOUR_HAND = Color.BLACK;
    private final Color MINUTES_HAND = Color.darkGray;
    private final Color SECOND_HAND = Color.red;
    
    //variables for drawing
    private Point clockCenter;
    private int halfAxisX;
    private int halfAxisY;

    public Clock() {
        setBackground(BACKGROUND);
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000);
                repaint();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        
        //draw background
        g2.setColor(BACKGROUND);
        g2.fillRect(0, 0, getWidth(), getHeight()); 
        
        //prepare values
        clockCenter = new Point(getWidth() / 2, getHeight() / 2);
        halfAxisX = getWidth() / 2;
        halfAxisY = getHeight() / 2;
        
        //draw clock and hands
        drawClock(g2, CONTOUR);
        Date date = new Date();  
        drawHand(g2, HOUR_HAND, 12, (date.getHours()*5)+calculatePartOfHour(date));
        drawHand(g2, MINUTES_HAND, 4, date.getMinutes());
        drawHand(g2, SECOND_HAND, 1, date.getSeconds());
    }
    
    /**
     * Hour is part of 5 degree, method calculate how many degree is
     * now from minutes.
     * @param date actually date
     * @return part of hour
     */
    private int calculatePartOfHour(Date date){
       return (int) ((double)(5*date.getMinutes())/60);
    }
    
    private void drawClock(Graphics2D g2, Color color) {
        g2.setColor(color);
        g2.setStroke(new BasicStroke(2));
        g2.drawOval(0, 0, getWidth(), getHeight());
        for (int i = 6; i <= 360; i = i + 6) {
            g2.setStroke((i % 90 == 0) ? new BasicStroke(3) : new BasicStroke(1));
            int length = (i % 90 == 0) ? 20 : ((i % 30 == 0) ? 30 : 10);
            g2.drawLine(
                    (int) (clockCenter.x + ((halfAxisX - length) * Math.cos(i / 57.2))),
                    (int) (clockCenter.y + ((halfAxisY - length) * Math.sin(i / 57.2))),
                    (int) (clockCenter.x + (halfAxisX * Math.cos(i / 57.2))),
                    (int) (clockCenter.y + (halfAxisY * Math.sin(i / 57.2))));
        }
    }

    private void drawHand(Graphics2D g2, Color color, int stroke, int value) {
        g2.setStroke(new BasicStroke(stroke));
        g2.setColor(color);
        g2.drawLine(
                clockCenter.x,
                clockCenter.y,
                (int) (clockCenter.x + ((halfAxisX - 35) * Math.cos(((value * 6) - 90) / 57.2))),
                (int) (clockCenter.y + ((halfAxisY - 35) * Math.sin(((value * 6) - 90) / 57.2))));
    }

}
