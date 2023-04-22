package termotomsk.manager.ocr;

import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
        Map<Integer, String> charPositions = new TreeMap<>();
        var subImage = image.getSubimage(120, 3, 50, 12);
        chars.forEach(it -> findChar(it, subImage, charPositions));
        StringBuilder ret = new StringBuilder();
        for (Map.Entry<Integer, String> entry : charPositions.entrySet()) {
            ret.append(entry.getValue());
        }
        return NumberUtils.parseNumber(ret.toString(), Double.class);
    }

    private void findChar(CharImage charImage, BufferedImage image, Map<Integer, String> charPositions) {
        var searchImage = charImage.getImage();
        var charWidth = searchImage.getWidth();
        var charHeight = charImage.getImage().getHeight();
        var imageWidth = image.getWidth();

        for (int scanWindow = 0; scanWindow < imageWidth - charWidth; scanWindow++) {
            var subImage = image.getSubimage(scanWindow, 0, charWidth, charHeight);
            var subImageDeep = new BufferedImage(subImage.getColorModel(),
                    subImage.getRaster().createCompatibleWritableRaster(charWidth, charHeight),
                    subImage.isAlphaPremultiplied(),
                    null);
            subImage.copyData(subImageDeep.getRaster());
            if (compareImages(searchImage, subImageDeep)) {
                charPositions.put(scanWindow, charImage.getName());
            }
        }
    }

    private boolean compareImages(BufferedImage searchImage, BufferedImage subImage) {
        var searchImageBuf = searchImage.getData().getDataBuffer();
        var subImageBuf = subImage.getData().getDataBuffer();
        if (searchImageBuf.getSize() != subImageBuf.getSize()) {
            return false;
        }
        for (int i = 0; i < searchImageBuf.getSize(); i++) {
            if(searchImageBuf.getElem(i) != subImageBuf.getElem(i)) {
                return false;
            }
        }
        return true;
    }
}
