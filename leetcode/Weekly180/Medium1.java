class Medium1 {
		List<Integer> stack;
		int maxValue;

		public CustomStack(int maxSize) {
			this.maxValue = maxSize;
			this.stack = new ArrayList<>();
		}

		public void push(int x) {
			if (stack.size() != maxValue) {
				stack.add(x);
			}
		}

		public int pop() {
            if (stack.size() == 0) {
				return -1;
			}
			int pop = stack.get(stack.size() - 1);
			stack.remove(stack.size() - 1);
			return pop;
		}

		public void increment(int k, int val) {
			k = Math.min(k, stack.size());
			for (int i = 0; i < k; i++) {
				stack.set(i, stack.get(i) + val);
			}
		}
	}
