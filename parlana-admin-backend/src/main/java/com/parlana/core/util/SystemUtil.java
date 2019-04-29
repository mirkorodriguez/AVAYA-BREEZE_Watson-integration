package com.parlana.core.util;

import static com.parlana.core.util.Constants.GCLOD_PATH;
import static com.parlana.core.util.Constants.GCLOD_SHELL;
import static com.parlana.core.util.Constants.GCLOD_TOKEN_JSON;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class SystemUtil {

	@SuppressWarnings("unchecked")
	public static void setEnv(String key, String value) {
		try {
			Map<String, String> env = System.getenv();
			Class<?> cl = env.getClass();
			Field field = cl.getDeclaredField("m");
			field.setAccessible(true);
			Map<String, String> map = (Map<String, String>) field.get(env);
			Map<String, String> writableEnv = map;
			writableEnv.put(key, value);
		} catch (Exception e) {
			throw new IllegalStateException("Failed to set environment variable", e);
		}
	}
	
	public static String executeShell() throws Exception {	
		ProcessBuilder ProcessBuilder = new ProcessBuilder();
		ProcessBuilder.directory(new File(GCLOD_PATH));
		ProcessBuilder.command(GCLOD_SHELL, GCLOD_TOKEN_JSON);
		Process process = ProcessBuilder.start();
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		StringBuilder stringBuilder = new StringBuilder();

		String line = null;
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
		}

		String result = stringBuilder.toString();
		System.out.println("TOKEN Generated : " + result);
		return result.trim();
	}
	
	public static String processCommand(String[] command) {
		String result = "";
		ProcessBuilder process = new ProcessBuilder(command);
	    Process p;
	    try
	    {
	        p = process.start();   
	        try {
				p.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	        
            BufferedReader reader =  new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ( (line = reader.readLine()) != null) {
                    builder.append(line);
                    builder.append(System.getProperty("line.separator"));
            }
            result = builder.toString();
	    }
	    catch (IOException e)
	    {   
	        e.printStackTrace();
	    }
	    return result;
	}
	
	public static String sendEmail() {
			/*
			 * This will initiate the API with the endpoint and your access key.
			 *
			 */
			Mailin http = new Mailin("https://api.sendinblue.com/v2.0","9GLpgnDZVwrP2ABI");

			/** Prepare variables for easy use **/

				Map < String, String > to = new HashMap < String, String > ();
					to.put("to@example.net", "to whom!");
				Map < String, String > cc = new HashMap < String, String > ();
					cc.put("cc@example.net", "cc whom!");
				Map < String, String > bcc = new HashMap < String, String > ();
					bcc.put("bcc@example.net", "bcc whom!");
				Map < String, String > headers = new HashMap < String, String > ();
					headers.put("Content-Type", "text/html; charset=iso-8859-1");
					headers.put("X-param1", "value1");
					headers.put("X-param2", "value2");
					headers.put("X-Mailin-custom", "my custom value");
					headers.put("X-Mailin-IP", "102.102.1.2");
					headers.put("X-Mailin-Tag", "My tag");

				Map < String, Object > data = new HashMap < String, Object > ();
					data.put("to", to);
					data.put("cc", cc);
					data.put("bcc", bcc);
					data.put("replyto", new String [] {"replyto@email.com","reply to!"});
					data.put("from", new String [] {"from@email.com","from email!"});
					data.put("subject", "My subject");
					data.put("html", "This is the <h1>HTML</h1>");
					data.put("text", "This is text");
					data.put("attachment", new String [] {});
					data.put("headers", headers);
					data.put("inline_image", new String [] {});

				String str = http.send_email(data);
				System.out.println(str);
				return str;
	}
	
	public static void replaceTextInFile(File file, String searchText, String replaceText) {
		try {
			FileReader fr = new FileReader(file);
			String s;
			String totalStr = "";
			BufferedReader br =  new BufferedReader(fr);
			while ((s = br.readLine()) != null) {
				totalStr += s;
			}
			totalStr = totalStr.replaceAll(searchText, replaceText);
			FileWriter fw = new FileWriter(file);
			fw.write(totalStr);
			fw.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void createJsonFile(String fullFilename, JSONObject obj) {
	    File file = new File(fullFilename);
	    if(file.exists()){
	    	file.delete();
	    }
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(fullFilename);
			fileWriter.write(obj.toString());
			fileWriter.flush();
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	public static void copyFile(File sourceFile, File destFile) throws IOException {
	    if(!destFile.exists()) {
	        destFile.createNewFile();
	    }

	    FileChannel source = null;
	    FileChannel destination = null;

	    try {
	        source = new FileInputStream(sourceFile).getChannel();
	        destination = new FileOutputStream(destFile).getChannel();
	        destination.transferFrom(source, 0, source.size());
	    }
	    finally {
	        if(source != null) {
	            source.close();
	        }
	        if(destination != null) {
	            destination.close();
	        }
	    }
	}
	
}
