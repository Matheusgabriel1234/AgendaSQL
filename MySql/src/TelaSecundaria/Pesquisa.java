package TelaSecundaria;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Classes.DadosAgenda;
import Classes.OperacoesMySql;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Pesquisa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtConsulta;
	private JTextField txtQntdLinhas;
	private JButton btnNewButton;
	private JTable tabelaAgenda;
	private JButton btnExcluir;
	Alterar enviaTexto;
	private JTextField txtCliqueVezes;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pesquisa frame = new Pesquisa();
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
	public Pesquisa() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				carregarItens();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 922, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Digite um nome pra pesquisar");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(27, 29, 331, 24);
		contentPane.add(lblNewLabel);
		
		txtConsulta = new JTextField();
		txtConsulta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String nome = "%" + txtConsulta.getText() + "%";
		
				
				OperacoesMySql itens = new OperacoesMySql();
				List<DadosAgenda> list = itens.FiltrarDados(nome);
				DefaultTableModel dados = (DefaultTableModel) tabelaAgenda.getModel();
				
				dados.setNumRows(0);
				
				for(DadosAgenda linha : list) {
					dados.addRow(new Object[] {
							linha.getId(),
							linha.getNome(),
							linha.getEmail(),
							linha.getTelefone(),
							linha.getDataNascimento(),
							linha.getFilhos(),
							linha.getQntdFilhos(),
							linha.getEndereco()});
				}
				
				
			}
		});
		txtConsulta.setBounds(30, 54, 706, 36);
		contentPane.add(txtConsulta);
		txtConsulta.setColumns(10);
		
		txtQntdLinhas = new JTextField();
		txtQntdLinhas.setFont(new Font("Arial", Font.BOLD, 20));
		txtQntdLinhas.setEditable(false);
		txtQntdLinhas.setColumns(10);
		txtQntdLinhas.setBounds(30, 100, 391, 24);
		contentPane.add(txtQntdLinhas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 134, 726, 333);
		contentPane.add(scrollPane);
		
		tabelaAgenda = new JTable();
		tabelaAgenda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.getClickCount() == 2) {
					DefaultTableModel model = (DefaultTableModel)tabelaAgenda.getModel();
					
					int SelectRow = tabelaAgenda.getSelectedRow();
					
					if(enviaTexto == null) {
						enviaTexto = new Alterar();
						enviaTexto.setVisible(true);
						enviaTexto.receberDados(model.getValueAt(SelectRow,0).toString(),
								model.getValueAt(SelectRow,1).toString(),
								model.getValueAt(SelectRow,2).toString(),
								model.getValueAt(SelectRow,3).toString(),
								model.getValueAt(SelectRow,4).toString(),
								model.getValueAt(SelectRow,5).toString(),
								model.getValueAt(SelectRow,6).toString(),
								model.getValueAt(SelectRow,7).toString()
								);
						
					}else {
						enviaTexto.setState(Alterar.NORMAL);
						enviaTexto.setVisible(true);
						enviaTexto = new Alterar();
						enviaTexto.receberDados(model.getValueAt(SelectRow,0).toString(),
								model.getValueAt(SelectRow,1).toString(),
								model.getValueAt(SelectRow,2).toString(),
								model.getValueAt(SelectRow,3).toString(),
								model.getValueAt(SelectRow,4).toString(),
								model.getValueAt(SelectRow,5).toString(),
								model.getValueAt(SelectRow,6).toString(),
								model.getValueAt(SelectRow,7).toString()
								);
					}
					
					
				}
			}
		});
		tabelaAgenda.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "nome", "email", "telefone", "Nascimento", "Filhos", "qntdFilhos", "Endere\u00E7o"
			}
		));
		scrollPane.setViewportView(tabelaAgenda);
		tabelaAgenda.setRowHeight(50);
		tabelaAgenda.setDefaultEditor(Object.class, null);
		
		
		
		btnNewButton = new JButton("Novo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaCadastros tc = new telaCadastros();
				tc.setVisible(true);
			}
		});
		btnNewButton.setBackground(UIManager.getColor("TextPane.selectionBackground"));
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton.setIcon(new ImageIcon(Pesquisa.class.getResource("/imagens/registro.png")));
		btnNewButton.setBounds(749, 173, 159, 36);
		contentPane.add(btnNewButton);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) tabelaAgenda.getModel();
				try {
					int selectedRow = tabelaAgenda.getSelectedRow();
					DadosAgenda objeto = new DadosAgenda();
					
					   int id = Integer.parseInt(model.getValueAt(selectedRow,0).toString());
					   objeto.setId(id);
					OperacoesMySql excluirDados = new OperacoesMySql();
					excluirDados.Excluir(objeto);
					
					carregarItens();
					
					
			
				   
				   
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,"Não há nenhum registro slecionado");
				}
			}
		});
		btnExcluir.setIcon(new ImageIcon(Pesquisa.class.getResource("/imagens/excluir.png")));
		btnExcluir.setFont(new Font("Arial", Font.PLAIN, 20));
		btnExcluir.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		btnExcluir.setBounds(746, 242, 159, 36);
		contentPane.add(btnExcluir);
		
		txtCliqueVezes = new JTextField();
		txtCliqueVezes.setText("Clique 2 vezes na linha para altera-lá");
		txtCliqueVezes.setEditable(false);
		txtCliqueVezes.setFont(new Font("Calibri", Font.BOLD, 16));
		txtCliqueVezes.setBounds(431, 99, 353, 24);
		contentPane.add(txtCliqueVezes);
		txtCliqueVezes.setColumns(10);
		carregarItens();
	}
	
	public void carregarItens() {
		OperacoesMySql ops = new OperacoesMySql();
		List<DadosAgenda> listar = ops.listarDados();
		DefaultTableModel model = (DefaultTableModel) tabelaAgenda.getModel();
		model.setNumRows(0);
		
		for(DadosAgenda linha: listar) {
			
			

			model.addRow(new Object[] { 
					 linha.getId(),
					    linha.getNome(), 
					    linha.getEmail(),
					    linha.getTelefone(),
					    linha.getDataNascimento(),
					    linha.getFilhos(),
					    linha.getQntdFilhos(),
					    linha.getEndereco()
				
			}) ;
			ContarLinhasTabela();
		}
		
		tabelaAgenda.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		tabelaAgenda.getColumnModel().getColumn(0).setPreferredWidth(50);;
		tabelaAgenda.getColumnModel().getColumn(1).setPreferredWidth(200);;
		tabelaAgenda.getColumnModel().getColumn(2).setPreferredWidth(250);;
		tabelaAgenda.getColumnModel().getColumn(3).setPreferredWidth(200);;
		tabelaAgenda.getColumnModel().getColumn(4).setPreferredWidth(200);;
		tabelaAgenda.getColumnModel().getColumn(5).setPreferredWidth(150);;
		tabelaAgenda.getColumnModel().getColumn(6).setPreferredWidth(50);;
		tabelaAgenda.getColumnModel().getColumn(7).setPreferredWidth(200);;
		
		
	
	}
public int ContarLinhasTabela() {
		int qntdDeLinhas = 0;
		
		qntdDeLinhas = tabelaAgenda.getRowCount();
		
		txtQntdLinhas.setText("Total de registros: " + qntdDeLinhas);
		
		return qntdDeLinhas;
	}
	

	
	
}
