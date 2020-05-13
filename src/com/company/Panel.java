package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends JPanel implements KeyListener, ActionListener {

    ImageIcon title = new ImageIcon("src/img/title.jpg");
    ImageIcon body = new ImageIcon("src/img/body.png");
    ImageIcon up = new ImageIcon("src/img/up.png");
    ImageIcon down = new ImageIcon("src/img/down.png");
    ImageIcon left = new ImageIcon("src/img/left.png");
    ImageIcon right = new ImageIcon("src/img/right.png");
    ImageIcon food = new ImageIcon("src/img/food.png");

    boolean started = false;
    ActionListener actionListener;
    // move snake
    Timer t = new Timer(100, this);

    int len = 3; //the length of snake
    int[] x = new int[750];
    int[] y = new int[750];
    String dir = "R"; // the direction of head

    public Panel() {
        init();
        this.setFocusable(true);
        this.addKeyListener(this);
        t.start();
    }

    public void init() {
        len = 3;
        x[0] = 100;
        y[0] = 100;
        x[1] = 75;
        y[1] = 100;
        x[2] = 50;
        y[2] = 100;

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();
        // if space is pressed, change the value of started
        if (code == KeyEvent.VK_SPACE) {
            started = !started;
            //repaint the panel
            repaint();
            t.start();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.WHITE);
        title.paintIcon(this, g, 25, 11);

        //game field
        g.fillRect(25, 75, 850, 600);

        //snake
            //head
        switch (dir) {
            case "R":
                right.paintIcon(this, g, x[0], y[0]);
                break;
            case "L":
                left.paintIcon(this, g, x[0], y[0]);
                break;
            case "U":
                up.paintIcon(this, g, x[0], y[0]);
                break;
            case "D":
                down.paintIcon(this, g, x[0], y[0]);
                break;
        }

            //body
        for (int i = 1; i < len; i++) {
            body.paintIcon(this, g, x[i], y[i]);
        }

        //start message
        if (! started) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Lucida Calligraphy", Font.BOLD, 40));
            g.drawString("Press space to start", 230, 300);

        }
    }
}
