package com.example.sixquiprend.Vue.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener {

    private JButton btnPlay = new JButton("Play");

    public Window() {
        super("6 Qui Prend");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(new FlowLayout());

        JLabel fond = new JLabel(new ImageIcon("../Image/menu.jpg"));
        contentPane.add(fond);

        contentPane.add(btnPlay);
        btnPlay.addActionListener(this);

        this.setSize(1000, 700);
        fond.setBounds(0, 0, getWidth(), getHeight());

        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

