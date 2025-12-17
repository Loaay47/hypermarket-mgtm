package view.dashboard;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import managers.AuthService;
import managers.MarketingManager;
import view.CommonConstants;
import view.Form;
import view.LoginView;

public class MarketingDashboard extends Form {
    private final AuthService auth;
    private MarketingManager marketingManager;
    private JPanel content;

    public MarketingDashboard(AuthService auth, MarketingManager marketingManager) {
        super("Marketing Dashboard");
        setSize(CommonConstants.DASHBOARD_SIZE);
        setLocationRelativeTo(null);
        this.auth = auth;
        this.marketingManager = marketingManager;
        addGuiCommponents();

    }

    @Override
    protected void addGuiCommponents() {
        JLabel header = new JLabel("Marketing Dashboard");
        header.setFont(new Font("Dialog", Font.BOLD, 36));
        header.setForeground(CommonConstants.MARKETING_COLOR);
        header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setBounds(0, 10, getWidth(), 60);

        add(header);

        JLabel userLabel = new JLabel(auth.getCurrentUsername());
        userLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        userLabel.setForeground(CommonConstants.MARKETING_COLOR.brighter());
        userLabel.setBounds(20, 10, 300, 30);

        add(userLabel);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(getWidth() - 120, 20, 100, 40);
        logoutButton.setBackground(CommonConstants.MARKETING_COLOR);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutButton.setFont(new Font("Dialog", Font.BOLD, 18));
        logoutButton.setFocusable(false);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to logout?",
                        "Confirm Logout",
                        JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    auth.logout();
                    dispose();
                    new LoginView(auth).setVisible(true);
                }
            }
        });

        add(logoutButton);


        // left menu panel
        JPanel menu = new JPanel(null);
        menu.setBackground(CommonConstants.PRIMARY_COLOR.darker());
        menu.setBounds(0, 80, 250, getHeight() - 100);
        add(menu);

        JButton productReportBtn = new JButton("Product Report");
        productReportBtn.setBounds(20, 40, 200, 40);
        productReportBtn.setBackground(CommonConstants.MARKETING_COLOR);
        productReportBtn.setForeground(Color.WHITE);
        productReportBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        productReportBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        productReportBtn.setFocusable(false);
        productReportBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        menu.add(productReportBtn);

        JButton specialOfferBtn = new JButton("Special Offer");
        specialOfferBtn.setBounds(20, 100, 200, 40);
        specialOfferBtn.setBackground(CommonConstants.MARKETING_COLOR);
        specialOfferBtn.setForeground(Color.WHITE);
        specialOfferBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        specialOfferBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        specialOfferBtn.setFocusable(false);
        specialOfferBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        menu.add(specialOfferBtn);

        JButton sendOfferBtn = new JButton("Offer to INV");
        sendOfferBtn.setBounds(20, 160, 200, 40);
        sendOfferBtn.setBackground(CommonConstants.MARKETING_COLOR);
        sendOfferBtn.setForeground(Color.WHITE);
        sendOfferBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sendOfferBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        sendOfferBtn.setFocusable(false);
        sendOfferBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                content.removeAll();

                content.revalidate();
                content.repaint();
            }
        });

        menu.add(sendOfferBtn);
        // content area
        content = new JPanel();
        content.setBackground(CommonConstants.BACKGROUND_COLOR);
        content.setBounds(260, 80, getWidth() - 280, getHeight() - 100);
        content.setLayout(new BorderLayout());
        add(content);

    }

}
