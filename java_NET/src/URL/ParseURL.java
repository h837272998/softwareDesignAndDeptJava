package URL;

import java.net.URL;

public class ParseURL {
    public static void main(String[] args) throws  Exception{
        URL url = new URL("http://java.sun.com:80/docs/books/\"\n" +
                "+ \"tutorial/index.html#DOWNLOADING");
        System.out.println("协议：" + url.getProtocol());
        System.out.println("主机：" + url.getHost());
        System.out.println("端口：" + url.getPort());
        System.out.println("文件名：" + url.getFile());
        System.out.println("引用：" + url.getRef());
        System.out.println("默认端口：" + url.getDefaultPort());
    }
}
