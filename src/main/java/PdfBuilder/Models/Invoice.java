package PdfBuilder.Models;

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

}