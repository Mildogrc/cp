#include <iostream>
#include <stdio.h>
#include <vector>
#include <sstream>
 
typedef long long ll;
 
using namespace std;
 
void setIO() {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
}
 
void setIO(string name) {
	ios_base::sync_with_stdio(0);
	cin.tie(0);
	freopen((name + ".in").c_str(), "r", stdin);
	freopen((name + ".out").c_str(), "w", stdout);
}
int main() {
	setIO();
	int t;
	cin >> t;
	while (t--) {
		int n;
		cin >> n;
		ll monsters[n][2];
		for (int i = 0; i < n; i++) {
			cin >> monsters[i][0] >> monsters[i][1];
		}
		ll kill[n];
		ll sum = 0;
		for (int i = 0; i < n; i++) {
			kill[i] = max((ll) 0, monsters[(i + 1) % n][0] - monsters[i][1]);
			sum += kill[i];
		}
		ll minPos = sum + monsters[0][0] - kill[n - 1];
		for (int i = 1; i < n; i++) {
			minPos = min(minPos, sum + monsters[i][0] - kill[i - 1]);
		}
		cout << minPos << "\n";
	}
}
