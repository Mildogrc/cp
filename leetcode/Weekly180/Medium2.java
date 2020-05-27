class Medium2 {
    void storeBSTTreeNodes(TreeNode root, List<TreeNode> nodes) {
		if (root == null)
			return;
		storeBSTTreeNodes(root.left, nodes);
		nodes.add(root);
		storeBSTTreeNodes(root.right, nodes);
	}

	TreeNode buildTreeUtil(List<TreeNode> nodes, int start, int end) {
		if (start > end)
			return null;
		int mid = (start + end) / 2;
		TreeNode node = nodes.get(mid);

		node.left = buildTreeUtil(nodes, start, mid - 1);
		node.right = buildTreeUtil(nodes, mid + 1, end);

		return node;
	}

	TreeNode balanceBST(TreeNode root) {
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		storeBSTTreeNodes(root, nodes);
		int n = nodes.size();
		return buildTreeUtil(nodes, 0, n - 1);
	}
}
