package graphicInterface;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class testInterface extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testInterface frame = new testInterface();
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
	public testInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 300, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		 
		FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
		 
		contentPane.setLayout(layout);
		JLabel txt = new JLabel("Escolha a quantidade de linhas por conjunto:");
		JButton submit = new JButton("Enviar");
		submit.setBackground(Color.PINK);
		
		contentPane.add(txt);
		
		textField = new JTextField("");
		contentPane.add(textField);
		contentPane.add(submit);
	}

}
