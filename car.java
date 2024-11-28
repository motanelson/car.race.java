import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class car extends JPanel {
    private int frame = 0;
    private int roadTopY = 100;
    private int roadBaseY = 600;
    private int roadWidthTop = 100;
    private int roadWidthBase = 300;
    private int curveAmplitude = 300;
    private double curveSpeed = 0.05;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Estrada com Curvas");
        car panel = new car();
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Animação
        Timer timer = new Timer(16, e -> {
            panel.nextFrame();
            panel.repaint();
        });
        timer.start();
    }

    public void nextFrame() {
        frame++;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Fundo preto
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Linha do horizonte
        g2d.setColor(Color.WHITE);
        int horizonY = 50;
        g2d.drawLine(0, horizonY, getWidth(), horizonY);
        roadTopY=roadTopY+20;
        if(roadTopY>roadBaseY-100)roadTopY=100;
        // Triângulo principal (estrada)
        int centerX = getWidth() / 2;
        Path2D mainRoad = new Path2D.Double();
        mainRoad.moveTo(centerX - roadWidthTop / 2, roadTopY);  // Topo esquerdo
        mainRoad.lineTo(centerX + roadWidthTop / 2, roadTopY);  // Topo direito
        mainRoad.lineTo(centerX + roadWidthBase / 2, roadBaseY);  // Base direita
        mainRoad.lineTo(centerX - roadWidthBase / 2, roadBaseY);  // Base esquerda
        mainRoad.closePath();
        g2d.setColor(Color.WHITE);
        g2d.fill(mainRoad);

        // Triângulo invertido (curvas)
        double curveOffset = Math.sin(frame * curveSpeed) * curveAmplitude;
        Path2D curve = new Path2D.Double();
        curve.moveTo(centerX - roadWidthTop / 2, roadTopY);  // Topo esquerdo
        curve.lineTo(centerX + roadWidthTop / 2, roadTopY);  // Topo direito
        curve.lineTo(centerX + curveOffset, 50) ;// Ponta invertida
        curve.closePath();
        g2d.setColor(Color.WHITE);
        g2d.fill(curve);
    }
}
