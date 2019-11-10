import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Copyright Junhyuk Kim /2019 Human League Co. all rights reserved.
 * AppModel.class
 * @author ������
 * 
 */

public class AppModel extends JFrame {
	
	public static int vocaMode;
	public static int pageState;
	
	protected Image screenImage;
	protected Graphics screenGraphic;
	
	protected Image background = new ImageIcon(Execute.class.getResource("images/background_guide.png")).getImage();
	protected JLabel menuBar = new JLabel(ImageData.menuBar);
	protected JButton exitButton = new JButton(ImageData.exitBasic);

	protected JButton voca1Button = new JButton(ImageData.voca1Basic);
	protected JButton voca2Button = new JButton(ImageData.voca2Basic);
	protected JButton voca3Button = new JButton(ImageData.voca3Basic);
	
	protected boolean isMainScreen = false;
	
	// step2���� ���� �̹����� ----------------------------------------------------------------------------------------------
	protected JPanel selectPanel;
	protected JComboBox fromCombo = new JComboBox();
	protected JComboBox toCombo = new JComboBox();

	protected JPanel optionPanel;
	protected JComboBox enToKoCombo = new JComboBox();
	protected JComboBox koToEnCombo = new JComboBox();
	protected JComboBox blankCombo = new JComboBox();
	
	protected JCheckBox checkOption1 = new JCheckBox();
	protected JCheckBox checkOption2 = new JCheckBox();
	
	protected JTextField maxQuiz = new JTextField(20);
	protected JTextField selectedQuiz = new JTextField(20);
   
	
	protected JButton foreButton = new JButton(ImageData.foreBasic);
	protected JButton nextButton = new JButton(ImageData.nextBasic);

	
	//step3���� ���� �ɼǵ� -------------------------------------------------------------------------------------------------
	
	protected JPanel testNamePanel;
	protected JPanel savePanel;
	
	protected JTextField academyName = new JTextField(10);
	protected JTextField testName = new JTextField(10);
	
	protected JButton makeTestButton = new JButton(ImageData.makeTestBasic);
	
	protected JButton quizRefer = new JButton(ImageData.referImage);
	protected JButton quizSave = new JButton(ImageData.saveImage);
	protected JButton answerRefer = new JButton(ImageData.referImage);
	protected JButton answerSave = new JButton(ImageData.saveImage);
	
	protected JButton homeButton = new JButton(ImageData.homeImage);
	
