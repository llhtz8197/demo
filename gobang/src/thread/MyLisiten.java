package thread;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyLisiten implements MouseListener{
	public MyFreme mf;
	int turn = 1; // 1��ʾ�ú��������� 2��ʾ��������
	int end = 0; //��Ϸ�����ж� end=��5��ʱ��Ϸ����
	int arrX,arrY;// ��¼����������Ӧ������λ��
	
	public void setG(MyFreme mf) {
		this.mf = mf;
	}
//public int gameCompleteUP(int arrX,int arrY) {
////�ж���Ϸ�Ƿ����
//if(arrY<=15 && end<=5) {
//if(mf.isArray[arrX][arrY] == 1) {
//end++;
//}
//int up = gameCompleteUP(arrX,arrY+1);
//		int down = gameCompleteUP(arrX,arrY-1);
//		end = up+down;	
//	}
//	return end;
//		
//}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO �Զ����ɵķ������
		int x = e.getX();               //��ȡ�����������
		int y = e.getY();
		
		// ����λ�÷�Χ���ж�  ����λ�������Ϊ�����λ��
		if(x%40<=20 && y%40<=20) {
			arrX = x/40-1;
			arrY = y/40-1;
		}else if (x%40>20 && y%40<=20) {
			arrX = x/40;
			arrY = y/40-1;
		}else if (x%40<20 && y%40>20) {
			arrX = x/40-1;
			arrY = y/40;
		}else {
			arrX = x/40;
			arrY = y/40;
		}
		if(mf.isArray[arrX][arrY]!= 0) {
			System.out.println("���λ���Ѿ���������,�뻻��λ�ã�");
		}else {
			Graphics g = mf.getGraphics();
			System.out.println(arrX + "  " +arrY); // ���������Ƿ�ʵ���������Ĺ���
			
			if(turn == 1) {    //��������
				int countX = 40*arrX+20;
				int countY = 40*arrY+20;
				g.setColor(Color.black);
				g.fillOval(countX, countY, 40, 40);
				mf.isArray[arrX][arrY] = 1;
				//gameCompleteUP(arrX, arrY);
				if(end>=5) {
					System.out.println("���ӻ�ʤ��");
					return;
				}
				
				turn++;
			}else {           //��������
				int countX = 40*arrX+20;
				int countY = 40*arrY+20;
				g.setColor(Color.red);
				g.fillOval(countX, countY, 40, 40);
				mf.isArray[arrX][arrY] = 2;
				turn--;
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}
	//Ϊ�������ṩ��������X�ķ���
	public int getArrX() {
		return arrX;
	}
	//Ϊ�������ṩ��������y�ķ���
	public int getArrY() {
		return arrY;
	}

	
}
