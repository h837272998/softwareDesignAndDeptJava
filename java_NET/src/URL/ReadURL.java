package URL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ReadURL {
    public static void main(String[] args) throws IOException {
        String spec = "http://jskx.zhku.edu.cn/info/1076/1637.html";
        URL url = new URL(spec);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        url.openStream(),"GBK"
                )
        );
        String inputLine;
        while ((inputLine=in.readLine())!=null){
            System.out.println(inputLine);
        }
        in.close();
    }
}
