import java.awt.*;
import javax.swing.*;


public class AfficheImage extends JFrame{
   public Image im;
   public AfficheImage(int w, int h) {
     setSize(w,h);
     // chargement de l’image
     Toolkit T=Toolkit.getDefaultToolkit();
     im = T.getImage("insaview.jpg");
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     setVisible(true);
     repaint(); // demande d’appel de la méthode paint
}
   public void paint(Graphics g) {
     int x = 0; int y = 0;
     g.drawImage( im,x,y,null );
}
   public static void main(String[] args) {
     AfficheImage mafenetre = new AfficheImage(800,600);
} }
