
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
    private String id;  // create in this class
    
    private String status;
    private boolean archived;
    private String title;
    private String body;
    
    /**
     * 
     * @param title 引数
     * @param body 
     * 
     */
    public Note(String title,String body){
    	
        Calendar dateCreated = Calendar.getInstance();
        this.dateCreated = dateCreated.getTimeInMillis();	// long
        this.id = UUID.randomUUID().toString();
        this.status = "success";
        this.archived = false;
        
        //-------------対策する
        this.title = title;	//
        this.body = body;

        System.out.println("Create Success!!");

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
