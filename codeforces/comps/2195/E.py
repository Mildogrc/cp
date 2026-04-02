def subtreefill(adj, u, lsubtreesz, rsubtreesz):
    if len(adj[u]) == 0:
        return 1
    lsubtreesz[u] = subtreefill(adj, adj[u][0], lsubtreesz, rsubtreesz)
    rsubtreesz[u] = subtreefill(adj, adj[u][1], lsubtreesz, rsubtreesz)
    return lsubtreesz[u] + rsubtreesz[u] + 1
    
def psubtreeszL(u, psubtreesz, lsubtreesz, rsubtreesz, p):
    if u < 0:
        return 0
    if psubtreesz[u] != -1:
        return psubtreesz[u]
    else:

        sbsz = lsubtreesz[u] * 2 + rsubtreesz[u] * 2 + 1 + psubtreeszL(p[u], psubtreesz, lsubtreesz, rsubtreesz, p)
        sbsz = sbsz % 1000000007
        psubtreesz[u] = sbsz
        return sbsz

def psubtreeszI(u, psubtreesz, lsubtreesz, rsubtreesz, p):
    if u < 0:
        return 0
    if psubtreesz[u] != -1:
        return psubtreesz[u]
    stack = [u]
    while psubtreesz[stack[-1]] == -1:
        stack.append(p[stack[-1]])
    sum = psubtreesz[stack[-1]]
    stack.pop()
    while len(stack) > 0:
        sum += lsubtreesz[stack[-1]] * 2 + rsubtreesz[stack[-1]] * 2 + 1
        sum %= 1000000007
        psubtreesz[stack[-1]] = sum
        stack.pop()
    return sum
        
def solve(adj):
    n = len(adj)
    p = [-1] * n
    for i in range(n):
        for j in adj[i]:
            p[j] = i
    lsubtreesz = [0] * n
    rsubtreesz = [0] * n
    psubtreesz = [-1] * n
    subtreefill(adj, 1, lsubtreesz, rsubtreesz)
    psubtreesz[0] = 0
    t = []
    # print(p)
    for i in range(n - 1):
        j = i + 1
        sum = lsubtreesz[j] * 2 + rsubtreesz[j] * 2 + 1 
        parentSum = psubtreeszI(p[j], psubtreesz, lsubtreesz, rsubtreesz, p)
        # print(j, ':', parentSum)
        sum += parentSum
        sum %= 1000000007
        t.append(sum)
    return t


def main():
    t = int(input())
    for _ in range(t):
        n = int(input())
        adj = [[1]]
        for _ in range(n):
            arr = list(map(int, input().split()))
            if arr[0] == 0 and arr[1] == 0:
                adj.append([])
            else:
                adj.append(arr)
        # print(adj)
        # print('t', solve(adj))
        print(' '.join(map(str, solve(adj))))

    
if __name__ == "__main__":
    main()