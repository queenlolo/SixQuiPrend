package com.example.sixquiprend.Vue.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener {

    private JButton btnPlay = new JButton("Play");

    public Window() {
        super("6 Qui Prend");
        this.setDefaultCloseOperation(3);

        JPanel contentPane = (JPanel) this.getContentPane();
        contentPane.setLayout(new FlowLayout());
        contentPane.add(btnPlay);
        btnPlay.addActionListener(this);

        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

