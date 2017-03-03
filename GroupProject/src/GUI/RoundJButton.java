package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


import javax.swing.JButton;

public class RoundJButton extends JButton{

    RoundJButton(String text) {

        super(text);

       
    }

     
    public void paintBorder(Graphics g){
    	
    }

   

    public void paintComponent(Graphics g) {

        Rectangle r = getBounds();

        int x = r.x + 20;

        int y = r.y + 20;

        int width = r.width - 40;

        int height = r.height- 40;

        g.setColor(Color.BLACK);

        g.fillOval(x, y, width, height);

        x += 2;

        y += 2;

        width -= 4;

        height -= 4;

        g.setColor(getBackground());

        g.fillOval(x, y, width, height);

        g.setColor(getForeground());

        y += (height / 2) - 10;

        g.drawString(getText(), x, y);

    }

}
