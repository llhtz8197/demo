package newgo2;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * ����ĳ�ʼ����ƣ�������Ҳ������
 * @author JAVA
 *
 */
public class DrawFrame {

	public static void main(String[] args) {
		DrawFrame frame = new DrawFrame();
		frame.showUI();
	}
	public void showUI() {
		// �����������
		javax.swing.JFrame jf = new javax.swing.JFrame();
		ImageIcon img = new ImageIcon("C:/Users/Administrator/workspace/gobang/src/newgo2/bigbackground1.jpg");
		JLabel jla = new JLabel(img);
		jf.getLayeredPane().add(jla, new Integer(Integer.MIN_VALUE));
		jla.setBounds(0, 0, 950, 640);
		JPanel jp1 = (JPanel) jf.getContentPane();
		jp1.setOpaque(false); 
		jf.setSize(950, 640);
		//jf.getContentPane().setBackground(Color.ORANGE);//���ñ���ɫ
		jf.setTitle("������");
		jf.setDefaultCloseOperation(3);
		// ���þ�����ʾ
		jf.setLocationRelativeTo(null);
		DrawMouse mouse = new DrawMouse();
		JButton jb1=new JButton("����Ϸ");
		jf.add(jb1);
		jb1.setBounds(675, 185, 100,40);
		jb1.addActionListener(mouse);
		JButton jb2=new JButton("����");
		jf.add(jb2);
		jb2.setBounds(675, 265, 100,40);
		jb2.addActionListener(mouse);				
		JButton jb3=new JButton("�˻���ս");
		jf.add(jb3);
		jb3.setBounds(675, 345, 100,40);	
		jb3.addActionListener(mouse);
		//���̶���
		ChessJpanel cp = new ChessJpanel();
		//cp.setBackground(Color.ORANGE);
		jf.add(cp,BorderLayout.CENTER);			
		cp.setOpaque(false); 		
		cp.addMouseListener(mouse);
		cp.addMouseMotionListener(mouse);	    
		jf.setVisible(true);
		Graphics g=cp.getGraphics();
		mouse.setgr(g);
		mouse.setcp(cp);
		cp.setMouse(mouse);					
	}

}
