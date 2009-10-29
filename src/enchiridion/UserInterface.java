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
//import javax.swing.event.*;
import java.awt.event.*;



/**
 *
 * @author Alex
 */
public class UserInterface extends JFrame
{
	private JPanel libraryPanel, editorPanel, sidePanel, sideButtons, mainPanel;
	private JEditorPane editor;
	private JSplitPane splitPane;
	private JList sideList, libraryList;
	private JScrollPane libraryScroll, editorScroll, sideScroll;
	private JLabel libraryLabel, editorLabel;
	private JButton libraryButton, editorButton;

	private RTFEditorKit rtf;
	private FileIO file;

	private Font sideFont = new Font ("SansSerif", Font.BOLD, 24);
	private ImageIcon libraryIcon = createImageIcon(
			"accessories-text-editor.png",
			"Library");
	private ImageIcon editorIcon = createImageIcon(
			"system-file-manager.png",
			"Editor");

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
		Library library = new Library();

		libraryPanel = library.getPanel();
		makeEditor();
		makeSidePanel();
		makeMainPanel();

		editorPanel.add(editorScroll);
		editorPanel.setVisible(false);

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
		Object[] listContent = new String[2];
//		libraryLabel = new JLabel("Library", libraryIcon, JLabel.CENTER);
//		editorLabel = new JLabel("Editor", editorIcon, JLabel.CENTER);

		libraryButton = new JButton("Library", libraryIcon);
		editorButton = new JButton("Editor", editorIcon);
		
//		sideList.setFont(sideFont);
//		sideList.addListSelectionListener(new LibrarySelectionHandler());

		libraryButton.addActionListener(new SidebarSelectionHandler());
		editorButton.addActionListener(new SidebarSelectionHandler());
		libraryButton.setActionCommand("library");
		editorButton.setActionCommand("editor");

	//	libraryButton.setAlignmentX(0);
	//	editorButton.setAlignmentX(0);

	//	libraryButton.setMinimumSize(new Dimension(200, 50));
	//	editorButton.setMinimumSize(new Dimension(200, 50));

		sideButtons = new JPanel();

		sideButtons.setLayout(new BoxLayout(sideButtons, BoxLayout.PAGE_AXIS));

		sideButtons.add(libraryButton);
		sideButtons.add(editorButton);

		sideScroll = new JScrollPane(sideButtons);
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

	private class SidebarSelectionHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (e.getActionCommand().equals("editor"))
			{
				editorPanel.setVisible(true);
				libraryPanel.setVisible(false);
			}

			if (e.getActionCommand().equals("library"))
			{
				editorPanel.setVisible(false);
				libraryPanel.setVisible(true);
			}

			//JOptionPane.showMessageDialog(null, e.getFirstIndex());
		}
	}

	/** Returns an ImageIcon, or null if the path was invalid. From Sun's site.
	 */
	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
}