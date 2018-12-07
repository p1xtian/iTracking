package local.itracking.models;

import com.google.gson.annotations.SerializedName;

public class Client {

    @SerializedName("codigo")
    public int Id;
    @SerializedName("ruc")
    public String RUC_DNI;
    @SerializedName("direccion")
    public String Address;
    @SerializedName("phone")
    public String Phone;
    @SerializedName("razon")
    public String RS_Name;
    @SerializedName("status")
    public String status;

    public Client(int id, String RUC_DNI, String address, String phone, String RS_Name) {
        Id = id;
        this.RUC_DNI = RUC_DNI;
        Address = address;
        Phone = phone;
        this.RS_Name = RS_Name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getRUC_DNI() {
        return RUC_DNI;
    }

    public void setRUC_DNI(String RUC_DNI) {
        this.RUC_DNI = RUC_DNI;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getRS_Name() {
        return RS_Name;
    }

    public void setRS_Name(String RS_Name) {
        this.RS_Name = RS_Name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
