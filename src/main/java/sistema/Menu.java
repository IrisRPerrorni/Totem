package sistema;

import model.ItemPedido;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Menu {

    public  void mostrarMenuInicial(){
        System.out.println("Digite a opção desejada:");
        System.out.println("1: Lanche");
        System.out.println("2: Bebida");
        System.out.println("3: Editar Item");
        System.out.println("4: Deletar Item");
        System.out.println("5: Consultar Item");
        System.out.println("6: Finalizar pedidos");
    }


    public  void mostrarMenuLanche(){
        System.out.println("Digite o lanche desejada:");
        System.out.println("1: X-Burger");
        System.out.println("2: X-Salada");

    }
    public  void mostrarMenuBebida(){
        System.out.println("Digite a bebida desejada:");
        System.out.println("1: Refrigerantes");
        System.out.println("2: Sucos");
    }
    public  void mostrarMenuPagamento(){
        System.out.println("Digite a forma de pagamento desejada:");
        System.out.println("1: Cartão de crédito");
        System.out.println("2: Cartão de débito");
        System.out.println("3: Cartão refeição");
        System.out.println("4: Vale refeição");
        System.out.println("5: Dinheiro ");
    }



    /*
       Esse metodo consulta os itens do cardapio e busca pelo nome do item retornando
       o nome e o valor do item, depois calculamos o valor total,
       retorna esse objeto ItemPedido com dados ajustados
       ItemPedido(nome = XBurger,valor=10.00,quantidade =2, valorTotal = 20.00)
    */
    public  ItemPedido buscaItemPorNome(String nome, int quantidade){
        ArrayList<ItemPedido> listaCardapio = obterCardapio();

        for (ItemPedido itemPedido : listaCardapio) {
            if (itemPedido.getNome() == nome) {
                itemPedido.setQuantidade(quantidade);
                itemPedido.setValorTotal(itemPedido.getValor() * quantidade);
                return itemPedido;
            }
        }

        return null;
    }

    // Armazena os itens que podem ser selecionados para usuário
    public  ArrayList<ItemPedido> obterCardapio(){
        ArrayList<ItemPedido> listaPedido = new ArrayList<>();

        listaPedido.add( new ItemPedido("X-Burger",10.00));
        listaPedido.add( new ItemPedido("X-Salada",12.00));
        listaPedido.add( new ItemPedido("Refrigerante",8.00));
        listaPedido.add( new ItemPedido("Suco",6.00));

        return listaPedido;
    }
    public  void pedirQuantidade(){
        System.out.println("Digite quantos itens voce quer?");
    }



}
