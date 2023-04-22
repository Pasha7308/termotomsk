package termotomsk.manager.ocr;

import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Service
public class Recogniser {
    private final List<CharImage> chars = new ArrayList<>();

    public Recogniser() {
        ImageReader.loadChars(chars);
    }

    public List<CharImage> getChars() {
        return chars;
    }

    public Double recognize(BufferedImage image) {
        chars.forEach(it -> findChar(it, image));
        return null;
    }

    private void findChar(CharImage charImage, BufferedImage image) {

    }
}
