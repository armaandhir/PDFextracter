import java.io.File;
import java.util.Scanner;


public class runApp {
	
	private static String inputFilePath() {
		String filePath = null;
		System.out.println("Please enter the full path of the file to READ: ");
		try {
			Scanner in = new Scanner(System.in);
			filePath = in.nextLine();
			in.close();
		} catch (Exception e) {
			System.out.println("Incorrect Format");
			return null;
		}
		return filePath;
	}
	
	public static void main(String[] args) {
		String parsedText = null;
		String fileName = "C:/Users/armaan/Desktop/test.pdf";
	    File file = new File(fileName);
		
	    PDFtextReader PDFtextReaderObj1 = new PDFtextReader();
		//PDFtextReaderObj1.test();
		//file = PDFtextReaderObj1.extractTextFromFile(inputFilePath());
		PDFtextReaderObj1.parsePDF(file);
		parsedText = PDFtextReaderObj1.stripPDF(file);
		
		//System.out.println(parsedText); 
	}

}
