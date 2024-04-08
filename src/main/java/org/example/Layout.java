package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Layout {

    private final JPanel panel;
    private List<String> listNumber;
    private final Random random = new Random();
    private final GridBagLayout layout;

    public Layout() {
        panel = new JPanel();
        layout = new GridBagLayout();
        panel.setLayout(layout);
        initLayout();
    }

    private void initLayout() {
        setListNumber();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int indexNumber = random.nextInt(listNumber.size());
                String number = listNumber.get(indexNumber);
                if (!number.equals("0")) {
                    JButton button = new JButton(number);
                    button.addActionListener(e -> {
                        Component btn = (Component) e.getSource();
                        GridBagConstraints constraints = layout.getConstraints(btn);
                        int x = constraints.gridx;
                        int y = constraints.gridy;
                        move(x, y);
                    });
                    GridBagConstraints constraints = new GridBagConstraints();
                    constraints.gridx = i;
                    constraints.gridy = j;
                    constraints.weightx = 1;
                    constraints.weighty = 1;
                    constraints.fill = GridBagConstraints.BOTH;

                    panel.add(button, constraints);
                }
                listNumber.remove(indexNumber);
            }
        }
    }


    private void setListNumber() {
        listNumber = new ArrayList<>(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"));
    }


    private JButton getButtonAtPosition(int x, int y) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            GridBagConstraints constraints = layout.getConstraints(component);
            if (constraints.gridx == x && constraints.gridy == y && component instanceof JButton) {
                return (JButton) component;
            }
        }
        return null;
    }

    private void swapButtons(int x1, int y1, int x2, int y2) {
        JButton button = getButtonAtPosition(x1, y1);
        String number = button.getText();
        panel.remove(button);
        JButton newButton = new JButton(number);
        newButton.addActionListener(e->{
            Component btn = (Component) e.getSource();
            GridBagConstraints constraints = layout.getConstraints(btn);
            int x = constraints.gridx;
            int y = constraints.gridy;
            move(x, y);
        });
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x2;
        constraints.gridy = y2;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        panel.add(newButton, constraints);
        panel.revalidate();
        panel.repaint();
    }


    private boolean isEmptyAtPosition(int x, int y) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            GridBagConstraints constraints = layout.getConstraints(component);
            if (constraints.gridx == x && constraints.gridy == y && component instanceof JButton) {
                return false;
            }
        }
        return true;
    }

    public void move(int x, int y) {
        if (x > 0 && isEmptyAtPosition(x - 1, y)) {
            swapButtons(x, y, x - 1, y);
        } else if (x < 3 && isEmptyAtPosition(x + 1, y)) {
            swapButtons(x, y, x + 1, y);
        } else if (y > 0 && isEmptyAtPosition(x, y - 1)) {
            swapButtons(x, y, x, y - 1);
        } else if (y < 3 && isEmptyAtPosition(x, y + 1)) {
            swapButtons(x, y, x, y + 1);
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}
