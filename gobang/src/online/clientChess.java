package online;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class clientChess {
	//���ѧϰ��Java�Ķ��̺߳������̺��������֪ʶд��һ���򵥵�������������С��Ϸ��
	//�ͻ��˺ͷ���������������ң�ʵ�������塢�ж���Ӯ������Ĺ��ܡ�
	private static myFrame frame=null;
	
	public clientChess(myFrame frame) {
	    this.frame=frame;
	}
	
	public void start() throws Exception{
	    Socket client=new Socket("176.148.23.151",8089);
	
	    final PrintWriter writer=new PrintWriter(client.getOutputStream(),true);
	    java.io.InputStream in=client.getInputStream();
	    new clientThread(client,frame).start();
	    //û�ֳ�ʤ��ʱ����Ҫ������������ӵ�����
	    Scanner sc=new Scanner(System.in);
	
	    new Thread(new Runnable() {
	        String line;
	
	        @Override
	        public void run() {
	            while((line=sc.nextLine())!=null) {
	                //frame.text.setText("�ң�"+line);
	                frame.text.append("�ң�"+line+"\r\n");
	                writer.println("chat:"+line);
	            }
	
	        }
	
	    }).start();
	    new Thread(new Runnable(){
	        @Override
	        public void run() {
	            while(!frame.isWin()) {
	                if(frame.backChess) {
	                    writer.println("back:"+frame.getRow()+":"+frame.getCol());  
	                }else {
	                    writer.println(frame.getRow()+","+frame.getCol());
	                }
	                frame.backChess=false;
	
	            }
	            writer.println("win:"+frame.isWin());
	
	        }
	
	    }).start();	
	}
	
	public static void main(String[] args) throws Exception {
	    myFrame frame=new myFrame();
	    clientChess client=new clientChess(frame);
	    client.start();
	}
}


//�ͻ����̣߳���Ҫ�������շ�������������Ϣ������������
class clientThread extends Thread{
	Socket socket=null;
	myFrame frame=null;
	java.io.InputStream stream=null;
	BufferedReader reader=null;
	PrintWriter writer = null;
	
