

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Notepad extends JFrame implements ActionListener {
    private JTextArea textArea;
    private JFileChooser fileChooser;
    
    JMenu m ,m2;
    JMenuItem i1,i2,i3,i4,i5;

    public Notepad() {
        // Set up the main frame
        setTitle("Notepad");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create a menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create a "File" menu
        JMenu fileMenu = new JMenu("File");
        
        m=new JMenu("Edit");
        m2=new JMenu("Close");
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");
        i1=new JMenuItem("Copy");
        i2=new JMenuItem("Paste");
        i3=new JMenuItem("Cut");
        i4=new JMenuItem("Print");
        i5=new JMenu("SelectAll");
        

        newItem.addActionListener(this);
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);
        i1.addActionListener(this);
        i2.addActionListener(this);
        i3.addActionListener(this);
        i4.addActionListener(this);
        i5.addActionListener(this);

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        m2.add(exitItem);
        m.add(i1);
        m.add(i2);
        m.add(i3);
        m.add(i5);
        m.addSeparator();
        m.add(i4);
        menuBar.add(fileMenu);
        menuBar.add(m);
        menuBar.add(m2);

        // Create the text area
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Add components to the frame
        setJMenuBar(menuBar);
        add(scrollPane);

        // Create a file chooser dialog
        fileChooser = new JFileChooser();

        // Display the frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("New")) {
            textArea.setText("");
        } else if (command.equals("Open")) {
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    textArea.setText(sb.toString());
                    reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (command.equals("Save")) {
            int returnVal = fileChooser.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    writer.write(textArea.getText());
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (command.equals("Exit")) {
            System.exit(0);
        }
        else if (e.getSource()==i1)
        	textArea.copy();
        
        else if (e.getSource()==i2)
        	textArea.paste();
        
        else if (e.getSource()==i3 )
        	textArea.cut();
        
        else if (e.getSource()==i5)
        	textArea.selectAll();
        
        else if (e.getSource()==i4)
        	textArea.print(getGraphics());
    }

    public static void main(String[] args) {
        new Notepad();
    }
}