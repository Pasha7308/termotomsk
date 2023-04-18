package termotomsk.manager.ocr;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.image.BufferedImage;
import java.io.File;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RecogniserTest {
    @Autowired
    private Recogniser recogniser;

    @Test
    void recognize() {
        File folder = new File(System.getProperty("user.dir") + "/src/test/resources/testImages");
        File[] files = folder.listFiles();

        assertNotNull(files);
        assertEquals(files.length, 26);

        var image = new BufferedImage(100, 10, TYPE_INT_RGB);

        var result = recogniser.recognize(image);

        assertNull(result);
    }
}