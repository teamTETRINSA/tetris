import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class AAA extends JFrame implements MouseListener, ActionListener{
    
    private JPanel JP1;
    private JPanel JP2;
    private JPanel JPMenu;
    
    public AAA(int w, int h, int pw, int ph) {
        setSize(w,h);
        setLocation(pw,ph);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
        
        JPanel JPG1 = new JPanel();
        JP1.setLayout(null);
        JP1.setBounds(10,10,10,10);
        JP1.setBackground(Color.green);
        this.add(JPG1);
        
        JPanel JPG2 = new JPanel();
        JP1.setLayout(null);
        JP1.setBounds(30,10,10,10);
        JP1.setBackground(Color.red);
        this.add(JPG2);
        
        JPanel JPMenu = new JPanel();
        JP1.setLayout(null);
        JP1.setBounds(50,10,10,10);
        JP1.setBackground(Color.yellow);
        this.add(JPMenu);
        
    }
    
    public void paint(Graphics g) {
        // dessin d’un rectangle de couleur cyan sur toute la surface du JFrame
        g.setColor( Color.cyan ); // choix de la couleur du crayon
        g.fillRect(0,0,getSize().width,getSize().height);
        // dessin d’une ligne bleue diagonale
        g.setColor( Color.blue); // changement de couleur
        int xA = 0; int yA = 0; // coordonnees du debut de la ligne
        int xB = getSize().width; int yB = getSize().height; // coordonnees de la fin de la ligne 3
        g.drawLine(xA,yA,xB,yB);
        // affichage d’un texte bleu aux coordonnees x=100 et y=100
        g.setColor(Color.black); // changement de couleur
        g.drawString("Mon texte", 100,100);
    }
    
    /********************************************************************************************************
     * GRAPHICAL METHODS
     * all methods that will take into account graphical parameters
     * */
    
    public void actionPerformed (ActionEvent e) {
    }
    
    public void mouseClicked (MouseEvent e){
    }
    
    public void mouseEntered(MouseEvent e){
    }
        
    public void mouseExited(MouseEvent e){
    }
    
    public void mousePressed(MouseEvent e){ 
    }
    
    public void mouseReleased(MouseEvent e){  
    }
    
    /********************************************************************************************************
     * MAIN
     * 
     * */
    
    public static void main(String[] args) {
        AAA maFenetre = new AAA(1000,600,100,50);
    }
} 
