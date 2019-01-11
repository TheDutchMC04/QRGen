package main;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class main {

	static String barcodeValue;
	static String startAt;
	static int atCurrentlyCounter, atCurrently;
	
	static int height, width, amount;
	
	public static void main(String[] args) {
		
		mainGui.init();
		
	}
	public static void checkInput() {
		
		barcodeValue = mainGui.inputLinkValue;
		startAt = mainGui.startNumberValue;
		height = Integer.parseInt(mainGui.height);
		width = Integer.parseInt(mainGui.width);
		amount = Integer.parseInt(mainGui.amountNumberValue) + Integer.parseInt(mainGui.startNumberValue);
		atCurrently = atCurrently + Integer.parseInt(mainGui.startNumberValue);
		
		while(amount >= atCurrently) {
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

	@SuppressWarnings("deprecation")
	public static void createQR(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text + "?=senderID:" + atCurrently, BarcodeFormat.QR_CODE, width, height);
		
		try {
		    String basePath = new File("").getAbsolutePath();
		    System.out.println(basePath);
		    File file = new File(String.format(atCurrently + ".png"));
		    MatrixToImageWriter.writeToFile(bitMatrix, "PNG", file);

		    System.out.println("Barcode " + atCurrently + " has been generated");

		    atCurrently++;
		    atCurrentlyCounter++;
		} catch (Exception e) {
		    e.printStackTrace();
		}
		System.out.println("Currently at: " + atCurrentlyCounter);
	}
	
	public static void reset() {
		barcodeValue = "";
		startAt = "";
		height = 0;
		width = 0;
		amount = 0;
		atCurrently = 0;
		atCurrentlyCounter = 0;
		
		System.out.println("Reset Complete");
	}
}
