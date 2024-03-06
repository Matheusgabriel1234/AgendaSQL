package TelaSecundaria;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import Classes.DadosAgenda;
import Classes.OperacoesMySql;

public class telaCadastros extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField textEndereco;
	private JTextField txtQntdFilhos;
	private static MaskFormatter maskData;
	private static MaskFormatter maskTelefone;
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaCadastros frame = new telaCadastros();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	 public static MaskFormatter arrumaData() {
	        try {
	            maskData = new MaskFormatter("##/##/####");

	        } catch (Exception e) {
	            e.printStackTrace();

	        }
	        return maskData;
	    }

	    public static MaskFormatter arrumaTelefone() {
	        try {
	            maskTelefone = new MaskFormatter("(##) #####-####");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return maskTelefone;
	    }



	/**
	 * Create the frame.
	 */
	public telaCadastros() {
		setTitle("Tela de cadastro");
		setBackground(SystemColor.text);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		Point centro = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
		
		int largura = 800;
		int altura = 500;
		
		setBounds(centro.x  - largura/2 , centro.y - altura/ 2 , 800,500);

		setContentPane(contentPane);
        getContentPane().setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 10, 829, 22);
        getContentPane().add(menuBar);

        JMenu menuArquivo = new JMenu("Arquivo");
        menuArquivo.setIcon(new ImageIcon(telaCadastros.class.getResource("/imagens/arquivo.png")));
        menuArquivo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(menuArquivo);

        JMenuItem subMenuContato = new JMenuItem("Contatos");
        subMenuContato.setIcon(new ImageIcon(telaCadastros.class.getResource("/imagens/agenda.png")));
        subMenuContato.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pesquisa pesquisa = new Pesquisa();
                pesquisa.setVisible(true);
            }
        });
        menuArquivo.add(subMenuContato);

        JMenu menuAjuda = new JMenu("Ajuda");
        menuAjuda.setIcon(new ImageIcon(telaCadastros.class.getResource("/imagens/ajuda.png")));
        menuAjuda.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        menuBar.add(menuAjuda);

        JMenuItem subMenuSair = new JMenuItem("Sair");
        subMenuSair.setIcon(new ImageIcon(telaCadastros.class.getResource("/imagens/sair.png")));
        subMenuSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        menuAjuda.add(subMenuSair);

        JLabel lblNewLabel = new JLabel("ID: ");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel.setBounds(10, 57, 90, 24);
        getContentPane().add(lblNewLabel);

        JLabel lblNome = new JLabel("Nome: ");
        lblNome.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNome.setBounds(10, 120, 90, 24);
        getContentPane().add(lblNome);

        JLabel lblEmail = new JLabel("Email : ");
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 20));
        lblEmail.setBounds(10, 187, 90, 24);
        getContentPane().add(lblEmail);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setFont(new Font("Arial", Font.PLAIN, 20));
        lblTelefone.setBounds(362, 120, 90, 24);
        getContentPane().add(lblTelefone);

        JFormattedTextField txtTelefone = new JFormattedTextField(arrumaTelefone());
        txtTelefone.setBounds(362, 153, 231, 24);
        getContentPane().add(txtTelefone);

        JLabel lblEndereo = new JLabel("Endereço: ");
        lblEndereo.setFont(new Font("Arial", Font.PLAIN, 20));
        lblEndereo.setBounds(10, 275, 122, 24);
        getContentPane().add(lblEndereo);

        JLabel lblNascimento = new JLabel("Nascimento:");
        lblNascimento.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNascimento.setBounds(10, 364, 122, 24);
        getContentPane().add(lblNascimento);

        JFormattedTextField txtNascimento = new JFormattedTextField(arrumaData());
        txtNascimento.setBounds(10, 398, 71, 24);
        getContentPane().add(txtNascimento);

        JLabel lblFiljo = new JLabel("Filhos:");
        lblFiljo.setFont(new Font("Arial", Font.PLAIN, 20));
        lblFiljo.setBounds(376, 187, 122, 24);
        getContentPane().add(lblFiljo);

        JComboBox boxFilhos = new JComboBox();
        boxFilhos.setModel(new DefaultComboBoxModel(new String[]{"-", "Sim", "Não"}));
        boxFilhos.setBounds(362, 217, 136, 21);
        getContentPane().add(boxFilhos);

        JButton btnSalvar = new JButton("Salvar:");
        btnSalvar.setBackground(SystemColor.activeCaption);
        btnSalvar.setIcon(new ImageIcon(telaCadastros.class.getResource("/imagens/salvar.png")));
        btnSalvar.setFont(new Font("Arial", Font.PLAIN, 20));
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	Pesquisa ps = new Pesquisa();
                	txtId.setText(Integer.toString(ps.ContarLinhasTabela() + 1));
                    DadosAgenda objeto = new DadosAgenda();
                    objeto.setId(Integer.parseInt(txtId.getText()));
                    objeto.setNome(txtNome.getText());
                    objeto.setEmail(txtEmail.getText());
                    objeto.setTelefone(txtTelefone.getText());
                  
                    
                    objeto.setDataNascimento(txtNascimento.getText());
                 
                    objeto.setFilhos(boxFilhos.getSelectedItem().toString());
                    objeto.setQntdFilhos(Integer.parseInt(txtQntdFilhos.getText()));
                    objeto.setEndereco(textEndereco.getText());
                    JOptionPane.showMessageDialog(null, "Conectado");

                    OperacoesMySql salvarDados = new OperacoesMySql();

                    salvarDados.Cadastrar(objeto);
                    dispose();
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, "Falha ao conectar");
                }
            }
        });
        btnSalvar.setBounds(359, 389, 265, 35);
        getContentPane().add(btnSalvar);

        JLabel lblNumeroDeFilhos = new JLabel("Numero de filhos: ");
        lblNumeroDeFilhos.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNumeroDeFilhos.setBounds(364, 275, 194, 24);
        getContentPane().add(lblNumeroDeFilhos);
        
        txtId = new JTextField();
        txtId.setEditable(false);
        txtId.setBounds(46, 63, 183, 19);
        contentPane.add(txtId);
        txtId.setColumns(10);
        
        txtNome = new JTextField();
        txtNome.setColumns(10);
        txtNome.setBounds(10, 154, 292, 23);
        contentPane.add(txtNome);
        
        txtEmail = new JTextField();
        txtEmail.setColumns(10);
        txtEmail.setBounds(10, 216, 292, 24);
        contentPane.add(txtEmail);
        
        textEndereco = new JTextField();
        textEndereco.setColumns(10);
        textEndereco.setBounds(10, 318, 292, 22);
        contentPane.add(textEndereco);
        
        txtQntdFilhos = new JTextField();
        txtQntdFilhos.setColumns(10);
        txtQntdFilhos.setBounds(358, 319, 183, 19);
        contentPane.add(txtQntdFilhos);
	}
}
