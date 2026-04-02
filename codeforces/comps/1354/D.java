import java.io.IOException;
import java.io.InputStream;

public class D {
	public static void main(String[] args) throws IOException {
		FastInput fi = new FastInput(System.in);
		int N = fi.readInt();
		int Q = fi.readInt();
		OrderStatisticTree BIT = new OrderStatisticTree((int) 2e6);
		int sz = 0;
		for (int i = 0; i < N; i++) {
			BIT.add(fi.readInt());
			sz++;
		}
		for (int i = 0; i < Q; i++) {
			int q = fi.readInt();
			if (q < 0) {
				BIT.delete(BIT.getOrderStat(-q));
				sz--;
			} else {
				BIT.add(q);
				sz++;
			}
		}
		System.out.println(sz == 0 ? 0 : BIT.getOrderStat(1));
	}

	static class OrderStatisticTree {
		int[] BIT;

		public OrderStatisticTree(int sz) {
			BIT = new int[sz];
		}

		void update(int i, int add) {
			while (i > 0 && i < BIT.length) {
				BIT[i] += add;
				i = i + (i & (-i));
			}
		}

		int sum(int i) {
			int ans = 0;
			while (i > 0) {
				ans += BIT[i];
				i = i - (i & (-i));
			}

			return ans;
		}

		int getOrderStat(int k) {
			int l = 0, r = BIT.length;
			while (l < r) {
				int mid = l + ((r - l) >> 1);
				if (k <= sum(mid))
					r = mid;
				else
					l = mid + 1;
			}
			return l;
		}

		void add(int x) {
			update(x, 1);
		}

		void delete(int x) {
			update(x, -1);
		}

		int findRank(int x) {
			return sum(x);
		}
	}

	static class FastInput {
		private final InputStream is;
		private byte[] buf = new byte[1024];
		private int bufLen;
		private int bufOffset;
		private int next;

		public FastInput(InputStream is) {
			this.is = is;
		}

		private int read() {
			while (bufLen == bufOffset) {
				bufOffset = 0;
				try {
					bufLen = is.read(buf);
				} catch (IOException e) {
					bufLen = -1;
				}
				if (bufLen == -1) {
					return -1;
				}
			}
			return buf[bufOffset++];
		}

		public void skipBlank() {
			while (next >= 0 && next <= 32) {
				next = read();
			}
		}

		public int readInt() {
			int sign = 1;

			skipBlank();
			if (next == '+' || next == '-') {
				sign = next == '+' ? 1 : -1;
				next = read();
			}

			int val = 0;
			if (sign == 1) {
				while (next >= '0' && next <= '9') {
					val = val * 10 + next - '0';
					next = read();
				}
			} else {
				while (next >= '0' && next <= '9') {
					val = val * 10 - next + '0';
					next = read();
				}
			}

			return val;
		}

	}
}
