/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio_5;

/**
 *
 * @author gag
 * Ejemplo de uso de la interfaz con métodos default
 */
public class MyClass implements MyMouseInputListener {

    @Override
    public void mouseClicked() {
        System.out.println("Mouse clicked");
    }

    public static void main(String args[]) {
        MyClass m  = new MyClass();
        m.mouseClicked();
        m.mousePresed();
    }
    
}
