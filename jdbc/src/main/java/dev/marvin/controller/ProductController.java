package dev.marvin.controller;

import dev.marvin.dto.ProductDto;
import dev.marvin.service.ProductService;
import dev.marvin.service.ProductServiceImpl;
import dev.marvin.util.DBUtil;

import java.sql.Connection;
import java.util.Scanner;

public class ProductController {
    private static final ProductService productService = new ProductServiceImpl();

    public static void main(String[] args) {
        Connection connection = DBUtil.getConnection();
        System.out.println(connection);

        Scanner ss = new Scanner(System.in);
        Scanner si = new Scanner(System.in);

        int choice;
        do {
            renderMenu();
            choice = si.nextInt();
            Object msg = switch (choice) {
                case 1 -> {
                    System.out.println("Enter product name");
                    String productName = ss.nextLine();

                    System.out.println("Enter unit price");
                    String unitPrice = ss.nextLine();

                    System.out.println("Enter manufacturing date [dd/MM/yyy]");
                    String mfgDate = ss.nextLine();

                    yield productService.addProduct(new ProductDto(null, productName, unitPrice, mfgDate));
                }
                case 2 -> productService.getAllProducts();
                case 3 -> {
                    System.out.println("Enter product ID");
                    String productId = ss.nextLine();
                    yield productService.getProductById(productId);
                }
                case 4 -> {
                    System.out.println("Enter product ID");
                    String productId = ss.nextLine();
                    yield productService.deleteProductById(productId);
                }
                case 5 -> {
                    System.out.println("Enter product ID");
                    String productId = ss.nextLine();

                    System.out.println("Enter product name");
                    String productName = ss.nextLine();

                    System.out.println("Enter unit price");
                    String unitPrice = ss.nextLine();

                    System.out.println("Enter manufacturing date [dd/MM/yyy]");
                    String mfgDate = ss.nextLine();

                    ProductDto productDto = new ProductDto(productId, productName, unitPrice, mfgDate);

                    yield productService.updateProductById(productDto);
                }
                case 6 -> {
                    System.out.println("Exiting...");
                    System.exit(1);
                    yield "Application closed";
                }
                default -> "INVALID CHOICE!!";
            };
            System.out.println(msg);

        } while (choice != 6);
    }

    private static void renderMenu() {
        String menu = """
                1. Add product
                2. Show all products
                3. Search product by ID
                4. Delete product
                5. Update product
                6. Exit
                Enter your choice:
                """;
        System.out.println(menu);
    }

}
