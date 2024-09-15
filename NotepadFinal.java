import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.Random;

class Header{
	int randomNumber;
	char encryptChar;
	public Header(){
				Random rand = new Random();
				randomNumber = rand.nextInt(100);
				char ch = '6';
			   	encryptChar = (char)(ch + randomNumber);
		}
	public int getRandomNumber(){
		return randomNumber;
	}
	public char getEncrypChar(){
		return encryptChar;
	}
}

class SampleDialog extends JDialog implements ActionListener {
	JButton replace,replaceAll,find,findNext;
	JTextField t1,t2;
	int indexOfFind, lenthOfFindText;
	int count = 0;
	JTextArea ta;
	Panel panel,findPanel,replacePanel;
	SampleDialog(Frame parent, String title, JTextArea ta){
		super(parent,title,false);
		this.ta = ta;
		panel = new Panel(new BorderLayout(10,10));
		findPanel = new Panel(new BorderLayout(10,10));
		replacePanel = new Panel(new BorderLayout(10,10));
		setLayout(new FlowLayout());
		setSize(400,400);
		replace = new JButton("Replace");
		replaceAll = new JButton("Replace All");
		find = new JButton("Find");
		findNext = new JButton("Find Next");
		t1 = new JTextField("",10);
		t2 = new JTextField("",10);

		findPanel.add(find,BorderLayout.WEST);
		findPanel.add(findNext,BorderLayout.EAST);
		findPanel.add(t1,BorderLayout.CENTER);

		replacePanel.add(replace,BorderLayout.WEST);
		replacePanel.add(replaceAll,BorderLayout.EAST);
		replacePanel.add(t2,BorderLayout.CENTER);

		panel.add(findPanel,BorderLayout.NORTH);
		panel.add(replacePanel,BorderLayout.SOUTH);
		
		replace.addActionListener(this);
		replaceAll.addActionListener(this);
		find.addActionListener(this);
		findNext.addActionListener(this);
		add(panel);

		ta.requestFocus();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we){
				dispose();
			}
		});
	}
	public void actionPerformed(ActionEvent ae){
		String str = ae.getActionCommand();
		String msg = ta.getText();
		String findText = t1.getText();
		String replaceText = t2.getText();
		String text = "Hello";

		if(str.equals("Replace")){

			if(replaceText != null){
				int index = msg.indexOf(findText);
				ta.replaceRange(replaceText,index,index + findText.length());
			}

		}else if(str.equals("Replace All")){
			if(replaceText != null){
				int index = msg.indexOf(findText);
					msg = msg.replace(findText, replaceText);
                	ta.setText(msg);
			}
		}else if(str.equals("Find")){

			indexOfFind = msg.indexOf(findText);
			lenthOfFindText = findText.length();
			if(indexOfFind != -1){
				ta.requestFocus();
				ta.select(indexOfFind, indexOfFind + lenthOfFindText);
			}else{
				System.out.println("no text found!");
			}
		}
		else if (str.equals("Find Next")) {
		    indexOfFind = msg.indexOf(findText, indexOfFind+count+1);
		    if (indexOfFind != -1) {
		        if (msg.contains("\r\n")) {
		        	count += 1;     
		            indexOfFind -= count;
		        }
		        ta.requestFocus(); 
		        ta.select(indexOfFind, indexOfFind + findText.length());

		    } else {
		    	count = -1;
		        System.out.println("No text found!");
		    }
		}
	}
}


class NotepadDemo extends JFrame implements ActionListener {
	JMenuBar mbar;
	JMenu fileMenu,editMenu,view;
	JFrame f;
	JMenuItem newTab,open,save,exit,closeTab,closeWindow;
	JMenuItem cut,copy,paste,delete,find,findNext,replace,replaceAll;
	JTabbedPane jtp;
	int key,randomNumber;
	char encryptChar;

