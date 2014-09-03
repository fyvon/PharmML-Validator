package uk.ac.ebi.ddmore.pharmml.validator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import eu.ddmore.libpharmml.ILibPharmML;
import eu.ddmore.libpharmml.IPharmMLResource;
import eu.ddmore.libpharmml.IPharmMLValidator;
import eu.ddmore.libpharmml.IValidationReport;
import eu.ddmore.libpharmml.PharmMlFactory;
import eu.ddmore.libpharmml.dom.PharmML;

public class Validator {

	public static void main(String[] args) {
		
		ILibPharmML libPharmML = PharmMlFactory.getInstance().createLibPharmML();
		
		for(String fileName : args){
			
			try {
				print("\n---------------------------------------\n");
				print("MODEL: "+fileName+"\n");

				InputStream in = new FileInputStream(fileName);
				IPharmMLResource pmlResource = libPharmML.createDomFromResource(in);
				in.close();
				
				IValidationReport creationReport = pmlResource.getCreationReport();
				print("CREATION REPORT: "+creationReport.numErrors()+" error(s)\n");
				for(int i=1;i<=pmlResource.getCreationReport().numErrors();i++){
					print("\t- "+pmlResource.getCreationReport().getError(i)+"\n");
				}
				@SuppressWarnings("unused")
				PharmML dom = pmlResource.getDom();
	
				IPharmMLValidator validator = libPharmML.getValidator();
				IValidationReport rpt = validator.createValidationReport(pmlResource);
				print("VALIDATION REPORT: "+rpt.numErrors()+" error(s)\n");
				for(int i=1;i<=rpt.numErrors();i++){
					print("\t- "+rpt.getError(i)+"\n");
				}	
			
			} catch (IOException e) {
				print("Error: "+e.getMessage()+"\n");
			} finally {
				print("\n---------------------------------------\n");
			}
			
		}
		
	}
	
	private static void print(String o){
		System.out.print(o);
	}

}
