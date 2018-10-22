package ua.shipment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.shipment.entity.Client;
import ua.shipment.entity.Invoice;
import ua.shipment.entity.Product;
import ua.shipment.model.ProdForm;
import ua.shipment.servicedb.ClientService;
import ua.shipment.servicedb.InvoiceService;
import ua.shipment.servicedb.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SearchController {

    @Autowired
    private ProductService productService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ClientService clientService;

    @PostMapping("/search")
    public String doSearch(@ModelAttribute("search") String search, @ModelAttribute("select") String select,
                           Model model) {

        /*int err = 0;
        model.addAttribute("err", err);*/

        switch (select) {
            case "1":
                List<Product> p = productService.findAllProductByStringWendor(search);
                BigDecimal sum = BigDecimal.ZERO;
                Map<Invoice, BigDecimal> invoices = new HashMap<>();
                for (Product product : p) {
                    invoices.put(product.getInvoice(), product.getRestQuantity());
                    sum = sum.add(product.getRestQuantity());
                }
                model.addAttribute("invs", invoices);
                model.addAttribute("sum", sum);
                return "rest";
            case "2":
                break;
            case "3":
                List<Invoice> invList = new ArrayList<>();
                List<Client> clients = clientService.findClientByNameShortSubstring(search);
                for (Client client : clients) {
                    List<Invoice> invs = invoiceService.findInvoicesNoClosedByClient(client);
                    invList.addAll(invs);
                }
                model.addAttribute("invs", invList);
                return "no_ship_invoices";
            case "5":
                List<Client> clnts = clientService.findClientByNameShortSubstring(search);
                if (clnts.size() == 0) {
                    model.addAttribute("errMsg", "По данному запросу ничего не найдено.");
                    return "error";
                } else if (clnts.size() > 1) {
                    model.addAttribute("errMsg", "По данному запросу найдено более одного клиента.\nУточните запрос.");
                    model.addAttribute("list", clnts);
                    return "error";
                }
                List<Product> list = productService.groupByNoShipProdsByClient(clnts.get(0));
                Map<String, ProdForm> groupBy = new HashMap<>();
                for (Product product : list) {
                    String vend = product.getProductSmall().getVendorCode();
                    if (!groupBy.containsKey(vend)) {
                        groupBy.put(vend, new ProdForm(vend, product.getProductSmall().getName(), product.getRestQuantity()));
                    } else {
                        ProdForm pf = groupBy.get(vend);
                        pf.setSum(pf.getSum().add(product.getRestQuantity()));
                        groupBy.put(vend, pf);
                    }
                }

                model.addAttribute("client", clnts.get(0));
                model.addAttribute("mapGroup", groupBy);
                return "group_by_client";
        }
        return "error";
    }


    @GetMapping("/rest")
    public String viewRest(Model model) {
        model.addAttribute("invs", new HashMap<Invoice, BigDecimal>());
        return "rest";
    }


}
