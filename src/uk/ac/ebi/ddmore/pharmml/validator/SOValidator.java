package uk.ac.ebi.ddmore.pharmml.validator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import eu.ddmore.libpharmml.IValidationReport;
import eu.ddmore.libpharmml.so.SOFactory;
import eu.ddmore.libpharmml.so.StandardisedOutputResource;
import eu.ddmore.libpharmml.so.dom.StandardisedOutput;
import eu.ddmore.libpharmml.so.impl.LibSO;

public class SOValidator {
	
	static LibSO libSO = SOFactory.getInstance().createLibSO();

	public static void main(String[] args) {
		
		for(String fileName : args){
			
			try {
//				
				validate(fileName);
			
			} catch (IOException e) {
				print("Error: "+e.getMessage()+"\n");
			} finally {
				print("---------------------------------------\n");
			}
			
		}

	}
	
	public static void validate(String fileName) throws IOException{
		print("Validating SO: "+fileName+"\n");
		
		InputStream in = new FileInputStream(fileName);
		StandardisedOutputResource soResource = libSO.createDomFromResource(in);
		in.close();
		
		// Creation report
		IValidationReport creationReport = soResource.getCreationReport();
		print("Creation report: "+creationReport.numErrors()+" error(s)\n");
		for(int i=1;i<=soResource.getCreationReport().numErrors();i++){
			print("\t- "+soResource.getCreationReport().getError(i)+"\n");
		}
		@SuppressWarnings("unused")
		StandardisedOutput dom = soResource.getDom();
	}
	
	private static void print(String o){
		System.out.print(o);
	}

}
