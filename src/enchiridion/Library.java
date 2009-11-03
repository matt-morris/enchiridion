/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package enchiridion;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Alex
 */
public class Library {

	private JPanel libraryPanel;

	public Library ()
	{
//		List listColumnData = new List {
//			"Name",
//			"Date",
//			"Location"
//		};

		// Special array initialization syntax
//		ArrayList listColumnData = new ArrayList() {{
//			add("Name");
//			add("Date");
//			add("Location");
//		}};

		Vector listColumns = new Vector();
		listColumns.add("Name");
		listColumns.add("Date");
		listColumns.add("Location");

		String[] listNames = {
			"Enchiridion Design Doc",
			"Blaatschapen",
			"Blarbl",
			"Foozlz",
			"Barnacle"
		};

		SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy");

		Date[] listDates = {
			new Date(2009, 11, 3),
			new Date(2009, 9, 4),
			new Date(2008, 12, 9),
			new Date(2007, 5, 27),
			new Date(2006, 3, 4)
		};

		String[] listLocs = {
			"Enchiridion.rtf",
			"Blaat.rtf",
			"Bla.rtf",
			"Foo.rtf",
			"Bar.rtf"
		};

		Vector listData = new Vector();

		for (int i = 0; i < 5; i++)
		{
			Vector dataList = new Vector();
			dataList.add(listNames[i]);

			StringBuilder stringDate = new StringBuilder(dateFormat.format(listDates[i]));
			dataList.add(stringDate.toString());

			dataList.add(listLocs[i]);
			listData.add(dataList);
		}

		libraryPanel = new JPanel();
		JTable libraryTable = new JTable(listData, listColumns);
		JScrollPane libraryScroll = new JScrollPane(libraryTable);


		libraryScroll.setPreferredSize(new Dimension(1000, 800));
		libraryScroll.setMaximumSize(new Dimension(1000, 800));
		
		libraryPanel.add(libraryScroll);
	}

	public JPanel getPanel ()
	{
		return libraryPanel;
	}
}
