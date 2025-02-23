package termotomsk.manager.ocr;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ImageReader {
    public static List<CharImage> loadChars() {
        var filenames = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "comma", "minus");
        return filenames.stream()
                .map(ImageReader::loadChar)
                .toList();
    }

    public static List<CharImage> loadCharsB() {
        var filenames = Arrays.asList("0b", "1b", "2b", "3b", "4b", "5b", "6b", "7b", "8b", "9b", "commab", "minusb");
        return filenames.stream()
                .map(ImageReader::loadChar)
                .toList();
    }

    private static CharImage loadChar(String name) {
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            var resource = classloader.getResourceAsStream("numbers/" + name + ".png");
            if (resource != null) {
                var img = ImageIO.read(resource);
                return CharImage.builder()
                        .name(improveChar(name))
                        .image(img)
                        .build();
            }
        } catch (IOException ignore) {
        }
        return null;
    }

    public static String improveChar(String name) {
        if ("b".equals(name.substring(name.length() - 1))) {
            name = name.substring(0, name.length() - 1);
        }
        return switch (name) {
            case "comma" -> ".";
            case "minus" -> "-";
            default -> name;
        };
    }
}
