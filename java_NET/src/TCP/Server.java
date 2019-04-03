package TCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    final static String doccBase = "C:\\Users\\HJH\\Desktop";

    public static void main(String[] args) {
        int port = 8888;
        Socket s = null;
        try {
            ServerSocket ss= new ServerSocket(port);
            System.out.println("Server is ready,listening in port:"+port+"....");
            while (true){
                s = ss.accept();
                BufferedReader in  = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String info = "";
                String request = "";
                if ((request = in.readLine())!=null){
                    info += request;
                    System.out.println("server receive: "+request);
                }
                PrintWriter out = new PrintWriter(s.getOutputStream());
//                if (isHtmlRequest(request)){
//                    String fileContent = read(request.trim());
//                    out.println(fileContent);
//                }
                out.println("Hello "+info);
                out.flush();
                s.shutdownInput();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

//    private static boolean isHtmlRequest(String request) {
//        return  request.endsWith(".html");
//    }
//
//    private static String read(String request){
//        try {
//            BufferedReader b = new BufferedReader(new FileReader(doccBase+request));
//            String line = null;
//            String resule = "";
//            while ((line=b.readLine())!=null)
//                resule +=line;
//            return resule;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
