package utils;

public class Pair<T1,T2> {
	private T1 x;
	private T2 y;
	
	public Pair(T1 x,T2 y) {
		this.x = x;
		this.y = y;
	}
	public T1 getKey() {
		return this.x;
	}
	public T2 getValue() {
		return this.y;
	}
	public void setx(T1 x) {
		this.x=x;
	}
	public void sety(T2 y) {
		this.y=y;
	}
}
