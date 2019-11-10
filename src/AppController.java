/**
 * Copyright Junhyuk Kim / 2019 Hackersedu all rights reserved.
 * PDF TEST EXTRA TRANSFORMATION PROJECT (06.15.19 -)
 *  
 * AppController.java
 * 
 * 
 */
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JComboBox;

public class AppController {
	
	private int mouseX, mouseY;
	
	public AppController()
	{
		AppManager.CreateInstance().setAppController(this);
		AppManager.CreateInstance().getAppModel().menuBar.addMouseListener(new MouseAdapter(){
			@Override
				public void mousePressed(MouseEvent e){
					mouseX = e.getX();
					mouseY = e.getY();
				}
		});
		AppManager.CreateInstance().getAppModel().menuBar.addMouseMotionListener(new MouseMotionAdapter()
		{
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				AppManager.CreateInstance().getAppModel().setLocation(x - mouseX, y - mouseY);
				
			}
		});
		
		AppManager.CreateInstance().getAppModel().exitButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e){
				AppManager.CreateInstance().getAppModel().exitButton.setIcon(ImageData.exitHover);
				AppManager.CreateInstance().getAppModel().exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));		
			}
			@Override
			public void mouseExited(MouseEvent e){
				AppManager.CreateInstance().getAppModel().exitButton.setIcon(ImageData.exitBasic);
				AppManager.CreateInstance().getAppModel().exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));	
			}
			@Override
			public void mousePressed(MouseEvent e){
				try
				{
					Thread.sleep(1000);
				}catch(Exception ex)
				{
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});
		AppManager.CreateInstance().getAppModel().voca1Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e){
				AppManager.CreateInstance().getAppModel().voca1Button.setIcon(ImageData.voca1Hover);
			}
			@Override
			public void mouseExited(MouseEvent e){
				AppManager.CreateInstance().getAppModel().voca1Button.setIcon(ImageData.voca1Basic);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				AppManager.CreateInstance().getAppModel().vocaMode = 1; 
				AppManager.CreateInstance().getAppModel().pageState = 1; 
				
				//크롤링 테스트 용
				AppManager.CreateInstance().getAppDAO().crollingFromCSV();

			
				
				// 옜 옜�  -> MAC 옜 옜
				//
				// /
				//다음 페이지로 넘어가기
				AppManager.CreateInstance().getAppModel().voca1Button.setVisible(false);
				AppManager.CreateInstance().getAppModel().voca2Button.setVisible(false);
				AppManager.CreateInstance().getAppModel().voca3Button.setVisible(false);
				
				for(int i =0; i<WordSelection.comboDay[AppManager.CreateInstance().getAppModel().vocaMode-1].length; i++) {
					AppManager.CreateInstance().getAppModel().fromCombo.addItem(WordSelection.comboDay[AppManager.CreateInstance().getAppModel().vocaMode-1][i]);		
				}
				AppManager.CreateInstance().getAppModel().step2();

				// 나머지 0으로 초기화시킬것
				AppManager.CreateInstance().getAppModel().enToKoCombo.addItem("0");
				AppManager.CreateInstance().getAppModel().koToEnCombo.addItem("0");
				AppManager.CreateInstance().getAppModel().blankCombo.addItem("0");
				
				WordSelection.enableUseNum = 0;
				WordSelection.nowEnToKo =0;
				WordSelection.nowKoToEn =0;
				WordSelection.nowBlank = 0;
				
				// 화면 전환해야 함.
				System.out.println("vocaMode = "+ AppManager.CreateInstance().getAppModel().vocaMode);
			}
		});
		
		AppManager.CreateInstance().getAppModel().voca2Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e){
				AppManager.CreateInstance().getAppModel().voca2Button.setIcon(ImageData.voca2Hover);
			}
			@Override
			public void mouseExited(MouseEvent e){
				AppManager.CreateInstance().getAppModel().voca2Button.setIcon(ImageData.voca2Basic);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				AppManager.CreateInstance().getAppModel().vocaMode = 2; 
				AppManager.CreateInstance().getAppModel().pageState = 1; 
				AppManager.CreateInstance().getAppDAO().crollingFromCSV();

				AppManager.CreateInstance().getAppModel().voca1Button.setVisible(false);
				AppManager.CreateInstance().getAppModel().voca2Button.setVisible(false);
				AppManager.CreateInstance().getAppModel().voca3Button.setVisible(false);		
				
				for(int i =0; i<WordSelection.comboDay[AppManager.CreateInstance().getAppModel().vocaMode-1].length; i++) {
					AppManager.CreateInstance().getAppModel().fromCombo.addItem(WordSelection.comboDay[AppManager.CreateInstance().getAppModel().vocaMode-1][i]);		
				}
				AppManager.CreateInstance().getAppModel().step2();
			


				// 나머지 0으로 초기화시킬것
				AppManager.CreateInstance().getAppModel().enToKoCombo.addItem("0");
				AppManager.CreateInstance().getAppModel().koToEnCombo.addItem("0");
				AppManager.CreateInstance().getAppModel().blankCombo.addItem("0");
			
				WordSelection.enableUseNum = 0;
				WordSelection.nowEnToKo =0;
				WordSelection.nowKoToEn =0;
				WordSelection.nowBlank = 0;
				
				System.out.println("vocaMode = "+ AppManager.CreateInstance().getAppModel().vocaMode);
				
				// 화면 전환해야 함.
			}
		});
		
		AppManager.CreateInstance().getAppModel().voca3Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e){
				AppManager.CreateInstance().getAppModel().voca3Button.setIcon(ImageData.voca3Hover);
			}
			@Override
			public void mouseExited(MouseEvent e){
				AppManager.CreateInstance().getAppModel().voca3Button.setIcon(ImageData.voca3Basic);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
				AppManager.CreateInstance().getAppModel().vocaMode = 3;
				AppManager.CreateInstance().getAppModel().pageState = 1;
				AppManager.CreateInstance().getAppDAO().crollingFromCSV();

				AppManager.CreateInstance().getAppModel().voca1Button.setVisible(false);
				AppManager.CreateInstance().getAppModel().voca2Button.setVisible(false);
				AppManager.CreateInstance().getAppModel().voca3Button.setVisible(false);
				
				//단순히 넣기만 하면 안됨
				for(int i =0; i<WordSelection.comboDay[AppManager.CreateInstance().getAppModel().vocaMode-1].length; i++) {
					AppManager.CreateInstance().getAppModel().fromCombo.addItem(WordSelection.comboDay[AppManager.CreateInstance().getAppModel().vocaMode-1][i]);		
				}
				
				// 나머지 0으로 초기화시킬것
				AppManager.CreateInstance().getAppModel().enToKoCombo.addItem("0");
				AppManager.CreateInstance().getAppModel().koToEnCombo.addItem("0");
				AppManager.CreateInstance().getAppModel().blankCombo.addItem("0");
				
				AppManager.CreateInstance().getAppModel().step2();
				
				WordSelection.enableUseNum = 0;
				WordSelection.nowEnToKo =0;
				WordSelection.nowKoToEn =0;
				WordSelection.nowBlank = 0;
				
				System.out.println("vocaMode = "+ AppManager.CreateInstance().getAppModel().vocaMode);
			}
		});
		
		
		// step2 마우스 이벤트 ----------------------------------------------------------------------------------------------------------------------------------------------
		AppManager.CreateInstance().getAppModel().fromCombo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			
				if(!"선택하세요".equals(AppManager.CreateInstance().getAppModel().fromCombo.getSelectedItem().toString())) {	//아예 완전히 초기화시키는 옵션
				// 선택하세요가 아닌 경우			
			
					AppManager.CreateInstance().getAppModel().toCombo.setEnabled(false);
					String temp = AppManager.CreateInstance().getAppModel().fromCombo.getSelectedItem().toString();
					AppManager.CreateInstance().getAppModel().toCombo.removeAllItems();	//전부 삭제
					
					for(int i = Integer.parseInt(temp); i<= WordSelection.vocaMax[AppManager.CreateInstance().getAppModel().vocaMode-1]; i++) {
						AppManager.CreateInstance().getAppModel().toCombo.addItem(Integer.toString(i));
					}
					
					AppManager.CreateInstance().getAppModel().toCombo.setEnabled(true);	
					AppManager.CreateInstance().getAppDAO().calculateEnableWordTestNum();
					AppManager.CreateInstance().getAppModel().maxQuiz.setText(Integer.toString(WordSelection.mainSelection.size()));
					AppManager.CreateInstance().getAppModel().selectedQuiz.setText("0");
					
					//다른 옵션들 모두 최신화 시키기
					int maxQuizNum = WordSelection.mainSelection.size();
					WordSelection.enableUseNum = 0;
					WordSelection.nowEnToKo =0;
					WordSelection.nowKoToEn =0;
					WordSelection.nowBlank = 0;
					
					//일단 비초기화 시킴
					AppManager.CreateInstance().getAppModel().enToKoCombo.setEnabled(false);
					AppManager.CreateInstance().getAppModel().koToEnCombo.setEnabled(false);
					AppManager.CreateInstance().getAppModel().blankCombo.setEnabled(false);
					
					AppManager.CreateInstance().getAppModel().enToKoCombo.removeAllItems();	
					AppManager.CreateInstance().getAppModel().koToEnCombo.removeAllItems();
					AppManager.CreateInstance().getAppModel().blankCombo.removeAllItems();
					
					for(int i = 0; i<= maxQuizNum; i++) {
						AppManager.CreateInstance().getAppModel().enToKoCombo.addItem(Integer.toString(i));
						AppManager.CreateInstance().getAppModel().koToEnCombo.addItem(Integer.toString(i));
						AppManager.CreateInstance().getAppModel().blankCombo.addItem(Integer.toString(i));
					}
					
					AppManager.CreateInstance().getAppModel().enToKoCombo.setEnabled(true);
					AppManager.CreateInstance().getAppModel().koToEnCombo.setEnabled(true);
					AppManager.CreateInstance().getAppModel().blankCombo.setEnabled(true);
				}
				else {			//선택하세요. 가 선택되었을 경우 모두 셧다운 시켜버리기.		
					AppManager.CreateInstance().getAppModel().toCombo.setEnabled(false);
					//나머지 모든 ComboBox 셧다운 시켜버리기
					AppManager.CreateInstance().getAppModel().enToKoCombo.setEnabled(false);
					AppManager.CreateInstance().getAppModel().koToEnCombo.setEnabled(false);
					AppManager.CreateInstance().getAppModel().blankCombo.setEnabled(false);
					
					AppManager.CreateInstance().getAppModel().maxQuiz.setText("0");
					AppManager.CreateInstance().getAppModel().selectedQuiz.setText("0");
					
				}
			}	
		});
		AppManager.CreateInstance().getAppModel().toCombo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(AppManager.CreateInstance().getAppModel().toCombo.isEnabled()) {
					AppManager.CreateInstance().getAppDAO().calculateEnableWordTestNum();
					AppManager.CreateInstance().getAppModel().maxQuiz.setText(Integer.toString(WordSelection.mainSelection.size()));
					AppManager.CreateInstance().getAppModel().selectedQuiz.setText("0");
					
					//다른 옵션들 모두 최신화 시키기//다른 옵션들 모두 최신화 시키기
					int maxQuizNum = WordSelection.mainSelection.size();
					WordSelection.enableUseNum = 0;
					WordSelection.nowEnToKo =0;
					WordSelection.nowKoToEn =0;
					WordSelection.nowBlank = 0;
					
					//일단 비초기화 시킴
					AppManager.CreateInstance().getAppModel().enToKoCombo.setEnabled(false);
					AppManager.CreateInstance().getAppModel().koToEnCombo.setEnabled(false);
					AppManager.CreateInstance().getAppModel().blankCombo.setEnabled(false);
					
					AppManager.CreateInstance().getAppModel().enToKoCombo.removeAllItems();	
					AppManager.CreateInstance().getAppModel().koToEnCombo.removeAllItems();
					AppManager.CreateInstance().getAppModel().blankCombo.removeAllItems();
					
					for(int i = 0; i<= maxQuizNum; i++) {
						AppManager.CreateInstance().getAppModel().enToKoCombo.addItem(Integer.toString(i));
						AppManager.CreateInstance().getAppModel().koToEnCombo.addItem(Integer.toString(i));
						AppManager.CreateInstance().getAppModel().blankCombo.addItem(Integer.toString(i));
					}
					
					AppManager.CreateInstance().getAppModel().enToKoCombo.setEnabled(true);
					AppManager.CreateInstance().getAppModel().koToEnCombo.setEnabled(true);
					AppManager.CreateInstance().getAppModel().blankCombo.setEnabled(true);
				}
			}
		});

		//// 핵심 기능 시작 ----------------------------------------------------------------------------------------------------------------------------------------------------------------
		AppManager.CreateInstance().getAppModel().enToKoCombo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	//무언가가 선택 되었을때 
				if(AppManager.CreateInstance().getAppModel().enToKoCombo.isEnabled()) {	//활성화 되어 있을 때
					
					/*
					 * 공통 로직 (enToKoCombo / koToEnCombo / blankCombo)
					 *
					 * Day를 바꾼 상태
					 * 1.전체 ComboBox 초기화 및 갱신
					 * 2.isEnabled 락 풀기
					 * 
					 * 선택된 상태 (각 콤보 옵션별 달리 적용하기)
					 * 1.isEnabled 락 시키기
					 * 2.WordSelection 에 현재 데이터 값을 저장한다.
					 * 3.EnableSum = 총 가능한 데이터 값-(세 가지 데이터 값 더하기)
					 * 4.세 가지 Combo 옵션 0부터 가능한 옵션 <= EnableSum 갱신
					 * 5.WordSelection 에 저장된 값 다시 각 Combo에 갱신하기
					 * 6.isEnabled 락 풀기
					 * 
					 * 
					 
					 
					 */
					
					AppManager.CreateInstance().getAppModel().enToKoCombo.setEnabled(false);
					AppManager.CreateInstance().getAppModel().koToEnCombo.setEnabled(false);
					AppManager.CreateInstance().getAppModel().blankCombo.setEnabled(false);
					
					WordSelection.nowEnToKo = Integer.parseInt(AppManager.CreateInstance().getAppModel().enToKoCombo.getSelectedItem().toString());
					WordSelection.nowKoToEn = Integer.parseInt(AppManager.CreateInstance().getAppModel().koToEnCombo.getSelectedItem().toString());
					WordSelection.nowBlank = Integer.parseInt(AppManager.CreateInstance().getAppModel().blankCombo.getSelectedItem().toString());
					
					WordSelection.enableUseNum = WordSelection.nowEnToKo + WordSelection.nowKoToEn + WordSelection.nowBlank;
					
					AppManager.CreateInstance().getAppModel().selectedQuiz.setText(Integer.toString(WordSelection.enableUseNum));
					
					int maxQuizNum = WordSelection.mainSelection.size() - WordSelection.enableUseNum;
					
					AppManager.CreateInstance().getAppModel().enToKoCombo.removeAllItems();	
					AppManager.CreateInstance().getAppModel().koToEnCombo.removeAllItems();
					AppManager.CreateInstance().getAppModel().blankCombo.removeAllItems();
					
					for(int i=0; i<= WordSelection.nowEnToKo + maxQuizNum; i++) {
						AppManager.CreateInstance().getAppModel().enToKoCombo.addItem(Integer.toString(i));
					}
					
					for(int i =0; i<=WordSelection.nowKoToEn+ maxQuizNum; i++) {
						AppManager.CreateInstance().getAppModel().koToEnCombo.addItem(Integer.toString(i));
					}
					
					for(int i =0; i<=WordSelection.nowBlank+ maxQuizNum; i++) {
						AppManager.CreateInstance().getAppModel().blankCombo.addItem(Integer.toString(i));		
					}
					
					AppManager.CreateInstance().getAppModel().enToKoCombo.setSelectedItem(Integer.toString(WordSelection.nowEnToKo));
					AppManager.CreateInstance().getAppModel().koToEnCombo.setSelectedItem(Integer.toString(WordSelection.nowKoToEn));
					AppManager.CreateInstance().getAppModel().blankCombo.setSelectedItem(Integer.toString(WordSelection.nowBlank));
					
					AppManager.CreateInstance().getAppModel().enToKoCombo.setEnabled(true);
					AppManager.CreateInstance().getAppModel().koToEnCombo.setEnabled(true);
					AppManager.CreateInstance().getAppModel().blankCombo.setEnabled(true);
				}
			}
		});
		
		AppManager.CreateInstance().getAppModel().koToEnCombo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(AppManager.CreateInstance().getAppModel().koToEnCombo.isEnabled()) {
					AppManager.CreateInstance().getAppModel().enToKoCombo.setEnabled(false);
					AppManager.CreateInstance().getAppModel().koToEnCombo.setEnabled(false);
					AppManager.CreateInstance().getAppModel().blankCombo.setEnabled(false);
					
					WordSelection.nowEnToKo = Integer.parseInt(AppManager.CreateInstance().getAppModel().enToKoCombo.getSelectedItem().toString());
					WordSelection.nowKoToEn = Integer.parseInt(AppManager.CreateInstance().getAppModel().koToEnCombo.getSelectedItem().toString());
					WordSelection.nowBlank = Integer.parseInt(AppManager.CreateInstance().getAppModel().blankCombo.getSelectedItem().toString());
					
					WordSelection.enableUseNum = WordSelection.nowEnToKo + WordSelection.nowKoToEn + WordSelection.nowBlank;
					
					AppManager.CreateInstance().getAppModel().selectedQuiz.setText(Integer.toString(WordSelection.enableUseNum));
					
					int maxQuizNum = WordSelection.mainSelection.size() - WordSelection.enableUseNum;
					
					AppManager.CreateInstance().getAppModel().enToKoCombo.removeAllItems();	
					AppManager.CreateInstance().getAppModel().koToEnCombo.removeAllItems();
					AppManager.CreateInstance().getAppModel().blankCombo.removeAllItems();
					
					for(int i=0; i<= WordSelection.nowEnToKo + maxQuizNum; i++) {
						AppManager.CreateInstance().getAppModel().enToKoCombo.addItem(Integer.toString(i));
					}
					for(int i =0; i<=WordSelection.nowKoToEn+ maxQuizNum; i++) {
						AppManager.CreateInstance().getAppModel().koToEnCombo.addItem(Integer.toString(i));
					}
					
					for(int i =0; i<=WordSelection.nowBlank+ maxQuizNum; i++) {
						AppManager.CreateInstance().getAppModel().blankCombo.addItem(Integer.toString(i));		
					}
					
					AppManager.CreateInstance().getAppModel().enToKoCombo.setSelectedItem(Integer.toString(WordSelection.nowEnToKo));
					AppManager.CreateInstance().getAppModel().koToEnCombo.setSelectedItem(Integer.toString(WordSelection.nowKoToEn));
					AppManager.CreateInstance().getAppModel().blankCombo.setSelectedItem(Integer.toString(WordSelection.nowBlank));
					
					AppManager.CreateInstance().getAppModel().enToKoCombo.setEnabled(true);
					AppManager.CreateInstance().getAppModel().koToEnCombo.setEnabled(true);
					AppManager.CreateInstance().getAppModel().blankCombo.setEnabled(true);
				}
				
			}
		});
		
		AppManager.CreateInstance().getAppModel().blankCombo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(AppManager.CreateInstance().getAppModel().blankCombo.isEnabled()) {
					AppManager.CreateInstance().getAppModel().enToKoCombo.setEnabled(false);
					AppManager.CreateInstance().getAppModel().koToEnCombo.setEnabled(false);
					AppManager.CreateInstance().getAppModel().blankCombo.setEnabled(false);
					
					WordSelection.nowEnToKo = Integer.parseInt(AppManager.CreateInstance().getAppModel().enToKoCombo.getSelectedItem().toString());
					WordSelection.nowKoToEn = Integer.parseInt(AppManager.CreateInstance().getAppModel().koToEnCombo.getSelectedItem().toString());
					WordSelection.nowBlank = Integer.parseInt(AppManager.CreateInstance().getAppModel().blankCombo.getSelectedItem().toString());
					
					WordSelection.enableUseNum = WordSelection.nowEnToKo + WordSelection.nowKoToEn + WordSelection.nowBlank;
					
					AppManager.CreateInstance().getAppModel().selectedQuiz.setText(Integer.toString(WordSelection.enableUseNum));
					
					int maxQuizNum = WordSelection.mainSelection.size() - WordSelection.enableUseNum;
					
					AppManager.CreateInstance().getAppModel().enToKoCombo.removeAllItems();	
					AppManager.CreateInstance().getAppModel().koToEnCombo.removeAllItems();
					AppManager.CreateInstance().getAppModel().blankCombo.removeAllItems();
					
					for(int i=0; i<= WordSelection.nowEnToKo + maxQuizNum; i++) {
						AppManager.CreateInstance().getAppModel().enToKoCombo.addItem(Integer.toString(i));
					}
					for(int i =0; i<=WordSelection.nowKoToEn+ maxQuizNum; i++) {
						AppManager.CreateInstance().getAppModel().koToEnCombo.addItem(Integer.toString(i));
					}
					for(int i =0; i<=WordSelection.nowBlank+ maxQuizNum; i++) {
						AppManager.CreateInstance().getAppModel().blankCombo.addItem(Integer.toString(i));		
					}
					
					AppManager.CreateInstance().getAppModel().enToKoCombo.setSelectedItem(Integer.toString(WordSelection.nowEnToKo));
					AppManager.CreateInstance().getAppModel().koToEnCombo.setSelectedItem(Integer.toString(WordSelection.nowKoToEn));
					AppManager.CreateInstance().getAppModel().blankCombo.setSelectedItem(Integer.toString(WordSelection.nowBlank));
					
					AppManager.CreateInstance().getAppModel().enToKoCombo.setEnabled(true);
					AppManager.CreateInstance().getAppModel().koToEnCombo.setEnabled(true);
					AppManager.CreateInstance().getAppModel().blankCombo.setEnabled(true);
				}
			}
		});
		
		
		//// 핵심 기능 끝-----------------------------------------------------------------------------------------------------------------------------------------------------------
		
		//JFileDialog 생성// 페턴
		AppManager.CreateInstance().getAppModel().foreButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e){
				AppManager.CreateInstance().getAppModel().foreButton.setIcon(ImageData.foreHover);
			}
			@Override
			public void mouseExited(MouseEvent e){
				AppManager.CreateInstance().getAppModel().foreButton.setIcon(ImageData.foreBasic);
			}
	
			@Override
			public void mousePressed(MouseEvent e) {
				if(AppManager.CreateInstance().getAppModel().pageState ==1) {
					AppManager.CreateInstance().getAppModel().vocaMode = 0;						//이전으로 돌아가기
					AppManager.CreateInstance().getAppModel().pageState = 0; 
					
					AppManager.CreateInstance().getAppModel().voca1Button.setVisible(true);
					AppManager.CreateInstance().getAppModel().voca2Button.setVisible(true);
					AppManager.CreateInstance().getAppModel().voca3Button.setVisible(true);
					
					//관련 데이터 모두 초기화 시키기
			
					// 가리기
					AppManager.CreateInstance().getAppModel().optionPanel.setVisible(false);
					AppManager.CreateInstance().getAppModel().selectPanel.setVisible(false);
					AppManager.CreateInstance().getAppModel().foreButton.setVisible(false);
					AppManager.CreateInstance().getAppModel().nextButton.setVisible(false);
			
					System.out.println("Enter to first page");				
				}
				else if(AppManager.CreateInstance().getAppModel().pageState ==2) {
					AppManager.CreateInstance().getAppModel().pageState = 1; 
					AppManager.CreateInstance().getAppModel().optionPanel.setVisible(true);
					AppManager.CreateInstance().getAppModel().selectPanel.setVisible(true);
					AppManager.CreateInstance().getAppModel().nextButton.setVisible(true);
					AppManager.CreateInstance().getAppModel().savePanel.setVisible(false);
					
					// 옜 옜 옜
					
					
					
					
					AppManager.CreateInstance().getAppModel().testNamePanel.setVisible(false);
					AppManager.CreateInstance().getAppModel().homeButton.setVisible(false);
					System.out.println("Enter to Second Page");
					//관련 데이터 초기화	
				}
			
			}
		});
		
		AppManager.CreateInstance().getAppModel().nextButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e){
				AppManager.CreateInstance().getAppModel().nextButton.setIcon(ImageData.nextHover);
			}
			@Override
			public void mouseExited(MouseEvent e){
				AppManager.CreateInstance().getAppModel().nextButton.setIcon(ImageData.nextBasic);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//관련 데이터 모두 초기화 시키기		
				// 가리기
				AppManager.CreateInstance().getAppModel().pageState = 2; 
				AppManager.CreateInstance().getAppModel().optionPanel.setVisible(false);
				AppManager.CreateInstance().getAppModel().selectPanel.setVisible(false);
				AppManager.CreateInstance().getAppModel().nextButton.setVisible(false);
				
				AppManager.CreateInstance().getAppModel().step3();
				AppManager.CreateInstance().getAppModel().testNamePanel.setVisible(true);
				AppManager.CreateInstance().getAppModel().savePanel.setVisible(true);
				System.out.println("Enter to last page");
			}
			
		});
	
		// 옜옜 옜
		//
		// 옜� 옜 옜
		//
		
