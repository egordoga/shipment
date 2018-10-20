package ua.shipment.utils;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.shipment.entity.Client;
import ua.shipment.entity.ProductBase;
import ua.shipment.entity.ProductSmall;
import ua.shipment.servicedb.ClientService;
import ua.shipment.servicedb.ProductBaseService;
import ua.shipment.servicedb.ProductSmallService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Component
public class ParseNomenclature {

    @Autowired
    private ProductBaseService productBaseService;

    @Autowired
    private ProductSmallService productSmallService;

    @Autowired
    private ClientService clientService;


    public void parseProduct() {

        XSSFWorkbook book = null;
        XSSFSheet sheet;
        Row row;
        ProductBase productBase;

        File file = new File("d://nomenclature.xlsx");

        try {
            book = new XSSFWorkbook(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert book != null;
        sheet = book.getSheetAt(0);

        int count = 0;

        while (count != sheet.getLastRowNum()) {
            count++;
            row = sheet.getRow(count);

            String code;
            if (row.getCell(1).getCellTypeEnum() == CellType.STRING) {
                code = row.getCell(1).getStringCellValue();
            } else {
                code = String.valueOf(row.getCell(1).getNumericCellValue());
            }
            String vendorCode;
            String nameShort;
            String name;

            if (row.getCell(2) != null) {
                if (row.getCell(2).getCellTypeEnum() == CellType.STRING) {
                    vendorCode = row.getCell(2).getStringCellValue();
                } else {
                    vendorCode = String.valueOf(row.getCell(2).getNumericCellValue());
                }
            } else {
                 vendorCode = "";
            }


            if (row.getCell(3) != null) {
                 nameShort = row.getCell(3).getStringCellValue();
            } else {
                 nameShort = "";
            }
            if (row.getCell(4) != null) {
                 name = row.getCell(4).getStringCellValue();
            } else {
                 name = "";
            }
            productBase = new ProductBase(code, vendorCode, nameShort, name);
            productBaseService.saveProductBase(productBase);
        }
    }

    public void fillProductSmall() {
        List<ProductBase> productBases = productBaseService.findAllProductBase();
        for (ProductBase productBase : productBases) {
            ProductSmall productSmall = new ProductSmall();
            productSmall.setName(productBase.getNameShort());
            productSmall.setVendorCode(productBase.getVendorCode());
            productSmallService.saveProductSmall(productSmall);
        }
    }

    public void delZero() {

        List<ProductSmall> list = productSmallService.findEndZero();
        for (ProductSmall productSmall : list) {
            String v = productSmall.getVendorCode();
            productSmall.setVendorCode(v.substring(0, v.length() - 2));
            productSmallService.saveProductSmall(productSmall);
        }
    }

    public void parseClient() {
        XSSFWorkbook book = null;
        XSSFSheet sheet;
        Row row;
        ProductBase productBase;

        File file = new File("d://client.xlsx");

        try {
            book = new XSSFWorkbook(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert book != null;
        sheet = book.getSheetAt(0);

        int count = 0;

        while (count != sheet.getLastRowNum()) {
            count++;
            row = sheet.getRow(count);

            String nameShort = row.getCell(0).getStringCellValue();
            String name = row.getCell(2).getStringCellValue();

            Client client = new Client(name, nameShort);
            clientService.saveClient(client);
        }
    }
}