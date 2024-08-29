package presentation;

import business.entity.Product;
import business.feature.IProductFeature;
import business.feature.impl.ProductFeatureImpl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ProductManage {

    IProductFeature productFeature = new ProductFeatureImpl();

    public void menuPro(Scanner sc) {
        boolean check = true;
        while (check) {
            System.out.println("========================= PRODUCT MANAGEMENT =========================");
            System.out.println("                 1.Nhập thông tin các sản phẩm                        ");
            System.out.println("                 2.Hiển thị thông tin các sản phẩm                    ");
            System.out.println("                 3.Sắp xếp các sản phẩm theo giá                      ");
            System.out.println("                 4.Cập nhật thông tin sản phẩm                        ");
            System.out.println("                 5.Xóa sản phẩm                                       ");
            System.out.println("                 6.Tìm kiếm các sản phẩm theo tên                     ");
            System.out.println("                 7.Tìm kiếm sản phẩm trong khoảng giá                 ");
            System.out.println("                 8.Thoát                                              ");
            System.out.println("======================================================================");
            System.out.println("Nhập lựa chọn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:{
                    addNewProduct(sc);
                    break;
                }
                case 2:{
                    showAllProduct();
                    break;
                }
                case 3:{
                    sortByPrice();
                    break;
                }
                case 4:{
                    updateProduct(sc);
                    break;
                }
                case 5:{
                    deleteProduct(sc);
                    break;
                }
                case 6:{
                    searchByNamePro(sc);
                    break;
                }
                case 7:{
                    searchByPricePro(sc);
                    break;
                }
                case 8:{
                    check = false;
                    break;
                }
                default:
                    System.err.println("Vui lòng nhập từ 1->8");
            }
        }
    }

    private void searchByPricePro(Scanner sc) {
        System.out.println("Nhap khoang gia bat dau: ");
        double from = Double.parseDouble(sc.nextLine());
        System.out.println("Nhap khoang gia ket thuc: ");
        double to = Double.parseDouble(sc.nextLine());

        List<Product> productList = ProductFeatureImpl.products.stream()
                .filter(product -> product.getPrice() >= from && product.getPrice() <= to).toList();
        if (productList.isEmpty()) {
            System.out.println("Khong tim duoc san pham nao");
        } else {
            productList.forEach(Product::displayDataPro);
        }
    }

    private void searchByNamePro(Scanner sc) {
        System.out.println("Nhap ten san pham: ");
        String keyword = sc.nextLine();
        List<Product> productList = ProductFeatureImpl.products.stream().filter(e->e.getProductName().contains(keyword)).toList();
        if (productList.isEmpty()) {
            System.err.println("Danh sach trong");
        } else {
            productList.forEach(Product::displayDataPro);
        }
    }

    private void deleteProduct(Scanner sc) {
        System.out.println("Nhap ma san pham: ");
        String proId = sc.nextLine();
        boolean isExist = ProductFeatureImpl.products.stream()
                .anyMatch(e->e.getProductId().equals(proId));
        if(isExist) {
            productFeature.delete(proId);
            System.out.println("Xoa thanh cong");
        } else {
            System.err.println("San pham khong ton tai");
        }
    }

    private void updateProduct(Scanner sc) {
        System.out.println("Nhap ma san pham: ");
        String proId = sc.nextLine();
        Optional<Product> productOpt = ProductFeatureImpl.products.stream()
                .filter(p -> p.getProductId().equals(proId)).findFirst();
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            product.inputDataProUpdate(sc);
            productFeature.addOrUpdate(product);
            System.out.println("Cap nhat thanh cong");
        } else {
            System.err.println("San pham khong ton tai");
        }
    }

    private void sortByPrice() {
        ProductFeatureImpl.products.forEach(e->{
            System.out.printf("[ ID: %s | Name: %s | Price: %s ] \n",e.getProductId(),e.getProductName(),e.getPrice());
        });
        ProductFeatureImpl.products.stream().sorted(Comparator.comparingDouble(Product::getPrice)).collect(Collectors.toList());
        System.out.println("Sap xep thanh cong");
        showAllProduct();
    }

    private void showAllProduct() {
        if(ProductFeatureImpl.products.isEmpty()) {
            System.err.println("Danh sach trong");
        }else {
            ProductFeatureImpl.products.forEach(Product::displayDataPro);
        }
    }

    private void addNewProduct(Scanner sc) {
        System.out.println("Nhao so luong muon them: ");
        int number = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < number; i++) {
            Product product = new Product();
            product.inputDataPro(sc);
            productFeature.addOrUpdate(product);
        }
        System.out.println("Them thanh cong");
    }
}
