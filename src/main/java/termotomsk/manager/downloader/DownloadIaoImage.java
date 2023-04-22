package termotomsk.manager.downloader;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import termotomsk.manager.ocr.Recogniser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

@Controller
@RequiredArgsConstructor
public class DownloadIaoImage {
    private final Recogniser recogniser;

    public String getUrl()
    {
        return "https://lop.iao.ru/graph/tor_now.PNG";
    }

    public BufferedImage downloadImage() {
        try(var in = new URL(getUrl()).openStream()){
            var image = ImageIO.read(in);
            return image;
        } catch (IOException ignore) {
        }
        return null;
    }

    public Double getIao() {
        var image = downloadImage();

        var result = recogniser.recognize(image);
        return result;
    }
}
