package g;
//TODO Sonra bakılacak
public class imageThread implements Runnable {
    private double width;
    private double height;
    public imageThread(double width,double height){
        this.height=height;
        this.width=width;
    }

    @Override
    public void run() {
        while(width>500 && height>500){
            width=(width*3)/4;
            height=(height*3)/4;
        }
    }
}
