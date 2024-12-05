
package View;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            LoginJFrame loginFrame = new LoginJFrame();
            loginFrame.setVisible(true);
        });
    }
}

