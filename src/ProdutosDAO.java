/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    conectaDAO conexao;
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public ProdutosDAO() {
        this.conexao = new conectaDAO();
        this.conn = this.conexao.connectDB();
    }
    
    public void cadastrarProduto (ProdutosDTO produto){
        //conn = new conectaDAO().connectDB();
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setString(2, String.valueOf(produto.getValor()));
            stmt.setString(3, produto.getStatus());
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ProdutosDAO" + e.getMessage());
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }

    public List<ProdutosDTO> listarTodos() {
        String sql = "SELECT * FROM produtos";
        List<ProdutosDTO> listaProdutos = new ArrayList<>();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                listaProdutos.add(produto);
            }
            
            return listaProdutos;
        } catch (Exception e) {
            System.out.println("Erro ao buscar produtos: " + e.getMessage());
            return null;
        }
    }
    
    public List<ProdutosDTO> listarVendidos() {
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
        List<ProdutosDTO> listaProdutos = new ArrayList<>();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                listaProdutos.add(produto);
            }
            
            return listaProdutos;
        } catch (Exception e) {
            System.out.println("Erro ao buscar produtos: " + e.getMessage());
            return null;
        }
    }
    
    public void venderProduto (int _id){
        //conn = new conectaDAO().connectDB();
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, String.valueOf(_id));
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Erro ProdutosDAO" + e.getMessage());
        }
    }
}

