package we;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class W extends JFrame {   //���������� 
 
 //private static final long serialVersionUID = 1L;

 public static void main(String args[]) {
  W frame = new W();
  frame.setVisible(true);
 }
 
 public W() {
  super();
  setTitle("ʹ�ù�����");
  setBounds(550,262, 500, 375);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  final JToolBar toolBar = new JToolBar("������");// ��������������
  toolBar.setFloatable(false);// ����Ϊ�������϶�
  //getContentPane().add(toolBar, BorderLayout.NORTH);// ��ӵ����񲼾ֵ��Ϸ�
  getContentPane().add(toolBar, BorderLayout.NORTH);// ��ӵ����񲼾ֵ��Ϸ�
 
  final JButton newButton = new JButton("�½�");// ������ť����
  newButton.addActionListener(new ButtonListener());// ��Ӷ����¼�������
  toolBar.add(newButton);// ��ӵ���������
 
  toolBar.addSeparator();// ���Ĭ�ϴ�С�ķָ���
  final JButton saveButton = new JButton("����");// ������ť����
  saveButton.addActionListener(new ButtonListener());// ��Ӷ����¼�������
  toolBar.add(saveButton);// ��ӵ���������
  
  toolBar.addSeparator(new Dimension(20, 0));// ���ָ����С�ķָ���
  final JButton exitButton = new JButton("�˳�");// ������ť����
  exitButton.addActionListener(new ButtonListener());// ��Ӷ����¼�������
  toolBar.add(exitButton);// ��ӵ���������
 }
 
 private class ButtonListener implements ActionListener {  //���ڽ��ղ����¼����������ӿ�
  public void actionPerformed(ActionEvent e) {
   JButton button = (JButton) e.getSource();
   System.out.println("���������ǣ�" + button.getText());
  }
 }
 
}
  