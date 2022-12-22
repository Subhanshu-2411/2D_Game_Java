import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 4; // 5

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 20;
    final int maxScreenRow = 12; // 9

    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;

    // player's default position

    int playerX = 100;
    int playerY = 100;

    int playerSpeed = 4;

    int FPS = 60;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
//        double nextDrawTime = System.nanoTime() + drawInterval; // method 1

        while(gameThread != null) {
//            System.out.println("Game Loop is Running");
//            long currentTime = System.nanoTime();
//
//            System.out.println(currentTime);

            currentTime = System.nanoTime();
            timer += (currentTime - lastTime);
            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

            // method 1

//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime /= 1000000;
//                if(remainingTime < 0) {
//                    remainingTime = 0;
//                }
//                Thread.sleep((long) remainingTime);
//
//                nextDrawTime += drawInterval;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

        }
    }

    public void update() {
        if(keyHandler.upPressed) {
            playerY -= playerSpeed;
        }
        else if(keyHandler.downPressed) {
            playerY += playerSpeed;
        }
        else if(keyHandler.leftPressed) {
            playerX -= playerSpeed;
        }
        else if(keyHandler.rightPressed) {
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setColor(Color.white);

        graphics2D.fillRect(playerX, playerY, tileSize, tileSize);

        graphics2D.dispose();

    }
}
