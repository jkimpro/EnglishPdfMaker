import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright Junhyuk Kim /2019 Human League all rights reserved.
 * 
 * AppDAO.java
 * 
 * @author Junhyuk Kim (Short term worker)
 * 
 * @date 06.25.19
 * @last_changed 07.17.19
 * @test_date 07.18.19
 * 
 */

/*
 * 1. csv 크롤링 모듈
 * 2. pdf 생성 모듈
 * 
 */
public class AppDAO {
	
	protected List<List<String>> ret = new ArrayList<List<String>>();
   
	public AppDAO() {
		AppManager.CreateInstance().setAppDAO(this); //설정	
	}
	
	public void crollingFromCSV() {
		BufferedReader br = null;
	    try{
			br = Files.newBufferedReader(Paths.get("src/database/database.csv"));
			Charset.forName("UTF-8");
			String line = "";
		
			while((line = br.readLine()) != null){
				    //CSV 1행을 저장하는 리스트
					List <String> tmpList = new ArrayList<String>();
					
					String array[] = line.split(",");
					//배열에서 리스트 반
					tmpList = Arrays.asList(array);
					
					System.out.println(tmpList);
					
					Word word = new Word();
					word.setBook(removeDoubleQuotes(tmpList.get(0)));
					word.setDay(removeDoubleQuotes(tmpList.get(1)));
					if(!word.getBook().equals("book")) {
						WordSelection.vocaMax[Integer.parseInt(word.getBook())-1] = 
								Math.max(WordSelection.vocaMax[Integer.parseInt(word.getBook())-1],
								Integer.parseInt(word.getDay()));	
					}
					word.setKorean(removeDoubleQuotes(tmpList.get(2)));
					word.setName(removeDoubleQuotes(tmpList.get(3)));
					word.setSentence1(removeDoubleQuotes(tmpList.get(4)));
					word.setTranslation1(removeDoubleQuotes(tmpList.get(5)));
					
					if(tmpList.size() > 6) {
						word.setSentence2(removeDoubleQuotes(tmpList.get(6)));
						word.setTranslation2(removeDoubleQuotes(tmpList.get(7)));	
					}
					if(tmpList.size() >8) {
						word.setSentence3(removeDoubleQuotes(tmpList.get(8)));
						word.setTranslation3(removeDoubleQuotes(tmpList.get(9)));		
					}
					if(tmpList.size() > 10) {
						word.setSentence3(removeDoubleQuotes(tmpList.get(10)));
						word.setTranslation3(removeDoubleQuotes(tmpList.get(11)));	
					}
					
					String one = new String("1");
					String two = new String("2");
					String three = new String("3");
				
					//책 별로 분류하기
					if(word.getBook().equals(one)) {
						WordSelection.voca1Selection.add(word);
						System.out.println("selection1 insert");
					}
					else if(word.getBook().equals(two)) {
						WordSelection.voca2Selection.add(word);
						System.out.println("selection2 insert");
					}
					else if(word.getBook().equals(three)) {
						WordSelection.voca3Selection.add(word);
						System.out.println("selection3 insert");
					}
					ret.add(tmpList);
					System.out.println(WordSelection.vocaMax[0] +" " +WordSelection.vocaMax[1] +" " + WordSelection.vocaMax[2] +" ");
				}
			}
		    catch(FileNotFoundException e){
		            e.printStackTrace();
		        }
		    catch(IOException e){
		            e.printStackTrace();
		        }
		    finally{
		            try{
		                if(br != null){
		                    br.close();
		                }
		            }catch(IOException e){
		                e.printStackTrace();
		          }
		    }
	}
	
	public void calculateEnableWordTestNum() {
		int voca = AppManager.CreateInstance().getAppModel().vocaMode;
		int fromNum = Integer.parseInt(AppManager.CreateInstance().getAppModel().fromCombo.getSelectedItem().toString());
		int toNum = Integer.parseInt(AppManager.CreateInstance().getAppModel().toCombo.getSelectedItem().toString());
	
		WordSelection.mainSelection.clear();
		if(voca ==1) {
			for(int i =0; i<WordSelection.voca1Selection.size(); i++) {
				int temp = Integer.parseInt(WordSelection.voca1Selection.get(i).getDay());
				if(temp >= fromNum && temp <=toNum) {
					WordSelection.mainSelection.add(WordSelection.voca1Selection.get(i));
				}
			}
		}
		else if(voca ==2) {
			for(int i =0; i<WordSelection.voca2Selection.size(); i++) {
				int temp = Integer.parseInt(WordSelection.voca2Selection.get(i).getDay());
				if(temp >= fromNum && temp <=toNum) {
					WordSelection.mainSelection.add(WordSelection.voca2Selection.get(i));
				}
			}
			
		}
		else {
			for(int i =0; i<WordSelection.voca3Selection.size(); i++) {
				int temp = Integer.parseInt(WordSelection.voca3Selection.get(i).getDay());
				if(temp >= fromNum && temp <=toNum) {
					WordSelection.mainSelection.add(WordSelection.voca3Selection.get(i));
				}
			}
		}
		
	}
	
	
	
	public void makeTest() {
		
	}
	
	public void getCountAllDays() {
		
	}
	public void getRandomNum() {
		
	}
	public void searchData(String word) {
	
	}

	public void makePdfFile() {
		ArrayList <Word> option[];
		
	}
	
	public static String removeDoubleQuotes(String input){	//큰 따옴표 제거 메소드
		StringBuilder sb = new StringBuilder();
		char[] tab = input.toCharArray();
		for( char current : tab ){
		    if( current != '"' )
			sb.append( current );	
		}
		return sb.toString();
	}
}
