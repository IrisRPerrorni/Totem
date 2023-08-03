package sistema;

import model.ItemPedido;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class mainTest {
    public static void main(String[] args) {
        Menu menu = new Menu();
        TotemSistema sistema = new TotemSistema();

        Scanner entrada = new Scanner(System.in);


        // ArrayList<ItemPedido> meuPedido = new ArrayList<>();

        while (true) {
            int opcao;
            menu.mostrarMenuInicial();
            opcao = capturarEntrada();

            switch (opcao) {
                case 1:

                    menu.mostrarMenuLanche();
                    int lanche = capturarEntrada();
                    int quantidade;
                    switch (lanche) {
                        case 1:
                            menu.pedirQuantidade();
                            quantidade = capturarEntrada();

                            ItemPedido xBurger = menu.buscaItemPorNome("X-Burger", quantidade);
                            //                   meuPedido.add(xBurger);
                            sistema.inserirPedidos(xBurger);
                            break;
                        case 2:
                            menu.pedirQuantidade();
                            quantidade = capturarEntrada();

                            ItemPedido xSalada = menu.buscaItemPorNome("X-Salada", quantidade);
                            //meuPedido.add(xSalada);
                            sistema.inserirPedidos(xSalada);
                            break;

                        default:
                            System.out.println("Opção Invalida");
                    }

                    break;

                case 2:
                    menu.mostrarMenuBebida();
                    int bebida = capturarEntrada();
                    int qtd;

                    switch (bebida) {
                        case 1:
                            menu.pedirQuantidade();
                            qtd = entrada.nextInt();
                            ItemPedido refrigerantes = menu.buscaItemPorNome("Refrigerante", qtd);
                            sistema.inserirPedidos(refrigerantes);

                            break;

                        case 2:
                            menu.pedirQuantidade();
                            qtd = capturarEntrada();
                            ItemPedido sucos = menu.buscaItemPorNome("Suco", qtd);
                            sistema.inserirPedidos(sucos);
                            break;
                    }

                    break;

                case 3:
                    System.out.println("Editando item ");
                    System.out.println("Informe o item que deseja editar");
                    int idItem = entrada.nextInt();
                    System.out.println("Informe a quantidade que deseja");
                    int qtdItem = entrada.nextInt();
                    sistema.editarItem(idItem, qtdItem);
                    sistema.inserirNaTabelaValorTotalAtualizado(idItem);
                    break;

                case 4:
                    System.out.println("Informe o item que deseja deletar");
                    sistema.consultarItens();
                    int idDeletar = entrada.nextInt();
                    sistema.deletarItem(idDeletar);
                    break;

                case 5:
                    System.out.println("Consultando itens");
                    sistema.consultarItens();
                    break;

                case 6:
                    System.out.println("Finalizar pedido");
                    sistema.consultarItens();
                    System.out.println("Sua compra ficou no valor de R$: ");
                    System.out.println(sistema.valorTotalDoPedido());
                    menu.mostrarMenuPagamento();
                    int num = capturarEntrada();
                    if (num == 1 || num == 2 || num == 3 || num == 4) {
                        System.out.println("Compra finalizada com sucesso! Boa refeição”");
                    } else if (num == 5) {
                        System.out.println("Qual o valor em dinheiro que irá pagar?");
                        double valor = entrada.nextDouble();
                        System.out.println(sistema.valorEmDinheiro(valor));

                    }
                    sistema.deletarTabela();

                    System.exit(0);
                    break;

                default:
                    System.out.println("Valor invalido");
            }

        }


    }

//    public static int capturarEntrada() {
//        Scanner entrada = new Scanner(System.in);
//
//        if (!entrada.hasNextInt()) {
//            System.out.println("Valor invalido! Digite um valor inteiro!");
//            capturarEntrada();
//        }
//        return entrada.nextInt();
//
//    }

    public static int capturarEntrada() {
        Scanner entrada = new Scanner(System.in);
        int valor = 0;

        try {
            valor = entrada.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Valor inválido! Digite um valor inteiro.");
            entrada.nextLine(); // Limpar a entrada inválida
            valor = capturarEntrada(); // Chamar o método recursivamente para capturar uma nova entrada válida
        }

        return valor;

    }

}
