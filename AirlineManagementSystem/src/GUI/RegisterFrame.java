package GUI;
import CODE.AppException;
import CODE.Database;
import CODE.SD;
import CODE.WindowController;
import Models.BaseUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends BaseFrame implements ActionListener{
    private JButton switchToLogInButton , submitUserRegisterButton;
    private JTextField userNameTextFiled ,emailTextFiled , passwordTextFiled;

    public  RegisterFrame(String frameName) throws  Exception{
        super(frameName);
        // ui
        setLayout(new BorderLayout());
        setSize(new Dimension(400 , 400));

        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Define the gradient from light blue to purple
                Paint p = new GradientPaint(0.0f, 0.0f, new Color(173, 216, 230),  // Light blue (top-left)
                        getWidth(), getHeight(), new Color(128, 0, 128));          // Purple (bottom-right)

                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(p);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

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
        userNamelabel.setForeground(Color.WHITE);
        userNamelabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        userNamelabel.setVisible(true);
        panel.add(userNamelabel);
        userNameTextFiled = new JTextField();
        userNameTextFiled.setAlignmentX(Component.CENTER_ALIGNMENT);
        userNameTextFiled.setPreferredSize(new Dimension(150, 20));  // Use preferred size
        userNameTextFiled.setMaximumSize(new Dimension(150, 20));    // Set max size to control its expansion
        userNameTextFiled.setVisible(true);
        panel.add(userNameTextFiled);

        //add margin
        panel.add(CreateFiller(2));

        // email group
        JLabel emailLabel = new JLabel("enter email");
        emailLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailLabel.setVisible(true);
        panel.add(emailLabel);
        emailTextFiled = new JTextField();
        emailTextFiled.setAlignmentX(Component.CENTER_ALIGNMENT);
        emailTextFiled.setPreferredSize(new Dimension(150, 20));  // Use preferred size
        emailTextFiled.setMaximumSize(new Dimension(150, 20));    // Set max size to control its expansion
        emailTextFiled.setVisible(true);
        panel.add(emailTextFiled);

        //add margin
        panel.add(CreateFiller(2));

        // password group
        JLabel passwordLabel = new JLabel("enter password");
        passwordLabel.setFont(new Font("Serif", Font.PLAIN, 15));
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordLabel.setVisible(true);
        panel.add(passwordLabel);
        passwordTextFiled = new JTextField();
        passwordTextFiled.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordTextFiled.setPreferredSize(new Dimension(150, 20));  // Use preferred size
        passwordTextFiled.setMaximumSize(new Dimension(150, 20));    // Set max size to control its expansion
        passwordTextFiled.setVisible(true);
        panel.add(passwordTextFiled);

        // add margin
        panel.add(CreateFiller(4));

        // submit button
        submitUserRegisterButton = new JButton("submit");
        submitUserRegisterButton.setFont(new Font("Serif", Font.PLAIN, 20));
        submitUserRegisterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitUserRegisterButton.setVisible(true);
        submitUserRegisterButton.addActionListener(this);
        panel.add(submitUserRegisterButton);

        // add margin
        panel.add(CreateFiller(2));

        // switch button
        switchToLogInButton = new JButton("Switch to log in screen");
        switchToLogInButton.setFont(new Font("Serif", Font.PLAIN, 20));
        switchToLogInButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        switchToLogInButton.setVisible(true);
        switchToLogInButton.addActionListener(this);
        panel.add(switchToLogInButton);


        panel.setVisible(true);
        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        try{
            if(e.getSource() == this.switchToLogInButton){
                this.windowController.SwitchFrame(SD.LogInFrameName);
            }
            else  if(e.getSource() == this.submitUserRegisterButton){
                String userName = this.userNameTextFiled.getText();
                String password = this.passwordTextFiled.getText();
                String email = this.emailTextFiled.getText();
                BaseUser currentUser = database.CreatePassanger(userName , email , password);
                this.database.SetActiveUser(currentUser);
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
