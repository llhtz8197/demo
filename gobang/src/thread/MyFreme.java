package thread;

//import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics;
//import java.awt.event.AWTEventListener;
//import java.awt.event.MouseEvent;

import javax.swing.JFrame;
public class MyFreme extends JFrame {
	private static final long serialVersionUID = 1L;
	//static Graphics g; //�½�����
	static int [][] isArray = new int[16][16];  //�������ӵĶ�ά����
	public static void main(String[] args) {
	//isArray[5][5] = 1;                        ��������
		MyFreme myfreme = new MyFreme();     //������Ϸ���ڱ���
		myfreme.start();                     //������Ϸ����
		
	}
	public void start() {
		MyFreme mf = new MyFreme();
		mf.setSize(800, 800);
		//mf.setLocation(200, 200);
		mf.setVisible(true);
		mf.setDefaultCloseOperation(3);
		mf.setLocationRelativeTo(null);
		mf.setBackground(Color.GRAY);
		// ��Ӷ���������Զ������
		MyLisiten ml = new MyLisiten();  
		ml.setG(mf);                        //����ǰ��Ϸ���ڵ����������
		mf.addMouseListener(ml);            // Ϊ��ǰ������Ӽ���
		
	}
	@Override
	public void paint(Graphics g) {
		// TODO �Զ����ɵķ������
//		super.paint(g);
		for (int i = 0; i <=15; i++) {  //����
			//�Ӵ����������»���,(x1,y1,x2,y2)
			//(x1,y1)������ߵ������,(x2,y2)�����ұߵ������
			g.drawLine(40, 40*i+40, 640, 40*i+40); 
			//�����һ������д������   0-15
			g.drawString(""+i, 40*i+33, 660);
			
		}
		for (int j = 0; j <=15; j++) {  //����
			//�Ӵ����������һ���,(x1,y1,x2,y2)
			//(x1,y1)�����ϱߵ������,(x2,y2)�����±ߵ������
			g.drawLine(40*j+40, 40, 40*j+40, 640); 
			//�������һ�����д������   
			g.drawString(""+j,20, 40*j+20);
		}
		//������
		for (int i = 0; i < 15; i++) {
			
			for (int j = 0; j < 15; j++) {
				//����Ϊ1
				if (isArray[i][j]==1 ) {
					int countX = 40*i+20;
					int countY = 40*j+20;
					g.setColor(Color.black);
					g.fillOval(countX, countY, 40, 40);
				}
				//����Ϊ2
				if (isArray[i][j]==2 ) {
					int countX = 40*i+20;
					int countY = 40*j+20;
					g.setColor(Color.red);
					g.fillOval(countX, countY, 40, 40);
				}
			}
		}
	}
}