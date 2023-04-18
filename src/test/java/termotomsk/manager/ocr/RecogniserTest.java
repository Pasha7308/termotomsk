package termotomsk.manager.ocr;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.NumberUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Optional;
import java.util.stream.Stream;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

        var image = new BufferedImage(100, 10, TYPE_INT_RGB);

        var result = recogniser.recognize(image);

        assertEquals(number, result);
    }
}