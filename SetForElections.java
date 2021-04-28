package id314022914_id206921777;

public class SetForElections<T> {
	private final int ENLARGE_FACTOR = 2;
	private T[] set;
	private int currentSize;

	public SetForElections() {
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

	public boolean add(T t) {
		enlargeArray();
		if (!(this.exist(t))) {
			this.set[this.currentSize] = t;
			this.currentSize++;
			return true;
		}
		System.out.println("already exist");
		return false;
	}

	private boolean exist(T t) {
		for (int i = 0; i < this.currentSize; i++) {
			if (t.equals(this.set[i]))
				return true;
		}
		return false;
	}

	public T get(int index) {
		return set[index];
	}

	public int getCurrentSize() {
		return this.currentSize;
	}
}
