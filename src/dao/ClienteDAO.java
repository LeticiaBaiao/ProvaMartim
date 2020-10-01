package dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Cliente;
import utils.Conexao;

public class ClienteDAO {

    private Connection con;

    public ClienteDAO() {
                this.con = new Conexao().getConnection();
//        this.con = new Conexao().getConnection();
//        Statement smtp = (Statement) this.con.createStatement(0, 0);
    }
    
    public void save(Cliente cliente) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO tb_clientes (nome, email, sexo, salario, dataNascimento) VALUES (?,?,?,?,?)");
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getSexo());
            ps.setFloat(4, cliente.getSalario());
            ps.setString(5, cliente.getDataNascimento());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Empregado cadastrado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Cliente cliente) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE tb_clientes SET  nome=?, email=?, sexo=?, salario=?, dataNascimento=? WHERE id=?");
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getSexo());
            ps.setFloat(4, cliente.getSalario());
            ps.setString(5, cliente.getDataNascimento());
            ps.setInt(6, cliente.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Empregado atualizado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveOrUpdate(Cliente cliente) {
        if (cliente.getId() == 0) {
            save(cliente);
        } else {
            update(cliente);
        }
    }

    public void delete(Cliente cliente) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM tb_clientes WHERE id=?");
            ps.setInt(1, cliente.getId());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Empregado deletado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void alterarCliente(Cliente cliente) {
        try {
            String sql = "update tb_clientes set  nome=?, email=?, sexo=?, salario=?, dataNascimento=?, celular=?, cep=? where id =?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getSexo());
            ps.setFloat(4, cliente.getSalario());
            ps.setString(5, cliente.getDataNascimento());
            ps.setInt(6, cliente.getId());

            ps.execute();
            ps.close();

            JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
        }
    }

    public List<Cliente> getAll() {
        List<Cliente> clientes = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tb_clientes");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setSalario(rs.getFloat("salario"));
                cliente.setDataNascimento(rs.getString("dataNascimento"));
                clientes.add(cliente);
            }
            return clientes;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
