package org.dice_research.sask_commons.parser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RIOT;

public class FormatParser {
	
	private FormatParser() {};
	
	public static String parse(String input, Lang inputFormat, Lang outputFormat) {
		
		System.out.println(inputFormat+" --> "+ outputFormat.getName());
		InputStream in = new ByteArrayInputStream(input.getBytes());
		RIOT.init();
		Model model = ModelFactory.createDefaultModel();
		model.read(in, null, inputFormat.getName());
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		OutputStream baos = new ByteArrayOutputStream();
		model.write(baos, outputFormat.getName());
		String ret = baos.toString();
		try {
			baos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
}
