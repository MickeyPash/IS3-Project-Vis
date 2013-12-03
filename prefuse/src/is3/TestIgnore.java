package is3;

import java.io.BufferedReader;
import java.io.File;
import java.util.Scanner;

public class TestIgnore {

	public static void main(String args[]) {

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
				if (g[1].equals("1")) {
					name = g[0];
					gold = g[12];
					silver = g[13];
					bronze = g[14];

					String s1 = "   <branch>";
					String s2 = "    <attribute name=\"name\" value=\"" + name+ "\"/>";
					String s3 = "    <leaf>";
					String s4 = "     <attribute name=\"name\" value=\"" + gold+ "\"/>";
					String s5 = "    </leaf>";
					String s6 = "    <leaf>";
					String s7 = "     <attribute name=\"name\" value=\""+ silver + "\"/>";
					String s8 = "    </leaf>";
					String s9 = "    <leaf>";
					String s10 = "     <attribute name=\"name\" value=\""+ bronze + "\"/>";
					String s11 = "    </leaf>";
					String s12 = "   </branch>";

					System.out.println(s1);
					System.out.println(s2);
					System.out.println(s3);
					System.out.println(s4);
					System.out.println(s5);
					System.out.println(s6);
					System.out.println(s7);
					System.out.println(s8);
					System.out.println(s9);
					System.out.println(s10);
					System.out.println(s11);
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