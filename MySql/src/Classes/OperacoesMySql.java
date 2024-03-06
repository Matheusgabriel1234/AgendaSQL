package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class OperacoesMySql {
private Connection conexao;


public OperacoesMySql() {
	this.conexao = new Conection().getConnection();
}

public void Cadastrar(DadosAgenda objeto) {
	
try {
	String infoSql = "INSERT INTO tabela_cadastro(id, nome, email, telefone, dataNascimento, filhos, qntdFilhos, endereco)" + " VALUES(?,?,?,?,?,?,?,?)";

	
	PreparedStatement gravarInformacao = conexao.prepareStatement(infoSql);
	gravarInformacao.setString(1, Integer.toString(objeto.getId()));
	gravarInformacao.setString(2, objeto.getNome());
	gravarInformacao.setString(3, objeto.getEmail());
	gravarInformacao.setString(4, objeto.getTelefone());
	gravarInformacao.setString(5, objeto.getDataNascimento());
	gravarInformacao.setString(6, objeto.getFilhos());
	gravarInformacao.setString(7, Integer.toString(objeto.getQntdFilhos()));
	gravarInformacao.setString(8, objeto.getEndereco());

     
	gravarInformacao.execute();
	gravarInformacao.close();
	
	JOptionPane.showMessageDialog(null,"Dados cadastrados com sucesso!!" );

}catch(SQLException e) {
	e.printStackTrace();
	JOptionPane.showMessageDialog(null, "Ocorreu um erro");
}

}

public List<DadosAgenda> listarDados(){
	

	String SqlBancodados = 	"select * from tabela_cadastro";
	
List<DadosAgenda> lista = new ArrayList<>();
	

	try {
		PreparedStatement gravarInformacao = conexao.prepareStatement(SqlBancodados);
		ResultSet result = gravarInformacao.executeQuery();
		
		while(result.next()) {
			DadosAgenda linha = new DadosAgenda();
			linha.setId(result.getInt("id"));
			linha.setNome(result.getString("nome"));
			linha.setEmail(result.getString("email"));
			linha.setTelefone(result.getString("telefone"));
			linha.setDataNascimento(result.getString("dataNascimento"));
			linha.setFilhos(result.getString("filhos"));
			linha.setQntdFilhos(result.getInt("qntdFilhos"));
			linha.setEndereco(result.getString("endereco"));
			
			lista.add(linha);
			
			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return lista;
			
}

public List<DadosAgenda> FiltrarDados(String nome){
	List<DadosAgenda> lista = new ArrayList<>();
	String SqlBancodados = "SELECT * FROM tabela_cadastro WHERE nome LIKE ?";
	try {
	    PreparedStatement gravarInformacao = conexao.prepareStatement(SqlBancodados);
	    gravarInformacao.setString(1, "%" + nome + "%");



	    ResultSet result = gravarInformacao.executeQuery();

	    while(result.next()) {
	        DadosAgenda linha = new DadosAgenda();
	        linha.setId(result.getInt("id"));
	        linha.setNome(result.getString("nome"));
	        linha.setEmail(result.getString("email"));
	        linha.setTelefone(result.getString("telefone"));
	        linha.setDataNascimento(result.getString("dataNascimento"));
	        linha.setFilhos(result.getString("filhos"));
	        linha.setQntdFilhos(result.getInt("qntdFilhos"));
	        linha.setEndereco(result.getString("endereco"));

	        lista.add(linha);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return lista;

	
}

public void Alterar(DadosAgenda objeto) {

try {
	String infoSql = "UPDATE tabela_cadastro set  nome=?, email=?, telefone=?, dataNascimento=?, filhos=?, qntdFilhos=?, endereco=? where id=?";

	
	PreparedStatement gravarInformacao = conexao.prepareStatement(infoSql);
	gravarInformacao.setInt(8, objeto.getId());
	gravarInformacao.setString(1, objeto.getNome());
	gravarInformacao.setString(2, objeto.getEmail());
	gravarInformacao.setString(3, objeto.getTelefone());
	gravarInformacao.setString(4, objeto.getDataNascimento());
	gravarInformacao.setString(5, objeto.getFilhos());
	gravarInformacao.setString(6, Integer.toString(objeto.getQntdFilhos()));
	gravarInformacao.setString(7, objeto.getEndereco());

     
	gravarInformacao.execute();
	gravarInformacao.close();
	
	JOptionPane.showMessageDialog(null,"Dados Alterados com sucesso!!" );

}catch(SQLException e) {
	e.printStackTrace();
	JOptionPane.showMessageDialog(null, "Ocorreu um erro");


}
}

public void Excluir(DadosAgenda objeto) {
	try {
		String infoSql = "delete from tabela_cadastro where id=?";

		
		PreparedStatement excluirInformacao = conexao.prepareStatement(infoSql);
		excluirInformacao.setInt(1, objeto.getId());
		

	     
	   excluirInformacao.execute();
		excluirInformacao.close();
		
		JOptionPane.showMessageDialog(null,"Dados excluidos com sucesso!!" );

	}catch(SQLException e) {
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, "Ocorreu um erro");


	}
}





}
