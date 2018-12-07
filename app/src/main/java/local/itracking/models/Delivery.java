package local.itracking.models;

public class Delivery
{

    public int Id;
    public String Status;
    public String VoucherUrl;
    public int UserId;
    public int OrderId;

    public Delivery(int id, String status, String voucherUrl, int userId, int orderId) {
        Id = id;
        Status = status;
        VoucherUrl = voucherUrl;
        UserId = userId;
        OrderId = orderId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getVoucherUrl() {
        return VoucherUrl;
    }

    public void setVoucherUrl(String voucherUrl) {
        VoucherUrl = voucherUrl;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }
}



