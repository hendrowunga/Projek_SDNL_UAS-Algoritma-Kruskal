package Projek_Akhir;

import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class KruskalGUI extends JFrame implements ActionListener {

    private JButton buttonAdd, buttonLihat, buttonMST;
    private JTextPane area;
    JTextField source, destinasion, weight;
    JTextPane textPane;
    private ArrayList<Edge> edges;

    JFrame form;

    public static void main(String[] args) {
        new KruskalGUI().initComponent();
    }

    public void initComponent() {
        edges = new ArrayList<>(Arrays.asList(
                new Edge("Alice", "Bob", 118),
                new Edge("Alice", "David", 71),
                new Edge("Bob", "Carol", 99),
                new Edge("Bob", "David", 140),
                new Edge("Bob", "Frank", 170),
                new Edge("Bob", "Alice", 118),
                new Edge("Carol", "Frank", 120),
                new Edge("Carol", "Bob", 99),
                new Edge("David", "Grace", 97),
                new Edge("David", "Eve", 85),
                new Edge("David", "Alice", 71),
                new Edge("David", "Bob", 140),
                new Edge("Eve", "Frank", 80),
                new Edge("Eve", "Isabel", 146),
                new Edge("Eve", "David", 85),
                new Edge("Eve", "Grace", 56),
                new Edge("Frank", "Isabel", 111),
                new Edge("Frank", "Eve", 80),
                new Edge("Frank", "Bob", 170),
                new Edge("Frank", "Carol", 120),
                new Edge("Grace", "Henry", 65),
                new Edge("Grace", "Olivia", 70),
                new Edge("Grace", "Eve", 56),
                new Edge("Grace", "David", 97),
                new Edge("Isabel", "Liam", 47),
                new Edge("Isabel", "Eve", 146),
                new Edge("Isabel", "Frank", 111),
                new Edge("Liam", "Isabel", 47),
                new Edge("Liam", "Henry", 146),
                new Edge("Liam", "Jack", 70),
                new Edge("Henry", "Grace", 65),
                new Edge("Henry", "Liam", 146),
                new Edge("Jack", "Liam", 70),
                new Edge("Jack", "Noah", 92),
                new Edge("Jack", "Haland", 151),
                new Edge("Noah", "Olivia", 35),
                new Edge("Noah", "Jack", 92),
                new Edge("Olivia", "Noah", 35),
                new Edge("Olivia", "Grace", 70),
                new Edge("Olivia", "Mia", 211),
                new Edge("Haland", "Jack", 151),
                new Edge("Haland", "Mia", 86),
                new Edge("Mia", "Haland", 86),
                new Edge("Mia", "Olivia", 211)
        ));

        JFrame form = new JFrame("Biaya Minimum untuk  Pemasangan Kabel Internet");
        form.setExtendedState(JFrame.MAXIMIZED_BOTH);
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setLocationRelativeTo(null);
        form.setLayout(null);

        textPane = new JTextPane();
        textPane.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setBounds(10, 20, 1000, 450);
        form.add(scrollPane);
        buttonLihat = new JButton("Lihat Graph");
        buttonLihat.setBounds(1100, 300, 150, 30);
        buttonLihat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icon/i.jpg"));
                Image image = icon.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(image);

                // Mendapatkan StyledDocument dari JTextPane
                StyledDocument doc = textPane.getStyledDocument();

                // Membuat style baru untuk gambar
                Style style = doc.addStyle("ImageStyle", null);
                StyleConstants.setIcon(style, scaledIcon);

                // Menyisipkan gambar ke dalam JTextPane
                try {
                    doc.insertString(doc.getLength(), "Placeholder text", style);
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        });

        form.add(buttonLihat);

        JLabel Source = new JLabel("Source Vertex");
        Source.setFont(new Font("Serif", Font.PLAIN, 17));
        Source.setBounds(20, 470, 200, 30);
        source = new JTextField();
        source.setBounds(200, 470, 200, 30);
        form.add(source);

        JLabel Destinasion = new JLabel("Destiansion Vertex");
        Destinasion.setFont(new Font("Serif", Font.PLAIN, 17));
        Destinasion.setBounds(20, 520, 200, 30);
        destinasion = new JTextField();
        destinasion.setBounds(200, 520, 200, 30);
        form.add(destinasion);

        JLabel labelweight = new JLabel("Edge ");
        labelweight.setFont(new Font("Serif", Font.PLAIN, 17));
        form.add(labelweight);
        labelweight.setBounds(20, 570, 200, 30);
        weight = new JTextField();
        weight.setBounds(200, 570, 200, 30);
        form.add(weight);

        JLabel labelJalurTerpendek = new JLabel("Jalur Terpendek");
        labelJalurTerpendek.setFont(new Font("Serif", Font.PLAIN, 17));
        labelJalurTerpendek.setBounds(1100, 440, 200, 30);
        area = new JTextPane();
        area.setBounds(1100, 470, 650, 530);
        area.setEditable(false);
        JScrollPane Pane = new JScrollPane(area);
        Pane.setBounds(1100, 470, 650, 530);
        form.add(Pane);
        buttonAdd = new JButton("Add Edge");
        buttonAdd.setBounds(200, 680, 90, 30);
        buttonAdd.addActionListener(this);
        form.add(buttonAdd);

        buttonMST = new JButton("FIND MST");
        buttonMST.setBounds(350, 680, 90, 30);
        form.add(buttonMST);
        buttonMST.addActionListener(this);
        form.add(Source);
        form.add(Destinasion);
        form.add(labelJalurTerpendek);
        form.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Add Edge")) {
            try {
                String src = source.getText();
                String dest = destinasion.getText();
                int bobot = Integer.parseInt(weight.getText());

                boolean edgeExists = false;
                for (Edge edge : edges) {
                    if (edge.getSource().equals(src) && edge.getDestination().equals(dest)) {
                        edgeExists = true;
                        break;
                    }
                }

                if (!edgeExists) {
                    edges.add(new Edge(src, dest, bobot));
                    source.setText("");
                    destinasion.setText("");
                    weight.setText("");
                    Collections.sort(edges); // Urutkan kembali edges setelah penambahan baru
                    displayEdges(); // Tampilkan ulang edges yang sudah ditambahkan
                } else {
                    JOptionPane.showMessageDialog(this, "Data sudah ada dalam daftar edge!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Masukan tidak valid! Silakan masukkan bilangan bulat untuk edge");
            }
        } else if (e.getActionCommand().equals("FIND MST")) {
            Graph g = new Graph(edges.size());
            for (Edge edge : edges) {
                g.addEdge(edge.getSource(), edge.getDestination(), edge.getWeight());
            }
            ArrayList<Edge> mst = g.calculateMST(edges);
            displayMST(mst);
        }
    }

    private void displayMST(ArrayList<Edge> mst) {
        StringBuilder output = new StringBuilder("Minimum Spanning Tree (MST):\n\n");
        for (Edge edge : mst) {
            output.append(edge.getSource()).append("     ----->     ").append(edge.getDestination()).append("  :  ").append(edge.getWeight()).append("   KM\n\n");
        }

        int totalWeight = new Graph().calculateTotalWeight(mst);
        output.append("Total Jarak Minimum Spanning Tree      : ").append(totalWeight).append("  KM\n");
        int totalCost = new Graph().calculateTotalCost(mst);
        // Mengubah format uang dengan separator ribuan (koma)
        String formattedCost = String.format("%,d", totalCost);
        output.append("Total Biaya Minimum Spanning Tree  Rp. : ").append(formattedCost).append("\n");

        area.setText(output.toString());
    }

    private void displayEdges() {
        StringBuilder output = new StringBuilder();
        for (Edge edge : edges) {
            output.append(edge.getSource()).append(" - ").append(edge.getDestination()).append(": ").append(edge.getWeight()).append("\n");
        }
        area.setText(output.toString());
    }

}
