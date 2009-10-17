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

	private RTFEditorKit rtf;
	private FileIO file;

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
		// For the editor pane.
		editorPanel = new JPanel();
		editor = new JEditorPane();
		rtf = new RTFEditorKit();
		file = new FileIO();

		JScrollPane scrollPane = new JScrollPane(editor);
		scrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		scrollPane.setPreferredSize(new Dimension(800, 800));
		scrollPane.setMaximumSize(new Dimension(800, 800));
		editor.setEditorKit(rtf);

		editorPanel.add(scrollPane);
		
		mainPanel = new JPanel();
		mainPanel.add(editorPanel);
		
		sidePanel = new JPanel();
		sidePanel.setPreferredSize(new Dimension(250, 100));
		sidePanel.setMaximumSize(new Dimension(250, 100));
		sidePanel.setMinimumSize(new Dimension(200, 100));

		// Ties both panes together.
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidePanel,
				mainPanel);

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