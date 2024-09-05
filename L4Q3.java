class Node {
    int data;
    Node prev, next;

    public Node(int data) {
        this.data = data;
        this.prev = this.next = this;
    }
}

public class L4Q3 {
    private Node first = null;
    private Node last = null;

    // Inserção ordenada de um elemento na lista
    public void insert(int value) {
        Node newNode = new Node(value);
        if (first == null) {
            first = last = newNode;
        } else {
            Node current = first;
            Node prev = null;

            // Encontrar a posição correta para inserção
            do {
                if (current.data == value) {
                    System.out.println("Erro: Valor já existe na lista.");
                    return;
                }
                if (current.data > value) break;
                prev = current;
                current = current.next;
            } while (current != first);

            // Inserção no início da lista
            if (prev == null) {
                newNode.next = first;
                newNode.prev = last;
                first.prev = newNode;
                last.next = newNode;
                first = newNode;
            } 
            // Inserção no final da lista
            else if (current == first) {
                last.next = newNode;
                newNode.prev = last;
                newNode.next = first;
                first.prev = newNode;
                last = newNode;
            } 
            // Inserção no meio da lista
            else {
                prev.next = newNode;
                newNode.prev = prev;
                newNode.next = current;
                current.prev = newNode;
            }
        }
    }

    // Remoção de um elemento da lista
    public void remove(int value) {
        if (first == null) {
            System.out.println("Erro: A lista está vazia.");
            return;
        }

        Node current = first;
        do {
            if (current.data == value) {
                // Remoção do único elemento
                if (first == last) {
                    first = last = null;
                } 
                // Remoção do primeiro elemento
                else if (current == first) {
                    last.next = first.next;
                    first = first.next;
                    first.prev = last;
                } 
                // Remoção do último elemento
                else if (current == last) {
                    last = last.prev;
                    last.next = first;
                    first.prev = last;
                } 
                // Remoção de um elemento do meio
                else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                return;
            }
            current = current.next;
        } while (current != first);

        System.out.println("Erro: Elemento não encontrado na lista.");
    }

    // Visualização direta (do primeiro ao último elemento)
    public void displayForward() {
        if (first == null) {
            System.out.println("A lista está vazia.");
            return;
        }
        Node current = first;
        do {
            System.out.print(current.data + " ");
            current = current.next;
        } while (current != first);
        System.out.println();
    }

    // Visualização reversa (do último ao primeiro elemento)
    public void displayBackward() {
        if (last == null) {
            System.out.println("A lista está vazia.");
            return;
        }
        Node current = last;
        do {
            System.out.print(current.data + " ");
            current = current.prev;
        } while (current != last);
        System.out.println();
    }

    // Busca de um elemento na lista
    public boolean search(int value) {
        if (first == null) return false;

        Node current = first;
        do {
            if (current.data == value) return true;
            current = current.next;
        } while (current != first);

        return false;
    }

    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        L4Q3 list = new L4Q3();
        int choice, value;

        do {
            System.out.println("Operações Disponíveis:");
            System.out.println("1. Inserir");
            System.out.println("2. Remover");
            System.out.println("3. Visualizar (Direta)");
            System.out.println("4. Visualizar (Reversa)");
            System.out.println("5. Buscar");
            System.out.println("6. Sair");
            System.out.println("Digite abaixo o número da operação que você quer realizar: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Digite o valor para inserir: ");
                    value = scanner.nextInt();
                    list.insert(value);
                    break;
                case 2:
                    System.out.print("Digite o valor para remover: ");
                    value = scanner.nextInt();
                    list.remove(value);
                    break;
                case 3:
                    list.displayForward();
                    break;
                case 4:
                    list.displayBackward();
                    break;
                case 5:
                    System.out.print("Digite o valor para buscar: ");
                    value = scanner.nextInt();
                    if (list.search(value)) {
                        System.out.println("Elemento encontrado.");
                    } else {
                        System.out.println("Elemento não encontrado.");
                    }
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (choice != 6);

        scanner.close();
    }
}