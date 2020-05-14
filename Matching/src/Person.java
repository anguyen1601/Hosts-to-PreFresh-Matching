public class Person{

    private String name;
    private String major;
    private String room;

    public Person(String name, String major, String room) { //constructor
        this.name = name;
        this.major = major;
        this.room = room;

    }

    public String getName() {
        return name;
    }


    public String getMajor() {
        return major;
    }

    public String getRoom() {
        return room;
    }


}
