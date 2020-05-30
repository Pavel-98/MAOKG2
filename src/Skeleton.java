import java.awt.*;
import java.awt.geom.GeneralPath;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;

@SuppressWarnings("serial")
public class Skeleton extends JPanel implements ActionListener {
    Timer timer;
Skeleton(){
    timer = new Timer(10, this);
    timer.start();
}
    // Всі дії, пов'язані з малюванням, виконуються в цьому методі
    double scale = 1.0;
    private double delta = 0.01;
    public void paint(Graphics g) {
// Оскільки Java2D є надбудовою над старішою бібліотекою, необхідно робити
        //це приведення
        Graphics2D g2d = (Graphics2D) g;
       // g2d.translate(maxWidth/2, maxHeight/2);
        g2d.setBackground(Color.red);
        g2d.clearRect(0, 0, maxWidth, maxHeight);
        BasicStroke bs4 = new BasicStroke(5, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_MITER);
        g2d.drawRect(55, 70, 800, 620);
// Далі йде безпосередньо малювання. Для прикладу намалюємо такий рядок
        //g2d.drawString("Привіт, Java 2D!", 50, 50);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                (float)scale));
        // Зсовуємо центр координат в центр вікна
        //g2d.translate(maxWidth/2, maxHeight/2);

        // Перетворення для анімації руху.
        g2d.translate(tx, ty);
        GeneralPath polygon = new GeneralPath();
        g2d.setPaint(new Color(0, 255, 255));
        double coords[][] = {{15, 80}, {100, 55}, {115, 130}, {130, 65}, {230, 100},
                {185, 179}, {120, 145}, {165, 205}, {110, 265}, {66, 185}, {110, 145}, {25, 170}};
        polygon.moveTo(coords[0][0], coords[0][1]);
        for(int i = 1; i < coords.length; i++){
            polygon.lineTo(coords[i][0], coords[i][1]);
        }
        polygon.closePath();
        g2d.setPaint(Color.green);
        g2d.fill(polygon);
        g2d.setPaint(Color.green);

        BasicStroke bs3 = new BasicStroke(5, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND);
        g2d.setStroke(bs3);
        //g2d.drawRect(0, 0, maxWidth, maxHeight);
        //g2d.

        GradientPaint gp = new GradientPaint(5, 25,
                Color.YELLOW, 20, 2, Color.BLUE, true);
        g2d.setPaint(gp);
        g2d.drawLine(57, 105, 167, 139);
        g2d.drawLine(167, 139, 115, 195);
        g2d.drawLine(115, 195, 57, 105);



        //g2d.drawRect(0, 0, maxWidth/4, maxHeight/4);
    }
    private static int maxWidth;
    private static int maxHeight;
    // Для анімації руху
   // private double dx = 1;
    private int tx = 55;
    //private double dy = 50;

    private int ty =55;
    // Цей метод буде викликано щоразу, як спрацює таймер
    public void actionPerformed(ActionEvent e) {
        if ( tx == 55 ) {
            if(ty == 400)
            {
                tx++;
            }
            else{
                ty++;
            }
        }
         else if(tx == 600){
            if(ty == 55){
                tx--;
            }
            else{
            ty--;
            }

        }
         else{
             if(ty == 55){
                 tx--;
             }
             if(ty == 400){
                 tx++;
             }
        }
        if ( scale < 0.01 ) {
            delta = -delta;
        } else if (scale > 0.99) {
            delta = -delta;
        }

        /*if ( ty < -maxHeight/3 ) {
            dy = -dy;
        } else if ( ty > maxHeight/3 ) {
            dy = -dy;
        }
        tx += dx;
        ty += dy;*/
        scale += delta;

        repaint();
    }

    public static void main(String[] args) {
        // Створюємо нове графічне вікно (формочка). Параметр конструктора - заголовок
        //вікна.
                JFrame frame = new JFrame("Привіт, Java 2D!");
        // Визначаємо поведінку програми при закритті вікна (ЛКМ на "хрестик")
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Визначаємо розмір вікна
        frame.setSize(1000, 1000);
        // Якщо позиція прив'язана до null, вікно з'явиться в центрі екрану
        frame.setLocationRelativeTo(null);
        // Забороняємо змінювати розміри вікна
        frame.setResizable(false);
        // Додаємо до вікна панель, що і описується даним класом
        // Зауважте, що точка входу в програму - метод main, може бути й в іншому класі
        frame.add(new Skeleton());
        //frame.add(new Ramka());
        // Показуємо форму. Важливо додати всі компоненти на форму до того, як зробити
        //її видимою.

        frame.setVisible(true);
        Dimension size = frame.getSize();
        Insets insets = frame.getInsets();
        maxWidth = size.width - insets.left - insets.right - 1;
        maxHeight = size.height - insets.top - insets.bottom - 1;

    }
}
//public class Skeleton {
//}
class Ramka extends JPanel{
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setBackground(new Color(1f,0f,0f,.5f ));
        g2d.drawRect(0, 0, 500, 500);
    }
}