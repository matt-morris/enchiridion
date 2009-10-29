/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package enchiridion;

import java.awt.Dimension;
import javax.swing.JList;
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
		String[] listContent = new String[5];
		listContent[0] = "Enchiridion.rtf";
		listContent[1] = "Blaat.rtf";
		listContent[2] = "Bla.rtf";
		listContent[3] = "Foo.rtf";
		listContent[4] = "Bar.rtf";

		libraryPanel = new JPanel();
		JList libraryList = new JList(listContent);
		JScrollPane libraryScroll = new JScrollPane(libraryList);

		libraryScroll.setPreferredSize(new Dimension(1000, 800));
		libraryScroll.setMaximumSize(new Dimension(1000, 800));
		
		libraryPanel.add(libraryScroll);
	}

	public JPanel getPanel ()
	{
		return libraryPanel;
	}
}
