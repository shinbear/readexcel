import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Ideone {
	public static void main(String[] args) throws java.lang.Exception {
		String str = "A. Hughes Goldie Waverly Ding A. Raymond Frackelton, Jr.";
		String strMax = identifMaxStr(str);
		System.out.println(strMax);

	}

	public static String identifMaxStr(String str) {
		// String str = "A. Hughes Goldie Waverly Ding A. Raymond Frackelton, Jr.";
		String[] strs = str.split("[.,\\s+]");
		String strMax = "";
		int maxNum = 0;
		for (String s : strs) {
			if (s.length() > maxNum) {
				maxNum=s.length();
				strMax = s;
				System.out.println(maxNum);
			}
		}
		return strMax;

	}
}