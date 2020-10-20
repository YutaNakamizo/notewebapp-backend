package pack;

/**
 * @author Tororocombu
 * 2020 10 18	at feat/#5		make setter for Archived 
 * 
 * @author Tororocombu
 * 2020 10 11   at feat/#4		make setter for title and body　and dateLastModified
 * 								
 * 
 * @authour Tororocombu
 * メモの実体を新規作成するクラス
 * 
 * @author Tororocombu
 * http通信の実装
 * 
 */
import java.util.Calendar;
import java.util.UUID;	//固有のIDを生成できる


public class Note{
	
    public String id;
    public String title;
    public String body;
    public long dateCreated;
    public long dateLastModified;
    public Boolean archived;
    

      
    /**
     * just set data to note class
     * @param title 引数
     * @param body 
     * 
     */
    public Note(String id,String title,String body,long dateCreated,long dateLastModified,boolean archived){
    	
        
        //-------------対策する
    	this.id		=	id;
    	this.title	= title;
    	this.body	= body;
    	this.dateCreated	= dateCreated;
    	this.dateLastModified	= dateLastModified;
    	this.archived	= archived;
    	
        
        System.out.println("Note method create....");
        
          showState();
    }
    
    /**
     *  create new ID and set it to new note 
     * @param title
     * @param body
     * @return
     */
    public static Note create(String title, String body) {
        
    	long dateCreated = Calendar.getInstance().getTimeInMillis();
        String id = UUID.randomUUID().toString();
        boolean archived = false;
        
        System.out.println("Create Success!!");
        return new Note(id, title, body, dateCreated, dateCreated, archived);
    }
    
    
    public void showState() {
    	
    	System.out.println("ID:"+ this.id);
        System.out.println("date:"+ this.dateCreated);
        System.out.println("Last Modified:"+this.dateLastModified);
        System.out.println("archived:"+this.archived);
        System.out.println("title:"+this.title);
        System.out.println("body:"+this.body+"\n");
    }
    
    
    
    //ゲッター for all field
    public long getDateCreated() {
    	return dateCreated;
    }
    
    public String getId() {
    	return id;
    }
    
    public long getDateLastModified(){
    	return dateLastModified;
    }
    
    public boolean getArchived() {
    	return archived;
    }
    
    public String getTitle() {
    	return title;
    }
    
    public String getBody() {
    	return body;
    }
    
    //セッター
    public void setTitle(String newTitle) {
    	this.title = newTitle;
    }
    
    public void setBody(String newBody) {
    	this.body = newBody;
    }
    
    public void setDateLastModified() {
    	this.dateLastModified = Calendar.getInstance().getTimeInMillis();
    	
    } 
    
    public void setArchived(boolean archived) {
    	this.archived = archived;
    	System.out.println("\n---Archived now: "+this.archived);
    }
    
}
