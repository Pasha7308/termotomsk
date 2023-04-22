package termotomsk.manager.ocr;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.NumberUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RecogniserTest {
    @Autowired
    private Recogniser recogniser;

    private static Stream<File> provideStringsForIsBlank() {
        File folder = new File(System.getProperty("user.dir") + "/src/test/resources/testImages");
        File[] files = folder.listFiles();

        return Optional.ofNullable(files).map(Stream::of).orElse(Stream.<File>builder().build());
    }

    @ParameterizedTest
    @MethodSource("provideStringsForIsBlank")
    void recognize(File file) {
        var name = file.getName();
        var numberPart = name.substring(7, 12);
        var number = NumberUtils.parseNumber(numberPart, Double.class);

        BufferedImage image;
        try {
            image = ImageIO.read(file);
        } catch (IOException ignore) {
            image = null;
        }

        assertNotNull(image);

        var result = recogniser.recognize(image);

        assertEquals(number, result);
    }
}