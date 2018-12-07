package local.itracking.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Order {
    @SerializedName("codigo")
    public int id;
    @SerializedName("usuario")
    public String userId;
    @SerializedName("cliente")
    public String clientId;
    @SerializedName("razon")
    public String ClientName;
    @SerializedName("origen")
    public String source;
    @SerializedName("destino")
    public String destination;
    @SerializedName("cajas")
    public String boxes;
    @SerializedName("phone")
    public String phone;
    @SerializedName("fecha")
    public String date;
    @SerializedName("estado")
    public String status;
    @SerializedName("validacion")
    public String validation;
    @SerializedName("photo")
    public String photo;

    public Order(int id, String userId, String clientId, String clientName, String source, String destination, String boxes, String phone, String date, String status, String validation, String photo) {
        this.id = id;
        this.userId = userId;
        this.clientId = clientId;
        ClientName = clientName;
        this.source = source;
        this.destination = destination;
        this.boxes = boxes;
        this.phone = phone;
        this.date = date;
        this.status = status;
        this.validation = validation;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getBoxes() {
        return boxes;
    }

    public void setBoxes(String boxes) {
        this.boxes = boxes;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
