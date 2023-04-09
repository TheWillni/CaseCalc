import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener{
    private MyUtils utils;
    private Calculator calc;
    private Inventory inv;
    private Config cfg;
    GUI (MyUtils util, Inventory inventory, Config config) {
        // initialise our objects
        utils = util;
        inv = inventory;
        cfg = config;
        calc = new Calculator(utils, cfg);
        System.out.println("Running UI");

        JFrame frame = new JFrame("CaseCalc");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = getConstraints();
        JButton button = new JButton("Run");
        button.addActionListener(this);
        frame.add(panel);
        frame.getContentPane().add(button); // Adds Button to content pane of frame
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        //System.out.println("Button was hit!");
        utils.print("Button was pressed!");
        calc.sumInventoryValue(inv);
    }

    private GridBagConstraints getConstraints() {
        // set our preferred constraints for our gridBagLayout
        GridBagConstraints c = new GridBagConstraints();

        return c;
    }
}
