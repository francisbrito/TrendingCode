package example.com.trendingcode;

/**
 * Created by miguel on 03/30/15.
 */
public class Comment {

    private String text;
    private int repoID;

    public Comment(String text, int repoID){
        this.text = text;
        this.repoID = repoID;
    }

    public Comment(){}

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

    /**
     * Sets the repository ID.
     *
     * @param  id
     */
    public void setrepoID(Integer id){
        this.repoID = id;
    }

    /**
     * Gets the repository ID.
     *
     * @return id
     */
    public Integer getRepoID(){
        return this.repoID;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
