package BaseCodeForCoding;

public class Pair<F, S> {
	F f;
	S s;

	Pair(F f, S s) {
		this.f = f;
		this.s = s;
	}

	public static <F, S> Pair<F, S> mp(F f, S s) {// Can be used even if it is not in class
		return new Pair<F, S>(f, s);
	}

	@Override
	public int hashCode() {
		int a = f.hashCode();
		int b = f.hashCode();
		return (a << 5) - a + b;
	}

	@Override
	public String toString() {
		return String.format("(%s, %s)", f.toString(), s.toString());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (o instanceof Pair<?, ?>) {
			Pair<?, ?> p = (Pair<?, ?>) o;
			return p.f.equals(f) && p.s.equals(s);
		}
		return false;
	}
}

