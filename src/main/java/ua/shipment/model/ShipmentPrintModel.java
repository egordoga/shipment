package ua.shipment.model;

import ua.shipment.entity.ProductShip;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ShipmentPrintModel extends AbstractTableModel {

    private List<ProductShip> productShips;

    public ShipmentPrintModel(List<ProductShip> productShips) {
        this.productShips = productShips;
    }

    @Override
    public int getRowCount() {
        return productShips.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return productShips.get(rowIndex).getProductSmall().getVendorCode();
            case 1:
                return productShips.get(rowIndex).getProductSmall().getName();
            case 2:
                return productShips.get(rowIndex).getDelivQuantity();
            case 3:
                return productShips.get(rowIndex).getDescription();
            default:
                return "";
        }
    }
}
