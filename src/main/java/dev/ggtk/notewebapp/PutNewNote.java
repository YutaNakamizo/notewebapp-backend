/**
 * 
 * @version 1.0　17 Sep 2020
 * @authour Tororocombu
 * メモの実体を新規作成するクラス
 * 
 */
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.codec.digest.DigestUtils;

public class PutNewNote{

    private static int SUCCESS=1;
    
    /*----dateCreated::仕様と違って,Calendarクラス使ってる-----------------*/
    private Calendar dateCreated; //get in this class
    private String id;  // create in this class
    
    private int status;
    private boolean archived;
    private String title;
    private String body;
    
    /**
     * 
     * @param title メモのタイトル
     * @param body  メモの内容
     * 
     */
    public PutNewNote(String title,String body){
        Calendar dateCreated = Calendar.getInstance();
        this.dateCreated = dateCreated;
        this.id = createID(title,dateCreated);   //method
        this.status = SUCCESS;
        this.archived = false;

        this.title = title;
        this.body = body;

        System.out.println("Create Success!!");

    }

    /**
     * 
     * @param title
     * @param dateCreated
     * @return id  SHA256 を使用
     * 
     */
    private String createID(String title,Calendar dateCreated){
        
        // class change:      Calendar -> Date -> String 
        Date date = dateCreated.getTime();
        String dateStr = date.toString(); 
        
        String random = RandomStringUtils.randomAlphabetic(5);
        String seed = title + dateStr+ random;
        String id = DigestUtils.sha256Hex(seed);
        id = id.substring(0, 24);
        
        return id;
    }

    public static void main(String[] args){

        String title = "かいもの";
        String body = "トマト、バナナ、おかし";

        PutNewNote note = new PutNewNote(title,body);

        System.out.println("dateCreated:"+note.dateCreated);
        System.out.println("id:"+note.id);
        System.out.println("status:"+note.status);
        System.out.println("archived:"+note.archived);
        System.out.println("title:"+note.title);
        System.out.println("body:"+note.body);

    }





}