package com.cruds.estructuras;

// Nodo genérico: guarda un dato y una referencia al siguiente nodo.
// Es la pieza básica con la que se construyen la pila y la cola.
public class Nodo<T> {

    private T dato;
    private Nodo<T> siguiente;

    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null; // al crearse no apunta a ningún nodo
    }

    public T getDato() {
        return dato;
    }

    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }
}