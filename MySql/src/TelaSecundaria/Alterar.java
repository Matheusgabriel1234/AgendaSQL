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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import Classes.DadosAgenda;
import Classes.OperacoesMySql;

public class Alterar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtAltrId;
	private JTextField txtAltrEndereco;
	private JTextField txtAltrNum;
	private JTextField txtAltrEmail;
	private JTextField txtAltrNome;
	private JFormattedTextField txtAltrData;
	private JFormattedTextField txtAltrTelefone;
	private JComboBox boxAltrFilhos;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Alterar frame = new Alterar();
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
	public Alterar() {
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
        
        txtAltrId = new JTextField();
        txtAltrId.setColumns(10);
        txtAltrId.setBounds(46, 54, 274, 24);
        contentPane.add(txtAltrId);
        
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNome.setBounds(46, 88, 110, 24);
        contentPane.add(lblNome);
        
        JLabel lblNewLabel_1_1 = new JLabel("Email:");
        lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_1.setBounds(46, 163, 59, 24);
        contentPane.add(lblNewLabel_1_1);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Telefone:");
        lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_1_1.setBounds(46, 252, 120, 24);
        contentPane.add(lblNewLabel_1_1_1);
        
        JLabel lblNewLabel_1_1_1_1 = new JLabel("Endereço:");
        lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_1_1_1.setBounds(46, 327, 139, 24);
        contentPane.add(lblNewLabel_1_1_1_1);
        
        txtAltrEndereco = new JTextField();
        txtAltrEndereco.setColumns(10);
        txtAltrEndereco.setBounds(46, 361, 275, 31);
        contentPane.add(txtAltrEndereco);
        
        JLabel lblNewLabel_1_1_2 = new JLabel("Filhos:");
        lblNewLabel_1_1_2.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_1_2.setBounds(392, 196, 139, 24);
        contentPane.add(lblNewLabel_1_1_2);
        
         boxAltrFilhos = new JComboBox();
        boxAltrFilhos.setBackground(UIManager.getColor("InternalFrame.activeTitleBackground"));
        boxAltrFilhos.setModel(new DefaultComboBoxModel<Object>(new String[] {"-", "Sim", "Não"}));
        boxAltrFilhos.setBounds(392, 230, 90, 21);
        contentPane.add(boxAltrFilhos);
        
        JLabel lblNewLabel_1_1_2_1 = new JLabel("Número de Filhos:");
        lblNewLabel_1_1_2_1.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel_1_1_2_1.setBounds(558, 196, 183, 24);
        contentPane.add(lblNewLabel_1_1_2_1);
        
        txtAltrNum = new JTextField();
        txtAltrNum.setColumns(10);
        txtAltrNum.setBounds(558, 231, 164, 19);
        contentPane.add(txtAltrNum);
        
        txtAltrEmail = new JTextField();
        txtAltrEmail.setColumns(10);
        txtAltrEmail.setBounds(46, 197, 275, 31);
        contentPane.add(txtAltrEmail);
        
        txtAltrNome = new JTextField();
        txtAltrNome.setColumns(10);
        txtAltrNome.setBounds(45, 122, 275, 31);
        contentPane.add(txtAltrNome);
        
        JLabel lblId = new JLabel("ID:");
        lblId.setFont(new Font("Arial", Font.BOLD, 20));
        lblId.setBounds(46, 30, 75, 19);
        contentPane.add(lblId);
        
        txtAltrData = new JFormattedTextField();
        txtAltrData.setBounds(374, 361, 241, 25);
        contentPane.add(txtAltrData);
        
        JLabel lblNewLabel = new JLabel("Data Nascimento:");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        lblNewLabel.setBounds(374, 328, 222, 22);
        contentPane.add(lblNewLabel);
        
        txtAltrTelefone = new JFormattedTextField();
        txtAltrTelefone.setBounds(46, 286, 274, 25);
        contentPane.add(txtAltrTelefone);
        
        btnNewButton = new JButton("Alterar:");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		DadosAgenda objeto = new DadosAgenda();
        		objeto.setId(Integer.parseInt(txtAltrId.getText()));
        	     objeto.setNome(txtAltrNome.getText());
        	     objeto.setEmail(txtAltrEmail.getText());
        	     objeto.setTelefone(txtAltrTelefone.getText());
        	     objeto.setDataNascimento(txtAltrData.getText());
        	     objeto.setFilhos(boxAltrFilhos.getSelectedItem().toString());
        	     objeto.setQntdFilhos(Integer.parseInt(txtAltrNum.getText()));
        	     objeto.setEndereco(txtAltrEndereco.getText());
        	     
        	     OperacoesMySql alterarDados = new OperacoesMySql();
        	     alterarDados.Alterar(objeto);
        	     
        	     dispose();
        	     
        	     
        	}
        });
        btnNewButton.setIcon(new ImageIcon(Alterar.class.getResource("/imagens/cadastro.png")));
        btnNewButton.setFont(new Font("Arial", Font.PLAIN, 20));
        btnNewButton.setBackground(UIManager.getColor("CheckBox.background"));
        btnNewButton.setBounds(390, 113, 164, 43);
        contentPane.add(btnNewButton);
      
	}
	
	public void receberDados(String id, String nome,String email,String telefone,String Nasc,String filhos,String qntdFilhos,String endereco){
		txtAltrId.setText(id);
		txtAltrNome.setText(nome);
		
		txtAltrEmail.setText(email);
		txtAltrTelefone.setText(telefone);
		txtAltrData.setText(Nasc);
		boxAltrFilhos.setSelectedItem(filhos);
		txtAltrNum.setText(qntdFilhos);
		txtAltrEndereco.setText(endereco);
		
		
		
	
		
	}
}
