package edu.umb.cs681.hw18;

@FunctionalInterface
public interface Observer<T> {
	public abstract void update(Observable<T> source, T event);
}
