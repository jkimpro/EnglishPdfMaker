/**
 * Copyright Junhyuk Kim / 2019 Human League Co. all rights reserved.
 * Execute.class
 * @author ������ 
 * 
 */
public class Execute {
	public final static int WIDTH = 800;					//������ ���� ����
	public final static int HEIGHT = 600;					//������ ���� ����
	
	public static void main(String [] args) {
		new AppModel();
		new AppController();	
		new AppDAO();
	}
	
}
