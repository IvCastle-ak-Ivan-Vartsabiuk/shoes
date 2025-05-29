package shoesorter;

import java.io.*;
import java.util.*;

public class ShoeProcessor {

    public static List<Shoe> readShoesFromFiles() {
        List<Shoe> shoes = new ArrayList<>();
        String[] filenames = {"data/input1.txt", "data/input2.txt", "data/input3.txt"};

        for (String filename : filenames) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Пропускаємо заголовки
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

    public static void writeShoesToFile(List<Shoe> shoes, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("N Shoes Size Price");
            for (Shoe s : shoes) {
                writer.printf("%d %s %d %.2f%n", s.getNumber(), s.getName(), s.getSize(), s.getPrice());
            }
        } catch (IOException e) {
            System.out.println("Помилка запису у файл: " + filename);
            e.printStackTrace();
        }
    }

    public static void processShoes(List<Shoe> allShoes, int sortOption) {
        List<Shoe> small = new ArrayList<>();
        List<Shoe> medium = new ArrayList<>();
        List<Shoe> large = new ArrayList<>();

        for (Shoe s : allShoes) {
            if (s.getSize() <= 36) {
                small.add(s);
            } else if (s.getSize() <= 40) {
                medium.add(s);
            } else {
                large.add(s);
            }
        }

        Comparator<Shoe> comparator = getComparator(sortOption);
        small.sort(comparator);
        medium.sort(comparator);
        large.sort(comparator);

        writeShoesToFile(small, "output_small.txt");
        writeShoesToFile(medium, "output_medium.txt");
        writeShoesToFile(large, "output_large.txt");
    }

    private static Comparator<Shoe> getComparator(int option) {
        return switch (option) {
            case 0 -> Comparator.comparing(Shoe::getName)
                    .thenComparing(Shoe::getSize)
                    .thenComparing(Shoe::getPrice);
            case 1 -> Comparator.comparing(Shoe::getSize)
                    .thenComparing(Shoe::getName)
                    .thenComparing(Shoe::getPrice);
            case 2 -> Comparator.comparing(Shoe::getName)
                    .thenComparing(Shoe::getPrice)
                    .thenComparing(Shoe::getSize);
            default -> throw new IllegalArgumentException("Невірна опція сортування");
        };
    }
}
