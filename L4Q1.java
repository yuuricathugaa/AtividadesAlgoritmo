class No {
    int chave;
    No esq, dir;

    public No(int chave) {
        this.chave = chave;
        this.esq = null;
        this.dir = null;
    }
}

public class L4Q1 {
    No raiz;

    public L4Q1() {
        this.raiz = null;
    }

    // a. Busca
    public No busca(No raiz, int chave) {
        if (raiz == null || raiz.chave == chave) {
            return raiz;
        }
        if (chave < raiz.chave) {
            return busca(raiz.esq, chave);
        } else {
            return busca(raiz.dir, chave);
        }
    }

    // b. Inserir
    public No inserir(No raiz, int chave) {
        if (raiz == null) {
            raiz = new No(chave);
            return raiz;
        }
        if (chave < raiz.chave) {
            raiz.esq = inserir(raiz.esq, chave);
        } else if (chave > raiz.chave) {
            raiz.dir = inserir(raiz.dir, chave);
        }
        return raiz;
    }

    // c. Sucessor
    public No sucessor(No raiz) {
        No atual = raiz;
        while (atual != null && atual.esq != null) {
            atual = atual.esq;
        }
        return atual;
    }

    // d. Remover
    public No remover(No raiz, int chave) {
        if (raiz == null) {
            return raiz;
        }
        if (chave < raiz.chave) {
            raiz.esq = remover(raiz.esq, chave);
        } else if (chave > raiz.chave) {
            raiz.dir = remover(raiz.dir, chave);
        } else {
            if (raiz.esq == null) {
                return raiz.dir;
            } else if (raiz.dir == null) {
                return raiz.esq;
            }
            No temp = sucessor(raiz.dir);
            raiz.chave = temp.chave;
            raiz.dir = remover(raiz.dir, temp.chave);
        }
        return raiz;
    }

    public void imprimirEmOrdem(No raiz) {
        if (raiz != null) {
            imprimirEmOrdem(raiz.esq);
            System.out.print(raiz.chave + " ");
            imprimirEmOrdem(raiz.dir);
        }
    }

    public static void main(String[] args) {
        L4Q1 abb = new L4Q1();

        abb.raiz = abb.inserir(abb.raiz, 18);
        abb.raiz = abb.inserir(abb.raiz, 35);
        abb.raiz = abb.inserir(abb.raiz, 20);
        abb.raiz = abb.inserir(abb.raiz, 24);
        abb.raiz = abb.inserir(abb.raiz, 10);
        abb.raiz = abb.inserir(abb.raiz, 40);
        abb.raiz = abb.inserir(abb.raiz, 50);
        abb.raiz = abb.inserir(abb.raiz, 65);
        abb.raiz = abb.inserir(abb.raiz, 25);
        abb.raiz = abb.inserir(abb.raiz, 13);
        abb.raiz = abb.inserir(abb.raiz, 17);
        abb.raiz = abb.inserir(abb.raiz, 11);

        System.out.println("Árvore em ordem:");
        abb.imprimirEmOrdem(abb.raiz);
        System.out.println();

        No busca = abb.busca(abb.raiz, 10);
        System.out.println("Busca pela chave 10: " + (busca != null ? "Encontrada" : "Não encontrada"));

        abb.raiz = abb.remover(abb.raiz, 10);
        System.out.println("Árvore após remover 10:");
        abb.imprimirEmOrdem(abb.raiz);
    }
}