package view;

import javax.swing.JFrame;

public class Form extends JFrame{
    public Form(String title) {
        super(title);
        setSize(CommonConstants.LOGIN_SIZE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        getContentPane().setBackground(CommonConstants.PRIMARY_COLOR);
    }
}

