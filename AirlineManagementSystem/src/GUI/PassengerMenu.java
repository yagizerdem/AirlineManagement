package GUI;

import CODE.SD;
import Models.Passanger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PassengerMenu extends BaseFrame implements ActionListener {
    JButton SettingsButton;
    public PassengerMenu(String frameName) throws Exception{
        super(frameName);
        setLayout(new BorderLayout());
        setSize(new Dimension(250 , 380));

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Define the gradient from dark blue to dark purple
                Paint p = new GradientPaint(0.0f, 0.0f, new Color(0, 0, 139),  // Dark blue (top-left)
                        getWidth(), getHeight(), new Color(75, 0, 130));        // Dark purple (bottom-right)

                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(p);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // header label
        JLabel header = new JLabel("Passenger menu");
        header.setFont(new Font("Serif", Font.PLAIN, 30));
        header.setForeground(Color.WHITE);
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.setVisible(true);
        panel.add(header);

        // navigation buttons
        // switch button
        SettingsButton = new JButton("Settings");
        SettingsButton.setFont(new Font("Serif", Font.PLAIN, 20));
        SettingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        SettingsButton.setVisible(true);
        SettingsButton.addActionListener(this);
        panel.add(SettingsButton);


        panel.setVisible(true);
        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == SettingsButton){
            windowController.SwitchFrame(SD.PassengerSettingsFrameName);
        }
    }
}
