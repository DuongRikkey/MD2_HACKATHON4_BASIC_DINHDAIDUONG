package Exam_Java_Core.ra.presenatation;

import Exam_Java_Core.ra.units.Color;
import Exam_Java_Core.ra.business.design.entity.Catalog;
import Exam_Java_Core.ra.business.design.features.ICatalogFeatures;
import Exam_Java_Core.ra.business.design.features.implement.CatalogFeatureImpl;
import Exam_Java_Core.ra.business.design.features.implement.ProductFeatureImpl;

import java.util.Scanner;

public class CatalogManagement {
    ICatalogFeatures catalogFeature=new CatalogFeatureImpl();
    public void menuCatalog(Scanner sc) {

        boolean isLoop = true;
        do
        {
            System.out.println(Color.GREEN+"""
        ======================= MENU-Catalog =======================
        1. Nhập số lượng danh mục mới được thêm vào và nhập thông tin cho từng danh mục
        2. Hiển thị thông tin của tất cả các danh mục
        3. Chỉnh sửa tên danh mục theo id danh mục
        4. Xóa danh mục theo mã danh mục (lưu ý: không xóa khi có sản phẩm)
        5. Quay lại							
        
        ============================================================
                        
        """+Color.PURPLE);
            int choice=0 ;
            while (true) {
                try {
                    System.out.println("Mời bạn lựa chọn menu");
                    choice = Integer.parseInt(sc.nextLine());
                    if (choice <= 0) {
                        System.err.println("Vui lòng nhập lại số duong");
                    }
                    break;
                }
                catch (NumberFormatException e
                )
                {
                    System.err.println("Bạn vui lòng nhập số nguyên");
                }
            }
            switch (choice)
            {
                case 1:
                {
                    addNewCatalog(sc);
                    break;
                }
                case 2:
                {
                    showAllCata();
                    break;
                }
                case 3:
                {
                    editAllCata(sc);
                    break;
                }
                case 4:
                {
                    deleteCata(sc);
                    break;
                }

                case 5:
                {
                    isLoop = false;
                    break;
                }
                default:
                    System.err.println("Nhập lại từ 1 -> 6 đê");
            }
        }
        while (isLoop);
    }

    private void deleteCata(Scanner sc) {
        int idDelete;
        while (true){
            try {
                System.out.println("Mời bạn nhập mã catalog:");
                idDelete = Integer.parseInt(sc.nextLine());


                if (idDelete <= 0) {
                    System.err.println("Bạn phải nhập số dương");
                    continue;
                }

                break;
            } catch (NumberFormatException e) {
                System.err.println("Bạn phải nhập số nguyên hợp lệ");
            }
        }



        int finalIdDelete = idDelete;
        boolean isExist = CatalogFeatureImpl.catalogs.stream().anyMatch(catalog -> catalog.getCatalogId()== finalIdDelete);
        if (!isExist) {
            System.err.println("Không tìm thấy"+idDelete);
            return;
        }
        boolean hasProduct= ProductFeatureImpl.products.stream().anyMatch(product -> product.getCatalog().getCatalogId()==finalIdDelete);
        if(hasProduct){
            System.err.println("Có sản phẩm ko thể xóa Catalog");
        }else {
            catalogFeature.remove(idDelete);
        }
    }

    private void editAllCata(Scanner sc) {
        int cataId;
        while (true){
            try {System.out.println("Nhập mã catalog vào");
                cataId = Integer.parseInt(sc.nextLine());
                if(cataId<=0){
                    System.err.println("Bạn phải nhập số lớn hơn 0");
                    continue;
                }
                break;

            }
            catch (NumberFormatException e) {
                System.err.println("Bạn phải nhập số nguyên");
            }

        }

        int indexUpdate=catalogFeature.findIndexById(cataId);
        if (indexUpdate==-1){
            System.out.println("Không tồn tại"+cataId);
        }else {
            Catalog catalogOld=CatalogFeatureImpl.catalogs.get(indexUpdate);
            catalogOld.inputUpdate(sc,CatalogFeatureImpl.catalogs);
            catalogFeature.save(catalogOld);
        }
    }

    private void showAllCata() {
        if(CatalogFeatureImpl.catalogs.isEmpty() ||CatalogFeatureImpl.catalogs==null){
            System.err.println("Danh sach trong");
            return;
        }
        CatalogFeatureImpl.catalogs.forEach(System.out::println);
    }

    private void addNewCatalog(Scanner sc) {
        System.out.println("Mời bạn nhập số lượng muốn add thêm vào nhé");
        int n=0;
        while (true){
            try {
                n=Integer.parseInt(sc.nextLine());
                if (n<=0){
                    System.err.println("Phải nhập số dương");
                }
                break;
            }
            catch (NumberFormatException e){
                System.err.println("Phải nhập số nguyên");
            }
        }
        for (int i=0;i<n;i++){
            System.out.println("Thêm phần tử thứ "+(i+1));
            Catalog catalog=new Catalog();
            catalog.inputData(sc,CatalogFeatureImpl.catalogs);
            catalogFeature.save(catalog);
        }
    }
}