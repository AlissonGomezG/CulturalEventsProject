package ucr.ac.cr.CulturalEvent.model.DTO;

public class LoginDTO {
    private String message;
    private String email;
    private String password;
    private String profile;

    public LoginDTO() {
    }

    public LoginDTO(String message, String email, String password, String profile) {
        this.message = message;
        this.email = email;
        this.password = password;
        this.profile = profile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail (String email){
        this.email = email;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password){
        this.password = password;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
