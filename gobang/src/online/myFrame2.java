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

public class myFrame2 extends JFrame{
	public static int[][] chess=new int[16][16];

	private volatile boolean isBlack = false;// �Ƿ��ֵ�����
	public static boolean occupy=false;
	private boolean isWin = false;// �Ƿ���ʤ��
	private boolean isStart = false;// �Ƿ������ɹ�
	private int row=0;
	private int col=0;

	public static boolean backChess=false;

	public JButton back;
	final serverPanel panel;
	public static JTextArea text;


	public myFrame2() {
	    this.setName("������������");
	    this.setBounds(100, 100, 500, 580);
	    this.setLayout(null);

	    JButton start=new JButton("��ʼ");
	    start.setBounds(10, 10, 60, 25);
	    this.add(start);
	    //��ʼ��ť

	    back=new JButton("����");
	    back.setBounds(80, 10, 60, 25);;
	    this.add(back);




	    JButton end=new JButton("�˳�");
	    end.setBounds(150, 10, 60, 25);
	    this.add(end);

	    JButton oppoBack=new JButton("�Է�����");
	    oppoBack.setBounds(230, 10, 120, 25);
	    this.add(oppoBack);




	    end.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            System.exit(0);
	        }
	    });


	    text=new JTextArea();
	    JScrollPane pane=new JScrollPane(text);
	    pane.setBounds(60, 430, 360, 100);
	    this.add(pane);



	    panel=new serverPanel(chess);
	    panel.setBounds(0, 0, 430, 500);
	    this.add(panel);



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
	                            JOptionPane.showMessageDialog(null, "��Ӯ�ˡ���������");
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
	            repaint();

	        }
	    });

	   oppoBack.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
	        panel.reducePiece(getRow(), getCol());
	        repaint();

	    }
	});

	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
	}






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

}
