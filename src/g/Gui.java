package g;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class Gui extends JFrame {
    JLabel label;
    ImageIcon icon;
    public BufferedImage image;
    public Gui(){
        super("ImageScale");
        setLayout(new FlowLayout());

        JButton upload=new JButton("Upload Image");
        // jframe olştur add yap hem burada hem aşağıda
        add(upload);


        ActionHandler actionHandler=new ActionHandler();
        upload.addActionListener(actionHandler);
    }

    private class ActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser=new JFileChooser();
            chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            //TODO alt satır kontrol et
            FileNameExtensionFilter filter=new FileNameExtensionFilter("images",".jpg",".png",".jpeg");
            chooser.addChoosableFileFilter(filter);
            int res=chooser.showSaveDialog(null);
            if(res==JFileChooser.APPROVE_OPTION){
                File selectedImage=chooser.getSelectedFile();
                try{
                    image=ImageIO.read(selectedImage);
                    System.out.print(image.getRGB(0,0));
                    icon=new ImageIcon(image);
                    label=new JLabel(icon);

                }catch (Exception e1){
                    e1.printStackTrace();
                }

            }

        }
    }
}
