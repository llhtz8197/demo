package newgo2;
/**
 * ��Ҫд���̵�����Լ�����Ļ�ԭ����
 */

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ChessJpanel extends JPanel implements Config{

	private Shape[] arrayshape;
    private DrawMouse mouse;
    
    public void setarray(Shape[] arrayshape)
    {
   	 this.arrayshape=arrayshape;
    }
    public void setMouse(DrawMouse mouse) 
    {
   	 this.mouse=mouse;
    }
    /*
     * ��������д������paint������
     * һ�����ڻָ���ʼ�����̣�
     * һ�����ڻָ������Լ����ӣ���ֹ��С�����ߴ����������»��涪ʧ����
     * ����ͨ��ѭ�������������������ӡ�
     * @see javax.swing.JComponent#paint(java.awt.Graphics)
     */
   public void paint (Graphics g)
   {      	
       super.paint(g); 
      	ImageIcon img = new ImageIcon("C:/Users/Administrator/workspace/gobang/src/newgo2/chessbackground.jpg");//����ͼƬ������ͼƬ
      	g.drawImage(img.getImage(), 175, 80, 455, 455, null);         	        	
   	for(int i=0;i<LINE;i++)
   	{
			g.drawLine(X0, Y0+i*SIZE, X0+(LINE-1)*SIZE, Y0+i*SIZE);
			g.drawLine(X0+i*SIZE, Y0, X0+i*SIZE,  Y0+(LINE-1)*SIZE);    					
   	}
   	g.drawLine(X0-10,Y0-10,X0+(LINE-1)*SIZE+10,Y0-10);
    	g.drawLine(X0-10,Y0+(LINE-1)*SIZE+10,X0+(LINE-1)*SIZE+10,Y0+(LINE-1)*SIZE+10);
   	g.drawLine(X0-10,Y0-10,X0-10,Y0+(LINE-1)*SIZE+10);
   	g.drawLine(X0+(LINE-1)*SIZE+10,Y0-10,X0+(LINE-1)*SIZE+10,Y0+(LINE-1)*SIZE+10);
   	 for(int i=0;i<mouse.index;i++)
        {
   		Shape shape=arrayshape[i];
   		if(shape!=null) 
   		{
        	shape.drawShape(g);
   		}
   		else
   		break;
        }        	
   }
   public void paint1 (Graphics g)
   {
   	super.paint(g);
      	ImageIcon img1 = new ImageIcon("C:/Users/Administrator/workspace/gobang/src/newgo2/chessbackground.jpg");//����ͼƬ������ͼƬ
      	g.drawImage(img1.getImage(), 175, 80, 455, 455, null);           
   	for(int i=0;i<LINE;i++)
   	{
			g.drawLine(X0, Y0+i*SIZE, X0+(LINE-1)*SIZE, Y0+i*SIZE);
			g.drawLine(X0+i*SIZE, Y0, X0+i*SIZE,  Y0+(LINE-1)*SIZE);       		
   	}
   	g.drawLine(X0-10,Y0-10,X0+(LINE-1)*SIZE+10,Y0-10);
    	g.drawLine(X0-10,Y0+(LINE-1)*SIZE+10,X0+(LINE-1)*SIZE+10,Y0+(LINE-1)*SIZE+10);
   	g.drawLine(X0-10,Y0-10,X0-10,Y0+(LINE-1)*SIZE+10);
   	g.drawLine(X0+(LINE-1)*SIZE+10,Y0-10,X0+(LINE-1)*SIZE+10,Y0+(LINE-1)*SIZE+10);        	
   }
}
