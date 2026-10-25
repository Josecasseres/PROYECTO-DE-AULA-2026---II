package com.cruds.estructuras;

import java.util.ArrayList;
import java.util.List;

// Pila (LIFO): el último que entra es el primero que sale.
// Se construye con nodos enlazados; solo se guarda una referencia a la cima.
public class Pila<T> {

    private Nodo<T> cima; // último elemento apilado
    private int tamanio;

    public Pila() {
        cima = null;
        tamanio = 0;
    }

    // Pone un elemento encima de la pila
    public void apilar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.setSiguiente(cima); // el nuevo apunta al que era la cima
        cima = nuevo;
        tamanio++;
    }

    // Saca y devuelve el elemento de la cima (null si la pila está vacía)
    public T desapilar() {
        if (estaVacia()) {
            return null;
        }
        T dato = cima.getDato();
        cima = cima.getSiguiente();
        tamanio--;
        return dato;
    }

    // Devuelve el elemento de la cima sin sacarlo (null si está vacía)
    public T verCima() {
        return estaVacia() ? null : cima.getDato();
    }

    public boolean estaVacia() {
        return cima == null;
    }

    public int getTamanio() {
        return tamanio;
    }

    // Devuelve los elementos de la cima hacia abajo (para mostrarlos en pantalla)
    public List<T> aLista() {
        List<T> lista = new ArrayList<>();
        Nodo<T> actual = cima;
        while (actual != null) {
            lista.add(actual.getDato());
            actual = actual.getSiguiente();
        }
        return lista;
    }
}
