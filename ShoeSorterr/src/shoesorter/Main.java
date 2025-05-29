package shoesorter;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ShoeSorterGUI gui = new ShoeSorterGUI();
            gui.setVisible(true);
        });
    }
}
public static List<Shoe> readShoesFromFiles() {
    List<Shoe> shoes = new ArrayList<>();
    String[] filenames = {"data/input1.txt", "data/input2.txt", "data/input3.txt"};

    for (String filename : filenames) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("N")) continue;

                String[] parts = line.trim().split("\\s+");
                if (parts.length == 4) {
                    int number = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int size = Integer.parseInt(parts[2]);
                    double price = Double.parseDouble(parts[3]);
                    shoes.add(new Shoe(number, name, size, price));
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка при зчитуванні з файлу: " + filename);
            e.printStackTrace();
        }
    }

    return shoes;
}
