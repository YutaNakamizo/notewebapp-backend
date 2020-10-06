
import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
    name = "CreateNewNote",
    urlPatterns = {"/api/notes"}
)


public class CreateNewNote extends HttpServlet {

  @Override
  public void doPut(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
	  
	  //データ受け取り
	  request.setCharacterEncoding("Windows-31J");	//受け取るデータ型の文字系列指定
	  String title = request.getParameter("title");	//データ受け取り
	  String body  = request.getParameter("body");
	  
	  //ノート作成
	  Note newnote = new Note(title,body); 
	  
	  //レスポンスの設定
      response.setContentType("text/plain");	//コンテントタイプの指定
      response.setCharacterEncoding("UTF-8");	//送るデータの文字系列の指定
      
      //レスポンス内容 出力する
      response.getWriter().println("Get Put Request!");
      
      response.getWriter().println("ID:"+ newnote.getId());
      response.getWriter().println("date:"+ newnote.getDateCreated());
      response.getWriter().println("status:"+ newnote.getStatus());
      response.getWriter().println("archived:"+newnote.getArchived());
      response.getWriter().println("title:"+newnote.getTitle());
      response.getWriter().println("body:"+newnote.getBody());
     
    

  }
}

