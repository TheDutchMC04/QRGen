package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

//import net.sourceforge.barbecue.Barcode;
//import net.sourceforge.barbecue.BarcodeException;
//import net.sourceforge.barbecue.BarcodeFactory;
//import net.sourceforge.barbecue.BarcodeImageHandler;
//import net.sourceforge.barbecue.output.OutputException;

public class main {

	static String barcodeValue;
	static String startAt;
	static int atCurrentlyCount, atCurrently;
	
	static int height, width, amount;
	
	public static void main(String[] args) {
		
		mainGui.init();
		
	}
	public static void checkInput() {
		
		barcodeValue = mainGui.inputLinkValue;
		startAt = mainGui.startNumberValue;
		
		height = Integer.parseInt(mainGui.height);
		width = Integer.parseInt(mainGui.width);
		
		amount = Integer.parseInt(mainGui.amountNumberValue);
		atCurrently = atCurrentlyCount + Integer.parseInt(startAt);
		
		while(amount >= atCurrentlyCount) {
			if(mainGui.isEntered) {
				try {
					createQR(barcodeValue, width, height);
				} catch (WriterException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Nothing Entered, waiting");
				try {
					TimeUnit.SECONDS.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				checkInput();
			}
		}
	}
	
	/*public static void outputtingBarcode() throws BarcodeException, IOException, OutputException {
		Barcode barcode3;
		barcode3 = BarcodeFactory.createPDF417(barcodeValue + "?=barcodeID:" + atCurrently);
	    barcode3.setBarHeight(40);
	    barcode3.setBarWidth(2);
		barcode3.setResolution(500);
		barcode3.setDrawingText(false);
		BarcodeImageHandler.savePNG(barcode3, new File(Integer.toString(atCurrently) + ".png"));
		
		System.out.println("barcode " + atCurrently + " has been generated");
		
		atCurrently = atCurrently + 1;
		atCurrentlyCount = atCurrentlyCount + 1;
		System.out.println("Currently at: " + atCurrently);
	}*/
	 
	public static void createQR(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text + "?=senderID:" +atCurrently, BarcodeFormat.QR_CODE, width, height);
		
		try {
		    String basePath = new File("").getAbsolutePath();
		    System.out.println(basePath);
		    File file = new File(String.format(atCurrently + ".png"));
		    MatrixToImageWriter.writeToFile(bitMatrix, "PNG", file);

		    System.out.println("Barcode " + atCurrently + " has been generated");

		    atCurrently = atCurrently + 1;
		    atCurrentlyCount = atCurrentlyCount + 1;
		} catch (Exception e) {
		    atCurrently = 0;
		}
		System.out.println("Currently at: " + atCurrently);
		
	}
}
