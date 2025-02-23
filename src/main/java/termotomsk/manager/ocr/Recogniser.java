package termotomsk.manager.ocr;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Getter
@Slf4j
@Service
public class Recogniser {
    private final List<CharImage> chars;
    private final List<CharImage> charsB;

    public Recogniser() {
        chars = ImageReader.loadChars();
        charsB = ImageReader.loadCharsB();
        log.info("Recognition templates loaded: {}", chars.size() + charsB.size());
    }

    public Double recognize(BufferedImage image) {
        var small = recognizeSmall(image);
        return small != null ? small : recognizeBig(image);
    }

    public Double recognizeSmall(BufferedImage image) {
        Map<Integer, String> charPositions = new TreeMap<>();
        var subImage = image.getSubimage(120, 3, 50, 12);
        chars.forEach(it -> findChar(it, subImage, charPositions));
        StringBuilder ret = new StringBuilder();
        for (Map.Entry<Integer, String> entry : charPositions.entrySet()) {
            ret.append(entry.getValue());
        }
        var str = ret.toString();
        return str.isEmpty() ? null : NumberUtils.parseNumber(str, Double.class);
    }

    public Double recognizeBig(BufferedImage image) {
        Map<Integer, String> charPositions = new TreeMap<>();
        var subImage = image.getSubimage(362, 6, 88, 21);
        charsB.forEach(it -> findChar(it, subImage, charPositions));
        StringBuilder ret = new StringBuilder();
        for (Map.Entry<Integer, String> entry : charPositions.entrySet()) {
            ret.append(entry.getValue());
        }
        var str = ret.toString();
        return str.isEmpty() ? null : NumberUtils.parseNumber(str, Double.class);
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
