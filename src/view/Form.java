package view;

import javax.swing.JFrame;

public abstract class Form extends JFrame{
    public Form(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(CommonConstants.PRIMARY_COLOR);
    }

    protected abstract void addGuiCommponents();
}

