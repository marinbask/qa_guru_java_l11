package json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class JsonDescription {
    private final String name;
    private final String age;
    private final String profession;
    private final String[] skills;

    @JsonCreator
    public JsonDescription(@JsonProperty("name") String name,
                           @JsonProperty("age") String age,
                           @JsonProperty("profession") String profession,
                           @JsonProperty("skills") String[] skills) {
        this.name = name;
        this.age = age;
        this.skills = skills;
        this.profession = profession;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getProfession() {
        return profession;
    }

    public String[] getSkills() {
        return skills;
    }
}