package example.com.trendingcode;

public class GithubRepository
{
    private Integer id;
    private String name;
    private String full_name;
    private String description;
	private Integer stargazers_count;
	private Integer watchers_count;
	private String language;

    /**
     * Construct.
     */
    public GithubRepository()
    {		
    }

    /**
     * Construct.
     */

    /**
     * Gets the id.
     * 
     * @return id
     */
    public String getID()
    {
        return id;
    }

    /**
     * Sets the id.
     * 
     * @param id
     *
     */
    public void setID(Integer val)
    {
        this.id = val;
    }

	 /**
     * Gets the name.
     * 
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name.
     * 
     * @param name
     *
     */
    public void setName(String val)
    {
        this.name = val;
    }
	
    /**
     * Gets the full_name.
     * 
     * @return full_name
     */
    public String getFullName()
    {
        return full_name;
    }

    /**
     * Sets the full_namename.
     * 
     * @param full_namename
     *
     */
    public void setFullName(String val)
    {
        this.full_name = val;
    }

    /**
     * Gets the description.
     * 
     * @return description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Sets the description.
     * 
     * @param description
     *
     */
    public void setDescription(String val)
    {
        this.description = val;
    }

    /**
     * Gets the stargazers_count.
     * 
     * @return stargazers_count
     */
    public Integer getStargazersCount()
    {
        return stargazers_count;
    }

    /**
     * Sets the stargazers_count.
     * 
     * @param stargazers_count
     *
     */
    public void setStargazersCount(Integer val)
    {
        this.stargazers_count = val;
    }

    /**
     * Gets the watchers_count.
     * 
     * @return watchers_count
     */
    public Integer getWatchersCount()
    {
        return watchers_count;
    }

    /**
     * Sets the watchers_count.
     * 
     * @param watchers_count
     *
     */
    public void setWatchersCount(Integer val)
    {
        this.watchers_count = val;
    }
	
	 /**
     * Gets the language.
     * 
     * @return language
     */
    public String getLanguage()
    {
        return language;
    }

    /**
     * Sets the language.
     * 
     * @param language
     *
     */
    public void setLanguage(String val)
    {
        this.language = val;
    }
}
