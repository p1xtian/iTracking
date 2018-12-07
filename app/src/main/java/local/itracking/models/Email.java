package local.itracking.models;

import com.google.gson.annotations.SerializedName;

public class Email {

    @SerializedName("asunto")
    public String subject;
    @SerializedName("cuerpo")
    public String body;
    @SerializedName("destinatario")
    public String emailTo;

    public Email(String subject, String body, String emailTo) {
        this.subject = subject;
        this.body = body;
        this.emailTo = emailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }
}
