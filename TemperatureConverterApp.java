import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TemperatureConverterApp extends JFrame implements ActionListener {
    private JComboBox<String> fromBox, toBox;
    private JTextField inputField, outputField;
    private JButton convertBtn, clearBtn;

    public TemperatureConverterApp() {
        // Frame settings
        setTitle("Temperature Converter");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Components
        JLabel title = new JLabel("Temperature Converter", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel fromLabel = new JLabel("From:");
        JLabel toLabel = new JLabel("To:");
        JLabel inputLabel = new JLabel("Enter Value:");
        JLabel outputLabel = new JLabel("Result:");

        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        fromBox = new JComboBox<>(units);
        toBox = new JComboBox<>(units);

        inputField = new JTextField();
        outputField = new JTextField();
        outputField.setEditable(false);

        convertBtn = new JButton("Convert");
        clearBtn = new JButton("Clear");

        // Add components to frame
        add(title);
        add(new JLabel(""));
        add(fromLabel);
        add(fromBox);
        add(toLabel);
        add(toBox);
        add(inputLabel);
        add(inputField);
        add(outputLabel);
        add(outputField);
        add(convertBtn);
        add(clearBtn);

        // Action listeners
        convertBtn.addActionListener(this);
        clearBtn.addActionListener(e -> {
            inputField.setText("");
            outputField.setText("");
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double input = Double.parseDouble(inputField.getText());
            String from = (String) fromBox.getSelectedItem();
            String to = (String) toBox.getSelectedItem();
            double result = convertTemperature(input, from, to);
            outputField.setText(String.format("%.2f", result));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double convertTemperature(double temp, String from, String to) {
        // Convert 'from' to Celsius first
        double celsius = temp;
        if (from.equals("Fahrenheit")) {
            celsius = (temp - 32) * 5 / 9;
        } else if (from.equals("Kelvin")) {
            celsius = temp - 273.15;
        }

        // Convert Celsius to target unit
        switch (to) {
            case "Fahrenheit": return (celsius * 9 / 5) + 32;
            case "Kelvin": return celsius + 273.15;
            default: return celsius;
        }
    }

    public static void main(String[] args) {
        new TemperatureConverterApp();
    }
}
