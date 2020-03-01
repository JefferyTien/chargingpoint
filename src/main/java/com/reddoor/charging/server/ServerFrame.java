package com.reddoor.charging.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.reddoor.charging.server.action.ServerFrameAction;
import com.reddoor.charging.util.Logger;

public class ServerFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private ServerFrameAction action;
	
	private DefaultListModel listModel;
	
	private JTextArea leftTextArea;
	
	private Server server;
	
	private String selectedClient;

	public ServerFrame(){
		this.init();
	}
	
	private void init(){
		this.setTitle("服务端");
        this.setSize(550, 550);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        this.add(mainPanel, BorderLayout.CENTER);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1,2,5,5));
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        leftTextArea = new JTextArea();
        leftTextArea.setEditable(false);
        leftTextArea.setBorder(new LineBorder(Color.BLUE));
        
        JScrollPane leftScrollPanel = new JScrollPane(leftTextArea);
        leftScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        leftScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        centerPanel.add(leftScrollPanel);
        
        listModel = new DefaultListModel();
        JList list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {

		            if (list.getSelectedIndex() == -1) {
		            //No selection
		            	selectedClient = null;
		            } else {
		            //Selection
		            	selectedClient = (String)list.getSelectedValue();
		            }
		        }
			}
        	
        });
        list.setVisibleRowCount(10);
        JScrollPane rightScrollPane = new JScrollPane(list);
        rightScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rightScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        JTextArea rightTextArea = new JTextArea();
//        rightTextArea.setEditable(false);
//        rightTextArea.setBorder(new LineBorder(Color.BLUE));
        
        centerPanel.add(rightScrollPane);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        
        JButton btnStart = new JButton("启动");
        JButton btnPrepareCharging = new JButton("准备充电");
        JButton btnEndCharging = new JButton("结束充电");
        bottomPanel.add(btnStart);
        bottomPanel.add(btnPrepareCharging);
        bottomPanel.add(btnEndCharging);
        
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(null != server){
					Logger.log("服务端启动");
					server.start();
					appendCommandLine("启动");
				}
			}
		});
        
        btnPrepareCharging.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!checkSelectedClient()){
					return;
				}
				Logger.log("准备充电");
				appendCommandLine("准备充电 ==> "+selectedClient);
				SocketWrapper wrapper = SocketHolder.get(selectedClient);
				action.doPrepareCharging(wrapper.getSocket());
			}
		});
        
        btnEndCharging.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!checkSelectedClient()){
					return;
				}
				Logger.log("结束充电");
				appendCommandLine("结束充电 ==> "+selectedClient);
				SocketWrapper wrapper = SocketHolder.get(selectedClient);
				action.doEndCharging(wrapper.getSocket());
			}
		});
	}
	
	private boolean checkSelectedClient(){
		if(null == selectedClient || "".equals(selectedClient)){
			JOptionPane.showMessageDialog(null, "请选择要通信的客户端", "标题",JOptionPane.WARNING_MESSAGE);  
			return false;
		}
		return true;
	}
	
	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}
	
	public void updateClientList(String clientName){
		if(!listModel.contains(clientName)){
			listModel.addElement(clientName);
		}
//		listModel.removeAllElements();
//		for (String key : SocketHolder.keySet()) {
//			listModel.addElement(key);
//		}
	}
	
	public void appendCommandLine(String content){
		leftTextArea.append(content + "\n");
	}

	public ServerFrameAction getAction() {
		return action;
	}

	public void setAction(ServerFrameAction action) {
		this.action = action;
	}


}
