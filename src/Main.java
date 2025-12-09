import javax.swing.SwingUtilities;

import managers.AuthService;
import managers.IdGenerator;
import view.LoginView;

public class Main {
    public static void main(String[] args) {
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AuthService auth = new AuthService(new IdGenerator());
                new LoginView(auth).setVisible(true);
            }
        });
    }
}
