package business.entity;

import business.feature.impl.CategoryFeatureImpl;
import business.feature.impl.ProductFeatureImpl;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Product implements Serializable
{
    private String productId;
    private String productName;
    private double price;
    private String description;
    private Date created ;
    private int catalogId;
    private int productStatus;

    public Product() {
    }

    public Product(String productId, String productName, double price, String description, Date created, int catalogId, int productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.created = created;
        this.catalogId = catalogId;
        this.productStatus = productStatus;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public void inputDataPro(Scanner sc) {
        this.productId = inputProId(sc);
        this.productName = inputProName(sc);
        this.description = inputDesc(sc);
        this.created = inputCreated(sc);
        this.price = inputPrice(sc);
        this.catalogId = inputCatalogId(sc);
        this.productStatus = inputProStatus(sc);
    }

    public void inputDataProUpdate(Scanner sc) {
        this.productName = inputProName(sc);
        this.description = inputDesc(sc);
        this.created = inputCreated(sc);
        this.price = inputPrice(sc);
        this.catalogId = inputCatalogId(sc);
        this.productStatus = inputProStatus(sc);
    }

    private int inputProStatus(Scanner sc) {
        System.out.println("Nhập trạng thái sản phẩm: ");
        while (true) {
            String statusStr = sc.nextLine().trim();
            if(statusStr.isEmpty()) {
                System.err.println("Trạng thái sản phẩm không được để trống");
            } else {
                int status = Integer.parseInt(statusStr);
                if(status >= 0 && status <= 2) {
                    return status;
                } else {
                    System.err.println("Vui lòng nhập từ 0->2");
                }
            }
        }
    }

    private int inputCatalogId(Scanner sc) {
        CategoryFeatureImpl.categories.forEach(e->{
            System.out.printf("[ ID: %d | Name: %s ] \n",e.getCatalogId(),e.getCatalogName());
        });
        System.out.println("Nhập mã danh mục: ");
        while (true) {
            try{
                int cateId = Integer.parseInt(sc.nextLine());
                /*boolean isExist = CategoryFeatureImpl.categories.stream().anyMatch(e->e.getCatalogId()==cateId);
                if(isExist) {
                    return cateId;
                }else {
                    System.err.println("Không tồn tại danh mục ");
                }*/

                Optional<Category> categoryOpt = CategoryFeatureImpl.categories.stream().filter(e->e.getCatalogId()==cateId).findFirst();
                if(categoryOpt.isPresent()) {
                    return cateId;
                }else {
                    System.err.println("Không tồn tại danh mục ");
                }
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập một số nguyên hợp lệ.");
            }
        }
    }

    private double inputPrice(Scanner sc) {
        System.out.println("Nhập giá sản phẩm: ");
        while (true) {
            String priceStr = sc.nextLine();
            if (priceStr.isEmpty()) {
                System.err.println("Giá sản phẩm không được để trống");
            }else {
                Double price = Double.parseDouble(priceStr);
                if (price <= 0) {
                    System.err.println("Giá sản phẩm phải lớn hơn 0");
                } else {
                    return price;
                }
            }
        }
    }

    private Date inputCreated(Scanner sc) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        System.out.println("Nhập ngày sản xuất: ");
        while (true) {
            try{
                String created = sc.nextLine().trim();
                if(created.isEmpty()) {
                    System.err.println("Ngày sản xuất không được để trống");
                } else {
                    return sdf.parse(created);
                }
            }catch(ParseException e){
                System.err.println("Nhập ngày sản xuất không đúng đúng dạng dd/MM/yyyy");
            }
        }
    }

    private String inputDesc(Scanner sc) {
        System.out.println("Nhập mô tả sản phẩm: ");
        while (true) {
            String desc = sc.nextLine().trim();
            if(desc.isEmpty()) {
                System.err.println("Mô tả sản phẩm không được để trống");
            } else {
                return desc;
            }
        }
    }

    private String inputProName(Scanner sc) {
        System.out.println("Nhập tên sản phẩm: ");
        while (true) {
            String proName = sc.nextLine().trim();
            if(proName.length() >= 10 && proName.length() <= 50) {
                boolean isExist = ProductFeatureImpl.products.stream().anyMatch(e->e.productName.equals(proName));
                if(!isExist) {
                    return proName;
                }else {
                    System.err.println("Tên sản phẩm đã tồn tại");
                }
            }
        }
    }

    private String inputProId(Scanner sc) {
        System.out.println("Nhập mã sản phẩm: ");
        while (true) {
            String proId = sc.nextLine().trim();
            if(proId.matches("^[CSA]\\w{3}$")) {
                boolean isExist = ProductFeatureImpl.products.stream()
                        .anyMatch(e -> e.getProductId().equals(proId));
                if(isExist){
                    System.err.println("Mã sản phẩm đã tồn tại");
                } else {
                    return proId;
                }
            }else {
                System.err.println("Mã sản phẩm bắt đầu bằng C,S,A và gồm 4 ký tự");
            }
        }
    }

    public void displayDataPro() {
        System.out.printf(" [ ID: %s | NamePro: %s | PricePro: %s | DescPro: %s | CreatedPro: %s | CatalogName: %s | ProductStatus: %s ] \n ",
                this.productId,
                this.productName,
                this.price,
                this.description,
                findCateById(this.catalogId).getCatalogName(),
                this.productStatus == 0 ? "Đang bán": this.productStatus == 1 ? "Hết hàng" : "Không bán");
    }

    private Category findCateById(int catalogId) {
        for(Category cat : CategoryFeatureImpl.categories) {
            if(cat.getCatalogId()==catalogId) {
                return cat;
            }
        }
        return null;
    }

}