	public clientThread(Socket socket,myFrame frame) {
		this.socket=socket;
		this.frame=frame;
		
		try {
			this.stream=this.socket.getInputStream();
			this.reader=new BufferedReader(new InputStreamReader(stream));
			this.writer=new PrintWriter(this.socket.getOutputStream(),true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
	    String line=null;
	    try {
	        while((line=this.reader.readLine())!=null) {
	            if(line.contains("ok")){
	                myFrame.occupy=false;
	            }
	            if(line.contains(",")) {
	                String[] data=line.split(",");
	                int row=Integer.parseInt(data[0]);
	                int col=Integer.parseInt(data[1]);
	                if(frame.chess[row][col]==0){
	                    writer.println("ok");
	                    frame.chess[row][col]=2;
	                    frame.repaint();
	                    //writer.println(frame.getRow()+","+frame.getCol());
	                }   
	            }

	            if(line.contains("win:")) {
	                //System.out.println("������");
	                JOptionPane.showMessageDialog(null, "������");
	                frame.repaint();
	            }

	            if(line.contains("chat:")) {
	                String[] data=line.split(":");
	                //frame.text.setText("�Է���"+data[1]);
	                frame.text.append("�Է���"+data[1]+"\r\n");
	            }

	            if(line.contains("back:")) {
	                System.out.println(line);
	                String[] data=line.split(":");
	                int row=Integer.parseInt(data[1]);
	                int col=Integer.parseInt(data[2]);
	                frame.chess[row][col]=0;
	                frame.repaint();
	                frame.setRow(Integer.parseInt(data[1]));
	                frame.setCol(Integer.parseInt(data[2]));

	            }
	        }

	        this.socket.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}


//�ͻ��˽��棺
class myPanel extends JPanel{
	public static int[][] board=new int[16][16];
	
	public myPanel(int[][] board){
		this.board=board; 
	}
	public myPanel() {

	}

	@Override
	public void paint(Graphics g) {
	    g.setColor(new Color(165,185,75));
	    g.fillRect(60, 60, 360, 360);
	    g.setColor(Color.black);
	    //��������
	    for(int i=0;i<15;i++) {
	        g.drawLine(65, 65+i*25, 415, 65+i*25);
	        g.drawLine(65+i*25, 65, 65+i*25, 415);
	    }
	    //��������
	    for(int i=1;i<board.length;i++) {
	        for(int j=1;j<board[i].length;j++) {
	            if(board[i][j]!=0) {
	                if(board[i][j]==1) {
	                    g.setColor(Color.BLACK);
	                }else {
	                    g.setColor(Color.WHITE);
	                }
	                g.fillOval(25*(j+2), 25*(i+2), 20, 20);

	                if(board[i][j]==2) {
	                    g.setColor(Color.BLACK);
	                    g.drawOval(25*(j+2), 25*(i+2), 20, 20);
	                }
	            }   
	        }
	    }
	}
	//��ʼ������
	public boolean addPiece(int row,int col,boolean isBlack) {
	    if(board[row][col]==0) {
	        board[row][col]=isBlack?1:2;
	        return true;
	    }
	    return false;

	}

	public boolean reducePiece(int row,int col) {
	    if(board[row][col]!=0) {
	        board[row][col]=0;
	        return true;
	    }
	    return false;


	}

	public boolean isWin(int row,int col,boolean isBlack) {

	    return checkH(row, col, isBlack) || 
	            checkV(row, col, isBlack) || 
	            checkX1(row, col, isBlack) || 
	            checkX2(row, col, isBlack); 

	}
	//�жϴ����ϵ����µ�б�����Ƿ�����5������
	private boolean checkX2(int row, int col, boolean isBlack) {
	    int counter=1;
	    int currentRow=row;
	    int currentCol=col;
	    int v=isBlack?1:2;
	    if(currentRow>0 && currentCol<14 && board[--currentRow][++currentCol]==v) {
	        counter++;
	    }
	    currentRow=row;
	    currentCol=col;
	    if(currentRow<14 && currentCol>0 && board[++currentRow][--currentCol]==v) {
	        counter++;
	    }
	    return counter>=5;
	}
	//�жϴ����ϵ�����
	private boolean checkX1(int row, int col, boolean isBlack) {
	    int counter=1;
	    int currentRow=row;
	    int currentCol=col;
	    int v=isBlack?1:2;
	    while(currentRow>0 && currentCol>0 && board[--currentRow][--currentCol]==v) {
	        counter++;
	    }
	    currentRow=row;
	    currentCol=col;
	    while(currentRow<14 && currentCol<14 && board[++currentRow][++currentCol]==v) {
	        counter++;
	    }
	    return counter>=5;
	}
	//�ж����ŷ����Ƿ�����5������
	private boolean checkV(int row, int col, boolean isBlack) {
	    int counter=1;
	    int currentRow=row;
	    int currentCol=col;
	    int v=isBlack?1:2;
	    while(currentRow>0 && board[--currentRow][currentCol]==v) {
	        counter++;
	    }
	    currentRow=row;
	    currentCol=col;
	    while(currentRow<14  && board[++currentRow][currentCol]==v) {
	        counter++;
	    }
	    return counter>=5;
	}

	private boolean checkH(int row, int col, boolean isBlack) {

	    int counter=1;
	    int currentRow=row;
	    int currentCol=col;
	    int v=isBlack?1:2;
	    while(currentCol>0 && board[currentRow][--currentCol]==v) {
	        counter++;
	    }
	    currentRow=row;
	    currentCol=col;
	    while(currentCol<14 && board[currentRow][++currentCol]==v) {
	        counter++;
	    }
	    return counter>=5;

	}
}











