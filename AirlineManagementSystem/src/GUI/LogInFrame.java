package GUI;

import CODE.AppException;
import CODE.SD;
import CODE.WindowController;
import Models.BaseUser;
import Models.UserType;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInFrame extends BaseFrame implements ActionListener {
    private  JButton switchToRegisterButton , logInButton;
    private  JTextField emailTextFiled , passwordTextFiled;
    public  LogInFrame(String frameName) throws  Exception{
        super(frameName);
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
        JLabel header = new JLabel("Log In panel");
        header.setFont(new Font("Serif", Font.PLAIN, 30));
        header.setForeground(Color.WHITE);
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.setVisible(true);
        panel.add(header);

        panel.add(CreateFiller(4));


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

        panel.add(CreateFiller(4));

        // switch button
        logInButton = new JButton("log in");
        logInButton.setFont(new Font("Serif", Font.PLAIN, 20));
        logInButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logInButton.setVisible(true);
        panel.add(logInButton);
        logInButton.addActionListener(this);

        // add margin
        panel.add(CreateFiller(2));

        // switch button
        switchToRegisterButton = new JButton("Switch to  register screen");
        switchToRegisterButton.setFont(new Font("Serif", Font.PLAIN, 20));
        switchToRegisterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        switchToRegisterButton.setVisible(true);
        panel.add(switchToRegisterButton);
        switchToRegisterButton.addActionListener(this);

        panel.setVisible(true);
        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if(e.getSource() == this.switchToRegisterButton){
                windowController.SwitchFrame(SD.RegisterFrameName);
            }
            if(e.getSource() == this.logInButton){
                String email = this.emailTextFiled.getText();
                String password = this.passwordTextFiled.getText();
                BaseUser userFromDb = database.LogIn(email , password);
                if(userFromDb == null){
                    throw new AppException("log in failed" , false);
                }
                database.SetActiveUser(userFromDb);
                if(userFromDb.GetUserType() == UserType.Passanger){
                    windowController.SwitchFrame(SD.PassengerMenuFrameName);
                }

            }
        } catch (AppException ex){
            JOptionPane.showMessageDialog(null, "An error occurred: " + ex.developerMessage);
            if(ex.isCritical){
                System.exit(1);
            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
        }

    }
}
