package Model;

public class Data {
    private int id;
    private String name;
    private String lname;
    private String age;
    private String description;
    private String timeStamp;

    public Data() {
    }

    public Data(int id, String name, String lname, String timeStamp, String description, String age) {
        this.id = id;
        this.name = name;
        this.lname = lname;
        this.age = age;
        this.description = description;
        this.timeStamp = timeStamp;
    }

    public Data(String name, String lname, String age, String description) {
        this.name = name;
        this.lname = lname;
        this.age = age;
        this.description = description;
    }

    public Data(int id, String name, String lname, String age, String description) {
        this.id = id;
        this.name = name;
        this.lname = lname;
        this.age = age;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
