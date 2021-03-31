package br.gov.infraero.siga.pen.client.util;

import java.io.*;
import java.nio.file.Files;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;


public class ConvertToPDF {
    public static void main(String ... args) throws IOException, DocumentException {
        Document document = new Document();
        String input = "/Users/tiagomeneses/Downloads/teste_convert.jpeg"; // .gif and .jpg are ok too!
        String output = "/Users/tiagomeneses/Downloads/teste_convert.pdf";
        String inputHtml = "/Users/tiagomeneses/Downloads/teste.html";
        String outputPdf = "/Users/tiagomeneses/Downloads/teste_out.pdf";

        byte[] pdf = fromHTML(Files.readAllBytes(new File(inputHtml).toPath()));
        Files.write(new File(outputPdf).toPath(), pdf);
        System.exit(0);

        byte[] bts = Files.readAllBytes(new File(input).toPath());
        byte[] result = fromImage(bts);
        Files.write(new File(output).toPath(), result);

        System.exit(0);

        try {
            FileOutputStream fos = new FileOutputStream(output);


            Image image = Image.getInstance(input);
            //Rectangle imageSize = new Rectangle(image.getWidth() + 1f, image.getHeight() + 1f);
            //image.scaleAbsolute(image.getWidth(), image.getHeight());
            //Document document = new Document(imageSize, 0, 0, 0, 0);
            PdfWriter writer = PdfWriter.getInstance(document, fos);
            writer.open();
            document.open();

            float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
                    - document.rightMargin() - 0) / image.getWidth()) * 100; // 0 means you have no indentation. If you have any, change it.
            image.scalePercent(scaler);
            image.setAlignment(Image.ALIGN_CENTER | Image.ALIGN_TOP);
            //com.lowagie.text.Document document = new com.lowagie.text.Document(imageSize, 0, 0, 0, 0);
            //image.setAbsolutePosition(0, 0);
            //image.setBorderWidth(0);
            //image.scaleAbsolute(PageSize.A4);
            document.add(image);
            document.close();
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] fromImage(byte[] imageBytes) throws IOException, DocumentException {
        Document document = new Document();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Image image = Image.getInstance(imageBytes);

        PdfWriter writer = PdfWriter.getInstance(document, baos);
        writer.open();
        document.open();

        float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
                - document.rightMargin() - 0) / image.getWidth()) * 100; // 0 means you have no indentation. If you have any, change it.
        image.scalePercent(scaler);
        image.setAlignment(Image.ALIGN_CENTER | Image.ALIGN_TOP);

        document.add(image);
        document.close();
        writer.close();

        return baos.toByteArray();
    }

    public static byte[] fromHTML(byte[] htmlBytes) throws IOException, DocumentException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteArrayInputStream bais = new ByteArrayInputStream(htmlBytes);
        HtmlConverter.convertToPdf(bais, baos);
        return baos.toByteArray();
    }
}
