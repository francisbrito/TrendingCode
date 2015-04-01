package example.com.trendingcode;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class GithubRepository implements Serializable{
    private static final String ID_FIELD = "id";
    private static final String NAME_FIELD = "name";
    private static final String FULL_NAME_FIELD = "full_name";
    private static final String DESCRIPTION_FIELD = "description";
    private static final String STARS_FIELD = "stargazers_count";
    private static final String WATCHERS_FIELD = "watchers_count";
    private static final String LANGUAGE_FIELD = "language";

    private Integer id;
    private String name;
    private String full_name;
    private String description;
    private Integer stargazers_count;
    private Integer watchers_count;
    private String language;

    public GithubRepository(){}

    /**
     * Construct.
     * @param id
     * @param name
     * @param fullName
     * @param description
     * @param stars
     * @param watchers
     * @param language
     */
    public GithubRepository(Integer id, String name, String fullName, String description,
                            Integer stars, Integer watchers, String language) {
        this.id = id;
        this.name = name;
        this.full_name = fullName;
        this.description = description;
        this.stargazers_count = stars;
        this.watchers_count = watchers;
        this.language = language;
    }

    /**
     * Construct.
     */

    /**
     * Gets the id.
     *
     * @return id
     */
    public Integer getID() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id
     */
    public void setID(Integer val) {
        this.id = val;
    }

    /**
     * Gets the name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name
     */
    public void setName(String val) {
        this.name = val;
    }

    /**
     * Gets the full_name.
     *
     * @return full_name
     */
    public String getFullName() {
        return full_name;
    }

    /**
     * Sets the full_namename.
     *
     * @param full_namename
     */
    public void setFullName(String val) {
        this.full_name = val;
    }

    /**
     * Gets the description.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description
     */
    public void setDescription(String val) {
        this.description = val;
    }

    /**
     * Gets the stargazers_count.
     *
     * @return stargazers_count
     */
    public Integer getStargazersCount() {
        return stargazers_count;
    }

    /**
     * Sets the stargazers_count.
     *
     * @param stargazers_count
     */
    public void setStargazersCount(Integer val) {
        this.stargazers_count = val;
    }

    /**
     * Gets the watchers_count.
     *
     * @return watchers_count
     */
    public Integer getWatchersCount() {
        return watchers_count;
    }

    /**
     * Sets the watchers_count.
     *
     * @param watchers_count
     */
    public void setWatchersCount(Integer val) {
        this.watchers_count = val;
    }

    /**
     * Gets the language.
     *
     * @return language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the language.
     *
     * @param language
     */
    public void setLanguage(String val) {
        this.language = val;
    }

    public static GithubRepository fromJSON(String jsonStr) throws JSONException {
        JSONObject json = new JSONObject(jsonStr);

        return GithubRepository.fromJSON(json);
    }

    @Override
    public String toString() {
        return this.full_name;
    }

    public static GithubRepository fromJSON(JSONObject json) throws JSONException {
        Integer id = json.getInt(ID_FIELD);
        String name = json.getString(NAME_FIELD);
        String fullName = json.getString(FULL_NAME_FIELD);
        String description = json.getString(DESCRIPTION_FIELD);
        Integer stars = json.getInt(STARS_FIELD);
        Integer watchers = json.getInt(WATCHERS_FIELD);
        String language = json.getString(LANGUAGE_FIELD);

        return new GithubRepository(id, name, fullName, description, stars, watchers, language);
    }

}
