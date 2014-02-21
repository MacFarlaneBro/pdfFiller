package gui;

import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainWindow {
	
	public static void main(String[] args){
		
		createGui();
	}
	
	public static void createGui(){
		JFrame guiFrame = new JFrame();
		
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Closes the application on the closing of the window
		guiFrame.setTitle("PDF Filler");
		guiFrame.setSize(300, 250);
		
		guiFrame.setLocationRelativeTo(null);
		
		String[] formDropDownOptions = {"Ascentric", "Standard Life", "Scottish Provident"};
				
		//The first JPanel contains a JLabel and a JCombobox
		final JPanel comboPanel = new JPanel();
		
		JLabel comboLabel = new JLabel();
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox formDropDown = new JComboBox(formDropDownOptions);
		
		//Adding the Label to the Panel
		comboPanel.add(comboLabel);
		comboPanel.add(formDropDown);
		
		TextField clientNumber = new TextField();
				
		comboPanel.add(formDropDown);
		comboPanel.add(clientNumber);
		
		JButton fillFormButton = new JButton("Fill Form");
		
		fillFormButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				comboPanel.setVisible(!comboPanel.isVisible());
			}

		});
		
		guiFrame.add(comboPanel, BorderLayout.NORTH);
		guiFrame.add(fillFormButton, BorderLayout.SOUTH);
		
		guiFrame.setVisible(true);
		
	}

}
