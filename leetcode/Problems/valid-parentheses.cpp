#define pb push_back
class Solution {
public:
    bool isValid(string s) {
        vector<char> stack;
        for (int i = 0; i < s.size(); i++) {
            if (s[i] == '(' || s[i] == '[' || s[i] == '{') {
                stack.pb(s[i]);
            } else {
                if (stack.empty()) {
                    return false;
                }
                if (s[i] == ')' && stack.back() != '(') {
                    return false;
                }
                if (s[i] == ']' && stack.back() != '[') {
                    return false;
                }
                if (s[i] == '}' && stack.back() != '{') {
                    return false;
                }
                stack.pop_back();
            }
        }
        return stack.empty();
    }
};
