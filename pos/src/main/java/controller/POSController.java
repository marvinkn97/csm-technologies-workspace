package controller;

import dto.BillDetailsDto;
import dto.BillProductDto;
import dto.ProductDto;
import service.BillService;
import service.BillServiceImpl;
import service.ProductService;
import service.ProductServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class POSController {

    private static ProductService productService = new ProductServiceImpl();
    private static BillService billService = new BillServiceImpl();

    public static void main(String[] args) {
        Scanner ss = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        System.out.println("Select an option: ");
        renderMenu();
        int choice = sc.nextInt();

        switch (choice) {
            case 1 -> {
                BillDetailsDto billDetailsDto = new BillDetailsDto();
                List<BillProductDto> billProductDtoList = new ArrayList<>();

                System.out.println("Enter customer name: ");
                billDetailsDto.setCustomerName(ss.nextLine().trim());

                System.out.println("Enter customer phone: ");
                billDetailsDto.setCustomerPhone(ss.nextLine().trim());

                char ch = 'y';
                BigDecimal totalAmount = BigDecimal.ZERO;
                do {
                    BillProductDto billProductDto = new BillProductDto();
                    System.out.println("Enter product ID " + productService.getAll());
                    int productId = sc.nextInt();

                    ProductDto productDto = productService.getAll().stream()
                            .filter(p -> p.getId().equals(String.valueOf(productId)))
                            .findFirst()
                            .orElse(null);

                    if (productDto != null) {
                        System.out.println("Enter number of units: ");
                        int noOfUnits = sc.nextInt();

                        if (noOfUnits <= Integer.parseInt(productDto.getQuantity())) {
                            billProductDto.setProductDto(productDto);
                            billProductDto.setNoOfUnits(String.valueOf(noOfUnits));
                            totalAmount = totalAmount.add(new BigDecimal(productDto.getUnitPrice()).multiply(new BigDecimal(noOfUnits)));
                            billProductDtoList.add(billProductDto);


                        } else {
                            System.out.println("Insufficient number of units. Available units is " + productDto.getQuantity());
                        }

                        billDetailsDto.setTotalAmount(totalAmount.toString());
                    }


                    System.out.println("Want to add more products [y/n]: ");
                    ch = ss.nextLine().charAt(0);

                } while (ch == 'y');

                String msg = billService.createBill(billDetailsDto, billProductDtoList);
                System.out.println(msg);
            }
        }


    }

    private static void renderMenu() {
        String menu = """
                1. Create Bill
                2. Show Bill
                3. Exit
                """;
        System.out.println(menu);
    }
}
