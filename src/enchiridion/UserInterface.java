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

/**
 *
 * @author Alex
 */
public class UserInterface extends JFrame
{
	private JPanel editorPanel, sidePanel, mainPanel;
	private JEditorPane editor;
	private JSplitPane splitPane;
	private JList sideList, libList;
	private JScrollPane editorScroll, sideScroll;

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
		makeEditor();
		makeSidePanel();
		makeMainPanel();

		editorPanel.add(editorScroll);
		
		sidePanel = new JPanel();
		sidePanel.setPreferredSize(new Dimension(250, 100));
		sidePanel.setMaximumSize(new Dimension(250, 100));
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

		sideScroll = new JScrollPane(sideList);
		sideScroll.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		sideScroll.setPreferredSize(new Dimension(200, 800));
		sideScroll.setMaximumSize(new Dimension(200, 800));
	}

	private void makeMainPanel ()
	{
		mainPanel = new JPanel();
		mainPanel.add(editorPanel);
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
}