package CODE;

import Models.Passanger;

import javax.swing.*;
import javax.xml.crypto.Data;

public class Main   {

    public static void main(String[] args) {
        try{
            Database.getDatabase();
        }catch (AppException ex){
            ex.printStackTrace();
            System.out.println("developer message : " + ex.developerMessage);
            if(ex.isCritical){
                System.exit(1); // exit with error
            }
        }
        catch (Exception ex){
            System.out.println("message : " + ex.getMessage());
            ex.printStackTrace();
        }

        // test code
//        try{
//            Database db = Database.getDatabase();
//            Passanger p = db.GetPassengerByEmail("yagiz@gmail.com");
//            db.SetActiveUser(p);
//        }catch (Exception E){
//
//        }


        // run gui manager
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try{
                    WindowController.GetWindowController();
                }catch (AppException ex){
                    System.out.println("developer message : " + ex.developerMessage);
                    if(ex.isCritical){
                        System.exit(1); // exit with error
                    }
                }
                catch (Exception ex){
                    System.out.println("message : " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
    }
}