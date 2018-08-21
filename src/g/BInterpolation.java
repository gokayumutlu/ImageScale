package g;

import java.awt.image.BufferedImage;

public class BInterpolation {

    private int g(int a,int n){
        return (a>>(n*8)) &0xFF;
    }

    private float l(float s, float e, float t){
        return s+(e-s)*t;
    }

    private float bl(float c00,float c10,float c01, float c11, float tx,float ty){
        return l(l(c00,c10,tx),l(c01,c11,tx),ty);
    }

    public BufferedImage scale( BufferedImage image, float sX, float sY){
        int newWidth=(int)(image.getWidth()*sX);
        int newHeight=(int)(image.getHeight()*sY);

        BufferedImage newImage=new BufferedImage(newWidth,newHeight,image.getType());
        for(int x=0;x<newWidth;x++){
            for(int y=0;y<newHeight;y++){
                int rgb=0;
                float gx=(float)x/newWidth*(image.getWidth()-1);
                float gy=(float)y/newHeight*(image.getHeight()-1);
                int gxi=(int)gx;
                int gyi=(int)gy;
                int c00=image.getRGB(gxi,gyi);
                int c10=image.getRGB(gxi+1,gyi);
                int c01=image.getRGB(gxi,gyi+1);
                int c11=image.getRGB(gxi+1,gyi+1);

                for (int i=0;i<=2;i++){
                    float b00=g(c00,i);
                    float b10=g(c10,i);
                    float b01=g(c01,i);
                    float b11=g(c11,i);

                    int ble=((int)bl(b00,b10,b01,b11,gx-gxi,gy-gyi)<<(8*i));
                    rgb=rgb|ble;
                }
                newImage.setRGB(x,y,rgb);

            }


        }
        return newImage;
    }
}
