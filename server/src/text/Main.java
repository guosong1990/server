package text;

import java.text.DateFormat;
import java.util.Date;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date date = new Date();
		String s = DateFormat.getDateInstance().format(date);
		System.out.println(s);
	}

}
