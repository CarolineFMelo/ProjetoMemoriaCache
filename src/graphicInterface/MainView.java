package graphicInterface;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;

public class MainView extends JFrame {

	private JPanel contentPane;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainView() {
		setTitle("CacheSimulator");
		setResizable(false);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel txt = new JLabel("Escolha um arquivo de configuração:");
		txt.setBounds(10, 11, 185, 22);
		contentPane.add(txt);
		
		JButton btnFileConfig = new JButton("Escolher arquivo");
		btnFileConfig.setBackground(Color.PINK);		
		btnFileConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

				int returnValue = jfc.showSaveDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					if (jfc.getSelectedFile().isFile()) {
						//JLabel file = new JLabel("Arquivo escolhido: " + jfc2.getSelectedFile());
						configFile = jfc.getSelectedFile().toString();
					}
				}
			}
		});
		btnFileConfig.setBounds(10, 34, 132, 23);
		contentPane.add(btnFileConfig);
	}
}
