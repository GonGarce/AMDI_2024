/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ejercicio_5;

/**
 *
 * @author gag
 * Ejemplo de interfaz con métodos default que podría usarse en lugar de tener
 * que crear clases Adapter.
 */
public interface MyMouseInputListener {
    default void mouseClicked() {};
    default void mousePresed() {}
}
