package models;

public class User {

    private Long id;
    private String name;

    public User(Long id, String name) {
        this.id=id;
        this.name=name;
    }

    public User() {} //Necessary for Json deserialization?

    public User(String name, Long id) {
        this.id=id;
        this.name=name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
