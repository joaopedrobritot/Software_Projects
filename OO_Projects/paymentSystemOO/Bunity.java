package paymentSystemOO;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.image.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.Color;

public class Bunity extends JFrame {

	private JPanel contentPane;
	private JTextField txtFalaAiGalerinha;
	private JTextField txtMasOq;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bunity frame = new Bunity();
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
	public Bunity() {
		
		
		
		setBackground(new Color(0, 102, 204));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setType(Type.NORMAL);
		//setIconImage(new Image());
		setBounds(100, 100, 717, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddEmployee = new JLabel("Administrator Functions");
		lblAddEmployee.setIcon(new ImageIcon("C:\\Users\\Jo\u00E3o Pedro\\Desktop\\Icons Folder\\icons8-administrador-masculino-filled-50.png"));
		lblAddEmployee.setBounds(134, 135, 49, 55);
		contentPane.add(lblAddEmployee);
		
		JButton btnAdministratorFunctions = new JButton("Administrator Functions");
		btnAdministratorFunctions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
				tabbedPane.setBounds(255, 239, 181, 125);
				contentPane.add(tabbedPane);
				
				JButton btnAlou = new JButton("alou");
				btnAlou.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "funcionou krai", "aeee poha", 1);
						tabbedPane.remove(0);
						tabbedPane.removeAll();
					
					}
				});
				btnAlou.setBounds(279, 280, 89, 23);
				
				tabbedPane.add("testa", btnAlou);
			}
		});
		btnAdministratorFunctions.setBounds(68, 216, 177, 23);
		contentPane.add(btnAdministratorFunctions);
		
		JLabel lblEmployeeFunctions = new JLabel("Employee Functions");
		lblEmployeeFunctions.setIcon(new ImageIcon("C:\\Users\\Jo\u00E3o Pedro\\Desktop\\Icons Folder\\icons8-usu\u00E1rio-50.png"));
		lblEmployeeFunctions.setBounds(474, 131, 54, 63);
		contentPane.add(lblEmployeeFunctions);
		
		JButton btnEmployeeFunctions = new JButton("Employee Functions");
		btnEmployeeFunctions.setBounds(415, 216, 168, 23);
		contentPane.add(btnEmployeeFunctions);
		
		
		
		
		
		
		
		String ze[] = new String[99];
		ze[0] = "matinho";
		ze[1] = "juau";
		
		
		
	}
}
