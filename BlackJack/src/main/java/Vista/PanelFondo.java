package Vista;

import javax.swing.*;
import java.awt.*;

/**
 * Panel personalizado para manejar imágenes de fondo con redimensionamiento dinámico.
 */
public class PanelFondo extends JPanel {

    private Image imagen;

    /**
     * @param rutaImagen Ruta del recurso
     */
    public PanelFondo(String rutaImagen) {
        java.net.URL imgURL = getClass().getResource(rutaImagen);

        if (imgURL != null) {
            this.imagen = new ImageIcon(imgURL).getImage();
        } else {
            System.err.println("Error: No se encontró la imagen en: " + rutaImagen);
        }
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (imagen != null) {
            Graphics2D g2d = (Graphics2D) g;
            
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            
            g2d.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        }
    }
}