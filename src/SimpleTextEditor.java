//      Abbligale Ried - ID#2301010638
//      Kwame Harriott - ID#2301011566
//      Shemoy Shay -  ID#2201011505
//      Ricardo Wright - ID#2201010833

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class SimpleTextEditor {
    private JPanel mainPanel; // Main panel
    private JTextArea txtArea; // Text area for editing
    private JMenuBar menuBar; // Menu bar used for dropdowns

    // Create and set up frame
    public SimpleTextEditor() {

        JFrame frame = new JFrame("Simple Text Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Initialize the main panel and text area
        mainPanel = new JPanel(new BorderLayout());
        txtArea = new JTextArea();

        // Create the menu bar
        menuBar = new JMenuBar();

        // Create the file menus
        JMenu fileMenu = new JMenu("File");

        // Create menu items
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");

        // Add action listeners to menu items
        openItem.addActionListener(e -> openFile());
        saveItem.addActionListener(e -> saveFile());
       exitItem.addActionListener(e -> System.exit(0));

        // Add items to the file menu
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Add the menus to the menu bar
        menuBar.add(fileMenu);

        // Set the menu bar for the frame
        frame.setJMenuBar(menuBar);

        // Set up the main panel layout
        mainPanel.add(new JScrollPane(txtArea), BorderLayout.CENTER);
        frame.add(mainPanel);

        // set to true for frame visibility
        frame.setVisible(true);
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(mainPanel);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                txtArea.setText(""); // Clear existing text in text field
                String line;
                while ((line = reader.readLine()) != null) {
                    txtArea.append(line + "\n");
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(mainPanel, "Error opening file: " + e.getMessage());
            }
        }
    }

    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(mainPanel);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(txtArea.getText());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(mainPanel, "Error saving file: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimpleTextEditor::new);
    }
}