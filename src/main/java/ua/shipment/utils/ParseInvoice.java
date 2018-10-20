package ua.shipment.utils;


import lombok.Getter;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.shipment.entity.*;
import ua.shipment.servicedb.ClientService;
import ua.shipment.servicedb.InvoiceService;
import ua.shipment.servicedb.ProductService;
import ua.shipment.servicedb.ProductSmallService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class ParseInvoice {

    public static final int QUANTITY_CELL = 37;

    private List<Product> productsList = new ArrayList<>();

    @Autowired
    private ProductSmallService productSmallService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ClientService clientService;
    @Autowired
    InvoiceService invoiceService;


    public void parseExcelInvoice(File file) {
        XSSFWorkbook book = null;
        XSSFSheet sheet;
        Row row;
        BigDecimal quantity;

        Invoice invoice = new Invoice();
        invoice.setIsView((byte) 0);
        invoice.setIsClosed((byte) 0);


        try {
            book = new XSSFWorkbook(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert book != null;
        sheet = book.getSheetAt(0);

        String[] strsNum = sheet.getRow(15).getCell(1).getStringCellValue().split(" ");
        String invoiceNumber = strsNum[6];
        String buyer = sheet.getRow(20).getCell(8).getStringCellValue();
        Client client = clientService.findClientByName(buyer);
        int rowNum = 26;

        while (sheet.getRow(rowNum).getCell(2) != null && sheet.getRow(rowNum).getCell(2).getCellTypeEnum() != CellType.NUMERIC) {
            rowNum++;

            row = sheet.getRow(rowNum);

            String vendorCode;
            if (row.getCell(4) != null) {
                if (row.getCell(4).getCellTypeEnum() == CellType.STRING) {
                    vendorCode = row.getCell(4).getStringCellValue();
                } else if (row.getCell(4).getCellTypeEnum() == CellType.NUMERIC){
                    vendorCode = String.valueOf(row.getCell(4).getNumericCellValue());
                } else continue;
            } else {
                continue;
            }

            quantity = new BigDecimal(String.valueOf(row.getCell(QUANTITY_CELL)));

            int priceCell = 0;
            String str;
            for (int i = 40; i < 47; i++) {
                if (sheet.getRow(25).getCell(i) != null && sheet.getRow(25).getCell(i).getCellTypeEnum() == CellType.STRING) {
                    str = sheet.getRow(25).getCell(i).getStringCellValue();
                } else {
                    continue;
                }
                if ("Цена без НДС".equals(str)) {
                    priceCell = i;
                }
            }
            BigDecimal price = null;

            if (priceCell != 0) {
                price = new BigDecimal(String.valueOf(row.getCell(priceCell).getNumericCellValue()));
            }

            ProductSmall productSmall =productSmallService.findProductSmallByVendorCode(vendorCode);

            if (productSmall == null) continue;

            Product product = new Product();
            product.setProductSmall(productSmall);
            product.setQuantity(quantity);
            product.setPrice(price);
            product.setIsShip((byte) 0);
            product.setRestQuantity(quantity);
            product.setDelivQuantity(BigDecimal.ZERO);
            product.setInvoice(invoice);

            productService.saveProduct(product);
            productsList.add(product);

        }

        invoice.setNumber(invoiceNumber);
        invoice.setProducts(productsList);
        invoice.setClient(client);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        invoice.setDateFormat(LocalDateTime.now().format(formatter));
        invoice.setDate(LocalDateTime.now());
        invoiceService.saveInvoice(invoice );
    }

    public void verifyFile(File file) {
        if (file != null) {
            String s = file.getName();
            if ("xlsx".equals(s.substring(s.length() - 4))) {
                parseExcelInvoice(file);
            }
        }
    }
}
