package GUI;

import CODE.Database;
import CODE.WindowController;

import javax.swing.*;
import java.awt.*;

public class BaseFrame extends JFrame {
    public String frameName;
    public WindowController windowController;
    public Database database;
    public BaseFrame(String frameName) throws  Exception{
        this.frameName = frameName;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        this.windowController = WindowController.GetWindowController();
        this.database = Database.getDatabase();
    }
    public Box.Filler CreateFiller(int space){
        Box.Filler vFill = new Box.Filler(new Dimension(0,space),
                new Dimension(0, space *  4),
                new Dimension(0, space * 8));
        return vFill;
    }


}
