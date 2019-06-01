package termotomsk.manager.Downloader;

import termotomsk.model.Weather;
import termotomsk.model.ServerValue;

import java.io.*;
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
            String strIn = readIt(is);
            strRet = processIt(strIn);
            conn.disconnect();
            if (is != null) {
                is.close();
            }
        } catch (IOException E) {
            strRet = "";
        }
        if (!strRet.isEmpty()) {
            double dRet = Double.parseDouble(strRet);
            int iRet = (int) ((dRet * 10) + 0.5);
            getServer().setTemp(iRet);
            getServer().setUpdated(OffsetDateTime.now());
            getServer().setActual(true);
        }
    }

    private String readIt(
        InputStream stream)
        throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    abstract public String processIt(
        String strIn);

    abstract public String getUrl();

    abstract public ServerValue getServer();
}
