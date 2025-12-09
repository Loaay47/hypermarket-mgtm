import javax.swing.SwingUtilities;

import managers.AuthService;
import managers.IdGenerator;
import view.LoginView;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AuthService auth = new AuthService(new IdGenerator());
                new LoginView(auth).setVisible(true);
            }
        });
    }
}
