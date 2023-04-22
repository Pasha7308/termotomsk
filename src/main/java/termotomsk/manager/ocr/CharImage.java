package termotomsk.manager.ocr;

import lombok.Builder;
import lombok.Data;

import java.awt.image.BufferedImage;

@Data
@Builder
public class CharImage {
    private String name;
    private BufferedImage image;
}
