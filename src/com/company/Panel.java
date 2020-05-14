package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Panel extends JPanel implements KeyListener, ActionListener {

    ImageIcon title = new ImageIcon("src/img/title.jpg");
    ImageIcon body = new ImageIcon("src/img/body.png");
    ImageIcon up = new ImageIcon("src/img/up.png");
    ImageIcon down = new ImageIcon("src/img/down.png");
    ImageIcon left = new ImageIcon("src/img/left.png");
    ImageIcon right = new ImageIcon("src/img/right.png");
    ImageIcon food = new ImageIcon("src/img/food.png");

    boolean started = false;
    boolean gameOver = false;
    ActionListener actionListener;
    // move snake
    Timer t = new Timer(100, this);
    //set food in random location
    int fx, fy; // the coordinate of food
    Random r = new Random();

    int score = 0;
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
        score = 0;
        x[0] = 100;
        y[0] = 100;
        x[1] = 75;
        y[1] = 100;
        x[2] = 50;
        y[2] = 100;
        fx = 25 + 25 * r.nextInt(34);
        fy = 75 + 25 * r.nextInt(24);
        dir = "R";
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int code = keyEvent.getKeyCode();
        // if space is pressed, change the value of started
        if (code == KeyEvent.VK_SPACE) {
            if (gameOver) {
                gameOver = false;
                init();
            } else {
                started = !started;
            }
            //repaint the panel
            repaint();
        } else if (code == keyEvent.VK_LEFT) {
            dir = "L";
        } else if (code == keyEvent.VK_RIGHT) {
            dir = "R";
        } else if (code == keyEvent.VK_UP) {
            dir = "U";
        } else if (code == keyEvent.VK_DOWN) {
            dir = "D";
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (started && !gameOver) {
            //move body
            for (int i = len - 1; i > 0; i--) {
                x[i] = x[i - 1];
                y[i] = y[i - 1];
            }

            //set direction
            if (dir == "R") {
                //move head
                x[0] = x[0] + 25;
                //when snake hits the walls
                if (x[0] > 850) {
                    x[0] = 25;
                }
            } else if (dir == "L") {
                x[0] = x[0] - 25;
                if (x[0] < 25) {
                    x[0] = 850;
                }
            } else if (dir == "U") {
                y[0] = y[0] - 25;
                if (y[0] < 75) {
                    y[0] = 650;
                }
            } else if (dir == "D") {
                y[0] = y[0] + 25;
                if (y[0] > 650) {
                    y[0] = 75;
                }
            }

            //eat
            if (x[0] == fx && y[0] == fy) {
                len++;
                score += 5;
                fx = 25 + 25 * r.nextInt(34);
                fy = 75 + 25 * r.nextInt(24);
            }

            //check
            for (int i = 1; i < len; i++) {
                if (x[i] == x[0] && y[i] == y[0]) {
                    gameOver = true;
                }
            }

            repaint();
        }
        t.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.WHITE);
        title.paintIcon(this, g, 25, 11);

        //game field
        g.fillRect(25, 75, 850, 600);

        //score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Ariel", Font.BOLD, 15));
        g.drawString("Score: " + score, 750, 40);

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

        //food
        food.paintIcon(this, g, fx, fy);

        //start message
        if (! started) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Lucida Calligraphy", Font.BOLD, 40));
            g.drawString("Press space to start", 230, 300);
        }

        //death message
        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Lucida Calligraphy", Font.BOLD, 30));
            g.drawString("Game over: Press space to start a new game", 76, 300);
        }
    }
}
