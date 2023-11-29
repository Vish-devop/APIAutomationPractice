package pojo;

import java.util.List;

public class postRequestBody {

    private String job;
    private String name;
    private List<String> languages;

    private List<cityRequest> cityRequestBody;
    public List<cityRequest> getcityRequestBody() {
        return cityRequestBody;
    }

    public void setCityRequestBody() {
        this.cityRequestBody = cityRequestBody;
    }
    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }


    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

}
