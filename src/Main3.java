import java.awt.GridLayout;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import java.io.File;
import jxl.*;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.Boolean;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.File;
import jxl.*;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.Boolean;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class Main3 {
	public static String searchWord = "";
	public static int ID_inner = 0;
	public static int ID_external = 0;
	public static int rawID_count = 0;
	public static String[] search_condition = new String[2];
	public static JTextField filename = new JTextField("F:/Jobs");
	public static String URL = "";
	public static JFrame frame = new JFrame();
	public static PrintWriter writer;

	public static void main(String[] args) throws IOException {
		try {
			input();
			String filepath = "E:/sistertask/2016-12-12/hhhh.xls";
			Sheet sheet;
			Workbook book;
			try {
				book = Workbook.getWorkbook(new File(filepath));
				sheet = book.getSheet(0);
				rawID_count = sheet.getRows(); // ��ȡ����
				for (int i = 1; i < rawID_count; i++) {
					readExcel(sheet, i);
					String kk="";
					initSearch(Integer.parseInt(search_condition[0]), search_condition[1], "bingSearch");
				}
				book.close();
			} catch (Exception e) {
			}

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage());
		}

		System.exit(0);
	}

	public static int initSearch(int cliuid_2, String searchWord, String engine) throws IOException {
		int flag = 0;
		input();
		if (engine.equals("bingSearch")) {
			URL = "http://cn.bing.com/search?q=" + searchWord;
			bingSearch(cliuid_2, URL, searchWord);
		}

		return flag;
	}

	public static void input() throws IOException {
		JPanel panel = new JPanel(new GridLayout(0, 1));
		panel.add(filename);

		int result = JOptionPane.showConfirmDialog(null, panel, "Dice.com - Search Criteria", /* 153 */ 2, -1);
		if (result == 0) {
			return;
		}
		JOptionPane.showMessageDialog(frame, "Cancelled");
		System.exit(0);
	}

	public static void bingSearch(int searchID, String URL1, String searchWord) throws IOException {
		Document doc = Jsoup.connect(URL1).timeout(0).get();
		Element body = doc.body();

		// Get the search result keylink contents
		Elements content = body.getElementsByClass("b_title");
		for (int i = 0; i < content.size(); i++) {
			// Get the inner keywords in results
			Elements Elements_h2 = content.get(i).getElementsByTag("h2");
			Elements links = Elements_h2.get(0).getElementsByTag("a");

			// Check out if the links are Linkedin or Facebook
			if (ifMatch(links, searchWord).equals("B_0")) {
				// linkedin process
				for (Element link : links) {
					String linkHref = link.attr("href");
					loginLinkedin(linkHref);
					break;
				}
			}

		}
	}

	public static String ifMatch(Elements content, String searchWord) throws

	IOException {
		String flag = "0";
		for (Element content_strong : content) {
			String strong_words = content_strong.getElementsByTag("a").text();
			if (strong_words.equals(searchWord + " | LinkedIn")) {
				flag = "B_0";
				break;
			}
		}
		return flag;
	}

	public static void loginLinkedin(String URL) throws IOException {
		WebClient webClient = new WebClient();// ����WebClient
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		// ��ȡҳ��
		HtmlPage page = webClient.getPage("https://www.linkedin.com/uas/login"); // ��linkedin

		// ���nameΪ"session_key"��htmlԪ��
		HtmlElement usernameEle = page.getElementByName("session_key");
		// ���idΪ"session_password"��htmlԪ��
		HtmlElement passwordEle = (HtmlElement) page.getElementById

		("session_password-login");
		usernameEle.focus(); // �������뽹��
		usernameEle.type("z_hao1975@hotmail.com"); // ��дֵ

		passwordEle.focus(); // �������뽹��
		passwordEle.type("zh197544"); // ��дֵ
		// ���nameΪ"submit"��Ԫ��
		HtmlElement submitEle = page.getElementByName("signin");
		// �������½��
		page = submitEle.click();
		String result = page.asXml();// ���click()���htmlҳ�棨������ǩ��
		if (result.contains("Sign Out")) {
			System.out.println("��½�ɹ�");
			HtmlPage page2 = webClient.getPage(URL);
			String pageXml = page2.asXml();
			Document doc2 = Jsoup.parse(pageXml);
			Element background_text = doc2.getElementById("background");
			// System.out.println(background_text);
			System.out.println(background_text.toString());
			writefile("c:/1.txt", background_text.toString(), false);
		} else {
			System.out.println("��½ʧ��");
		}
	}

	// ���ı��ļ���д������
	public static void writefile(String path, String content, boolean append) {
		BufferedReader bufread;
		BufferedWriter bufwriter;
		File writefile;
		String filepath, filecontent, read;
		String readStr = "";
		try {
			boolean addStr = append; // ͨ������������ж��Ƿ����ı��ļ���׷������
			filepath = path; // �õ��ı��ļ���·��
			filecontent = content; // ��Ҫд�������
			writefile = new File(filepath);
			if (writefile.exists() == false) // ����ı��ļ��������򴴽���
			{
				writefile.createNewFile();
				writefile = new File(filepath); // ����ʵ����
			}
			FileWriter filewriter = new FileWriter(writefile, addStr);
			// ɾ��ԭ���ļ�������
			// java.io.RandomAccessFile file = new
			// java.io.RandomAccessFile(path, " rw ");
			// file.setLength(0);
			// д���µ��ļ�����
			writefile.delete();
			filewriter.write(filecontent);
			filewriter.flush();
			filewriter.close();
		} catch (Exception d) {
			System.out.println(d.getMessage());
		}
	}

	public static void readExcel(Sheet sheet, int rawID) {
		Cell cell1, cell2;
		// System.out.println("*****");
		try {
			// ��ȡÿһ�еĵ�Ԫ��
			cell1 = sheet.getCell(0, rawID);// ���У��У�
			cell2 = sheet.getCell(1, rawID);

			if ("".equals(cell1.getContents()) != true) // �����ȡ������Ϊ��
			{
				search_condition[0] = cell1.getContents();
				search_condition[1] = cell2.getContents();
				System.out.println(cell1.getContents() + " " + cell2.getContents());
			}

		} catch (Exception e) {
		}
	}

}
