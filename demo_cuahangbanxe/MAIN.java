package test;
import java.util.*;

enum Status {
    IN_STOCK, SOLD //các giá trị cố định của kiểu status
}
//====DONG XE====
class MotorbikeModel{
    private String modelId;// ma xe
    private String modelName;// ten xe
    private String brand;//hãng
    public MotorbikeModel(String modelId,String modelName,String brand ){
        this.modelId=modelId;
        this.modelName=modelName;
        this.brand=brand;
    }
    public String getmodelId(){return modelId;}
    public String getmodelname(){return modelName;}
    public String getbrand(){return brand;}
    public String tostring(){
        return "mã xe : "+ modelId +" tên xe : "+modelName+" hãng xe : "+brand;
    }
}
//=====version====
class MotorbikeVersion{
    private String color;// màu xe
    private String phan_khoi;// phân khối
    private String price;// giá
    private MotorbikeModel model;
    public MotorbikeVersion(String color,String phan_khoi,String price, MotorbikeModel model){
        this.color=color;
        this.phan_khoi=phan_khoi;
        this.price=price;
        this.model=model;
    }
    public MotorbikeModel getmodel(){return model;}
    public String getcolor(){return color;}
    public String getphankhoi(){return phan_khoi;}
    public String getprice(){return price;}
    // public String tostring(){
    //     return "mã xe : "+ modelId +" tên xe : "+modelName+" hãng xe : "+brand;
    // }

}
// ===== Motorbike =====
class MotorbikeInstance {
    private String vin;
    private String engineNumber;
    //private String CCCD;
    private Status status;
    private MotorbikeVersion version;
    public MotorbikeInstance(String vin, String engineNumber,MotorbikeVersion version) {
        this.vin = vin;
        this.engineNumber = engineNumber;
        this.version=version;
        this.status = Status.IN_STOCK;
    }
    public MotorbikeVersion getversion(){
        return version;
    }
    public String getVin() { return vin; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    @Override
    public String toString() {
        return "VIN: " + vin +" | Engine: " + engineNumber +
        " | bikename: " + version.getmodel().getmodelname() +
         " | Color: " + version.getcolor() + " | Status: " + status;
    }
}

// ===== Customer =====
class Customer {
    private String name;
    private String phone;

    public Customer(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String toString() {
        return name + " - " + phone;
    }
}

// ===== SalesOrder =====
class SalesOrder {
    private MotorbikeInstance xe;// vin, numberbike, status
    private Customer customer;// phone, name

    public SalesOrder(MotorbikeInstance xe, Customer customer) {
        this.xe = xe;
        this.customer = customer;
    }

    public String toString() {
        return "Ban xe " + xe.getVin() + " cho " + customer;
    }
}

// ===== Warranty =====
class WarrantyBook {
    private String vin;// số khung
    private List<String> history = new ArrayList<>();

    public WarrantyBook(String vin) {
        this.vin = vin;
    }

    public void addRecord(String record) {
        history.add(record);
    }
}

// ===== Service =====
class HeadService {
    private List<MotorbikeInstance> kho = new ArrayList<>();

    public void addMotorbike(MotorbikeInstance xe) {
        for (MotorbikeInstance m : kho) {
            if (m.getVin().equals(xe.getVin())) {
                System.out.println("Trung VIN!");
                return;
            }
        }
        kho.add(xe);
    }

    public void showKho() {
        for (MotorbikeInstance m : kho) {
            System.out.println(m);
        }
    }

    public void sellMotorbike(String vin, Customer c) {
        for (MotorbikeInstance m : kho) {
            if (m.getVin().equals(vin) && m.getStatus() == Status.IN_STOCK) {
                m.setStatus(Status.SOLD);
                System.out.println("Ban thanh cong!");
                return;
            }
        }
    }

    public void addMaintenance(String vin, String note) {
        System.out.println("Bao hanh: " + vin + " - " + note);
    }
}

// ===== MAIN =====
public class MAIN {
    public static void main(String[] args) {

        // ===== 1. Tạo service =====
        HeadService service = new HeadService();

        // ===== 2. Tạo Model =====
        MotorbikeModel model1 = new MotorbikeModel("M01", "Vision", "Honda");
        MotorbikeModel model2 = new MotorbikeModel("M02", "AirBlade", "Honda");

        // ===== 3. Tạo Version =====
        MotorbikeVersion v1 = new MotorbikeVersion("Red", "110cc", "30tr", model1);
        MotorbikeVersion v2 = new MotorbikeVersion("Blue", "125cc", "45tr", model2);

        // ===== 4. Tạo xe thực tế =====
        MotorbikeInstance xe1 = new MotorbikeInstance("VIN001", "E001", v1);
        MotorbikeInstance xe2 = new MotorbikeInstance("VIN002", "E002", v2);

        // ===== 5. Nhập kho =====
        service.addMotorbike(xe1);
        service.addMotorbike(xe2);

        System.out.println("\n--- DANH SACH XE TRONG KHO ---");
        service.showKho();

        // ===== 6. Bán xe =====
        Customer c1 = new Customer("Bao", "0123");
        service.sellMotorbike("VIN001", c1);

        System.out.println("\n--- SAU KHI BAN ---");
        service.showKho();

        // ===== 7. Bảo hành =====
        service.addMaintenance("VIN001", "Thay dau");
        service.addMaintenance("VIN001", "Bao tri dinh ky");

        // ===== 8. Test thêm =====
        System.out.println("\n--- TEST TRUNG VIN ---");
        MotorbikeInstance xe3 = new MotorbikeInstance("VIN001", "E003", v1);
        service.addMotorbike(xe3); // sẽ báo trùng

    }
}