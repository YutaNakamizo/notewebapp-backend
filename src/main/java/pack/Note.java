package pack;

/**
 * 
 * 
 * @version 1.0　17 Sep 2020
 * @authour Tororocombu
 * メモの実体を新規作成するクラス
 * 
 * @version 2.0 02 Sep 2020
 * @author Tororocombu
 * http通信の実装
 * 
 */
import java.util.Calendar;
import java.util.UUID;	//固有のIDを生成できる


public class Note{

    
    private long dateCreated; //get in this class
    private long dateLastModified;
    private String id;  // create in this class
    private boolean archived;
    
    private String title;
    private String body;
    
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
    	
        
        System.out.println("Create Success!!");

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
    
    
    
    //ゲッター for all field
    public long getDateCreated() {
    	return dateCreated;
    }
    
    public String getId() {
    	return id;
    }
    
    public String getStatus() {
    	return status;
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
    
 
    
}
