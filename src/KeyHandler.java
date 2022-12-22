import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    public boolean upPressed(int code) {
        return code == KeyEvent.VK_UP || code == KeyEvent.VK_W;
    }

    public boolean downPressed(int code) {
        return code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S;
    }

    public boolean leftPressed(int code) {
        return code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A;
    }

    public boolean rightPressed(int code) {
        return code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(upPressed(code)) {
            upPressed = true;
        }
        else if(downPressed(code)) {
            downPressed = true;
        }
        else if(leftPressed(code)) {
            leftPressed = true;
        }
        else if(rightPressed(code)) {
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(upPressed(code)) {
            upPressed = false;
        }
        else if(downPressed(code)) {
            downPressed = false;
        }
        else if(leftPressed(code)) {
            leftPressed = false;
        }
        else if(rightPressed(code)) {
            rightPressed = false;
        }
    }
}
