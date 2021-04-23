public class testGUI {
	
	public static void main (String[] args) {
        
            grid a = new grid ();
            a.bestScore = 4;
			
			//tetrisGUI myTetrisGUI = new tetrisGUI ();
			
			helpPopUp helpWindow = new helpPopUp (a);
			helpWindow.setVisible(true);

			//WelcomeGUI newWindow = new WelcomeGUI (a);
			
			//info1PlayerPopUp newWindowBis = new info1PlayerPopUp ();
			
			//info2PlayerPopUp newWindowBisbis = new info2PlayerPopUp ();
			//newWindowBisbis.setVisible(true);
			
			//GameOverGUI WindowGameover = new GameOverGUI (3,a);
			//WindowGameover.setVisible(true);
	}
}

