package termotomsk.manager.downloader;

import termotomsk.model.ServerValue;
import termotomsk.model.Weather;

public class DownloadIao extends Download {

    private final String tStart = "\"temp\":";
    private final String tEnd = ",\"hum\"";

    public DownloadIao(Weather weatherIn) {
        super(weatherIn);
    }

    @Override
    public String getUrl()
    {
        return "https://meteo.iao.ru/weather.php?lang=en";
    }

    @Override
    public ServerValue getServer()
    {
        return weather.getServerIao();
    }

    @Override
    @SuppressWarnings("UnnecessaryLocalVariable")
    public String processIt(
            String strIn)
    {
    /*
     * 	     {"datetime":1440342000,"temp":18,"hum":38,"press":742,"wind_s":2.5,"wind_d":"w","dewp":3.4}
     */
        if (!strIn.contains("datetime") || !strIn.contains("temp")) {
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
