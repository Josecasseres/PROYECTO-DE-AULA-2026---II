package com.cruds.estructuras;

import java.util.ArrayList;
import java.util.List;

// Cola (FIFO): el primero que entra es el primero que sale.
// Se construye con nodos enlazados, guardando referencias al frente y al final.
public class Cola<T> {

    private Nodo<T> frente; // próximo elemento en salir
    private Nodo<T> fin;    // último elemento que entró
    private int tamanio;

    public Cola() {
        frente = null;
        fin = null;
        tamanio = 0;
    }

    // Agrega un elemento al final de la cola
    public void encolar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (estaVacia()) {
            frente = nuevo; // si estaba vacía, el nuevo es también el frente
        } else {
            fin.setSiguiente(nuevo);
        }
        fin = nuevo;
        tamanio++;
    }

    // Saca y devuelve el elemento del frente (null si la cola está vacía)
    public T desencolar() {
        if (estaVacia()) {
            return null;
        }
        T dato = frente.getDato();
        frente = frente.getSiguiente();
        if (frente == null) {
            fin = null; // la cola quedó vacía
        }
        tamanio--;
        return dato;
    }

    // Devuelve el elemento del frente sin sacarlo (null si está vacía)
    public T verFrente() {
        return estaVacia() ? null : frente.getDato();
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int getTamanio() {
        return tamanio;
    }

    // Devuelve los elementos en orden de salida (para mostrarlos en pantalla)
    public List<T> aLista() {
        List<T> lista = new ArrayList<>();
        Nodo<T> actual = frente;
        while (actual != null) {
            lista.add(actual.getDato());
            actual = actual.getSiguiente();
        }
        return lista;
    }
}