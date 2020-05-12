package com.company;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    ImageIcon title = new ImageIcon("src/img/title.jpg");

    public Panel() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.WHITE);
        title.paintIcon(this, g, 25, 11);

        //game field
        g.fillRect(25, 75, 850, 600);
    }
}
