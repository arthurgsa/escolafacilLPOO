package EscolaFacil.maven;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.ItemSelectable;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controladores.Backup;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class TelaBackup extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Backup backup;
	private ArrayList<String> arquivosBackup;
	private String[] nomesBackup;
	private String itemSelecionado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaBackup frame = new TelaBackup();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaBackup() {
		getContentPane().setBackground(new Color(48, 105, 41));
		setBounds(100, 100, 1280, 720);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(80, 70, 1100, 540);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 21, 1048, 474);
		panel.add(scrollPane);
		
		backup = new Backup();
		arquivosBackup = new ArrayList<String>();
		arquivosBackup = backup.listarArquivos();
		nomesBackup = arquivosBackup.toArray(new String[arquivosBackup.size()]);
		
		JList list = new JList();
		list.setListData(nomesBackup);
		
		scrollPane.setViewportView(list);

		JButton btnGerarBackup = new JButton("Gerar Backup");
		btnGerarBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(btnGerarBackup, "Deseja gerar o backup? ") == JOptionPane.YES_NO_OPTION) {
					backup.gerarBackup();
					arquivosBackup = backup.listarArquivos();
					nomesBackup = arquivosBackup.toArray(new String[arquivosBackup.size()]);
					list.setListData(nomesBackup);
					revalidate();
					repaint();
				}
			}
		});
		
		btnGerarBackup.setBounds(10, 506, 89, 23);
		panel.add(btnGerarBackup);
		
		JButton btnRestBackup = new JButton("Restaurar Backup");
		btnRestBackup.setEnabled(false);
		btnRestBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(btnGerarBackup, "Deseja restaurar o backup? ") == JOptionPane.YES_NO_OPTION) {
					try {
						backup.restaurarBackup(itemSelecionado);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					
				}
			}
		});
		btnRestBackup.setBounds(252, 506, 89, 23);
		panel.add(btnRestBackup);
		
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(!e.getValueIsAdjusting() ) {
					if(list.getSelectedIndex() == -1) {
						list.setSelectedIndex(e.getFirstIndex());
					}
					itemSelecionado = ((JList<String>)e.getSource()).getSelectedValue();
					if(itemSelecionado != null) {
						btnRestBackup.setEnabled(true);
					}
				}
				
			}
		});
	}
}
