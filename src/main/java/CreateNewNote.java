/**
 * 
 * 2020/10/08 at feat/#15   make doGet request ,which return all notes 
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;


import pack.Note;



@WebServlet(
    name = "CreateNewNote",
    urlPatterns = {"/api/notes"}
)

//

// ローカルループバックアドレス　http://127.0.0.1:8080/api/
public class CreateNewNote extends HttpServlet {

  @Override
  public void doPut(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
	  
	  
	  //----データ受け取り------------------------------------------------------------
	  request.setCharacterEncoding("UTF-8");	//受け取るデータ型の文字系列指定

	  
	    
	      //. JSON テキストを全部取り出す
	      BufferedReader br = new BufferedReader( request.getReader() );
	      
	      String jsonText = br.readLine();
	      jsonText = URLDecoder.decode( jsonText, "UTF-8" );
	      System.out.println( jsonText );

	      //. JSON オブジェクトに変換
	      JSONParser parser = new JSONParser();
	      
		
		  JSONObject jsonObj = null;
		try {
			jsonObj = ( JSONObject )parser.parse( jsonText );
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		

	      //. JSON オブジェクトから特性の属性を取り出す
	      String title= ( String )jsonObj.get( "title" );
	      String body = ( String )jsonObj.get( "body" );
	      
	      
	    
	  //ノートクラスをJSONへ変換してレスポンス--------------------------------------------------------------
	  
	  
	  //ノート作成
	  Note newnote = Note.create(title,body); 
	  
	  
	  //Note class -> JSON
	  ObjectMapper mapper = new ObjectMapper();
	  String jsonStringNote = mapper.writeValueAsString(newnote);

	  //JSON形式の文字列を表示
	  System.out.println(jsonStringNote);
	    
	  
	  //レスポンスの設定
      response.setContentType("application/json");	//コンテントタイプの指定
      response.setCharacterEncoding("UTF-8");	//送るデータの文字系列の指定
      
      //レスポンス内容 
      	PrintWriter writer = response.getWriter();
      	writer.println(jsonStringNote);
      	writer.flush();
      	
      	System.out.println(jsonStringNote);
      	
    

  }
  

  

public Note[] makeNoteArray() {
	  
	  Note[] notes = new Note[3];
	  
	  String title1 ="tamanegi";
	  String body1  ="3ko"; 
	  
	  String title2 ="tomato";
	  String body2  ="2ko"; 
	  
	  String title3 ="niku";
	  String body3  ="200g"; 


    
  //ノート作成
	  notes[0] = Note.create(title1,body1); 
	  notes[1] = Note.create(title2,body2); 
	  notes[2] = Note.create(title3,body3); 
	  
	  return notes;
  }
  
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {

//    response.setContentType("text/plain");
//    response.setCharacterEncoding("UTF-8");
//
//    response.getWriter().print("Hello World create new Note!!!\r\n");
	  
	  //ここで配列を初期化している
	  Note[] notes = makeNoteArray(); 
	  
	  
	  //Note class -> JSON
	  ObjectMapper mapper = new ObjectMapper();
	  String jsonStringNote = mapper.writeValueAsString(notes);

	  //JSON形式の文字列をコンソールに表示
	  System.out.println(jsonStringNote);
	    
	  
	  //レスポンスの設定
	  response.setContentType("application/json");	//コンテントタイプの指定
	  response.setCharacterEncoding("UTF-8");	//送るデータの文字系列の指定
    
    //レスポンス内容 
	  PrintWriter writer = response.getWriter();
	  writer.println(jsonStringNote);
	  writer.flush();
	  
    
  }
}

