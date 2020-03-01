package com.reddoor.charging.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import com.reddoor.charging.client.action.ClientFrameAction;
import com.reddoor.charging.util.Logger;

public class ClientFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private ClientFrameAction action;
	
	private JTextArea leftTextArea;
	
	public ClientFrame(){
		this.init();
	}
	
	private void init(){
		this.setTitle("客户端");
        this.setSize(450, 450);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        
        this.add(mainPanel, BorderLayout.CENTER);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1,1,5,5));
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        leftTextArea = new JTextArea();
        leftTextArea.setName("CommandLine");
        leftTextArea.setEditable(false);
        leftTextArea.setLineWrap(false);
        leftTextArea.setBorder(new LineBorder(Color.BLUE));
        
        JScrollPane scrollPanel = new JScrollPane(leftTextArea);
        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        centerPanel.add(scrollPanel);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        
        JButton btnStart = new JButton("启动");
        JButton btnStartCharging = new JButton("开始充电");
        JButton btnFullyCharged = new JButton("充满");
        JButton btnTerminateCharging = new JButton("终止充电");
        bottomPanel.add(btnStart);
        bottomPanel.add(btnStartCharging);
        bottomPanel.add(btnFullyCharged);
        bottomPanel.add(btnTerminateCharging);
        
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Logger.log("客户端启动");
				ClientHolder.getClient().start();
				appendCommandLine("启动");
			}
		});
        
        btnStartCharging.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Logger.log("开始充电");
				action.doStartCharging();
			}
		});
        
        btnFullyCharged.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Logger.log("充满");
				action.doFullyCharged();
			}
		});
        
        btnTerminateCharging.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Logger.log("终止充电");
				action.doTerminateCharging();
			}
		});
        
	}
	
	public void appendCommandLine(String content){
		leftTextArea.append(content + "\n");
	}

	public ClientFrameAction getAction() {
		return action;
	}

	public void setAction(ClientFrameAction action) {
		this.action = action;
	}
	

}
