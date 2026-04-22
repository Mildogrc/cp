#include <bits/stdc++.h>
#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/tree_policy.hpp>
using namespace __gnu_pbds;

typedef tree<
    int,
    int, //null_type if just set
    less<int>,
    rb_tree_tag,
    tree_order_statistics_node_update>
treemap;