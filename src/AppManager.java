/**
 * Copyright Junhyuk Kim /2019 Human League Co. all rights reserved.
 * AppManager.class
 * @author 김준혁
 * 
 */
public class AppManager {												//별도로 객체를 선언 하지 않음
	private static AppManager s_instance;								//AppManager instance 객체 생성
	private AppModel m_appModel;										//appModel 객체 생성
	private AppController m_appController;								//appController 객체 생성
	private AppDAO m_appDAO;											
	
	private AppManager()												//AppManager private 생성자 
	{
		 s_instance =null;
	}
	
	public AppModel getAppModel() {										//AppModel 반환
		return m_appModel;
	}

	public void setAppModel(AppModel m_appModel) {						//AppModel 객체 붙이기
		this.m_appModel = m_appModel;
	}

	public AppController getAppController() {							//AppController 반환
		return m_appController;
	}

	public void setAppController(AppController m_appController) {		//AppController 객체붙이기
		this.m_appController = m_appController;
	}

	public AppDAO getAppDAO() {											//AppDAO 반환
		return m_appDAO;
	}

	public void setAppDAO(AppDAO m_appDAO) {							//AppDAO 객체 붙이기
		this.m_appDAO = m_appDAO;
	}
	
	public static AppManager CreateInstance()							//AppManager Instance 설정
	{
		if(s_instance == null)											//Instance 변수가 없으면 생성 하여반환
		{
			s_instance = new AppManager();
			return s_instance;
		}
		else															//Instance 변수가 있다면 그대로 반환
		{
			return s_instance;		
		}
	}
}
