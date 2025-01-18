package com.ztp.ztpproject.memento;

public interface ICaretaker<T> {
    public void addMementoFromOriginator();
    public void restoreFromLastMemento();
    public void restoreFromMemento(int index);
    public T getOriginator();
} 
