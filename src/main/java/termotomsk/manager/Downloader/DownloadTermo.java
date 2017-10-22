package termotomsk.manager.Downloader;

import termotomsk.model.Weather;
import termotomsk.model.ServerValue;

public class DownloadTermo extends Download {

    public DownloadTermo(Weather weatherIn) {
        super(weatherIn);
    }

    @Override
    public String getUrl()
    {
        return "http://termo.tomsk.ru/data.xml";
    }

    @Override
    public int getBuferLen() {
        return 1000;
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
     * 	     	<current temp="-21.0" date="28.01.2013" time="21:19" change="-"/>
     */
        if (strIn.length() < 500) {
            return "Wrong answer";
        }
        int start = strIn.indexOf("<current temp=\"") + "<current temp=\"".length();
        int finish = strIn.indexOf("\" date=\"");
        if ((start == -1) || (finish == -1)) {
            return "";
        }
        String temp = strIn.substring(start, finish);
        return temp;
    }
}
