package shoesorter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ShoeSorterGUI extends JFrame implements ActionListener {
    private JComboBox<String> sortOptions;
    private JButton processButton;

    public ShoeSorterGUI() {
        setTitle("Сортування взуття");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        sortOptions = new JComboBox<>(new String[]{
                "По назві, потім розмір, потім ціна",
                "По розміру, потім назві, потім ціна",
                "По назві, потім ціні, потім розмір"
        });

        processButton = new JButton("Сортувати");
        processButton.addActionListener(this);

        JPanel panel = new JPanel();
        panel.add(sortOptions);
        panel.add(processButton);
        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selected = sortOptions.getSelectedIndex();
        List<Shoe> allShoes = ShoeProcessor.readShoesFromFiles();
        ShoeProcessor.processShoes(allShoes, selected);
        JOptionPane.showMessageDialog(this, "Файли створено успішно!");
    }
}
