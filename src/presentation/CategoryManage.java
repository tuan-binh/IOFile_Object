package presentation;

import business.entity.Category;
import business.feature.ICategoryFeature;
import business.feature.impl.CategoryFeatureImpl;

import java.util.Optional;
import java.util.Scanner;

public class CategoryManage {

    ICategoryFeature categoryFeature = new CategoryFeatureImpl();

    public void menuCate(Scanner sc) {
        boolean flag = true;
        while(flag) {

            System.out.println("========================= CATEGORIES MENU =========================");
            System.out.println("                 1.Nhập thông tin các danh mục                     ");
            System.out.println("                 2.Hiển thị thông tin các danh mục                 ");
            System.out.println("                 3.Cập nhật thông tin danh mục                     ");
            System.out.println("                 4.Xóa danh mục                                    ");
            System.out.println("                 5.Cập nhật trạng thái danh mục                    ");
            System.out.println("                 6.Thoát                                           ");
            System.out.println("===================================================================");
            System.out.println("Nhập lựa chọn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:{
                    addNewCate(sc);
                    break;
                }
                case 2:{
                    showAllCate();
                    break;
                }
                case 3:{
                    updateCate(sc);
                    break;
                }
                case 4:{
                    deleteCate(sc);
                    break;
                }
                case 5:{
                    updateStatusCate(sc);
                    break;
                }
                case 6:{
                    flag = false;
                    break;
                }
                default:
                    System.err.println("Vui lòng nhập từ 1->6");
            }
        }
    }

    private void updateStatusCate(Scanner sc) {
        System.out.println("Nhập mã danh mục muốn cập nhật trạng thái: ");
        int idCate = Integer.parseInt(sc.nextLine());

        Optional<Category> cateOpt = CategoryFeatureImpl.categories.stream()
                .filter(e->e.getCatalogId() == idCate).findFirst();

        if(cateOpt.isPresent()) {
            Category cate = cateOpt.get();
            cate.setCatalogStatus(!cate.isCatalogStatus());
            System.out.println("Cập nhật trạng thái danh mục thành công");
        } else {
            System.err.println("Không tồn tại danh mục");
        }
    }

    private void deleteCate(Scanner sc) {
        System.out.println("Nhập mã danh mục cần xóa: ");
        int idDelete = Integer.parseInt(sc.nextLine());

        Optional<Category> cateOpt = CategoryFeatureImpl.categories.stream()
                .filter(e->e.getCatalogId() == idDelete).findFirst();

        if(cateOpt.isPresent()) {
            categoryFeature.delete(idDelete);
            System.out.println("Xóa danh mục thành công.");
        } else {
            System.err.println("Không tồn tại danh mục");
        }
    }

    private void updateCate(Scanner sc) {
        System.out.println("Nhập mã danh mục cần update: ");
        int idUpdate = Integer.parseInt(sc.nextLine());

        Optional<Category> cateOpt = CategoryFeatureImpl.categories.stream()
                .filter(e->e.getCatalogId() == idUpdate).findFirst();

        if(cateOpt.isPresent()) {
            Category catUpdate = cateOpt.get();
            catUpdate.inputDataCateUpdate(sc);
            categoryFeature.addOrUpdate(catUpdate);
            System.out.println("Cập nhật thành công");
        } else {
            System.err.println("Không tồn tại danh mục");
        }
    }

    private void showAllCate() {
        if(CategoryFeatureImpl.categories.isEmpty()) {
            System.err.println("Danh sách trống...");
        }else {
            CategoryFeatureImpl.categories.forEach(Category::displayDataCate);
        }
    }

    private void addNewCate(Scanner sc) {
        System.out.println("Nhập số lượng muốn thêm: ");
        int number = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < number; i++) {
            Category newCate = new Category();
            newCate.inputDataCate(sc);
            categoryFeature.addOrUpdate(newCate);
        }
    }
}
