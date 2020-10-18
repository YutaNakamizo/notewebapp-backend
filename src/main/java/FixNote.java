/**
 * @author Tororocombu
 * 2020/10/18 at feat/#5	start to make doDelete request
 * 
 * @author　Tororocmbu
 * 2020/10/16 at feat/#4	finished creating doPost request
 * 
 * @author Tororocombu
 * 2020/10/11 at feat/#4	make doGet request(for debug),
 * 							start to make doPost request
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Calendar;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import pack.Note;


//	/*と指定すると、それ以降のpathは任意になる。
@WebServlet(
    name = "FixNote",
    urlPatterns = {"/api/notes/*"}
)

public class FixNote extends HttpServlet{
	
	/**
	 * Just show request body
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException{
		
		//for debug
		String uri = request.getRequestURI();
		String servPath = request.getServletPath();
		String id = StringUtils.difference(servPath+"/",uri);
		
		System.out.println("uri:"+uri);	
		System.out.println("servPath:"+servPath);	
		System.out.println("id:"+id);
		
		
		response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
 	    response.getWriter().print("Hello World Fix Note!!!\r\n");
		  
		
	}
	
	
	/**
	 * 
	 * @param 	id:	対象のノートのID
	 * @return 	Noteクラスの　Json 文字列
	 * 
	 * DBの代わりになるメソッド	:渡したidと一致するnoteクラスのJSONを返すという設定
	 * デバッグの都合上　渡したidを用いてクラス作成している 
	 */
	
	public String substituteForDB(String id) {
		System.out.println("----In substituteForDB");
		
		String title="test";
		String body = "now testing";
		long dateCreated = Calendar.getInstance().getTimeInMillis();
		
		//渡したidと同じ
		Note target = new Note(id,title,body,dateCreated,dateCreated,false);
		
		//Note ->  JSON
		ObjectMapper mapper = new ObjectMapper();
		String targetStringJson = null;
		try {
			targetStringJson = mapper.writeValueAsString(target);
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		
		//for debug
		//System.out.println("In DB: "+targetStringJson);
		
		System.out.println("change this note to json and send back...");
		System.out.println("Finish substituteForDB\n");
		
		return targetStringJson;
	}
	

/**
 * putリクエストのタスクを8つのプロセスと定義
 * 
 * １	id,title,body の受け取り
 * 2	idを基に　DBへ該当するデータの問い合わせ		←今回は実装しない
 * 3	DBから　Json形式で送り返されるデータの受け取り	←メソッドを用いてDBの代用  substituteForDB（String）メソッド
 * 4	3のJsonを基に　Noteクラスを作成。
 * 5	作成したクラスのtitle,bodyを更新、更新日時の取得
 * 6	更新したNoteクラスをJson形式にする
 * 7	更新後JsonをDBへ送り返す							←今回は実装しない
 * 8	更新後jsonをレスポンス
 * 
 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException{
		
		
		//---	１	id,title,body の受け取り
		String uri = request.getRequestURI();
		String servPath = request.getServletPath();
		String id = StringUtils.difference(servPath+"/",uri);
		
		//for debug		
		System.out.println("uri:"+uri);	
		System.out.println("servPath:"+servPath);	
		System.out.println("id:"+id);
		
		  request.setCharacterEncoding("UTF-8");	//受け取るデータ型の文字系列指定

		  //. JSON テキストを全部取り出す
		  BufferedReader br = new BufferedReader( request.getReader() );
		      
	      String jsonText = br.readLine();
		  jsonText = URLDecoder.decode( jsonText, "UTF-8" );
		  
		  //for debug
		  System.out.println( "Request Body: "+jsonText + "\n" );
		  
	      //. JSON オブジェクトに変換
		  JSONParser parser = new JSONParser();
	      JSONObject jsonObj = null;
	      
		  try {
			  jsonObj = ( JSONObject )parser.parse( jsonText );
		  } catch (ParseException e) {
			  e.printStackTrace();
		   }
			
		   //. JSON オブジェクトから特性の属性を取り出す
		   String newTitle= ( String )jsonObj.get( "title" );
	       String newBody = ( String )jsonObj.get( "body" );
		
		
	       //---	2	idを基に　DBへ該当するデータの問い合わせ		←今回は実装しない
	       //---	3	DBから　Json形式で送り返されるデータの受け取り	←メソッドを用いてDBの代用  
	       String jsonStringFromDB = substituteForDB(id);
	       System.out.println("----In doPost,change received Json to Note");
	       //System.out.println("In doPost: " + jsonStringFromDB);

	       
	       
	       //---	4	3のJsonを基に　Noteクラスを作成。
	       	//JSON変換用のクラス
	       JSONObject jsonObjFromDB = null;
			try {
				jsonObjFromDB = ( JSONObject )parser.parse( jsonStringFromDB );
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
			

			//. JSON オブジェクトから特性の属性を取り出す
		   String idFromDB				= ( String )jsonObjFromDB.get("id");
		   String title				= ( String )jsonObjFromDB.get( "title" );
		   String body 				= ( String )jsonObjFromDB.get( "body" );
		   long   dateCreated 		= ( long )jsonObjFromDB.get("dateCreated");
		   long	  dateLastModified 	= ( long )jsonObjFromDB.get("dateLastModified");
		   boolean archived 		= ( boolean )jsonObjFromDB.get("archived");
		   
		   
	       System.out.println("Note from DB");
	       Note noteRes = new Note(id,title,body,dateCreated,dateLastModified,archived);
	       
	       
	       //---	5	作成したクラスのtitle,bodyを更新、更新日時の取得
	       noteRes.setTitle(newTitle);
	       noteRes.setBody(newBody);
	       noteRes.setDateLastModified();
	       
	       System.out.println("\n---Note was fixed");
	       noteRes.showState();
	       
	       //---	6	更新したNoteクラスをJson形式にする
	       ObjectMapper mapper = new ObjectMapper();
	 	   String JsonStringRes = mapper.writeValueAsString(noteRes);
	       
	 	   
	       //---	7	更新後JsonをDBへ送り返す							←今回は実装しない
	       //---	8	更新後jsonをレスポンス
	 	   
	 	   //JSON形式の文字列をコンソールに表示
	 	  System.out.println("Responce:"+JsonStringRes);
	 	    
	 	  
	 	  //レスポンスの設定
	 	  response.setContentType("application/json");	//コンテントタイプの指定
	 	  response.setCharacterEncoding("UTF-8");	//送るデータの文字系列の指定
	     
	     //レスポンス内容 
	 	  PrintWriter writer = response.getWriter();
	 	  writer.println(JsonStringRes);
	 	  writer.flush();
	       	
	}//	doPost メソッド
	
	/**
	 * 
	 * [/api/notes/id]へのdeleteリクエストを受け付けるクラス。
	 * 以下のプロセスで実装
	 * 1	リクエストに含まれるノートIDの取得
	 * 2	DBを参照					←今回は実装しない
	 * 3	削除対象のデータ取得		←substituteForDBメソッドで代用
	 * 4 	3のJsonを基に　Noteクラスを作成。
	 * 5	作成したクラスの	Archive	を変更
	 * 6	更新したNoteクラスをJson形式にする
	 * 
	 * 7	更新後JsonをDBへ送り返す	???? OR 削除要請 ?????			←今回は実装しない
	 * 
	 * 8	更新後jsonをレスポンス
	 * 
	 */
	
	@Override 
	public void doDelete(HttpServletRequest request, HttpServletResponse response)
		throws IOException{ 
		
		
		//----1　リクエストに含まれるノートIDの取得  
		String uri = request.getRequestURI();
		String servPath = request.getServletPath();
		String id = StringUtils.difference(servPath+"/",uri);
		
		//for debug		
		System.out.println("uri:"+uri);	
		System.out.println("servPath:"+servPath);	
		System.out.println("id:"+id);
		
		
		//----2　DBを参照				←今回は実装しない
	    //----3 DBから削除対象のデータ取得		←substituteForDBメソッドで代用
	     
	    String jsonStringFromDB = substituteForDB(id);
	    System.out.println("----In doDelete,received target json");
	    //System.out.println("In doDelete: " + jsonStringFromDB);
	     
	       
	    //---	4	3のJsonを基に　Noteクラスを作成。
	     //JSON変換用のクラス
	    JSONParser parser = new JSONParser();
	    JSONObject jsonObjFromDB = null;
	    try {
	    	jsonObjFromDB = ( JSONObject )parser.parse( jsonStringFromDB );
		} catch (ParseException e) {
				
			e.printStackTrace();
		}
			

		//. JSON オブジェクトから特性の属性を取り出す
		String idFromDB				= ( String )jsonObjFromDB.get("id");
		String title				= ( String )jsonObjFromDB.get( "title" );
		String body 				= ( String )jsonObjFromDB.get( "body" );
		long   dateCreated 		= ( long )jsonObjFromDB.get("dateCreated");
		long	  dateLastModified 	= ( long )jsonObjFromDB.get("dateLastModified");
		boolean archived 		= ( boolean )jsonObjFromDB.get("archived");
		   
		   
	    System.out.println("Note from DB");
	    Note noteRes = new Note(id,title,body,dateCreated,dateLastModified,archived);
	       
	       
	    //---	5	作成したクラスの	Archive	を変更,  更新日時の取得
	    noteRes.setArchived(true);
	    noteRes.setDateLastModified();
	    noteRes.showState();
	       
	       
	    //---	6	更新したNoteクラスをJson形式にする
	    ObjectMapper mapper = new ObjectMapper();
	 	String JsonStringRes = mapper.writeValueAsString(noteRes);
	       
	 	   
	 	//---	7	更新後JsonをDBへ送り返す	???? OR 削除要請 ?????			←今回は実装しない
	    //---	8	更新後jsonをレスポンス
	 	   
	 	//JSON形式の文字列をコンソールに表示
	 	System.out.println("Responce:"+JsonStringRes);
	 	    
	 	  
	 	//レスポンスの設定
	 	response.setContentType("application/json");	//コンテントタイプの指定
	 	response.setCharacterEncoding("UTF-8");	//送るデータの文字系列の指定
	     
	    //レスポンス内容 
	 	PrintWriter writer = response.getWriter();
	 	writer.println(JsonStringRes);
	 	writer.flush();   
	 	
	}	//doDelete method
	

}
