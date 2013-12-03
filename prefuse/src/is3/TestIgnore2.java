package is3;

import java.io.BufferedReader;
import java.io.File;
import java.util.Scanner;

public class TestIgnore2 {

	public static void main(String[] args){
		String name = null;
		String gold = null;
		String silver = null;
		String bronze = null;

		String s13 = "  </branch>";
		String s14 = "</branch>";
		String s15 = "</tree>";

		BufferedReader br = null;
		File file = null;
		Scanner newS = null;
		int row = 1;
		int collumn = 0;
		try {

			// br = new BufferedReader(new
			// FileReader("/prefuse/data/WrangledData.csv"));
			// file = new File("amazon.txt");
			file = new File(System.getProperty("user.dir")
					+ "/data/WrangledData.csv");
			String test = file.getAbsolutePath();
			// System.out.println(test);
			newS = new Scanner(file);
			String strLine;
			strLine = newS.nextLine();
			// Read File Line By Line
			while ((strLine = newS.nextLine()) != null) {
				// Print the content on the console
				Scanner lineScanner = new Scanner(strLine);
				String[] g = strLine.split(",");
				if (g[1].equals("7")) {
					name = g[0];
					gold = g[12];
					silver = g[13];
					bronze = g[14];

					
//				  String d1=   " <branch>";
//				  String d2=   "  <attribute name=\"name\" value=\"Gold\"/>";
//				  String d3=   "   <leaf>";
//				  String d4=   "    <attribute name=\"name\" value=\"" + gold+ "\"/>";
//				  String d5=   "   </leaf>";
//				  String d6=   " </branch> ";
//				  String d7=   " <branch>";
//				  String d8=   "  <attribute name=\"name\" value=\"Silver\"/>";
//				  String d9=   "   <leaf>";
//				  String d10=  "    <attribute name=\"name\" value=\"" + silver+ "\"/>";
//				  String d11=  "   </leaf>";
//				  String d12=  " </branch> ";
//				  String d13=  " <branch>";
//				  String d14=  "  <attribute name=\"name\" value=\"Bronze\"/>";
//				  String d15=  "   <leaf>";
//				  String d16=  "    <attribute name=\"name\" value=\"" + bronze+ "\"/>";
//				  String d17=  "   </leaf>";
//				  String s18=  " </branch> ";
					
					String s1 =    "   <branch>";
					String s2 =    "    <attribute name=\"name\" value=\"" + name+ "\"/>";
					  String d1=   "     <branch>";
					  String d2=   "      <attribute name=\"name\" value=\"Gold\"/>";
					  String d3=   "       <leaf>";
					  String d4=   "        <attribute name=\"name\" value=\"" + gold+ "\"/>";
					  String d5=   "       </leaf>";
					  String d6=   "     </branch> ";
					  String d7=   "     <branch>";
					  String d8=   "      <attribute name=\"name\" value=\"Silver\"/>";
					  String d9=   "       <leaf>";
					  String d10=  "        <attribute name=\"name\" value=\"" + silver+ "\"/>";
					  String d11=  "       </leaf>";
					  String d12=  "     </branch> ";
					  String d13=  "     <branch>";
					  String d14=  "      <attribute name=\"name\" value=\"Bronze\"/>";
					  String d15=  "       <leaf>";
					  String d16=  "        <attribute name=\"name\" value=\"" + bronze+ "\"/>";
					  String d17=  "       </leaf>";
					  String d18=  "     </branch> ";
					String s12 =   "   </branch>";

					System.out.println(s1);
					System.out.println(s2);
					System.out.println(d1);
					System.out.println(d2);
					System.out.println(d3);
					System.out.println(d4);
					System.out.println(d5);
					System.out.println(d6);
					System.out.println(d7);
					System.out.println(d8);
					System.out.println(d9);
					System.out.println(d10);
					System.out.println(d11);
					System.out.println(d12);
					System.out.println(d13);
					System.out.println(d14);
					System.out.println(d15);
					System.out.println(d16);
					System.out.println(d17);
					System.out.println(d18);
					System.out.println(s12);
				}
			}
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		} finally {
			newS.close();
		}
	}
	
}
	
