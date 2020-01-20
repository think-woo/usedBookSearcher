import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BookSearcher {

	public static class MyGUI extends JFrame {

		// _              _                                      _         _          
		// (_)            | |                                    | |       | |         
		//  _  _ __   ___ | |_   __ _  _ __    ___   ___       __| |  __ _ | |_   __ _ 
		// | || '_ \ / __|| __| / _` || '_ \  / __| / _ \     / _` | / _` || __| / _` |
		// | || | | |\__ \| |_ | (_| || | | || (__ |  __/    | (_| || (_| || |_ | (_| |
		// |_||_| |_||___/ \__| \__,_||_| |_| \___| \___|     \__,_| \__,_| \__| \__,_|

		int _flag;

		//       			 _    _                 _ 
		//       			| |  | |               | |
		// _ __ ___    ___ 	| |_ | |__    ___    __| |
		// | '_ ` _ \  / _ \| __|| '_ \  / _ \  / _` |
		// | | | | | ||  __/| |_ | | | || (_) || (_| |
		// |_| |_| |_| \___| \__||_| |_| \___/  \__,_|

		MyGUI(int flag) {

			_flag = flag;

			//Frame 설정
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setPreferredSize(new Dimension(800, 600));
			setBackground(Color.pink);

			//component 설정
			JTextArea area = new JTextArea();
			area.setEditable(false);
			area.append("책 제목\t\t번호\t\t원가\t상\t중\t하\n");

			JScrollPane scroll = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			add(scroll);

			//독서목록 불러오기
			String	path	= "src/BookListExample.txt";
			File	file	= new File(path);

			try {

				BufferedReader	reader	= new BufferedReader(new FileReader(file));

				String			s;
				while ((s = reader.readLine()) != null) {
					String[] titleAndNum = s.split("\\s");

					area.append(titleAndNum[0] + "\t\t");
					area.append(titleAndNum[1] + "\t");

					Iterator<Element> price = null;

					if (_flag == 1) {
						price = searchBookForYes24(titleAndNum[1]);
					} else if (_flag == 2) {
						price = searchBookForAladin(titleAndNum[1]);
					}

					while (price.hasNext()) {

						area.append(price.next().text() + "\t");
					}
					area.append("\n");
				} //while

				reader.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
			pack();
			setTitle("중고책 가격 검색");
			setVisible(true);

		}//constructor

		//@@Yes24전용
		public Iterator<Element> searchBookForYes24(String num) {

			String		url	= "http://www.yes24.com/Mall/buyback/Search?CategoryNumber=018&SearchWord=" + num + "&SearchDomain=BOOK,FOREIGN&BuybackAccept=N/";
			Document	doc	= null;

			try {
				doc = Jsoup.connect(url).get();
			} catch (Exception e) {
				e.printStackTrace();
			}

			Elements			element	= doc.select("div.bbG_price");

			Iterator<Element>	price	= element.select("td").iterator();

			return price;
		}

		//@@알라딘 전용
		public Iterator<Element> searchBookForAladin(String num) {

			String		url	= "https://www.aladin.co.kr/shop/usedshop/wc2b_search.aspx?ActionType=1&SearchTarget=All&KeyWord=" + num + "&x=0&y=0/";
			Document	doc	= null;

			try {
				doc = Jsoup.connect(url).get();
			} catch (Exception e) {
				e.printStackTrace();
			}

			Elements			element	= doc.select("div#pnItemList");

			Iterator<Element>	price	= element.select("td.c2b_tablet3").iterator();

			return price;
		}

	}//MyGUI class

	public static void main(String[] args) {

		System.out.println("YES24 >> 1");
		System.out.println("알라딘 >> 2");

		Scanner	scanner	= new Scanner(System.in);
		int		flag	= scanner.nextInt();

		new MyGUI(flag);

	}//main()

}//BookSearcher class
