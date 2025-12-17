package view;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;

public abstract class Form extends JFrame {
    public Form(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        Image icon = Toolkit.getDefaultToolkit().getImage("assets/logo.jpg");
        setIconImage(icon);
        setResizable(false);
        getContentPane().setBackground(CommonConstants.PRIMARY_COLOR);
    }

    protected abstract void addGuiCommponents();
}
