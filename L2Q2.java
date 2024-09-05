import java.util.Scanner;

class ListaSequencialOrdenadaDinamica {
    private int[] lista;
    private int tamanho;
    private int capacidade;

    public ListaSequencialOrdenadaDinamica(int capacidade) {
        this.capacidade = capacidade;
        this.lista = new int[capacidade];
        this.tamanho = 0;
    }

    public void visualizar() {
        System.out.print("Lista: ");
        for (int i = 0; i < capacidade; i++) {
            if (i < tamanho) {
                System.out.print(lista[i] + " ");
            } else {
                System.out.print("NIL ");
            }
        }
        System.out.println();
    }

    public int buscar(int valor) {
        for (int i = 0; i < tamanho; i++) {
            if (lista[i] == valor) {
                return i;
            }
        }
        return -1;
    }

    public void inserir(int valor) {
        if (tamanho >= capacidade) {
            redimensionar();
        }

        int posicao = 0;
        while (posicao < tamanho && lista[posicao] < valor) {
            posicao++;
        }

        for (int i = tamanho; i > posicao; i--) {
            lista[i] = lista[i - 1];
        }

        lista[posicao] = valor;
        tamanho++;
    }

    private void redimensionar() {
        int novaCapacidade = capacidade * 2;
        int[] novaLista = new int[novaCapacidade];
        for (int i = 0; i < capacidade; i++) {
            novaLista[i] = lista[i];
        }
        lista = novaLista;
        capacidade = novaCapacidade;
        System.out.println("A capacidade da lista aumnetou para " + novaCapacidade);
    }

    public void remover(int valor) {
        int posicao = buscar(valor);
        if (posicao == -1) {
            System.out.println("Elemento não foi encontrado na lista, tente novamente.");
            return;
        }

        for (int i = posicao; i < tamanho - 1; i++) {
            lista[i] = lista[i + 1];
        }

        lista[tamanho - 1] = 0;
        tamanho--;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Defina a capacidade máxima inicial da lista: ");
        int capacidade = scanner.nextInt();
        ListaSequencialOrdenadaDinamica lista = new ListaSequencialOrdenadaDinamica(capacidade);

        while (true) {
            System.out.println("\nEscolha uma das operações:");
            System.out.println("1. Visualizar lista");
            System.out.println("2. Buscar elemento");
            System.out.println("3. Inserir elemento");
            System.out.println("4. Remover elemento");
            System.out.println("5. Sair");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    lista.visualizar();
                    break;
                case 2:
                    System.out.print("Digite o elemento que você procura: ");
                    int valorBusca = scanner.nextInt();
                    int posicao = lista.buscar(valorBusca);
                    if (posicao == -1) {
                        System.out.println("Elemento não encontrado!");
                    } else {
                        System.out.println("Elemento encontrado na posição: " + posicao);
                    }
                    break;
                case 3:
                    System.out.print("Digite o elemento que você quer inserir: ");
                    int valorInserir = scanner.nextInt();
                    lista.inserir(valorInserir);
                    break;
                case 4:
                    System.out.print("Digite o elemento que você quer remover: ");
                    int valorRemover = scanner.nextInt();
                    lista.remover(valorRemover);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente!");
            }
        }
    }
}