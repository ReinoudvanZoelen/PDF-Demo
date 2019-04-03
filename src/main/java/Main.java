import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import PdfBuilder.Builders.Implementations.iTextPdfBuilder;
import PdfBuilder.Models.Invoice;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        new Main().CreatePdfDocument();
    }

    public void CreatePdfDocument() throws FileNotFoundException {
        Invoice invoice = MockInvoiceGenerator.Generate();

        try {
            new iTextPdfBuilder(getFilePath(), getFileName())
                    .AddTextboxParagraph(invoice.supplierInformation.toString(), 350, 610, 200)
                    .AddTitleImage()
                        .AddWhiteline()
                        .AddWhiteline()
                        .AddWhiteline()
                        .AddWhiteline()
                        .AddWhiteline()
                    .AddWhiteline().AddParagraph(invoice.personalInformation.toString())
                        .AddWhiteline()
                        .AddWhiteline()
                    .AddInvoiceTitle("Factuur")
                    .AddInvoiceInformation(invoice)
                        .AddWhiteline()
                    .AddVehiclesTables(invoice.vehicleInvoices)
                .Build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("Pdf created");
    }

    private String getFilePath(){
        // Linux
        //return "/home/reinoud/Downloads/pdfpoc/output";
        // Windows 
        return "c:/repos/PDF-demo/output";
    }

    private String getFileName(){
        return "sample_" + new Date().getTime();
    }

}
