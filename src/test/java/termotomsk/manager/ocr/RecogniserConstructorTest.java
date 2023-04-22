package termotomsk.manager.ocr;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RecogniserConstructorTest {
    @Autowired
    private Recogniser recogniser;

    @Test
    void recognize() {
        var chars = recogniser.getChars();
        assertNotNull(chars);
        assertEquals(12, chars.size());
    }
}