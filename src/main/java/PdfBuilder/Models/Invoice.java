package PdfBuilder.Models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Invoice {
    public String invoiceNumberString;
    public Date invoiceDate;
    // Date ExpirationDate;

    // Communication
    public PersonalInformation personalInformation;
    public SupplierInformation supplierInformation;

    // Vehicles
    public List<VehicleInvoice> vehicleInvoices;

    public Invoice(String invoiceNumberString, Date invoiceDate, PersonalInformation personalInformation,
            SupplierInformation supplierInformation, List<VehicleInvoice> vehicleInvoices) {
        this.invoiceNumberString = invoiceNumberString;
        this.invoiceDate = invoiceDate;
        this.personalInformation = personalInformation;
        this.supplierInformation = supplierInformation;
        this.vehicleInvoices = vehicleInvoices;
    }

    public BigDecimal calculatePriceBeforeTaxes() {
        System.out.println("Calculating Invoice price before taxes");
        BigDecimal total = new BigDecimal(0);

        for (VehicleInvoice vi : vehicleInvoices) {
            total = total.add(vi.calculatePriceBeforeTaxes());
            System.out.println("Adding value " + vi.calculatePriceBeforeTaxes() + " to Invoice, now " + total);
        }

        System.out.println("Finished calculating Invoice price before taxes");
        return total;
    }

    public BigDecimal calculateTaxes() {
        return this.calculatePriceAfterTaxes().subtract(this.calculatePriceBeforeTaxes());
    }

    public BigDecimal calculatePriceAfterTaxes() {
        return this.calculatePriceBeforeTaxes().multiply(new BigDecimal(1.21));
    }

}