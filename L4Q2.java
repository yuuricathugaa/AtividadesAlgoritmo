class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class L4Q2 {
    private static final int DEFAULT_SIZE = 10;
    private int size;
    private Integer[] tabela;
    private Node[] listaEncadeada;
    private boolean usandoLista;

    public L4Q2() {
        this(DEFAULT_SIZE);
    }

    public L4Q2(int tamanho) {
        this.size = tamanho;
        this.tabela = new Integer[size];
        this.listaEncadeada = new Node[size];
        this.usandoLista = false;
    }

    // Função hash
    private int hash(int chave) {
        return chave % size;
    }

    // Inserção na tabela hash
    public void inserir(int chave) {
        if (usandoLista) {
            inserirComLista(chave);
        } else {
            if (!inserirComLinearProbing(chave)) {
                // Tabela cheia, muda para endereçamento fechado
                mudarParaListaEncadeada();
                inserirComLista(chave);
            }
        }
    }

    // Inserção com linear probing
    private boolean inserirComLinearProbing(int chave) {
        int posicao = hash(chave);
        int originalPos = posicao;

        while (tabela[posicao] != null) {
            if (tabela[posicao].equals(chave)) {
                System.out.println("Erro: Elemento já existe na tabela.");
                return false;
            }
            posicao = (posicao + 1) % size;
            if (posicao == originalPos) {
                System.out.println("Erro: Tabela cheia, mudando para endereçamento fechado.");
                return false;
            }
        }
        tabela[posicao] = chave;
        return true;
    }

    private void mudarParaListaEncadeada() {
        for (int i = 0; i < size; i++) {
            listaEncadeada[i] = null;
            if (tabela[i] != null) {
                inserirComLista(tabela[i]);
            }
        }
        tabela = null;
        usandoLista = true;
    }

    private void inserirComLista(int chave) {
        int posicao = hash(chave);
        Node novoNode = new Node(chave);
        if (listaEncadeada[posicao] == null) {
            listaEncadeada[posicao] = novoNode;
        } else {
            Node atual = listaEncadeada[posicao];
            while (atual.next != null) {
                if (atual.data == chave) {
                    System.out.println("Erro: Elemento já existe na tabela.");
                    return;
                }
                atual = atual.next;
            }
            atual.next = novoNode;
        }
    }

    // Busca na tabela hash
    public boolean buscar(int chave) {
        if (usandoLista) {
            return buscarComLista(chave);
        } else {
            return buscarComLinearProbing(chave);
        }
    }

    // Busca com linear probing
    private boolean buscarComLinearProbing(int chave) {
        int posicao = hash(chave);
        int originalPos = posicao;

        while (tabela[posicao] != null) {
            if (tabela[posicao].equals(chave)) {
                return true;
            }
            posicao = (posicao + 1) % size;
            if (posicao == originalPos) {
                return false;
            }
        }
        return false;
    }

    // Busca com lista encadeada
    private boolean buscarComLista(int chave) {
        int posicao = hash(chave);
        Node atual = listaEncadeada[posicao];
        while (atual != null) {
            if (atual.data == chave) {
                return true;
            }
            atual = atual.next;
        }
        return false;
    }

    public static void main(String[] args) {
        L4Q2 hash = new L4Q2(5);

        hash.inserir(10);
        hash.inserir(15);
        hash.inserir(20);
        hash.inserir(25);
        hash.inserir(30);
        hash.inserir(35);

        System.out.println("Busca pela chave 10: " + hash.buscar(10)); // Retorna true
        System.out.println("Busca pela chave 15: " + hash.buscar(15)); // Retorna true
        System.out.println("Busca pela chave 30: " + hash.buscar(30)); // Retorna true
        System.out.println("Busca pela chave 40: " + hash.buscar(40)); // Retorna false
    }
}