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
		int a = f == null ? 0 : f.hashCode();
		int b = s == null ? 0 : s.hashCode();
		return (a << 5) - a + b;
	}

	@Override
	public String toString() {
		return String.format("(%s, %s)", f == null ? "null" : f.toString(), s == null ? "null" : s.toString());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (o instanceof Pair<?, ?>) {
			Pair<?, ?> p = (Pair<?, ?>) o;
			return ((f == p.f) || p.f.equals(f)) && (s == p.s || p.s.equals(s));
		}
		return false;
	}
}
