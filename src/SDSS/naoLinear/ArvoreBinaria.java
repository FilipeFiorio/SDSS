package SDSS.naoLinear;

import java.awt.Color;
import aesd.ds.interfaces.BinaryTree;

public class ArvoreBinaria extends JanelaArvore {

    private BinarySearchTree<Integer, Integer> arvore;
    private DesenhoArvore desenho;

    public ArvoreBinaria() {
        super("Árvore Binária");
    }

    @Override
    public void create() {
        super.create();

        // Inicializa árvore e classe de desenho
        arvore = new BinarySearchTree<>();
        desenho = new DesenhoArvore();
    }

    @Override
    public void update(double delta) {
        super.update(delta);
        // Nenhuma lógica aqui — a árvore será manipulada pela JanelaPrincipal
    }

    @Override
    public void draw() {
        super.draw();

        // Desenha a árvore (somente visual)
        desenho.desenhar(arvore, this, camera);
    }

    public BinarySearchTree<Integer, Integer> getArvore() {
        return arvore;
    }

    public void setArvore(BinarySearchTree<Integer, Integer> arvore) {
        this.arvore = arvore;
    }

    public static void main(String[] args) {
        new ArvoreBinaria();
    }
}
