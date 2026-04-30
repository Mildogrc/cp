#include <bits/stdc++.h>
#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/tree_policy.hpp>
using namespace __gnu_pbds;

#define PBDS_RB rb_tree_tag
#define PBDS_OST tree_order_statistics_node_update
template<class K, class V> using treemap = tree<K, V, less<K>, PBDS_RB, PBDS_OST>;
template<class K> using treeset = treemap<K, null_type>;
template<class K> struct multitreeset {
    tree<pair<K,int>, null_type, less<pair<K,int>>, PBDS_RB, PBDS_OST> t;
    int id = 0; void insert(K x) { t.insert({x, id++}); }
    bool erase_one(K x) { auto it = t.lower_bound({x, -1}); 
    if (it == t.end() || it->first != x) return 0; t.erase(it); return 1; }
    int order_of_key(K x) { return t.order_of_key({x, -1}); }
    K find_by_order(int k) { return t.find_by_order(k)->first; }
};