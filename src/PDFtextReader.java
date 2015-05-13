/*
 * -----------------------------------------
 * PDFanalyzer
 * Uses Apache PDFBox[v1.8.8] API 
 * -----------------------------------------
 * Author: Armaan Dhir
 * Start Date: 2014-12-20
 * End Date: 
 * Time: 1 hour and 5 minutes
 * -----------------------------------------
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class PDFtextReader {
	//private File file;
	private PDFParser pdfParser;
	private String parsedText;
	private PDFTextStripper pdfStripper;
	private PDDocument pdDoc;
	private COSDocument cosDoc;
	
	public PDFtextReader() {
		//file = null;
		parsedText = null;
		pdfStripper = null;
		pdDoc = null;
		cosDoc = null;
	}
	
	// Method to read the PDF document
	public File extractTextFromFile(String fileName) {
		File file = new File(fileName);
		if(!file.isFile()) {
			System.out.println("File " + fileName + " does not exist!");
			return null;
		}
		return file;
	}
	
	// Parse pdf and return PDDocument to be used by PDFStripper
	public void parsePDF(File file){
	//	try {
	//		pdfParser = new PDFParser(new FileInputStream(file));
	//	} catch (IOException e) {
	//		System.err.println("Unable to open PDF Parser. " + e.getMessage());
			//return null;
	//	}
		try {
			pdfParser = new PDFParser(new FileInputStream(file));
			pdfParser.parse();
			cosDoc = pdfParser.getDocument();
			pdDoc = new PDDocument(cosDoc);
			pdfStripper = new PDFTextStripper();
		} catch (Exception e) {
			System.err.println("An exception occured in parsing the PDF Document." + e.getMessage());
		}
		finally {
			try {
				if (cosDoc != null)
					cosDoc.close();
				//if (pdDoc != null)
				//	pdDoc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//return pdDoc;
	}
	
	public void closeDocument() {
		try {
			if (cosDoc != null)
				cosDoc.close();
			if (pdDoc != null)
				pdDoc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// strip text from the Document
	public String stripPDF(File file) {
		parsePDF(file);
		try {
			//pdfStripper = new PDFTextStripper();
			parsedText = pdfStripper.getText(pdDoc);
		} catch (IOException e) {
			System.err.println("An exception occured in stripping the PDF Document." + e);
		} catch (Exception e) {
			System.out.println(e);
		}
		finally {
			//closeDocument();
			if (pdDoc != null)
				try {
					pdDoc.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return parsedText;
	}
	
	// strip text from the Document with assigned start and end page number
	public String stripPdf(int startPage, int endPage) {
		try {
			pdfStripper = new PDFTextStripper();
			pdfStripper.setStartPage(startPage);
			pdfStripper.setEndPage(endPage);
			parsedText = pdfStripper.getText(pdDoc);
		} catch (IOException e) {
			System.err.println("An exception occured in stripping the PDF Document." + e.getMessage());
		}
		return parsedText;
	}
	
	public void test() {
		//String parsedText;
	    String fileName = "C:/Users/armaan/Desktop/test.pdf";
	    File file = new File(fileName);
	    try {
	        pdfParser = new PDFParser(new FileInputStream(file));
	        pdfParser.parse();
	        cosDoc = pdfParser.getDocument();
	        pdfStripper = new PDFTextStripper();
	        pdDoc = new PDDocument(cosDoc);
	        parsedText = pdfStripper.getText(pdDoc);
	        //System.out.println(parsedText);
	    } catch (Exception e) {
	        e.printStackTrace();
	        try {
	            if (cosDoc != null)
	                cosDoc.close();
	            if (pdDoc != null)
	                pdDoc.close();
	        } catch (Exception e1) {
	            e.printStackTrace();
	        } 
	    }
	}
	
	
	public void printParsedText() {
		System.out.println(parsedText);
	}
	
}
