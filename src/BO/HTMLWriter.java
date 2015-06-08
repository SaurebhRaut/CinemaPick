package BO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class HTMLWriter<E> {
	private static String folderName = "";
	private static String reportPath = "";
	
	public static String getFolderName() {
		return folderName;
	}

	public static void setFolderName(String folderName) {
		HTMLWriter.folderName = folderName;
	}

	public HTMLWriter() {

	}

	public void writeIntoFile(String fileName, ArrayList<E> listData,
			String title, int numElementsToShow, boolean append)
			throws IOException, IllegalArgumentException,
			IllegalAccessException {

		/* Helper Strings */
		String tr = "<tr>";
		String endtr = "</tr>";
		String endtd = "</td>";
		String td = "<td>";
		String br = "<br>";
		String th = "<th>";
		String endth = "</th>";

		String diskPath = "C:" + File.separator+ "Users" + File.separator + "suhas" + File.separator + "Desktop" + File.separator + "Course Work" + File.separator + "Q3" + File.separator+ "WebApps";
		
		//Create a new folder
		//File dir = new File(folderName);
		File dir = new File(diskPath+File.separator + folderName);
		if(dir.mkdir())
				System.out.println("SUCCESS");
		
		//String workingDirectory = System.getProperty("user.dir");
		//System.out.println("Working Directory = " + workingDirectory);
		String absoluteFilePath = "";
		
		
		//absoluteFilePath = workingDirectory + File.separator + folderName + File.separator + fileName;
		absoluteFilePath = diskPath + File.separator + folderName + File.separator + fileName;
		String reportPath = diskPath + File.separator + folderName + File.separator;
		this.setreportPath(reportPath);
		
		File filee = new File(absoluteFilePath);
		System.out.println("Absolute Path = " + absoluteFilePath);
		if (!filee.exists()) {
			filee.createNewFile();
		}

		int i = 0;
		String htmlString = "<center><div><h2>" + title + "</h2><br>";
		FileWriter fWriter = new FileWriter(filee.getAbsoluteFile(), append);
		BufferedWriter buff = new BufferedWriter(fWriter);
		buff.write(htmlString);
		// buff.write(br);
		buff.write("The count of errors is = " + listData.size());
		buff.write(br);
		buff.write("<table border=2>");

		if (listData.size() > 0) {
			// Make the table headers
			Object ob = listData.get(0);
			Class c = ob.getClass();
			Field[] fie = c.getFields();
			buff.write(tr);
			for (int k = 0; k < numElementsToShow; k++) {
				String toShow = fie[k].getName();
				buff.write(th);
				buff.write(toShow);
				buff.write(endth);
			}
			buff.write(endtr);

			// Display the data
			while (i < listData.size()) {
				buff.write(tr);
				Object obj = listData.get(i);
				Class cl = obj.getClass();
				Field[] fields = cl.getFields();
				for (int j = 0; j < numElementsToShow; j++) {
					buff.write(td);
					Object obj1;
					obj1 = fields[j].get(obj);
					if (obj1 != null) {
						buff.write(obj1.toString());
						System.out.println(obj1.toString());
					}
					buff.write(endtd);

				}
				i++;
				buff.write(endtr);
			}
			buff.write("</table>");
			buff.write("</center></div>");
			System.out.println("Done!");
		} else {
			String noResults = "<center><h4>No Errors in this category.</h4></center>";
			buff.write(noResults);
		}

		buff.close();
	}

	public String getreportPath() {
		return reportPath;
	}

	public void setreportPath(String reportPath) {
		HTMLWriter.reportPath = reportPath;
	}
}
