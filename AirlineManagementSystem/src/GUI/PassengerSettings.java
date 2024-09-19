package GUI;

import CODE.AppException;
import CODE.SD;
import Models.BaseUser;
import Models.Passanger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PassengerSettings extends BaseFrame implements ActionListener {
    private  JTextField userNameTextFiled ,emailTextFiled , passwordTextFiled;
    private JButton submitButton , switchToPassengerMenu;
    public PassengerSettings(String BaseFrame) throws Exception{
        super(BaseFrame);
        setSize(new Dimension(250 , 380));
        setLayout(new BorderLayout());
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Define the gradient from dark blue to dark purple
                Paint p = new GradientPaint(0.0f, 0.0f, new Color(41, 41, 65),  // Dark blue (top-left)
                        getWidth(), getHeight(), new Color(27, 13, 35));        // Dark purple (bottom-right)

                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(p);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };;
        panel.setLayout(new BoxLayout(panel , BoxLayout.Y_AXIS));

        // hader lable
        JLabel header = new JLabel("Register Panel");
        header.setFont(new Font("Serif", Font.PLAIN, 30));
        header.setForeground(Color.WHITE);
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.setVisible(true);
        panel.add(header);

        // add margin
        panel.add(CreateFiller(4));

        // user name group
        JLabel userNamelabel = new JLabel("enter user name");
        userNamelabel.setFont(new Font("Serif", Font.PLAIN, 15));
        userNamelabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userNamelabel.setVisible(true);
        userNamelabel.setForeground(Color.WHITE);
        panel.add(userNamelabel);
        userNameTextFiled = new JTextField();
        userNameTextFiled.setEnabled(false);
        userNameTextFiled.setAlignmentX(Component.CENTER_ALIGNMENT);
        userNameTextFiled.setPreferredSize(new Dimension(150, 20));  // Use preferred size
        userNameTextFiled.setMaximumSize(new Dimension(150, 20));    // Set max size to control its expansion
        userNameTextFiled.setVisible(true);
        userNameTextFiled.setText(database.GetActiveUser() != null ? database.GetActiveUser().GetUserName():"");
        panel.add(userNameTextFiled);

        //add margin
        panel.add(CreateFiller(2));

        // email group
        JLabel emailLabel = new JLabel("enter email");
        emailLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailLabel.setVisible(true);
        emailLabel.setForeground(Color.WHITE);
        panel.add(emailLabel);
        emailTextFiled = new JTextField();
        emailTextFiled.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailTextFiled.setPreferredSize(new Dimension(150, 20));  // Use preferred size
        emailTextFiled.setMaximumSize(new Dimension(150, 20));    // Set max size to control its expansion
        emailTextFiled.setVisible(true);
        emailTextFiled.setText(database.GetActiveUser() != null ? database.GetActiveUser().GetEmail():"");
        panel.add(emailTextFiled);

        //add margin
        panel.add(CreateFiller(2));

        // password group
        JLabel passwordLabel = new JLabel("enter password");
        passwordLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setVisible(true);
        panel.add(passwordLabel);
        passwordTextFiled = new JTextField();
        passwordTextFiled.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordTextFiled.setPreferredSize(new Dimension(150, 20));  // Use preferred size
        passwordTextFiled.setMaximumSize(new Dimension(150, 20));    // Set max size to control its expansion
        passwordTextFiled.setVisible(true);
        passwordTextFiled.setText(database.GetActiveUser() != null ? database.GetActiveUser().GetPassword() :"");
        panel.add(passwordTextFiled);

        // submit button
        submitButton = new JButton("submit");
        submitButton.setFont(new Font("Serif", Font.PLAIN, 20));
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setVisible(true);
        submitButton.addActionListener(this);
        panel.add(submitButton);

        // add margin
        panel.add(CreateFiller(2));

        // switch button
        switchToPassengerMenu = new JButton("Switch to passenger menu");
        switchToPassengerMenu.setFont(new Font("Serif", Font.PLAIN, 20));
        switchToPassengerMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        switchToPassengerMenu.setVisible(true);
        switchToPassengerMenu.addActionListener(this);
        panel.add(switchToPassengerMenu);

        // add margin
        panel.add(CreateFiller(4));
        panel.setVisible(true);
        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if(e.getSource() == this.switchToPassengerMenu){
                this.windowController.SwitchFrame(SD.PassengerMenuFrameName);
            }
            else  if(e.getSource() == this.submitButton){
                String email = this.emailTextFiled.getText();
                String password = this.passwordTextFiled.getText();
                Passanger updatedUser = database.updatePassenger(email ,password);
                if(updatedUser != null){
                    // update successfull
                    windowController.SwitchFrame(SD.PassengerMenuFrameName);
                }
            }
        }
        catch (AppException ex){
            JOptionPane.showMessageDialog(null, "An error occurred: " + ex.developerMessage);
            if(ex.isCritical){
                System.exit(1);
            }

        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
        }
    }
}
