package example.com.trendingcode;

/**
 * Created by miguel on 03/30/15.
 */
public class Comment {

    private int id;
    private String text;

    public Comment(int id, String txt){
        this.id = id;
        this.text = txt;
    }

    /**
     * Sets the id.
     *
     * @param  id
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * Gets the id.
     *
     * @return id
     */
    public int getId(){
        return this.id;
    }

    /**
     * Sets the text.
     *
     * @param  text
     */
    public void setText(String text){
        this.text = text;
    }

    /**
     * Gets the text.
     *
     * @return text
     */
    public String getText(){
        return this.text;
    }
}
