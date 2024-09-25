/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio_5;

import ejercicio_4.DialogSurvey4;
import java.awt.event.MouseEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author gag
 */
public class EjemploMouseInputAdapter extends MouseInputAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
        JDialog dialog = new DialogSurvey4((JFrame) SwingUtilities.getWindowAncestor(e.getComponent()), true);
        dialog.setVisible(true);
    }
    
}
