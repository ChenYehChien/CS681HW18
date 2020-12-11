package edu.umb.cs681.hw18;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Observable<T> {
	private ConcurrentLinkedQueue<Observer<T>> observers;
	private AtomicBoolean change = new AtomicBoolean(false); //new added
	
	public Observable () {
		observers = new ConcurrentLinkedQueue<Observer<T>>(); 
	}
	//LE
	//it is not the same as some observer already in the set
	public void addObserver(Observer<T> o) {
		if (observers.contains(o))
			return;
		observers.offer(o);
	}
	//LE
	//Deletes an observer from the set of observers of this object
	public void deleteObserver(Observer<T> o) {
		observers.remove(o);
	}
	protected void setChanged() {
		change.getAndSet(true);
	};
	protected void clearChanged() {
		change.getAndSet(false);
	};
	public boolean hasChanged() {
		//true if and only if the setChanged method has been called 
		//more recently than the clearChanged method on this object; 
		//false otherwise.
		if (change.get())
			return true;
		return false;
	}
	public void notifyObservers(T obj) {
		if (hasChanged()) {
			observers.forEach((o) -> {
				o.update(this, obj);
			});
		}
	}
	
}