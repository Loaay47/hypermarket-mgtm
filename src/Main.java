import javax.swing.SwingUtilities;
import view.LoginView;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView());
    }
}
