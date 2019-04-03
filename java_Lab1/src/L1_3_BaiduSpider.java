import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class L1_3_BaiduSpider {
	private static String search;
	public static void main(String[] args) { 
			Scanner sc = new Scanner(System.in);
			System.out.println("����������֣�");
			search = sc.nextLine();
			saveToLocalFile();  
			LinkSpider("pagecontent.txt");
		}  
		public static String getPageContent(String myurl){//�ڴ˵�����ַ����  
	        StringBuffer sb = new StringBuffer();  
	        URL url =null;  
	        Scanner scanner = null;  
	        try {  
	            url = new URL(myurl); 
	            URLConnection conn = url.openConnection();  
	            String str = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.67 Safari/537.36";
	            conn.addRequestProperty("User-Agent", str);
	           //���301 ��302  ��վ�ض�������
	            if(conn.getHeaderField("Location") != null) {
	            	url = new URL(conn.getHeaderField("Location")); 
	            	conn = url.openConnection();  
	            	conn.addRequestProperty("User-Agent", str);
	            }
	            scanner = new Scanner(conn.getInputStream());  
	            while (scanner.hasNextLine()) {  
	                String content = scanner.nextLine();  
	                sb.append(content).append("\r\n");  
	            }  
	        } catch (MalformedURLException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }finally {  
	            scanner.close();  
	        }  
	        return sb.toString();  
	    }
		
		 //����ҳ��������������ı��ļ�  
	    public static void saveToLocalFile(){
	    	String encodeStr = null;
	    	try {
				encodeStr = URLEncoder.encode(search, "utf-8");  //ת��UTF-8
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
	        String pageContent = getPageContent("https://www.baidu.com/s?ie=utf-8&wd="+encodeStr);  
	        PrintWriter pw = null;  
	        try {  
	            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("pagecontent.txt")));  
	            pw.println(pageContent);  
	            pw.flush();  
	        } catch (FileNotFoundException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }finally {  
	            pw.close();  
	        }
	        System.out.println("�����ļ����ݳɹ���");
	    }

	    
	  //������ƥ��
	    public static String parseLink(String line){  
	        StringBuffer sb = new StringBuffer();  
	        Pattern pattern = 
	        		Pattern.compile("href\\s*=\\s*\"[^\\s]*\"\\s*target=\"_blank\"\\s*>.{0,20}<em>[^\\s]{0,36}</em>.{0,20}</a>");
	        Matcher matcher = pattern.matcher(line);
	        
	        while(matcher.find()){
	        	String t = matcher.group();
	        	String temp = "";
	        	Pattern pattern1 = Pattern.compile(">(.{0,20})<em>(.{0,36})</em>(.{0,20})<");
	            Matcher matcher1 = pattern1.matcher(t);  
	            while(matcher1.find()){
	                temp += "���⣺" + matcher1.group(1) + matcher1.group(2) + matcher1.group(3)+"  ";
	            }  
	            Pattern pattern2 = Pattern.compile("\\s*=\\s*\"([^\\s]*)\"\\s*target=\"_blank\"\\s*>");
	            Matcher matcher2 = pattern2.matcher(t);  
	            while(matcher2.find()){
	            	String thet = matcher2.group(1);
	                temp += "���ӣ�" + thet;
	                System.out.println(temp);
	                temp += "\r\n���ݣ�\r\n" + getPageContent(thet);
	            } 
	            sb.append(temp).append("\r\n");  
	        }  
	        return sb.toString();  
	    }  
	    
	    public static void LinkSpider(String fileName){//���·��Ϊpagecontent.txt  
	        StringBuffer sb = new StringBuffer();  
	        Scanner scanner = null;  
	        PrintWriter pw = null;  
	        try {  
	            scanner = new Scanner(new FileInputStream(fileName));  
	            while (scanner.hasNextLine()) {  
	                String content = scanner.nextLine();  
	                sb.append(content);  
	            }  
	            String parseMails = parseLink(sb.toString());  
	            System.out.println("������ַ�ɹ���");
	            pw = new PrintWriter("Link.txt");  
	            pw.println(parseMails);  
	            pw.flush();  
	        } catch (FileNotFoundException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }finally {  
	            pw.close();  
	            scanner.close();  
	        }  
	          
	          
	    }  
}
