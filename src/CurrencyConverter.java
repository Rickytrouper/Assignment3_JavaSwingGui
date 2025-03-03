import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyConverter {
    private JPanel mainPanel;
    private JTextField txtInputValue; // Input field for amount
    private JComboBox<String> cbCurrencyType; // Combo box for currency selection
    private JTextField JmdAmount; // Output field for converted amount
    private JButton btnConvert; // Button for conversion
    private JButton btnClear; // Button to clear fields
    private JLabel jmdAmount; // Label for JMD amount
    private JLabel JlCurrencyType; // Label for currency type
    private JLabel JlInputValue; // Label for input value

    // initializing  teh frame size
    public CurrencyConverter() {
        JFrame frame = new JFrame("Currency Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 250);

        // Initialize components
        mainPanel = new JPanel();
        txtInputValue = new JTextField(10);
        String[] currencies = {"US Dollar", "Canadian Dollar", "Euro"};
        cbCurrencyType = new JComboBox<>(currencies);
        JmdAmount = new JTextField(10);
        JmdAmount.setEditable(false); // setting to false preventing teh Output field from been edited
        btnConvert = new JButton("Convert");
        btnClear = new JButton("Clear");

        // Initialize labels
        jmdAmount = new JLabel("JMD Amount $:");
        JlCurrencyType = new JLabel("Currency Type:");
        JlInputValue = new JLabel("Input Amount $:");

        // Set up layout
        mainPanel.add(JlInputValue);
        mainPanel.add(txtInputValue);
        mainPanel.add(JlCurrencyType);
        mainPanel.add(cbCurrencyType);
        mainPanel.add(jmdAmount);
        mainPanel.add(JmdAmount);
        mainPanel.add(btnConvert);
        mainPanel.add(btnClear);

        // Add action listeners for the converter and teh clear button
        btnConvert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        // Add the main panel to the frame
        frame.add(mainPanel);
        frame.setVisible(true);// set to true for frame visibility
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(txtInputValue.getText()); // converting the string input to a double
            double convertedAmount = 0.0;
            String selectedCurrency = (String) cbCurrencyType.getSelectedItem();

            switch (selectedCurrency) {
                case "US Dollar":
                    convertedAmount = amount * 157.57; // USD conversion rate
                    break;
                case "Canadian Dollar":
                    convertedAmount = amount * 110.14; //  Canadian conversion rate
                    break;
                case "Euro":
                    convertedAmount = amount * 165.41; // Euro conversion rate
                    break;
            }

            JmdAmount.setText(String.format("%.2f JMD", convertedAmount)); // format the output to 2 decimal place
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(mainPanel, "Please enter a valid number.");
        }
    }

    private void clearFields() {
        txtInputValue.setText(""); // Clearing the  input field
        JmdAmount.setText(""); // Clear teh output field
        cbCurrencyType.setSelectedIndex(0); // Reset the selection field
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CurrencyConverter());
    }
}