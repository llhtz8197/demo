package thread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * �����ҿͷ���
 * @author JAVA
 *
 */
public class Client {
	/*
	 * java.net.Socket  �׽���
	 * Socket��װ��TCPͨѶ��ϸ��,ʹ������ʹ��
	 * TCPЭ������̨�����(�ͻ��˳��������˳���)
	 * ֮��ͨѶ�����Ķ�д�������.  
	 */
	private Socket socket;
	
	private MyLisiten ml;
	/**
	 * ���췽��,������ʼ���ͻ���
	 */
	public Client() {
		try {
			/*
			 * ʵ����Socket�Ĺ��̾�����������˽������ӵĹ���
			 * ������Ҫ������������:
			 * 1:����˵�ַ��Ϣ(IP��ַ)
			 * 2:����˴򿪵Ķ˿�
			 * ����ͨ��IP�����ҵ������ϵķ�������ڵļ����,
			 * ͨ���˿ڿ����ҵ������ڸû����ϵķ����Ӧ�ó���
			 * 
			 */
			System.out.println("�������ӷ����........");
			socket =new Socket("localhost",8088);//localhostΪ����ip
			System.out.println("�����ӷ����");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �ͻ��˿�ʼ�����ķ���
	 */
	public void start() {
		try {
			//����������ȡ�������Ϣ���߳�
			ServerHandler handler = new ServerHandler();
			Thread t = new Thread(handler);
			t.start();
			/*
			 * OutputStream getOutputStream()
			 * ͨ��Socket��ȡһ�������,
			 * ͨ����������д�����ֽڻᷢ�͸�Զ�˼����
			 */
			OutputStream out = socket.getOutputStream();
			OutputStreamWriter osw =new OutputStreamWriter(out,"utf-8");
			BufferedWriter bw = new BufferedWriter(osw);
			PrintWriter pw = new PrintWriter(bw,true);
			
			//��ȡ���ӵ�����λ��,��������������
			ml.getArrX();
			ml.getArrY();
			Scanner scan = new Scanner(System.in);
			while(true) {
			String str = scan.nextLine();
			pw.println(str);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		client.start();
	}
	/**
	 * ���̸߳���ѭ����ȡ����
	 * @author JAVA
	 *
	 */
	private class ServerHandler implements Runnable{
		public void run(){
			try {
				/*
				 * ͨ��socket��ȡ������,��ȡ����˷��͹�������Ϣ
				 */
				InputStream is= socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(is,"utf-8");
				BufferedReader br = new BufferedReader(isr);
				
				String message = null;
				while((message = br.readLine())!=null) {
					System.out.println(message);
				}
			} catch (Exception e) {
			}
		}
}

}










