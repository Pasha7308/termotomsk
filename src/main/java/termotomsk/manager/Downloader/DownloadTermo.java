package termotomsk.manager.Downloader;

import termotomsk.model.Weather;
import termotomsk.model.ServerValue;

public class DownloadTermo extends Download {

    private final String tStart = "Погода в Томске</h4>                <h2>";
    private final String tEnd = " &deg; C  <i";

    public DownloadTermo(Weather weatherIn) {
        super(weatherIn);
    }

    @Override
    public String getUrl()
    {
        return "http://termopogoda.ru/tomsk/";
    }

    @Override
    public ServerValue getServer()
    {
        return weather.getServerTermo();
    }

    @Override
    @SuppressWarnings("UnnecessaryLocalVariable")
    public String processIt(
            String strIn)
    {
    /*
 	    <div class="col-md-4 col-md-push-4">
            <div class="service-block-inner blue service-block-height">
                <h4><i class="fa fa-info-circle"></i> Погода в Томске</h4>
                <h2>+8.5 ° C  <i class="fa fa-angle-up" title="Повышение температуры"></i></h2>
            </div>
        </div>
     */
        if (strIn.length() < 500) {
            return "Wrong answer";
        }
        int start = strIn.indexOf(tStart);
        int finish = strIn.indexOf(tEnd);
        if ((start == -1) || (finish == -1)) {
            return "";
        }
        String temp = strIn.substring(start + tStart.length(), finish);
        return temp;
    }
}
