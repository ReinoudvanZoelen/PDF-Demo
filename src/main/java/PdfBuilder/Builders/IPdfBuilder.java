package PdfBuilder.Builders;

import java.util.List;

import PdfBuilder.Models.VehicleInvoice;

public interface IPdfBuilder {
    IPdfBuilder AddWhiteline();
    IPdfBuilder AddNewPage();
    IPdfBuilder AddParagraph(String text);
    IPdfBuilder AddVehiclesTables(List<VehicleInvoice> vehicleInvoices);
    void Build();
}