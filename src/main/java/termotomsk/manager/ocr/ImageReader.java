package termotomsk.manager.ocr;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ImageReader {
    public static void loadChars(List<CharImage> chars) {
        File folder = new File(System.getProperty("user.dir") + "/src/main/resources/numbers");
        File[] files = folder.listFiles();

        if (files != null) {
            Arrays.stream(files).forEach(it -> loadChar(it, chars));
        }
    }

    private static void loadChar(File file, List<CharImage> chars) {
        var name = getNameWithoutExtension(file.getName());
        try {
            var img = ImageIO.read(file);
            chars.add(CharImage.builder().name(name).image(img).build());
        } catch (IOException ignore) {
        }
    }

    public static String getNameWithoutExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
    }

}
