package termospring.manager.Downloader;

import termospring.model.Weather;
import termospring.model.ServerValue;

public class DownloadIao extends Download {

    public DownloadIao(Weather weatherIn) {
        super(weatherIn);
    }

    @Override
    public String getUrl()
    {
        return "http://meteo.iao.ru/weather.php?lang=en";
    }

    @Override
    public int getBuferLen() {
        return 1000;
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
        int start = strIn.indexOf("\"temp\":") + "\"temp\":".length();
        int finish = strIn.indexOf(",\"hum\"");
        if ((start == -1) || (finish == -1)) {
            return "";
        }
        String temp = strIn.substring(start, finish);
        return temp;
    }
}
