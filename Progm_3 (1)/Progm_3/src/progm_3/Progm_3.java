/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package progm_3;

/**
 *
 * @author Hans Schneider
 */
public class Progm_3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        java.awt.EventQueue.invokeLater(() -> {
            new Formularios.FormLogin().setVisible(true);
            System.out.println("Aplicación iniciada");
        });
    }
    
}
