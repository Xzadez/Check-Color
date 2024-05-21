import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) {
        // Path gambar yang akan diproses
        String imagePath = "assets/gambar_1.jpg";

        try {
            // Membaca gambar
            BufferedImage image = ImageIO.read(new File(imagePath));

            // HashMap untuk menyimpan frekuensi kemunculan warna
            HashMap<Integer, Integer> colorCount = new HashMap<>();

            // Mendapatkan ukuran gambar
            int width = image.getWidth();
            int height = image.getHeight();

            // Menghitung frekuensi kemunculan setiap warna
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int pixel = image.getRGB(x, y);

                    // Memperbarui frekuensi warna dalam HashMap
                    colorCount.put(pixel, colorCount.getOrDefault(pixel, 0) + 1);
                }
            }

            // Menentukan warna dominan
            int dominantColor = findDominantColor(colorCount);

            // Menampilkan hasil
            System.out.println("Warna dominan pada gambar adalah: #" + Integer.toHexString(dominantColor).toUpperCase());
            System.out.println("Jumlah frekuensi warna #" + Integer.toHexString(dominantColor).toUpperCase() + ": " + colorCount.get(dominantColor) + " Pixel");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca gambar: " + e.getMessage());
        }
    }

    // Metode untuk menemukan warna dominan
    public static int findDominantColor(HashMap<Integer, Integer> colorCount) {
        int dominantColor = 0;
        int maxCount = 0;

        for (Map.Entry<Integer, Integer> entry : colorCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                dominantColor = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        return dominantColor;
    }
}
