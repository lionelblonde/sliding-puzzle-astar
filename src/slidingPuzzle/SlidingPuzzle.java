package slidingPuzzle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import search.*;
import basicElements.*;
import heuristic.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.AbstractBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.plaf.basic.BasicBorders;

public class SlidingPuzzle extends JFrame {

    public byte[] tabByteAtt;
    public Path solutionAtt;
    public int globalStep = 1;

    // Create two containers
    private Container c1 = new Container();
    private Container c2 = new Container();

    private JButton nextStep = new JButton("Next step");
    private JLabel labelStep = new JLabel();
    private Blinky quit = new Blinky("Quit");

    // The graphical interface consists in a window

    public SlidingPuzzle(byte[] tabByte, Path solution) {
        // Sliding puzzle constructor

        this.tabByteAtt = tabByte;
        this.solutionAtt = solution;

        // Define window title
        this.setTitle("Sliding puzzle w/ A*");
        // Define window size: 400px wide, 100px high
        this.setSize(400, 200);
        // Center window
        this.setLocationRelativeTo(null);
        // Shut down the java process upon closing the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Allow redimensioning
        this.setResizable(true);
        // Tie a layout manager to the window
        this.setLayout(new BorderLayout());

        // Compute the diemnsion of the sliding puzzle grid
        // := sqrt of the number of cells
        int dim = (int) Math.sqrt(tabByte.length);

        // Define layout managers for both containers
        GridLayout gl = new GridLayout(dim, dim);
        int gap = 20;
        gl.setHgap(gap);
        gl.setVgap(gap);
        c1.setLayout(gl);
        c2.setLayout(new FlowLayout());

        // ******** CONTAINER 1 ********

        JLabel[] tabLabel = new JLabel[tabByte.length];
        // Init the grid w/ empty elements: ""
        for (int i = 0; i < tabByte.length; i++) {
            tabLabel[i] = new JLabel("");
            tabLabel[i].setHorizontalAlignment(JLabel.CENTER);
            tabLabel[i].setVerticalAlignment(JLabel.CENTER);
            tabLabel[i].setForeground(Color.BLUE);
        }
        String delimiter = new String(",");
        String str = (solutionAtt.getNodes())[0].getState().toString();
        // Change control and aesthetics when the game is over
        if (solutionAtt.getNodes().length == 0) {
            // Disable click on 'Next step' button
            nextStep.setEnabled(false);
            // Make the 'Quit' button blink
            quit.blink();
        }
        str = str.replaceAll(" ", "");
        str = str.replaceAll("\\[", "");
        str = str.replaceAll("\\]", "");
        String[] strList_ = str.split(delimiter);
        // Init sliding puzzle grid
        for (int k = 0; k < tabByte.length; k++) {
            tabLabel[k].setText(strList_[k]);
        }
        // Associate a label to each grid cell
        for (int i = 0; i < tabByte.length; i++) {
            c1.add(tabLabel[i]);
        }

        // ******** CONTAINER 2 ********

        // Add action listeners to both buttons
        nextStep.addActionListener(new ButtonNextListener(tabLabel, labelStep));
        c2.add(nextStep);
        c2.add(labelStep);
        quit.addActionListener(new ButtonQuitListener());
        c2.add(quit);
        // Add both containers to window
        this.getContentPane().add(c1, BorderLayout.CENTER);
        this.getContentPane().add(c2, BorderLayout.SOUTH);
        // Render visible
        this.setVisible(true);

    }

    public class ButtonNextListener implements ActionListener {

        private JLabel[] tabJLabel;
        private JLabel labelStep;

        public ButtonNextListener(JLabel[] tabJLabel_, JLabel labelStep_) {
            this.tabJLabel = new JLabel[tabJLabel_.length];
            for (int i = 0; i < tabJLabel.length; i++){
                this.tabJLabel[i] = tabJLabel_[i];
            }
            this.labelStep = labelStep_;
        }

        public void actionPerformed(ActionEvent e) {
            // Define what to do when 'Next step' is clicked

            String delimiter = new String(",");

            // Decompose the list each step
            String str0 = (solutionAtt.getNodes())[globalStep].getState().toString();

            // Change control and aesthetics when the game is over
            if (globalStep + 1 == solutionAtt.getNodes().length) {
                // Disable click on 'Next step' button
                nextStep.setEnabled(false);
                // Make the 'Quit' button blink
                quit.blink();
            }
            str0 = str0.replaceAll(" ", "");
            str0 = str0.replaceAll("\\[", "");
            str0 = str0.replaceAll("\\]", "");
            String[] strList = str0.split(delimiter);
            // Init sliding puzzle grid
            for (int k = 0; k < tabByteAtt.length; k++) {
                tabJLabel[k].setText(strList[k]);
            }
            this.labelStep.setText("Step : " + (globalStep));
            globalStep++;
        }

    }

    public class ButtonQuitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Quit the app
            System.exit(0);
        }
    }

}

