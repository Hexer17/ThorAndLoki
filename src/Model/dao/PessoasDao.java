/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.dao;

import java.sql.ResultSet;
import Connection.Conexao;
import Model.bean.Pessoas;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Guilherme Freire
 */
public class PessoasDao {
 
    public ResultSet loadPessoas(){

        Conexao conn = new Conexao();

        ResultSet  rs = conn.select(" SELECT * FROM alunos");

        return rs;
        
    }
    
    public ResultSet loadSearch(Pessoas pessoas){
        
        System.out.println("Entrou");
        
        
        Conexao conn = new Conexao();
        String addQuery = "";  

        if(!pessoas.getNome().equals("Nome") && !pessoas.getNome().equals("")){

            addQuery += " AND alun_nome LIKE '%" + pessoas.getNome().trim()+"%'";

        }
        if( !pessoas.getCpf().equals("")){
  
            addQuery += " AND alun_cpf =  '" + pessoas.getCpf()+"'";

        }
        if(!pessoas.getEndereco().equals("Endereço") && !pessoas.getEndereco().equals("")){

            addQuery += " AND alun_endereco LIKE '%" + pessoas.getEndereco().trim()+"%'";

        }
        if(!pessoas.getTelefone().equals("Telefone") && !pessoas.getTelefone().equals("") ){

            addQuery += " AND alun_telefone = '" + pessoas.getTelefone() + "'";

        }

        ResultSet rs = conn.select(" SELECT * FROM alunos WHERE alun_status != 'off' "+addQuery);

        return rs;

        
    }
    
    
    public void addPessoa(Pessoas pessoa) throws Exception{
        
        
    
        Conexao conn = new Conexao();
        
        String erro = "";
        
        if(pessoa.getNome().equals("Nome") || pessoa.getNome().equals("")){

           erro += "Digite um nome, ";   
           
        }
        
        if(pessoa.getCpf().equals("CPF") || pessoa.getCpf().equals("")){

           erro += "Digite um CPF, ";

        }
        if(pessoa.getEndereco().equals("Endereço") || pessoa.getEndereco().equals("")){

           erro += "Digite um endereço, ";

        }
        if(pessoa.getTelefone().equals("Telefone") || pessoa.getTelefone().equals("") ){

           erro += "Digite um Telefone ";

        }
        
        
        System.out.println(erro);
        
        if(!erro.equals("")){
            
            JOptionPane.showMessageDialog(null, "Erro no cadastro: " + erro);
            
        }else{
        
            try{
            
                conn.query("INSERT INTO alunos VALUES (DEFAULT,'"+ pessoa.getNome() +"','"+pessoa.getCpf()+"',19,70,8,'"+ pessoa.getEndereco()+"','"+pessoa.getTelefone()+"','on')");
                JOptionPane.showMessageDialog(null, "Pessoa cadastrada com sucesso!");
            
            }catch(Exception ex){

               JOptionPane.showMessageDialog(null, "Erro no cadastro!");

            }
        
        }
        
    
    }
    
    public void updatePessoa(Pessoas pessoa,int id){
    
        Conexao conn = new Conexao();
        
        try{
            
            conn.query("UPDATE alunos SET alun_nome = '"+ pessoa.getNome() +"', alun_cpf =  '"+pessoa.getCpf()+"', alun_endereco = '"+ pessoa.getEndereco()+"', alun_telefone = '"+pessoa.getTelefone()+"' WHERE alun_id = " + id);
            JOptionPane.showMessageDialog(null, "Pessoa atualizada com sucesso!");
            
        }catch(Exception ex){
            
           JOptionPane.showMessageDialog(null, "Erro na atualização!");
            
        }
        
    
    }
     
    
}
