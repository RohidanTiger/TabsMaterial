package nextop.asia.pratise.model;

/**
 * Created by QuyDV on 4/13/17.
 */

public class Hero {
    private String id;
    private String name;
    private String nickName;
    private String role;

    public Hero() {
    }

    public Hero(String id, String name, String nickName, String role) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
