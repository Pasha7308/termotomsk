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
        return switch (name) {
            case "comma" -> ".";
            case "minus" -> "-";
            default -> name;
        };
    }
}
