/**
 * Copyright Junhyuk Kim / 2019 Human League Co. all rights reserved.
 * Execute.class
 * @author 김준혁 
 * 
 */
public class Execute {
	public final static int WIDTH = 800;					//프레임 가로 길이
	public final static int HEIGHT = 600;					//프레임 세로 길이
	
	public static void main(String [] args) {
		new AppModel();
		new AppController();	
		new AppDAO();
	}
	
}
