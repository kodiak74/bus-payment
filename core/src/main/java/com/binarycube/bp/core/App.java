package com.binarycube.bp.core;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import com.binarycube.bp.core.model.ProcessingBatch;

/**
 * Core commandline interface for running bus payment system
 *
 */
public class App {

	public App() {
	}
	
	public void run(String[] args) {
		if (args.length != 2) {
			printUsuage();
			System.exit(1);
		}
		System.out.println( "Processing input file '"+ args[0] + "' to '" + args[1] +"'." );
		
		//Check input exists
		File inFile = new File(args[0]);
		if (!inFile.exists()) {
			System.out.println( "Error: Input file '"+ args[0] + "' not found." );
			System.exit(1);
		}
		
		//Check output file exists
		File outFile = new File(args[1]);
		if (!outFile.exists()) System.out.println( "Output file '"+ args[1] + "' exists and will be overwritten." );

		//And pass reader and writer to core processor
		try {
			Writer outWriter = new FileWriter(outFile, false);
			Reader inReader = new FileReader(inFile);
			ITripProcessor tripProcessor = new TripFileProcessor(outWriter);
			BusPaymentProcesser bpp = new BusPaymentProcesser(args[0], inReader, tripProcessor);
			ProcessingBatch pb = bpp.run();
			
			if (pb.getStatus() == 0) {
				System.out.println(pb.toString());
			}
			else System.out.println("Processing error: " + pb.getMessage());
			//Cleanup
		    inReader.close();
		    outWriter.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}    
		
	}
	
	public void printUsuage() {
		StringBuilder buffer= new StringBuilder();
		buffer.append("Usage: [app] source dest").append("\n");
		buffer.append("Requires first parameter to be the source file (CSV)").append("\n");
		buffer.append("Requires second parameter to be the output filename (Note: Will overwrite if already exists.)").append("\n");
		System.out.println(buffer.toString());
	}
	
	
	public static void main( String[] args ) {
		App myApp = new App();
		myApp.run(args);
    }
    
}
