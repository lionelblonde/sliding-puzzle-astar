package slidingPuzzle;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.Timer;


public class Blinky extends JButton {

	private static final long serialVersionUID = 5379263748310201606L;
	private Timer timer;
    private JButton bouton;
    private Color tempcolor;

    public Blinky(String label) {
        super(label);
        bouton = this;
    }

    public void blink() {
        tempcolor = this.getBackground();
        timer = new Timer(100, new BlinkingListener());
        this.setForeground(Color.RED);
        timer.start();
    }

    public class BlinkingListener implements ActionListener {

    	public void actionPerformed(ActionEvent evt) {
    		// Color inversion
            if (bouton.getForeground().equals(tempcolor))
              setForeground (Color.RED);
            else
              bouton.setForeground(tempcolor);
    	}

    }

}

