package termotomsk.manager.downloader;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Controller;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

@Controller
public class DownloadIaoImage {
    public String getUrl()
    {
        return "https://lop.iao.ru/graph/tor_now.PNG";
    }

    public Image downloadImage() {
        try(var in = new URL(getUrl()).openStream()){
            var image = ImageIO.read(in);
            return image;
        } catch (IOException ignore) {
        }
        return null;
    }

    public String getIao() throws TesseractException {
        var image = downloadImage();

        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("src/main/resources/tessdata");
        tesseract.setLanguage("Cyrillic");
        tesseract.setPageSegMode(1);
        tesseract.setOcrEngineMode(1);
//        tesseract.setConfigs(List.of("digits"));
//        String result = tesseract.doOCR((BufferedImage)image, new Rectangle(116, 1, 57, 17));
        String result = tesseract.doOCR((BufferedImage)image);
        return result;
    }
}
