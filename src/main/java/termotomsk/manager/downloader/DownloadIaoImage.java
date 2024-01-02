package termotomsk.manager.downloader;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import termotomsk.manager.ocr.Recogniser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class DownloadIaoImage {
    private final Recogniser recogniser;

    public String getUrl()
    {
        return "https://lop.iao.ru/graph/tor_now.PNG";
    }

    public Double getIao() {
        return Optional.ofNullable(downloadImage()).map(recogniser::recognize).orElse(0.0);
    }

    private BufferedImage downloadImage() {
        try(var in = new URL(getUrl()).openStream()){
            return ImageIO.read(in);
        } catch (IOException ignore) {
        }
        return null;
    }
}