	public NotepadDemo(){
		Header h = new Header();
		randomNumber = h.getRandomNumber();
		encryptChar = h.getEncrypChar();
		setTitle("Notepad");
		setSize(800, 800);
        setVisible(true);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        jtp = new JTabbedPane();
        
		mbar = new JMenuBar();
		fileMenu = new JMenu("File");
		editMenu = new JMenu("Edit");

		newTab = new JMenuItem("New Tab");
		open = new JMenuItem("Open");
		save = new JMenuItem("Save");
		closeTab = new JMenuItem("Close Tab");
		closeWindow = new JMenuItem("Close Window");
		exit = new JMenuItem("Exit");

		cut = new JMenuItem("Cut");
		copy = new JMenuItem("Copy");
		paste = new JMenuItem("Paste");
		delete = new JMenuItem("Delete");
		find = new JMenuItem("Find");
		findNext = new JMenuItem("Find Next");
		replace = new JMenuItem("Replace");
		replaceAll = new JMenuItem("Replace All");
		

		setJMenuBar(mbar);
		mbar.add(fileMenu);
		mbar.add(editMenu);


		fileMenu.add(newTab);
		fileMenu.addSeparator();
		fileMenu.add(open);
		fileMenu.add(save);
		fileMenu.addSeparator();
		fileMenu.add(closeTab);
		fileMenu.add(closeWindow);
		fileMenu.add(exit);

		editMenu.add(cut);
		editMenu.add(copy);
		editMenu.add(paste);
		editMenu.add(delete);
		editMenu.addSeparator();
		editMenu.add(find);
		editMenu.add(findNext);
		editMenu.add(replace);
		editMenu.add(replaceAll);

		add(jtp);
		addNewTab();

		cut.setEnabled(false);
		copy.setEnabled(false);
		paste.setEnabled(false);
		delete.setEnabled(false);
		newTab.addActionListener(this);
		find.addActionListener(this);
		open.addActionListener(this);
		save.addActionListener(this);
		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		delete.addActionListener(this);
		closeTab.addActionListener(this);
		closeWindow.addActionListener(this);

		newTab.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_DOWN_MASK));
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        closeTab.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
        find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,KeyEvent.CTRL_DOWN_MASK));

		char decryptChar = (char)(encryptChar - randomNumber);
        String keyChar = String.valueOf(decryptChar);
		key = Integer.parseInt(keyChar);

	}
	private void addNewTab(){
		TextAreaTab tab = new TextAreaTab();
		jtp.add("Untitled",tab);
	}

	public void actionPerformed(ActionEvent ae){
		if(ae.getActionCommand() == "New Tab"){
			addNewTab();
		}
		else if(ae.getActionCommand() == "Find"){
			TextAreaTab tab = (TextAreaTab) jtp.getSelectedComponent();
			JTextArea jta = tab.getTextArea();
			SampleDialog dnew = new SampleDialog(this,"Find",jta);
			dnew.setVisible(true);
		}else if (ae.getActionCommand() == "Open") {
        	FileDialog fd = new FileDialog(this, "File Dialog", FileDialog.LOAD);
        	fd.setVisible(true);
	        if (fd.getDirectory() != null && fd.getFile() != null) {
	            try {
	                String filePath = fd.getFile();
	                String[] parts = filePath.split("\\.");
	                TextAreaTab tab = (TextAreaTab) jtp.getSelectedComponent();
	                JTextArea jta = tab.getTextArea();
	                jta.setText("");
	                File f = new File(fd.getDirectory() + fd.getFile());
	                FileInputStream fis = new FileInputStream(f);
					int selectedIndex = jtp.getSelectedIndex();
					jtp.setTitleAt(selectedIndex,filePath);

	                if (parts[1].equals("kj")) {
	                    int character;
	                    char decryptChar;
	                    int i=0;
	                    Character decKey = null;
	                    
	                    while ((character = fis.read()) != -1) {
	                    	if(i<6){
	                    		if(i==4){
	                    			decKey = (char)(character - randomNumber); 
	                    		}
	                    	}
	                        else{
	                        	decryptChar = (char) (character - Integer.parseInt(Character.toString(decKey)));
	                        	jta.append(String.valueOf(decryptChar));
	                        }
	                        i++;
	                    }
	                } else {
	                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
	                    String line;
	                    while ((line = br.readLine()) != null) {
	                        jta.append(line + "\n");
	                    }
	                    br.close();
	                }
	                fis.close();
	            } catch (FileNotFoundException e) {
	                System.out.println("File Not Found Exception caught!");
	            } catch (IOException e) {
                	System.out.println("IOException caught!");
            	}
        	}
    	}else if(ae.getActionCommand()=="Save"){
			FileDialog fd = new FileDialog(f,"file Dialog",FileDialog.SAVE);
			
			fd.setVisible(true);
			if(fd.getDirectory()!=null || fd.getFile()!=null){
				try{
					String filePath = fd.getFile();	
	                String[] parts = filePath.split("\\.");
	                TextAreaTab tab = (TextAreaTab) jtp.getSelectedComponent();
	                JTextArea jta = tab.getTextArea();
	                String content = jta.getText();
	                File file = new File(fd.getDirectory() + fd.getFile());
	                FileOutputStream fos = new FileOutputStream(file, false);
	                String headerContent = "Key:"+key;
					if (parts[1].equals("kj")) {
						for(int i=0;i<headerContent.length();i++){
							char ch = headerContent.charAt(i);
							char encryKey = (char)(ch + randomNumber);
							fos.write(encryKey);
						}
						fos.write('\n');
                    	for (int i = 0; i < content.length(); i++) {
                        	char ch = content.charAt(i);
                    	    char encryptChar = (char) (ch + key);
                        	fos.write(encryptChar);
                    	}
                	} else {
                    	fos.write(content.getBytes());
	                }
	           	}catch(IOException e){
	           		e.printStackTrace();
	           	}
			}
			if(fd.getFile()!=null){
				String filePath = fd.getFile();	
				int selectedIndex = jtp.getSelectedIndex();
				jtp.setTitleAt(selectedIndex,filePath);
			}
		}else if(ae.getActionCommand() == "Cut"){
			TextAreaTab tab = (TextAreaTab) jtp.getSelectedComponent();
			JTextArea jta = tab.getTextArea();
			jta.cut();
		}else if(ae.getActionCommand() == "Copy"){
			TextAreaTab tab = (TextAreaTab) jtp.getSelectedComponent();
			JTextArea jta = tab.getTextArea();
			jta.copy();
		}else if(ae.getActionCommand() == "Paste"){
			TextAreaTab tab = (TextAreaTab) jtp.getSelectedComponent();
			JTextArea jta = tab.getTextArea();
			jta.paste();
		}else if(ae.getActionCommand() == "Delete"){
			TextAreaTab tab = (TextAreaTab) jtp.getSelectedComponent();
			JTextArea jta = tab.getTextArea();
			String selectedText = jta.getSelectedText();
			String msg = jta.getText();
			int index = msg.indexOf(selectedText);
			jta.replaceRange("",index,index+selectedText.length());
		}else if(ae.getActionCommand() == "Close Tab"){
			int selectedIndex = jtp.getSelectedIndex();
			jtp.removeTabAt(selectedIndex);

		}else if(ae.getActionCommand() == "Close Window"){
			System.exit(0);
		}
	}
}

class TextAreaTab extends JPanel{
	private JTextArea jta;
	public TextAreaTab(){
		setLayout(new BorderLayout());
		jta = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(jta);
		add(scrollPane,BorderLayout.CENTER);
	}
	public JTextArea getTextArea(){
		return jta;
	}
}

public class NotepadFinal{
	public static void main(String[] args) {
		new NotepadDemo();
	}
}