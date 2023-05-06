package graphicInterface;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import functions.Associative.Fifo;
import functions.Associative.Lfu;
import functions.Associative.Lru;
import functions.Associative.Random;
import functions.Direct.DirectExecution;

import java.awt.*;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemoryCacheSimulator extends JFrame {
	private static final long serialVersionUID = 1L;

	public static String file;
	public static String configFile;
	
	public static String getFile() {
		return file;
	}

	public static void setFile(String file) {
		MemoryCacheSimulator.file = file;
	}
	
	public static String getConfigFile() {
		return configFile;
	}

	public static void setConfigFile(String configFile) {
		MemoryCacheSimulator.configFile = configFile;
	}

	public MemoryCacheSimulator() {
		super("CacheSimulator");
		setBounds(500, 200, 300, 300);
		
		JButton btnFileConfig = new JButton("Escolher arquivo");
		btnFileConfig.setBackground(Color.PINK);
		
		FlowLayout layout = new FlowLayout();
		Container box = getContentPane();
		JLabel txt = new JLabel("Escolha um arquivo de configuração:");
		
		box.setLayout(layout);
		box.add(txt);
		box.add(btnFileConfig);
		
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		btnFileConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

				int returnValue = jfc.showSaveDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					if (jfc.getSelectedFile().isFile()) {
						//JLabel file = new JLabel("Arquivo escolhido: " + jfc2.getSelectedFile());
						configFile = jfc.getSelectedFile().toString();
						
						JFrame frame = new JFrame("CacheSimulator");
						frame.setBounds(500, 200, 300, 300);
						frame.setVisible(true);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						 
						FlowLayout layout = new FlowLayout();
						 
						Container box = frame.getContentPane();
						box.setLayout(layout);
						JLabel txt = new JLabel("Escolha um arquivo de entrada:");
						JButton btnFile = new JButton("Escolher arquivo");
						btnFile.setBackground(Color.PINK);
						
						box.setLayout(layout);
						box.add(txt);
						box.add(btnFile);
						
						btnFile.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
								jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

								int returnValue = jfc.showSaveDialog(null);
								if (returnValue == JFileChooser.APPROVE_OPTION) {
									if (jfc.getSelectedFile().isFile()) {
										//JLabel file = new JLabel("Arquivo escolhido: " + jfc.getSelectedFile());
										file = jfc.getSelectedFile().toString();
										
										JFrame frame = new JFrame("CacheSimulator");
										frame.setBounds(500, 200, 300, 300);
										frame.setVisible(true);
										frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
										 
										FlowLayout layout = new FlowLayout();
										 
										Container box = frame.getContentPane();
										box.setLayout(layout);
										
										JButton btnD = new JButton("Direto");
										btnD.setBackground(Color.PINK);
										
										JButton btnA = new JButton("Associativo");
										btnA.setBackground(Color.PINK);
										
										JButton btnSA = new JButton("Associativo em conjunto");
										btnSA.setBackground(Color.PINK);
										
										JLabel txt = new JLabel("Escolha um dos métodos de mapeamento:");
										
										box.add(txt);
										box.add(btnD);
										box.add(btnA);
										box.add(btnSA);
									
										
										btnD.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent evt) {
												DirectExecution direct = new DirectExecution();
									        	direct.readingDataDirect();
											}
										});
										
										btnA.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent evt) {
												
												JFrame frame = new JFrame("CacheSimulator");
												frame.setBounds(500, 200, 300, 300);
												frame.setVisible(true);
												frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
												 
												FlowLayout layout = new FlowLayout();
												 
												Container content = frame.getContentPane();
												content.setLayout(layout);
												 
												JLabel txt = new JLabel("Escolha um dos algoritmos de substituição:");
												JButton lfu = new JButton("LFU");
												lfu.setBackground(Color.PINK);
												
												JButton lru = new JButton("LRU");
												lru.setBackground(Color.PINK);
												
												JButton fifo = new JButton("FIFO");
												fifo.setBackground(Color.PINK);
												
												JButton random = new JButton("Random");
												random.setBackground(Color.PINK);
												
												content.add(txt);
												content.add(lfu);
												content.add(lru);
												content.add(fifo);
												content.add(random);
												
												lfu.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent evt) {
														Lfu lfuAlgorithm = new Lfu();
									    				lfuAlgorithm.runLfu();
													}
												});
												
												lru.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent evt) {
														Lru lruAlgorithm = new Lru();
									    				lruAlgorithm.runLru();
													}
												});
												
												fifo.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent evt) {
														Fifo fifoAlgorithm = new Fifo();
									    				fifoAlgorithm.runFifo();
													}
												});
												
												random.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent evt) {
														Random randomAlgorithm = new Random();
									    				randomAlgorithm.runRandom();
													}
												});
											}
										});
										
										btnSA.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent evt) {
												JFrame frame = new JFrame("CacheSimulator");
												frame.setBounds(500, 200, 300, 300);
												frame.setVisible(true);
												frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
												 
												FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
												 
												Container content = frame.getContentPane();
												content.setLayout(layout);
												JLabel txt = new JLabel("Escolha a quantidade de linhas por conjunto:");
												JTextField rowsPerSet = new JTextField("");
												JButton submit = new JButton("Enviar");
												submit.setBackground(Color.PINK);
												
												content.add(txt);
												content.add(rowsPerSet);
												content.add(submit);
											}
										});
									}
								}
							}
						});
					}
				}	
			}
		});
	}

	public static void main(String args[]) {
		MemoryCacheSimulator frame = new MemoryCacheSimulator();
	}
	
}