	public AppModel() {
		
		AppManager.CreateInstance().setAppModel(this);
		
		vocaMode = 0; //�⺻ ��
		pageState =0;
		
		setSize(Execute.WIDTH,Execute.HEIGHT);
		setUndecorated(true);						  // ������ Ÿ��Ʋ�ٸ� ���ش�.
	
		setResizable(false);																					//����ڰ� ���Ƿ� �ٲܼ� ����
		setLocationRelativeTo(null);									 										//���߾ӿ� �߰� ��
		setVisible(true);
		setBackground(new Color(0,0,0,0));
		setLayout(null);	
		setLocation(700,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// �⺻ �޴�
		menuBar.setBounds(0,0,800,30);
		menuBar.setVisible(true);
		add(menuBar);
		
		exitButton.setBounds(770, 0, 30, 30);
		exitButton.setBorderPainted(false);																		//�츮�� �Ϲ������� �����ϴ� Ŀ������ ������ ���ø��� �ƴϱ� ������ ������ ���־�� �Ѵ�.	
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.setVisible(true);
		menuBar.add(exitButton);
		
		//voca �߰� ��ư
		voca1Button.setBounds(70,130,200,300);
		voca1Button.setContentAreaFilled(false);
		voca1Button.setFocusPainted(false);
		voca1Button.setVisible(true);
		add(voca1Button);
		
		voca2Button.setBounds(300,130,200,300);
		voca2Button.setBorderPainted(false);																		//�츮�� �Ϲ������� �����ϴ� Ŀ������ ������ ���ø��� �ƴϱ� ������ ������ ���־�� �Ѵ�.	
		voca2Button.setContentAreaFilled(false);
		voca2Button.setFocusPainted(false);
		voca2Button.setVisible(true);
		add(voca2Button);
		
		voca3Button.setBounds(530,130,200,300);
		voca3Button.setBorderPainted(false);																		//�츮�� �Ϲ������� �����ϴ� Ŀ������ ������ ���ø��� �ƴϱ� ������ ������ ���־�� �Ѵ�.	
		voca3Button.setContentAreaFilled(false);
		voca3Button.setFocusPainted(false);
		voca3Button.setVisible(true);
		add(voca3Button);
	}

	public void paint(Graphics g)					//������ �ʱ� �۾���.
	{
		screenImage = createImage(Execute.WIDTH, Execute.HEIGHT);
		screenGraphic = screenImage.getGraphics(); //�׷��� ��ü�� ����.
		screenDraw((Graphics2D)screenGraphic);		//��Ʈ �������� ���� �� ����ȯ	//�׸��� ����
		g.drawImage(screenImage,0,0, null);			//��ũ�� �̹����� 0,0 ���� �׷���.
	}
	public void screenDraw(Graphics2D g) //2D�� ����ȯ
	{
		g.drawImage(background, 0, 0, null);	//�ܼ��ϰ� �̹����� �������. //�Ϲ����� �̹��� Ȥ��, gif �� �̰����� �����.
		paintComponents(g);							//�̹����� �ܼ��ϰ� �׷��ִ� �� �̿ܿ� JFrame�� JLabel�� �߰����ִ� ����̴�. (������ ��) //add�� ���Ѱ�
		try {
			Thread.sleep(5);
		}catch(Exception e){
			e.printStackTrace();
		}
		this.repaint();								//���α׷��� ����Ǵ� �������� ��� Ʋ���ְ� ��.
	}
	// ������ ��ư
	
	public void step2() {
		
		if(vocaMode ==1) {
			selectPanel = new JPanel() {
				 public void paintComponent(Graphics g) {
		                g.drawImage(ImageData.voca1Select.getImage(), 0, 0, null);
		                setOpaque(false); //�׸��� ǥ���ϰ� ����,�����ϰ� ����
		                super.paintComponent(g);
		            }
			};
		}
		else if(vocaMode == 2) {
			selectPanel = new JPanel() {
				 public void paintComponent(Graphics g) {
		                g.drawImage(ImageData.voca2Select.getImage(), 0, 0, null);
		                setOpaque(false); //�׸��� ǥ���ϰ� ����,�����ϰ� ����
		                super.paintComponent(g);
		            }
			};
		}
		else {
			selectPanel = new JPanel() {
				 public void paintComponent(Graphics g) {
		                g.drawImage(ImageData.voca3Select.getImage(), 0, 0, null);
		                setOpaque(false); //�׸��� ǥ���ϰ� ����,�����ϰ� ����
		                super.paintComponent(g);
		            }
			};
		}
		
		selectPanel.setBounds(50,50,700,100);
		selectPanel.setLayout(null);	 
		selectPanel.setVisible(true);
	
		// �޺��ڽ� �߰� (���� �ɼ� �� �߰����� Ȯ���ϱ�)
		fromCombo.setBounds(195,46,90,20);
		fromCombo.setVisible(true);
		fromCombo.setEnabled(true);
		selectPanel.add(fromCombo);
		
		toCombo.setBounds(424,46,90,20);
		toCombo.setEnabled(false);
		toCombo.setVisible(true);
		selectPanel.add(toCombo);
		add(selectPanel);
		
		
		//////////---------------------------------------------------------------------------------------------------
		optionPanel = new JPanel() {
			 public void paintComponent(Graphics g) {
	                g.drawImage(ImageData.optionSelect.getImage(), 0, 0, null);
	                setOpaque(false); //�׸��� ǥ���ϰ� ����,�����ϰ� ����
	                super.paintComponent(g);
	            }
		};
		
		optionPanel.setBounds(50, 200, 700, 300);
		optionPanel.setLayout(null);
		optionPanel.setVisible(true);
		
		enToKoCombo.setBounds(280,79,90,20);
		enToKoCombo.setVisible(true);
		enToKoCombo.setEnabled(false);
		optionPanel.add(enToKoCombo);
		
		koToEnCombo.setBounds(280,142,90,20);
		koToEnCombo.setVisible(true);
		koToEnCombo.setEnabled(false);
		optionPanel.add(koToEnCombo);
		
		blankCombo.setBounds(280,210,90,20);
		blankCombo.setVisible(true);
		blankCombo.setEnabled(false);
		optionPanel.add(blankCombo);
		//checkbox ���� �޼ҵ� �����ϱ�.
		
		checkOption1.setBounds(52,169,20,20);
		checkOption1.setBorderPainted(false);																		//�츮�� �Ϲ������� �����ϴ� Ŀ������ ������ ���ø��� �ƴϱ� ������ ������ ���־�� �Ѵ�.	
		checkOption1.setContentAreaFilled(false);
		checkOption1.setFocusPainted(false);
		checkOption1.setVisible(true);
		optionPanel.add(checkOption1);
		
		checkOption2.setBounds(52,232,20,20);
		checkOption2.setBorderPainted(false);																		//�츮�� �Ϲ������� �����ϴ� Ŀ������ ������ ���ø��� �ƴϱ� ������ ������ ���־�� �Ѵ�.	
		checkOption2.setContentAreaFilled(false);
		checkOption2.setFocusPainted(false);
		checkOption2.setVisible(true);
		optionPanel.add(checkOption2);
		
		maxQuiz.setBounds(608,72,50,25);
		maxQuiz.setEditable(false);
		maxQuiz.setVisible(true);
		optionPanel.add(maxQuiz);
		
		selectedQuiz.setBounds(608,125,50,25);
		selectedQuiz.setEditable(false);
		selectedQuiz.setVisible(true);
		optionPanel.add(selectedQuiz);
		add(optionPanel);
		
		foreButton.setBounds(150,520,150,50);
		foreButton.setBorderPainted(false);																		//�츮�� �Ϲ������� �����ϴ� Ŀ������ ������ ���ø��� �ƴϱ� ������ ������ ���־�� �Ѵ�.	
		foreButton.setContentAreaFilled(false);
		foreButton.setFocusPainted(false);
		foreButton.setVisible(true);
		add(foreButton);
	
		nextButton.setBounds(500,520,150,50);
		nextButton.setBorderPainted(false);																		//�츮�� �Ϲ������� �����ϴ� Ŀ������ ������ ���ø��� �ƴϱ� ������ ������ ���־�� �Ѵ�.	
		nextButton.setContentAreaFilled(false);
		nextButton.setFocusPainted(false);
		nextButton.setVisible(true);
		add(nextButton);
	
	}
	
	
	////step3---------------------------------------------------------------------------------
	public void step3() {
		testNamePanel = new JPanel() {
			public void paintComponent(Graphics g) {
                g.drawImage(ImageData.testNameImage.getImage(), 0, 0, null);
                setOpaque(false); //�׸��� ǥ���ϰ� ����,�����ϰ� ����
                super.paintComponent(g);
            }
		};
		
		// ���� �ɼ�
		testNamePanel.setBounds(50,50,700,200);
		testNamePanel.setLayout(null);	 
		testNamePanel.setVisible(true);
		//���� ��ư �� ĭ ä�� �ֱ�
		
		academyName.setBounds(170,70, 450,20);
		academyName.setVisible(true);
		testNamePanel.add(academyName);
		
		testName.setBounds(170,126, 450,20);
		testName.setVisible(true);
		testNamePanel.add(testName);
		
		
		makeTestButton.setBounds(275,147,150,50);
		makeTestButton.setBorderPainted(false);																		//�츮�� �Ϲ������� �����ϴ� Ŀ������ ������ ���ø��� �ƴϱ� ������ ������ ���־�� �Ѵ�.	
		makeTestButton.setContentAreaFilled(false);
		makeTestButton.setFocusPainted(false);
		makeTestButton.setVisible(true);
		testNamePanel.add(makeTestButton);
	
		
		add(testNamePanel);
		
		savePanel = new JPanel() {
			public void paintComponent(Graphics g) {
	                g.drawImage(ImageData.savePanelImage.getImage(), 0, 0, null);
	                setOpaque(false); //�׸��� ǥ���ϰ� ����,�����ϰ� ����
	                super.paintComponent(g);
	            }
		};
		// test�� ���� �������� Ȱ��ȭ ��Ű�� ����.
		
		// ���� �ɼ� 
		savePanel.setBounds(50, 300, 700, 200);
		savePanel.setLayout(null);
		savePanel.setVisible(true);
		
		quizRefer.setBounds(220,60,150,50);
		quizRefer.setContentAreaFilled(false);
		quizRefer.setFocusPainted(false);
		quizRefer.setVisible(true);
		quizRefer.setEnabled(false);
		savePanel.add(quizRefer);
	
		quizSave.setBounds(400,60,150,50);
		quizSave.setContentAreaFilled(false);
		quizSave.setFocusPainted(false);
		quizSave.setVisible(true);
		quizSave.setEnabled(false);
		savePanel.add(quizSave);
	
		answerRefer.setBounds(220,126,150,50);
		answerRefer.setContentAreaFilled(false);
		answerRefer.setFocusPainted(false);
		answerRefer.setVisible(true);
		answerRefer.setEnabled(false);
		savePanel.add(answerRefer);
		
		answerSave.setBounds(400,126,150,50);
		answerSave.setContentAreaFilled(false);
		answerSave.setFocusPainted(false);
		answerSave.setVisible(true);
		answerSave.setEnabled(false);
		savePanel.add(answerSave);
		add(savePanel);
		
		homeButton.setBounds(500,520,150,50);
		homeButton.setContentAreaFilled(false);
		homeButton.setBorderPainted(false);																		//�츮�� �Ϲ������� �����ϴ� Ŀ������ ������ ���ø��� �ƴϱ� ������ ������ ���־�� �Ѵ�.	
		homeButton.setFocusPainted(false);
		homeButton.setVisible(true);
		add(homeButton);
	}
}

