package g;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class Gui extends JFrame {
    private JLabel label;
    private ImageIcon icon;
    private BufferedImage image,newImage;
    private boolean uploaded=false;
    public Gui(){
        super("ImageScale");

        JButton upload=new JButton("Upload Image");
        upload.setBounds(400,10,150,35);
        label=new JLabel();
        JButton islemButon=new JButton("Islem");
        islemButon.setBounds(400,50,150, 35);
        add(upload);
        add(islemButon);
        add(label);

        upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Action();
            }
        });

        islemButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(uploaded){
                    BInterpolation bInterpolation=new BInterpolation();
                    newImage=bInterpolation.scale(image,1.6f,1.6f);
                    File newFile=new File("larger2.jpg");
                    try {
                        ImageIO.write(newImage,"jpg",newFile);
                        System.out.print("Tamamlandı");

                    }catch (Exception e1){
                        e1.printStackTrace();
                    }

                }
                else {
                    System.out.print("not uploaded");
                }

            }
        });




    }

    public void Action(){
        JFileChooser chooser=new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int res=chooser.showSaveDialog(null);
        if(res==JFileChooser.APPROVE_OPTION){
            File selectedImage=chooser.getSelectedFile();
            try{
                image=ImageIO.read(selectedImage);
                double width=image.getWidth();
                double height=image.getHeight();
                if(image.getWidth() > 500 || image.getHeight() > 500){
                    System.out.print("if");
                    System.out.println(image.getWidth()+"-"+image.getHeight());
                    //TODO boyut küçültme. Sonra bakılacak
                    while(width>500 && height>500){
                        width=(width*3)/4;
                        height=(height*3)/4;
                    }

                    System.out.println(width+"-"+height);
                    ImageIcon newIcon=new ImageIcon(image);
                    Image newImage=newIcon.getImage();
                    Image newestImage=newImage.getScaledInstance((int)width,(int)height,Image.SCALE_SMOOTH);
                    newIcon=new ImageIcon(newestImage);
                    label.setIcon(newIcon);

                }
                else {
                    System.out.print("else");

                    label.setBounds(50,50,image.getWidth(),image.getHeight());
                    icon=new ImageIcon(image);
                    label.setIcon(icon);

                }
                //System.out.print(image.getRGB(0,0));
                uploaded=true;

            }catch (Exception e1){
                e1.printStackTrace();
            }

        }

    }



}
