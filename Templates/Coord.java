public class Coord {
	int x, y;

	Coord(int a, int b) {
		x = a;
		y = b;
	}

	Coord(String s) {
		String[] tokens = s.split(" ");
		x = Integer.parseInt(tokens[0]);
		y = Integer.parseInt(tokens[1]);
	}

	Coord(Coord c) {
		x = c.x;
		y = c.y;
	}

	double dist(Coord c) {
		double dx = x - c.x;
		double dy = y - c.y;
		return Math.sqrt(dx * dx + dy + dy);
	}

	int distSq(Coord c) {
		int dx = x - c.x;
		int dy = y - c.y;
		return dx * dx + dy * dy;
	}

	int distMH(Coord c) {
		return Math.abs(x - c.x) + Math.abs(y - c.y);
	}

	Coord up() {
		return new Coord(x, y + 1);
	}

	Coord down() {
		return new Coord(x, y - 1);
	}

	Coord left() {
		return new Coord(x - 1, y);
	}

	Coord right() {
		return new Coord(x + 1, y);
	}

	Coord go(char x) {
		if (x == 'N' || x == 'U' || x == '0')
			return this.up();
		if (x == 'E' || x == 'R' || x == '1')
			return this.right();
		if (x == 'S' || x == 'D' || x == '2')
			return this.down();
		if (x == 'W' || x == 'L' || x == '3')
			return this.left();
		return new Coord(this);
	}

	Coord go(String s) {
		Coord ret = new Coord(this);
		for (int i = 0; i < s.length(); i++)
			ret = ret.go(s.charAt(i));
		return ret;
	}

	Coord go(int i) {
		return go(String.valueOf(i));
	}

	Coord go(long l) {
		return go(String.valueOf(l));
	}

	@Override
	public String toString() {
		return String.format("(%d, %d)", x, y);
	}

	@Override
	public int hashCode() {
		final long prime = 31;
		final int m = (int) 1e9 + 9;
		long result = 1;
		result = ((prime * result + x) % m);
		result = ((prime * result + y) % m);
		return (int) result;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (this == o)
			return true;
		if (o instanceof Coord) {
			Coord c = (Coord) o;
			return x == c.x && y == c.y;
		}
		return false;
	}
}

