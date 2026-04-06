
import java.lang.reflect.Array;
import java.util.*;
import java.time.LocalDate;
//import models.WarrantyVisit;

enum Status {
    IN_STOCK, SOLD //các giá trị cố định của kiểu status
}

class WarrantyVisit {
    private int id;
    private int warrantyBookId;
    private Date visitDate;
    private int kmReading;
    private ArrayList<String> error;
    private String technicianNotes;

    public WarrantyVisit(int id, int warrantyBookId, Date visitDate, int kmReading, String error) {
        this.id = id;
        this.warrantyBookId = warrantyBookId;
        this.visitDate = visitDate;
        this.kmReading = kmReading;
        this.error.add(error);
    }

    // Các hàm Getter/Setter cơ bản
    public void setTechnicianNotes(String notes) { this.technicianNotes = notes; }

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
    private Status status;
    private MotorbikeVersion version;
    private LocalDate importDate;

    public MotorbikeInstance(String vin, String engineNumber,MotorbikeVersion version, LocalDate importDate) {
        this.vin = vin;
        this.engineNumber = engineNumber;
        this.version=version;
        this.status = Status.IN_STOCK;
        this.importDate = importDate;
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

// ===== Customer =====
class Customer {
    private int id;
    private String identityCard;
    private String fullName;
    private LocalDate dateOfBirth;
    private String phone;
    private String address;
    private String email;
    private ArrayList<Integer> numVehicle = new ArrayList<>();
    private ArrayList<WarrantyVisit> historyWar = new ArrayList<>();

    // Constructor khởi tạo khách hàng mới
    public Customer(String fullName, String phone, int id, String identityCard, LocalDate dateOfBirth, String address, String email) {
        this.fullName = fullName;
        this.phone = phone;
        this.id = id;
        this.identityCard = identityCard;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.email = email;
    }
}


// ===== SalesOrder =====
class SaleOrder {
    private int id;
    private int customerId;
    private LocalDate orderDate;
    private String paymentStatus;

    public SaleOrder(int id, int customerId, LocalDate orderDate) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.paymentStatus = "PAID";
    }

}

// ===== Warranty =====
class WarrantyBook {
    private String vin;
    private int customerId;
    private LocalDate issueDate;
    private LocalDate expDate;

    public WarrantyBook( String vin, int customerId, LocalDate issueDate, LocalDate expDate) {
        this.vin = vin;
        this.customerId = customerId;
        this.issueDate = issueDate;
        this.expDate = expDate;
    }

    public void checkWarranty() {
       
    }
}

// ===== Service ===== 
// hàm này làm cái j đây
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
        //HeadService service = new HeadService();

        // ===== 2. Tạo Model =====
        MotorbikeModel model1 = new MotorbikeModel("M01", "Vision", "Honda");
        MotorbikeModel model2 = new MotorbikeModel("M02", "AirBlade", "Honda");

        // ===== 3. Tạo Version =====
        MotorbikeVersion v1 = new MotorbikeVersion("Red", "110cc", "30tr", model1);
        MotorbikeVersion v2 = new MotorbikeVersion("Blue", "125cc", "45tr", model2);

        // // ===== 4. Tạo xe thực tế =====
        // MotorbikeInstance xe1 = new MotorbikeInstance("VIN001", "E001", v1);
        // MotorbikeInstance xe2 = new MotorbikeInstance("VIN002", "E002", v2);

        // // ===== 5. Nhập kho =====
        // service.addMotorbike(xe1);
        // service.addMotorbike(xe2);

        // System.out.println("\n--- DANH SACH XE TRONG KHO ---");
        // service.showKho();

        // // ===== 6. Bán xe =====
        // Customer c1 = new Customer("Bao", "0123");
        // service.sellMotorbike("VIN001", c1);

        // System.out.println("\n--- SAU KHI BAN ---");
        // service.showKho();

        // // ===== 7. Bảo hành =====
        // service.addMaintenance("VIN001", "Thay dau");
        // service.addMaintenance("VIN001", "Bao tri dinh ky");

        // // ===== 8. Test thêm =====
        // System.out.println("\n--- TEST TRUNG VIN ---");
        // MotorbikeInstance xe3 = new MotorbikeInstance("VIN001", "E003", v1);
        // service.addMotorbike(xe3); // sẽ báo trùng
    }
}}