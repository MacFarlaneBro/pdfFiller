package pdfFiller;
 
import java.io.IOException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfReader;
 
public class TextFields {
 
	
    /**
     * Main method
     * @param args no arguments needed
     * @throws IOException
     * @throws DocumentException
     */
    public static void main(String[] args) throws DocumentException, IOException {
    	String filename = "AscentricForm.pdf";
        PdfReader reader = new PdfReader(filename);
		  System.out.println(filename);
		  System.out.print("Number of pages: ");
		  System.out.println(reader.getNumberOfPages());
		  Rectangle mediabox = reader.getPageSize(1);
		  System.out.print("Size of page 1: [");
		  System.out.print(mediabox.getLeft());
		  System.out.print(',');
		  System.out.print(mediabox.getBottom());
		  System.out.print(',');
		  System.out.print(mediabox.getRight());
		  System.out.print(',');
		  System.out.print(mediabox.getTop());
		  System.out.println("]");
		  System.out.print("Rotation of page 1: "); 
		  System.out.println(reader.getPageRotation(1)); 
		  System.out.print("Page size with rotation of page 1: "); 
		  System.out.println(reader.getPageSizeWithRotation(1)); 
		  System.out.print("File length: "); 
		  System.out.println(reader.getFileLength()); 
		  System.out.print("Is rebuilt? "); 
		  System.out.println(reader.isRebuilt());
		  System.out.print("Is encrypted? ");
		  System.out.println(reader.isEncrypted());
		  System.out.println();
		  System.out.flush();
        System.out.println("Completed!");
    }
}
