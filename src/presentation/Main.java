package presentation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("============================== SHOP MENU ==============================");
            System.out.println("                  1.Quản lý danh mục sản phẩm                          ");
            System.out.println("                  2.Quản lý sản phẩm                                   ");
            System.out.println("                  3.Thoát                                              ");
            System.out.println("=======================================================================");
            System.out.println("Nhập lựa chọn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:{
                    CategoryManage menuCategory = new CategoryManage();
                    menuCategory.menuCate(sc);
                    break;
                }
                case 2:{
                    ProductManage menuProduct = new ProductManage();
                    menuProduct.menuPro(sc);
                    break;
                }
                case 3:{
                    System.exit(0);
                    break;
                }
                default:
                    System.err.println("Vui lòng nhập từ 1->3");
            }
        }while (true);
    }
}
