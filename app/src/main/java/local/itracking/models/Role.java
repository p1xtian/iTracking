package local.itracking.models;

public class Role {
    public int Id;
    public String Role;

    public Role(int id, String role) {
        Id = id;
        Role = role;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }
}
