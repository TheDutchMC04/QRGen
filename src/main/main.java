package main;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

public class main {

	static String barcodeValue;
	static String startAt, amount;
	static int atCurrentlyCount, atCurrently;
	
	public static void main(String[] args) {
		
		mainGui.init();
		
	}
	public static void checkInput() {
		
		barcodeValue = mainGui.inputLinkValue;
		startAt = mainGui.startNumberValue;
		amount = mainGui.amountNumberValue;
		
		int amountInt = Integer.parseInt(amount);
		atCurrently = atCurrentlyCount + Integer.parseInt(startAt);
		
		while(amountInt >= atCurrentlyCount) {
			if(mainGui.isEntered) {
				try {
					outputtingBarcode128x2();
				} catch (BarcodeException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (OutputException e) {
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
	
	public static void outputtingBarcode128x2() throws BarcodeException, IOException, OutputException {
		Barcode barcode3;
		barcode3 = BarcodeFactory.createCode128(barcodeValue + "?=barcodeID:" + atCurrently);
		barcode3.setResolution(500);
		barcode3.setDrawingText(false);
		BarcodeImageHandler.savePNG(barcode3, new File(Integer.toString(atCurrently) + ".png"));
		
		System.out.println("barcode " + atCurrently + " has been generated");
		
		atCurrently = atCurrently + 1;
		atCurrentlyCount = atCurrentlyCount + 1;
		
		System.out.println("Currently at: " + atCurrently);
}
	 
}
