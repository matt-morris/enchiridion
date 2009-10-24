/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package enchiridion;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.rtf.*;
import java.awt.event.*;
import javax.swing.event.*;


/**
 *
 * @author Alex
 */
public class UserInterface extends JFrame
{
	private JPanel libraryPanel, editorPanel, sidePanel, mainPanel;
	private JEditorPane editor;
	private JSplitPane splitPane;
	private JList sideList, libraryList;
	private JScrollPane libraryScroll, editorScroll, sideScroll;

	private RTFEditorKit rtf;
	private FileIO file;

	private Font sideFont = new Font ("SansSerif", Font.BOLD, 24);

	public UserInterface ()
	{
		// Window title.
		super("Enchiridion Text Editing Environment");

		// Window settings.
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		BorderLayout guiLayout = new BorderLayout();
		setLayout(guiLayout);

		makeUI();
		add(splitPane, BorderLayout.CENTER);

		setVisible(true);
	}

	private void makeUI ()
	{
		makeLibrary();
		makeEditor();
		makeSidePanel();
		makeMainPanel();

		editorPanel.add(editorScroll);
		editorPanel.setVisible(false);

		libraryPanel.add(libraryScroll);

		sidePanel = new JPanel();
		sidePanel.setPreferredSize(new Dimension(200, 100));
		sidePanel.setMaximumSize(new Dimension(200, 100));
		sidePanel.setMinimumSize(new Dimension(200, 100));

		// Ties both panes together.
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidePanel,
				mainPanel);

		// Sidebar
		sidePanel.add(sideScroll);
	}

	private void makeSidePanel ()
	{
		String[] listContent = new String[2];
		listContent[0] = "Main Library";
		listContent[1] = "Editor";

		sideList = new JList(listContent);
		sideList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		sideList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		sideList.setVisibleRowCount(-1);
		sideList.setFont(sideFont);
		sideList.addListSelectionListener(new LibrarySelectionHandler());

		sideScroll = new JScrollPane(sideList);
		sideScroll.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		sideScroll.setPreferredSize(new Dimension(200, 800));
		sideScroll.setMaximumSize(new Dimension(200, 800));
	}

	private void makeMainPanel ()
	{
		mainPanel = new JPanel();
		mainPanel.add(libraryPanel);
		mainPanel.add(editorPanel);
	}

	private void makeLibrary ()
	{
		String[] listContent = new String[5];
		listContent[0] = "Enchiridion.rtf";
		listContent[1] = "Blaat.rtf";
		listContent[2] = "Bla.rtf";
		listContent[3] = "Foo.rtf";
		listContent[4] = "Bar.rtf";

		libraryPanel = new JPanel();
		libraryList = new JList(listContent);
		libraryScroll = new JScrollPane(libraryList);

		libraryScroll.setPreferredSize(new Dimension(1000, 800));
		libraryScroll.setMaximumSize(new Dimension(1000, 800));
	}

	private void makeEditor ()
	{
		// For the editor pane.
		editorPanel = new JPanel();
		editor = new JEditorPane();
		rtf = new RTFEditorKit();
		file = new FileIO();

		// Make file editable.
		editor.setEditorKit(rtf);

		editorScroll = new JScrollPane(editor);
		editorScroll.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		editorScroll.setPreferredSize(new Dimension(800, 800));
		editorScroll.setMaximumSize(new Dimension(800, 800));

		editorOpen();
	}

	private void editorOpen ()
	{
		// Populate editor with content.
		FileInputStream fileStream = file.read("/enchiridion/enchiridion.rtf");

		try
		{
			rtf.read(fileStream, editor.getDocument(), 0);
		}
		catch (IOException e)
		{
			System.out.println("IO Exception");
		}
		catch (BadLocationException e)
		{
			System.out.println("Bad Location");
		}
	}

	private class LibrarySelectionHandler implements ListSelectionListener
	{
		public void valueChanged(ListSelectionEvent e)
		{
			if (e.getFirstIndex() == 1)
			{
				editorPanel.setVisible(true);
				libraryPanel.setVisible(false);
			}

			if (e.getFirstIndex() == 0)
			{
				editorPanel.setVisible(false);
				libraryPanel.setVisible(true);
			}

			JOptionPane.showMessageDialog(null, e.getFirstIndex());
		}
	}
}