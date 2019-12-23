package online;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class myFrame extends JFrame{

	public static int[][] chess=new int[16][16];
	private volatile boolean isBlack = true;// �Ƿ��ֵ�����
	public static boolean occupy=false;
	private boolean isWin = false;// �Ƿ���ʤ��
	private boolean isStart = false;// �Ƿ������ɹ���ʼ��Ϸ
	public static boolean backChess=false;
	private int row=0;
	private int col=0;
	
	public static JTextArea text;


	public boolean isWin() {
	    return isWin;
	}

	public void setWin(boolean isWin) {
	    this.isWin = isWin;
	}


	public int getRow() {
	    return row;
	}

	public void setRow(int row) {
	    this.row = row;
	}

	public int getCol() {
	    return col;
	}

	public void setCol(int col) {
	    this.col = col;
	}



	public myFrame() {
	    this.setName("������������");
	    this.setBounds(100, 100, 500, 580);
	    this.setLayout(null);

	    JButton start=new JButton("��ʼ");
	    start.setBounds(10, 10, 60, 25);
	    this.add(start);
	    //��ʼ��ť

	    JButton back=new JButton("����");
	    back.setBounds(80, 10, 60, 25);;
	    this.add(back);

	    text=new JTextArea();
	    JScrollPane pane=new JScrollPane(text);
	    pane.setBounds(60, 430, 360, 100);
	    this.add(pane);


	    JButton end=new JButton("�˳�");
	    end.setBounds(150, 10, 60, 25);
	    this.add(end);

	    end.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            System.exit(0);
	        }
	    });


	    final myPanel panel=new myPanel(chess);
	    panel.setBounds(0, 0, 500, 500);
	    this.add(panel);

	    //JTextField innfo=new


	    panel.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mousePressed(MouseEvent e) {
	            if(isStart  && !isWin() && !occupy ) {
	                int x=e.getX();
	                int y=e.getY();
	                if(x>=65 && x<=415 && y>=65 &&y<=415) {
	                    setRow(Math.round((y-65)/25f));
	                    setCol(Math.round((x-65)/25f));
	                    //System.out.println(getRow()+","+getCol());
	                    repaint();
	                    if(panel.addPiece(getRow(), getCol(), isBlack)) {
	                        setWin(panel.isWin(getRow(), getCol(), isBlack));
	                        if(!isWin()) {

	                        }else {
	                            JOptionPane.showMessageDialog(null, "��Ӯ�ˡ�������");
	                            setWin(true);
	                            repaint();
	                        }
	                    }

	                }
	            }

	        }
	    });


	    start.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            isStart=true;


	        }
	    });




	    //���尴ť�����¼�
	    back.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            System.out.println(getRow()+","+getCol());
	            chess[getRow()][getCol()]=0;
	            backChess=true;
	         // backChess=panel.reducePiece(getRow(), getCol());
	            repaint();
	        }
	    });

	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
	}
}


