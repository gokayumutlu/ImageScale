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
    private JLabel label;
    private JButton upload,islemButon;
    private ImageIcon icon;
    public BufferedImage image;
    ProgressMonitor monitor;
    public Gui(){
        super("ImageScale");

        upload=new JButton("Upload Image");
        upload.setBounds(400,10,150,35);
        label=new JLabel();
        //label.setBorder(BorderFactory.createLineBorder(Color.black));
        islemButon=new JButton("Islem");
        upload.setBounds(400,50,150, 35);
        add(upload);
        add(islemButon);
        add(label);

        //monitor=new ProgressMonitor(Gui.this,"Processing...","",0,)
        upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser=new JFileChooser();
                chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                //TODO alt satır kontrol et
                //FileNameExtensionFilter filter=new FileNameExtensionFilter("images",".jpg",".png",".jpeg");
                //chooser.addChoosableFileFilter(filter);
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

                    }catch (Exception e1){
                        e1.printStackTrace();
                    }

                }

            }
        });

        //setLayout(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setSize(1920,1080);
        //setVisible(true);


    }

}
