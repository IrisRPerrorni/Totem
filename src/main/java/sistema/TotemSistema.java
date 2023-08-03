package sistema;

import model.ItemPedido;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import static connection.Conectar.getConnection;

public class TotemSistema {
    private Statement statatement;
    private Scanner entrada;

    public TotemSistema() {
        try {
            statatement = getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void inserirPedidos(ItemPedido pedido) {

        if (existeRepeticaoComEsseNome(pedido.getNome())) {
            atualizaPedidoJaExistente(pedido);
        } else {
            inserirPedidoNaoExistente(pedido);
        }

    }

    private void atualizaPedidoJaExistente(ItemPedido pedido) {
        try {
            String sql = "UPDATE tb_pedidos SET qtd = qtd + " + pedido.getQuantidade() + ", valortotal = valortotal + " + pedido.getValorTotal() + " WHERE nome = '" + pedido.getNome() + "'";
            statatement.executeUpdate(sql);
            System.out.println(" Pedido atualizado ");

        } catch (SQLException e) {
            System.out.println("Não foi possivel atualizar o pedido");
            e.printStackTrace();
        }

    }

    private void inserirPedidoNaoExistente(ItemPedido pedido) {
        try {
            String sql = "INSERT INTO tb_pedidos (nome, valor, qtd, valortotal) VALUES " +
                    "('" + pedido.getNome() + "', " + pedido.getValor() + ", " + pedido.getQuantidade() +
                    ", " + pedido.getValorTotal() + ")";
            statatement.executeUpdate(sql);
            System.out.println(" Pedido cadastrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existeRepeticaoComEsseNome(String nome) {
        int numRepeticoes = 0;
        try {
            String sql = "SELECT COUNT(*) AS repeticoes FROM tb_pedidos WHERE nome = '" + nome + "'";
            ResultSet resultSet = statatement.executeQuery(sql);
            if (resultSet.next()) {
                numRepeticoes = resultSet.getInt("repeticoes");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numRepeticoes > 0;
    }


    public void editarItem(int id, int novaQtd) {
        String sql = "UPDATE tb_pedidos set qtd='" + novaQtd + "'" +
                "WHERE id='" + id + "'";
        try {
            statatement.executeUpdate(sql);
            System.out.println(" Pedido atualizado ");

        } catch (SQLException e) {
            System.out.println("Não foi possivel atualizar o pedido");
            e.printStackTrace();
        }
    }

    public void inserirNaTabelaValorTotalAtualizado(int id){
        double valor = calcularValorTotal(id);
        String sql = "UPDATE tb_pedidos set valortotal='" + valor + "'" +
                "WHERE id='" + id + "'";
        try {
            statatement.executeUpdate(sql);
            System.out.println("Valor atualizado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public double calcularValorTotal(int id){
        try {
            String sql = "SELECT valor, qtd  FROM tb_pedidos WHERE id = " + id;
            ResultSet resultSet = statatement.executeQuery(sql);
            if (resultSet.next()){
                double valor = resultSet.getDouble("valor");
                int qtd = resultSet.getInt("qtd");
                double valorTotal = valor*qtd;
                return valorTotal;
            } else {
                System.out.println("Registro não encontrado");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0.0;
    }

    public void deletarItem(int id) {
        String sql = "DELETE FROM tb_pedidos WHERE id='" + id + "'";
        try {
            statatement.executeUpdate(sql);
            System.out.println("Item excluido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Não foi possivel excluir");
            e.printStackTrace();
        }

    }

    public void consultarItens() {
        String sql = "SELECT * FROM tb_pedidos";
        try {
            ResultSet resultSet = statatement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println("Codigo: " + resultSet.getInt("id") +
                        "/ Nome: " + resultSet.getString("nome") +
                        "/ Quantidade: " + resultSet.getInt("qtd") +
                        "/ Valor: " + resultSet.getDouble("valor") +
                        "/ Valor Total: " + resultSet.getDouble("valortotal"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Double valorTotalDoPedido() {
        try {
            String sql = "SELECT SUM(valorTotal) AS soma_total FROM tb_pedidos";
            ResultSet resultSet = statatement.executeQuery(sql);
            if (resultSet.next()) {
                double valorTotal = resultSet.getDouble("soma_total");

                return valorTotal;
            } else {
                System.out.println("Nenhum resultado encontrado");
                return 0.0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public double valorEmDinheiro(double valor) {

        double valortotal = valorTotalDoPedido();
        if (valor >= valortotal) {
            double troco = valor - valortotal;
            System.out.println("O troco será:");
            return troco;
        } else if (valor <= valortotal) {
            System.out.println("Sua compra deu um valor maior do que está pagando");
        } else {

        }
        return valortotal;

    }

    public void deletarTabela() {
        try {
            String sql = "DELETE FROM tb_pedidos";
            int rowsDeleted = statatement.executeUpdate(sql);
            System.out.println("foram deletados os registro da tabela");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
