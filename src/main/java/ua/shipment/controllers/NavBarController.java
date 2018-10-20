package ua.shipment.controllers;

import lombok.Getter;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.shipment.utils.ParseInvoice;

import java.io.File;
import java.io.IOException;

@Getter
@Controller
public class NavBarController {
    private File file;

    @Autowired
    private ParseInvoice parseInvoice;

    @GetMapping("/header")
    public String viewHeader() {
        return "/header";
    }

   /* @GetMapping("/upload")
    public String viewOops() {
        return "/oops";
    }

    @PostMapping("/upload")
    public String getExcelFile(@RequestParam("file") MultipartFile multFile) {
        file = new File(multFile.getOriginalFilename());
        try {
            FileUtils.writeByteArrayToFile(file, multFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("hhhhhhhhh");
        System.out.println(file.getName());
        System.out.println();
        parseInvoice.parseExcelInvoice(file);
        return "/oops";
    }*/
}
