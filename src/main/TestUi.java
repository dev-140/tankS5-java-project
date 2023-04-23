package main;

import java.awt.BorderLayout;

import javax.swing.*;

public class TestUi {
   public static void main(String[] args) {
      JFrame frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      JPanel panel1 = new JPanel();
      panel1.add(new JLabel("Panel 1"));

      JPanel panel2 = new JPanel();
      panel2.add(new JLabel("Panel 2"));

      JPanel barrierPanel = new JPanel();
      barrierPanel.setOpaque(false);

      JRootPane rootPane = frame.getRootPane();
      rootPane.setGlassPane(barrierPanel);
      barrierPanel.setVisible(false);

      JButton openButton = new JButton("Open Barrier");
      openButton.addActionListener(e -> {
          barrierPanel.setVisible(true);
          barrierPanel.requestFocus();
      });

      JButton closeButton = new JButton("Close Barrier");
      closeButton.addActionListener(e -> {
          barrierPanel.setVisible(false);
          barrierPanel.transferFocus();
      });

      JPanel buttonPanel = new JPanel();
      buttonPanel.add(openButton);
      buttonPanel.add(closeButton);

      frame.add(panel1);
      frame.add(panel2);
      frame.add(buttonPanel, BorderLayout.SOUTH);
      frame.pack();
      frame.setVisible(true);
   }
}