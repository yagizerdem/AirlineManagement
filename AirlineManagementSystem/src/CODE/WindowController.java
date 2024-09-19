package CODE;

import GUI.*;

import javax.swing.*;
import java.util.ArrayList;

public class WindowController  {
    private static WindowController instance = null;
    private ArrayList<BaseFrame> allFrames = new ArrayList<>();
    private int ActiveFrameIndex = 0;
    public WindowController() throws  Exception{
       if(WindowController.instance != null){
           return;
       }
        WindowController.instance = this;

        // initilize frames
        RegisterFrame registerFrame = new RegisterFrame(SD.RegisterFrameName);
        LogInFrame logInFrame = new LogInFrame(SD.LogInFrameName);
        PassengerMenu passengerMenuFrame = new PassengerMenu(SD.PassengerMenuFrameName);
        PassengerSettings passengerSettingsFrame = new PassengerSettings(SD.PassengerSettingsFrameName);

        // add referace
        allFrames.add(registerFrame);
        allFrames.add(logInFrame);
        allFrames.add(passengerMenuFrame);
        allFrames.add(passengerSettingsFrame);

        CloseAllFrames();
        ShowActiveFrame();
    }

    public void CloseAllFrames(){
        for(JFrame f : this.allFrames){
            f.setVisible(false);
        }
    }
    public  void ShowActiveFrame(){
        allFrames.get(this.ActiveFrameIndex).setVisible(true);
    }

    public void SwitchFrame(int activeIndex){
        this.ActiveFrameIndex = activeIndex;
        CloseAllFrames();
        ShowActiveFrame();
    }
    public  void SwitchFrame(String frameName){
        for(int i  = 0; i < this.allFrames.size(); i++){
            if(this.allFrames.get(i).frameName == frameName){
                this.ActiveFrameIndex = i;
                break;
            }
        }
        CloseAllFrames();
        ShowActiveFrame();
    }
    public static WindowController GetWindowController() throws Exception{
        if(WindowController.instance == null){
            return new WindowController();
        }
        else{
            return  WindowController.instance;
        }
    }
}
