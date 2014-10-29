/*******************************************************************************
 * Copyright (c) 2014 European Molecular Biology Laboratory,
 * Heidelberg, Germany.
 *
 * Licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in
 * compliance with the License.  You may obtain a copy of
 * the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, 
 * software distributed under the License is distributed on 
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY 
 * KIND, either express or implied. See the License for the 
 * specific language governing permissions and limitations 
 * under the License.
 *******************************************************************************/
package uk.ac.ebi.ddmore.pharmml.validator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import eu.ddmore.libpharmml.ILibPharmML;
import eu.ddmore.libpharmml.IPharmMLResource;
import eu.ddmore.libpharmml.IValidationReport;
import eu.ddmore.libpharmml.PharmMlFactory;
import eu.ddmore.libpharmml.dom.PharmML;

public class Validator {

	public static void main(String[] args) {
		
		ILibPharmML libPharmML = PharmMlFactory.getInstance().createLibPharmML();
		
		for(String fileName : args){
			
			try {
//				print("\n---------------------------------------\n");
				print("Validating model: "+fileName+"\n");

				InputStream in = new FileInputStream(fileName);
				IPharmMLResource pmlResource = libPharmML.createDomFromResource(in);
				in.close();
				
				// Creation report
				IValidationReport creationReport = pmlResource.getCreationReport();
				print("Creation report: "+creationReport.numErrors()+" error(s)\n");
				for(int i=1;i<=pmlResource.getCreationReport().numErrors();i++){
					print("\t- "+pmlResource.getCreationReport().getError(i)+"\n");
				}
				@SuppressWarnings("unused")
				PharmML dom = pmlResource.getDom();
	
				// Validation report
//				IPharmMLValidator validator = libPharmML.getValidator();
//				IValidationReport rpt = validator.createValidationReport(pmlResource);
//				print("Validation report: "+rpt.numErrors()+" error(s)\n");
//				for(int i=1;i<=rpt.numErrors();i++){
//					print("\t- "+rpt.getError(i)+"\n");
//				}	
			
			} catch (IOException e) {
				print("Error: "+e.getMessage()+"\n");
			} finally {
				print("---------------------------------------\n");
			}
			
		}
		
	}
	
	private static void print(String o){
		System.out.print(o);
	}

}
