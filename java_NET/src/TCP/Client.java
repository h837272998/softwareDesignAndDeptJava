package TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		String host = "localhost";
		int port=8888;
		try(Socket s = new Socket(host, port);){

			PrintWriter out = new PrintWriter(s.getOutputStream());
			out.println("jack");
			out.flush();
			s.shutdownOutput();

			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String temp=null;
            String info="";
            while((temp=in.readLine())!=null){
                info+=temp;
               System.out.println("服务端接收到客户端信息："+info+",当前客户端ip为："+s.getInetAddress().getHostAddress());
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

