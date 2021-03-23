/*
 * helpPopUp.java
 * 
 * Copyright 2021 Bich-lien Phan <bich-lienphan@Bich-liens-MacBook.local>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */


import java.util.ArrayList; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;

public class helpPopUp extends JFrame {	
		public helpPopUp (){
			this.setTitle("How to Play:");
			//Set size of the window
			this.setSize(400,400);
			//intital position
			this.setLocation(300,200);
			this.setLayout(null);
			this.setVisible(false);
			
			JPanel helpPane = new JPanel();
			//Set position and size (x,y,dx,dy)
			helpPane.setBounds(0,0,400,400);
			//absolute positioning
			helpPane.setLayout(null);
			//Set colour
			helpPane.setBackground(new Color(243,120,48));
			//add this pane to the frame
			this.add(helpPane);
			
			JLabel helpText = new JLabel();
			helpText.setFont(new Font("Ariel", Font.PLAIN, 16));
			//add text
			helpText.setText("Write text on how to play the game");
			//Set position and size (x,y,dx,dy)
			helpText.setBounds(0,0,340,50);
			//add this to the main pane
			helpPane.add(helpText);
			
			
		}
}
		


