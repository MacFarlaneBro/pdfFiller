package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainWindow {
	
	public static void main(String[] args){
		
		createGui();
	}
	
	public static void createGui(){
		JFrame guiFrame = new JFrame();
		
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("PDF Filler");
		guiFrame.setSize(300, 250);
		
		guiFrame.setLocationRelativeTo(null);
		
		String[] fruitOptions = {"Ascentric"};
		
		String[] vegOptions = {"Automatic", "Manual"};
		
		//The first JPanel contains a JLabel and a JCombobox
		final JPanel comboPanel = new JPanel();
		
		JLabel comboLabel = new JLabel();
		JComboBox fruits = new JComboBox(fruitOptions);
		
		//Adding the Label to the Panel
		comboPanel.add(comboLabel);
		comboPanel.add(fruits);
		
		final JPanel listPanel = new JPanel();
		
		listPanel.setVisible(false);
		JLabel listLabel = new JLabel("Vegetables");
		JList vegs = new JList(vegOptions);
		vegs.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		
		comboPanel.add(listLabel);
		comboPanel.add(vegs);
		
		JButton vegFruitBut = new JButton("Fill Form");
		
		vegFruitBut.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event){
				listPanel.setVisible(!listPanel.isVisible());
				comboPanel.setVisible(!comboPanel.isVisible());
			}

		});
		
		guiFrame.add(comboPanel, BorderLayout.NORTH);
		guiFrame.add(listPanel, BorderLayout.CENTER);
		guiFrame.add(vegFruitBut, BorderLayout.SOUTH);
		
		guiFrame.setVisible(true);
		
	}

}
