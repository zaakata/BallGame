package kulki;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BallGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Prosta gra w kulki");
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setVisible(true);
    }
}

class GamePanel extends JPanel implements ActionListener {
    private static final int BALL_SIZE = 20;
    private static final int PADDLE_WIDTH = 100;
    private static final int PADDLE_HEIGHT = 10;
    private int x = 0;
    private int y = 0;
    private double dx = 2;
    private double dy = 2;
    private int paddleX = 250;
    private int score = 0;
    private double speedMultiplier = 1.1;
    private Timer timer;

    public GamePanel() {
        timer = new Timer(10, this);
        timer.start();
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                paddleX = e.getX() - PADDLE_WIDTH / 2;
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillOval(x, y, BALL_SIZE, BALL_SIZE);
        g.setColor(Color.BLACK);
        g.fillRect(paddleX, getHeight() - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);
        g.drawString("Score: " + score, 10, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x += dx;
        y += dy;

        if (x < 0 || x > getWidth() - BALL_SIZE) {
            dx = -dx;
        }
        if (y < 0) {
            dy = -dy;
        } else if (y > getHeight() - BALL_SIZE - PADDLE_HEIGHT) {
            if (x > paddleX - BALL_SIZE && x < paddleX + PADDLE_WIDTH) {
                dy = -Math.abs(dy) * speedMultiplier;
                dx *= speedMultiplier;
                score++;
            } else if (y > getHeight()) {
                JOptionPane.showMessageDialog(this, "Gra skończona! Twój wynik to: " + score);
                System.exit(0);
            }
        }

        repaint();
    }

    // Methods below are package-private to facilitate unit testing
    void stopTimer() {
        if (timer != null) {
            timer.stop();
        }
    }

    int getScore() {
        return score;
    }
}
