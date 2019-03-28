import java.io.FileNotFoundException;
import java.util.Date;

import PdfBuilder.Builders.Implementations.iTextPdfBuilder;
import PdfBuilder.Models.Invoice;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        new Main().CreatePdfDocument();
    }

    public void CreatePdfDocument() throws FileNotFoundException {
        Invoice invoice = MockInvoiceGenerator.Generate();

        new iTextPdfBuilder(getFilePath(), getFileName())
            .AddParagraph(invoice.personalInformation.toString())
            .AddWhiteline()
            .AddParagraph(invoice.supplierInformation.toString())
            .AddWhiteline()
            .AddVehiclesTables(invoice.vehicleInvoices)
            .Build();
        
        System.out.println("Pdf created");
    }

    private String getFilePath(){
        return "/home/reinoud/Downloads/pdfpoc/output";
    }

    private String getFileName(){
        long datetime = new Date().getTime();
        return "sample_" + datetime;
    }

}
