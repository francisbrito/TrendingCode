package example.com.trendingcode;

/**
 * Created by dripoll24 on 3/25/2015.
 */
public class Repositories {
    private long id;
    private String name;
    private String fullName;
    private String URL;
    private String description;
    private String starGazers;
    private String watchers;
    private String forks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStarGazers() {
        return starGazers;
    }

    public void setStarGazers(String starGazers) {
        this.starGazers = starGazers;
    }

    public String getWatchers() {
        return watchers;
    }

    public void setWatchers(String watchers) {
        this.watchers = watchers;
    }

    public String getForks() {
        return forks;
    }

    public void setForks(String forks) {
        this.forks = forks;
    }
}
