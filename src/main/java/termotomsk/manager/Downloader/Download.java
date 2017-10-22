package termotomsk.manager.Downloader;

import termotomsk.model.Weather;
import termotomsk.model.ServerValue;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.OffsetDateTime;

public abstract class Download implements Runnable {
    Weather weather;

    Download(Weather weatherIn) {
        weather = weatherIn;
    }

    @Override
    public void run() {
        String strRet;
        try {
            URL url = new URL(getUrl());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000); // milliseconds
            conn.setConnectTimeout(10000); // milliseconds
            InputStream is = conn.getInputStream();
            String strIn = readIt(is, getBuferLen());
            strRet = processIt(strIn);
            conn.disconnect();
            if (is != null) {
                is.close();
            }
        } catch (IOException E) {
            strRet = "";
        }
        if (!strRet.isEmpty()) {
            Double dRet = Double.parseDouble(strRet);
            int iRet = (int) ((dRet * 10) + 0.5);
            getServer().setTemp(iRet);
            getServer().setUpdated(OffsetDateTime.now());
            getServer().setActual(true);
        }
    }

    private String readIt(
        InputStream stream,
        int len)
        throws IOException
    {
        Reader reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        int read = reader.read(buffer);
        if (read > 0) {
            return new String(buffer);
        } else {
            return "";
        }
    }

    abstract public String processIt(
        String strIn);

    abstract public String getUrl();

    abstract public int getBuferLen();

    abstract public ServerValue getServer();
}
