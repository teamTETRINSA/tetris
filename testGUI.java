/*
 * testGUI.java
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


public class testGUI {
	
	public static void main (String[] args) {
        
            grid a = new grid ();
            a.bestScore = 4;
			
			//tetrisGUI myTetrisGUI = new tetrisGUI ();
			
			//helpPopUp helpWindow = new helpPopUp ();
			//helpWindow.setVisible(true);

			//WelcomeGUI newWindow = new WelcomeGUI (a);
			
			//info1PlayerPopUp newWindowBis = new info1PlayerPopUp ();
			
			//info2PlayerPopUp newWindowBisbis = new info2PlayerPopUp ();
			//newWindowBisbis.setVisible(true);
			
			GameOverGUI WindowGameover = new GameOverGUI (3,a);
			WindowGameover.setVisible(true);
	}
}