//		AppManager.CreateInstance().getAppModel().makeTestInit.addMouseListener(new Mouse Adapter(){
//		
//		
//		
//		
//		});
		
		
		
		//step3 ------------------------------------------------------------------------------------------------------------------------------------
		//미리보기 두가지 옵션 초기화 하기
		
		AppManager.CreateInstance().getAppModel().makeTestButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e){
				AppManager.CreateInstance().getAppModel().makeTestButton.setIcon(ImageData.makeTestHover);
			}
			@Override
			public void mouseExited(MouseEvent e){
				AppManager.CreateInstance().getAppModel().makeTestButton.setIcon(ImageData.makeTestBasic);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				/* 테스트지 생성 *
				 */
				
				
				
				
			}
			
		});
		AppManager.CreateInstance().getAppModel().quizRefer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e){
				AppManager.CreateInstance().getAppModel().quizRefer.setIcon(ImageData.referHover);
			}
			@Override
			public void mouseExited(MouseEvent e){
				AppManager.CreateInstance().getAppModel().quizRefer.setIcon(ImageData.referImage);
			}
			@Override
		public void mousePressed(MouseEvent e) {
				
			/*
			 * 
			 * 시험지 만들기
			 * 미리보기 링크 메소드 호출
			 * 
			 * */
			}
			
		});
		AppManager.CreateInstance().getAppModel().quizSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e){
				AppManager.CreateInstance().getAppModel().quizSave.setIcon(ImageData.saveHover);
			}
			@Override
			public void mouseExited(MouseEvent e){
				AppManager.CreateInstance().getAppModel().quizSave.setIcon(ImageData.saveImage);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
			/*
			 * 
			 * 시험지 만들기
			 * 저장링크 (다른이름으로 저장 창 띄우기)
			 * 관련 메소드 호출
			 * 
			 * */
			}
		});


		AppManager.CreateInstance().getAppModel().answerRefer.addMouseListener(new
				MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e){
				AppManager.CreateInstance().getAppModel().answerRefer.setIcon(ImageData.referHover);
			}
			@Override
			public void mouseExited(MouseEvent e){
				AppManager.CreateInstance().getAppModel().answerRefer.setIcon(ImageData.referImage);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
			/*
			 * 
			 * 답안지 만들기
			 * 미리보기 링크 메소드 호출
			 * 
			 * */
			}		
		});
		
		AppManager.CreateInstance().getAppModel().answerSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e){
				AppManager.CreateInstance().getAppModel().answerSave.setIcon(ImageData.saveHover);
			}
			@Override
			public void mouseExited(MouseEvent e){
				AppManager.CreateInstance().getAppModel().answerSave.setIcon(ImageData.saveImage);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
			/*
			 * 
			 * 답안지 만들기
			 * 미리보기 링크 메소드 호출
			 * 
			 * */
			}
		
			
		});
		
		//저장하기 두가지 옵션 초기화 하기
		
		
		AppManager.CreateInstance().getAppModel().homeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e){
				AppManager.CreateInstance().getAppModel().homeButton.setIcon(ImageData.homeHover);
			}
			@Override
			public void mouseExited(MouseEvent e){
				AppManager.CreateInstance().getAppModel().homeButton.setIcon(ImageData.homeImage);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//관련 데이터 모두 초기화 시키기		
				// 가리기
				
				//////////////////////////////////////////////////////확인 다이얼로그 한번 더 키기
				//step1() 으로 돌아가기.
				/*
				AppManager.CreateInstance().getAppModel().pageState = 0; 
				
				AppManager.CreateInstance().getAppModel().optionPanel.setVisible(false);
				AppManager.CreateInstance().getAppModel().selectPanel.setVisible(false);
				AppManager.CreateInstance().getAppModel().nextButton.setVisible(false);
				
				AppManager.CreateInstance().getAppModel().step3();
				AppManager.CreateInstance().getAppModel().testNamePanel.setVisible(true);
				AppManager.CreateInstance().getAppModel().savePanel.setVisible(true);
				System.out.println("Enter to last page");
				*/
			}
			
			
		});
	
	}
	
	public void initiateAllOptions() {	//STEP2 에서 이전 단계 버튼, STEP3에서 홈버튼 단계 버튼 이벤트 발생시 가동함
		
		//step2 설정 초기화
		AppManager.CreateInstance().getAppModel().fromCombo.removeAllItems();
		AppManager.CreateInstance().getAppModel().toCombo.removeAllItems();
		AppManager.CreateInstance().getAppModel().enToKoCombo.removeAllItems();
		AppManager.CreateInstance().getAppModel().koToEnCombo.removeAllItems();
		AppManager.CreateInstance().getAppModel().blankCombo.removeAllItems();
		
		AppManager.CreateInstance().getAppModel().maxQuiz.setText("0");
		AppManager.CreateInstance().getAppModel().selectedQuiz.setText("0");
		
		
		
	}
	
}
