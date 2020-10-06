
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;




import pack.Note;

@WebServlet(
    name = "CreateNewNote",
    urlPatterns = {"/api/notes"}
)


public class CreateNewNote extends HttpServlet {

  @Override
  public void doPut(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
	  
	  //データ受け取り
	  request.setCharacterEncoding("UTF-8");	//受け取るデータ型の文字系列指定
	  String title = request.getParameter("title");	//データ受け取り
	  String body  = request.getParameter("body");
	  
	  //ノート作成
	  Note newnote = Note.create(title,body); 
	  
	  
	  //Note class -> JSON
	  ObjectMapper mapper = new ObjectMapper();
	  	
	    
	  //JSON形式の文字列に変換
	  String jsonStringNote = mapper.writeValueAsString(newnote);

	  //JSON形式の文字列を表示
	  System.out.println(jsonStringNote);
	    
	  
	  
	  //レスポンスの設定
      response.setContentType("application/json");	//コンテントタイプの指定
      response.setCharacterEncoding("UTF-8");	//送るデータの文字系列の指定
      
      //レスポンス内容 返す
      
      	PrintWriter writer = response.getWriter();
      	writer.println(jsonStringNote);
      	writer.flush();
      	
      	System.out.println(jsonStringNote);
      	
      	
      	//for text
//      writer.println("Get Put Request!");
//      
//      writer.println("ID:"+ newnote.getId());
//      writer.println("date:"+ newnote.getDateCreated());
//      writer.println("status:"+ newnote.getStatus());
//      writer.println("archived:"+newnote.getArchived());
//      writer.println("title:"+newnote.getTitle());
//      writer.println("body:"+newnote.getBody());
     
    

  }
}

