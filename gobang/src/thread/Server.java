package thread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * �����ҷ����
 * @author JAVA
 *
 */
public class Server {
	/*
	 * java.net.ServerSocket
	 * �����ڷ���˵�ServerSocket��Ҫ����������:
	 * 1:�������������˿�,�ͻ��˾���ͨ��
	 * ����˿������˽������ӵ�
	 * 2:��������˿�,һ���ͻ��������˾ͻ��Զ�����
	 * ����һ��Socketʵ��,ͨ����Socket�Ϳ�����ͻ��˽�����
	 */
	private ServerSocket server;
	private String host;
	
	/*
	 * ������������ClientHandler�ж�Ӧ�ͻ��˵������,
	 * �Ա�㲥��Ϣʹ��
	 */
	private PrintWriter[] allOut = {};
	private int index=0;
	/**
	 * ���췽��,������ʼ�������
	 */
	public Server() {			
		try {
			System.out.println("�������������.......");
			server =new ServerSocket(8088);
			System.out.println("������������");
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
	
	/**
	 * ����˿�ʼ�����ķ���
	 */
	public void start() {
		try {
			/*
			 * Socket accept()
			 * ServerSocket�ṩ�ĸ÷������ڵȴ��ͻ��˵�����,
			 * һ��һ���ͻ��˽�������,�÷��������̷���һ��Socketʵ��,
			 * ͨ����Socket�Ϳ�����ÿͻ��˽�����.
			 * ��ε��ø÷������Եȴ�����ͻ��˵�����
			 */
			while(true) {
			System.out.println("�ȴ��ͷ�������.......");
			Socket socket = server.accept();
			//��ÿͻ��˵�ip��ַ
			host=socket.getInetAddress().getHostAddress();
			//ʵ����ip��ַ�ķ���
			Name name=new Name();
			//��ipת��Ϊ����֮����
			host=name.name(host);
			System.out.println(host+"�ͻ���������");
			//����һ���̴߳���ÿͻ��˽���
			ClientHandler handler = new ClientHandler(socket,host);
			Thread t = new Thread(handler);
			t.start();
			index++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}

	private class ClientHandler implements Runnable{
		
		private Socket socket;
		private String host;
		public ClientHandler(Socket socket,String host) {
			this.socket=socket;
			this.host=host;
		}
		public void run() {
			PrintWriter pw=null;
			try {
				/*
				 * InputStream getInputStream()
				 * ͨ��Socket��ȡ����������ȡ���ֽ�
				 * ��Զ�˼�������͹������ֽ�.
				 */	
			InputStream in = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(in,"utf-8");
			BufferedReader br = new BufferedReader(isr);
			
			/*
			 * ͨ��socket��ȡ�����,���ڸ��ͻ��˷�����Ϣ
			 */
			OutputStream out = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(out,"utf-8");
			BufferedWriter bw = new BufferedWriter(osw);
			pw = new PrintWriter(bw,true);
			/*
			 * �������������allOut�����ڹ���
			 */
			synchronized(allOut) {
				//1.��allOut��������
				allOut = Arrays.copyOf(allOut, allOut.length+1);
				//2.��pw��������
				allOut[allOut.length-1] = pw;
			}
			System.out.println(host+"������,��ǰ��������:"+allOut.length);
			
			String message = null;
				/*
				 * ʹ��BufferedReader��ȡ�ͻ��˷��͹�����һ���ַ���ʱ,
				 * ���ͻ��˶Ͽ�����,��ʱ�ͻ��˵�ϵͳ��ͬʱ,��Ӧͨ����ͬ:
				 * ��windows�Ŀͻ��˶Ͽ�ʱ,readLine����ͨ����ֱ���׳�SockeException
				 * ��linux�Ŀͻ��˶Ͽ�ʱ,readLine�����᷵��nullֵ
				 */
				while((message = br.readLine())!=null) { 
				System.out.println(host+"˵:"+message);
				
				synchronized(allOut) {
					for(int i=0;i<allOut.length;i++)
						//����allOut,����Ϣ���͸����пͻ���
						allOut[i].println(host+"˵:"+message);
				}
				}
			} catch (Exception e) {
				
			}finally {
				//���ﴦ��ͻ��˶Ͽ����Ӻ�Ĳ���
				synchronized(allOut) {
				//���ÿͻ��˵��������allOut��ɾ��
					for(int i=0;i<allOut.length;i++) {
						if(allOut[i]==pw) {
							allOut[i]=allOut[allOut.length-1];
							allOut = Arrays.copyOf(allOut, allOut.length-1);
							break;
						}
					}
				}
				System.out.println(host+"������,��ǰ��������:"+allOut.length);
				try {
					socket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	};
}
