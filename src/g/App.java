package g;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class App extends JFrame {


    public static void main(String args[]){
        Gui gui=new Gui();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(500,500);
        gui.setVisible(true);

    }

    public void imageUpload(){
        try {

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
