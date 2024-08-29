package business.entity;

import business.feature.impl.CategoryFeatureImpl;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class Category implements Serializable
{

    private int catalogId;
    private String catalogName;
    private String description;
    private boolean catalogStatus;

    public Category() {
    }

    public Category(int catalogId, String catalogName, String description, boolean catalogStatus) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.description = description;
        this.catalogStatus = catalogStatus;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    public void inputDataCate(Scanner sc) {
        this.catalogId = inputCateId(CategoryFeatureImpl.categories);
        this.catalogName = inputCateName(sc,CategoryFeatureImpl.categories);
        this.description = inputDesc(sc);
        this.catalogStatus = inputCateStatus(sc);
    }

    public void inputDataCateUpdate(Scanner sc) {
        this.catalogName = inputCateName(sc,CategoryFeatureImpl.categories);
        this.description = inputDesc(sc);
        this.catalogStatus = inputCateStatus(sc);
    }

    private boolean inputCateStatus(Scanner sc) {
        System.out.println("Nhập trạng thái danh mục: ");
        while (true) {
            String status = sc.nextLine().trim();
            if(status.equalsIgnoreCase("true") || status.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(status);
            } else {
                System.err.println("Trạng thái chỉ nhập true hoặc false");
            }
        }
    }

    private String inputDesc(Scanner sc) {
        System.out.println("Nhập mô tả danh mục: ");
        while (true) {
            String desc = sc.nextLine().trim();
            if(desc.isBlank()) {
                System.err.println("Mô tả danh mục không để trống");
            } else {
                return desc;
            }
        }
    }

    private String inputCateName(Scanner sc, List<Category> categories) {
        System.out.println("Nhập tên danh mục: ");
        while (true) {
            String nameCate = sc.nextLine().trim();
            if(nameCate.isBlank()) {
                System.err.println("Tên danh mục không được để trống");
            } else {
                if(nameCate.length() <= 50) {
                    boolean isExist = CategoryFeatureImpl.categories.stream()
                            .anyMatch(e->e.catalogName.equals(nameCate));
                    if(!isExist) {
                        return nameCate;
                    } else {
                        System.err.println("Tên danh mục đã tồn tại");
                    }
                } else {
                    System.err.println("Tên danh mục không quá 50 ký tự");
                }
            }
        }
    }


    private int inputCateId(List<Category> categories) {
        CategoryFeatureImpl.categories.stream()
                .map(Category::getCatalogId).max(Integer::compareTo)
                .ifPresent(catalogId -> this.catalogId = catalogId+1);
        return catalogId;
    }

    public void displayDataCate() {
        System.out.printf("[ ID: %d | Name: %s | Desc: %s | Status: %s ]\n",
                this.catalogId,
                this.catalogName,
                this.description,
                this.catalogStatus ? "Hoạt động" : "Không hoạt động");
    }
}
