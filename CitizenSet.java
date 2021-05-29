package id314022914_id206921777;

import java.io.Serializable;

import id314022914_id206921777.exceptions.WrongTypeException;

public class CitizenSet<T> implements Serializable {
	private final int ENLARGE_FACTOR = 2;
	private T[] set;
	private int currentSize;

	public CitizenSet() {
		this.set = (T[]) new Object[ENLARGE_FACTOR];
		this.currentSize = 0;
	}

	public void enlargeArray() {
		if (set[set.length - 1] != null) {
			T[] temp = (T[]) new Object[this.set.length * ENLARGE_FACTOR];
			for (int i = 0; i < this.currentSize; i++)
				temp[i] = this.set[i];
			this.set = temp;
		}
	}

	public boolean add(T t) throws Exception {
		if (t instanceof Citizen) {
			enlargeArray();
			if (exist(t) == -1) {
				this.set[this.currentSize] = t;
				this.currentSize++;
				return true;
			}
			return false;
		} else
			throw new WrongTypeException();
	}

	public void replace(int index, T t) throws Exception {
		if (t instanceof Citizen)
			this.set[index] = (T) t;
		else
			throw new WrongTypeException();

	}

	public int exist(T t) {
		for (int i = 0; i < this.currentSize; i++) {
			if (t.equals(this.set[i]))
				return i;
		}
		return -1;
	}

	public T get(int index) {
		return set[index];
	}

	public int getCurrentSize() {
		return this.currentSize;
	}

	public int getCurrentCapacitiySize() {
		return this.set.length;

	}

	public int existById(String id) {
		for (int i = 0; i < this.currentSize; i++) {
			if (((Citizen) this.set[i]).getId() == id)
				return i;
		}
		return -1;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer("The citizens are :  " +" \n" + "\n");
		for (int i = 0; i < this.currentSize; i++) {
			str.append("________-" + (i + 1) + "-________\n" + this.set[i].toString() + "\n\n");
		}
		return str.toString();
	}

}
