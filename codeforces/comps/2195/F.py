def larger(x, y):
    a = x[0] - y[0]
    b = x[1] - y[1]
    c = x[2] - y[2]
    if a == 0:
        return b == 0 and c > 0
    ymin = c - (b * b) / (4 * a)
    return ymin > 0 and a > 0

def rev(g):
    rg = {}
    for i in g:
        rg[i] = []
    for i in g:
        for j in g[i]:
            rg[j].append(i)
    return rg

def longest_path(g):
    in_degree = [0] * len(g)
    path = [0] * len(g)
    for i in g:
        for j in g[i]:
            in_degree[j] += 1
    zero_degree = []
    for i in g:
        if in_degree[i] == 0:
            zero_degree.append(i)
    while(len(zero_degree) > 0):
        u = zero_degree.pop()
        for v in g[u]:
            in_degree[v] -= 1
            path[v] = max(path[v], path[u] + 1)
            if in_degree[v] == 0:
                zero_degree.append(v)
    return path
    

def solve(arr):
    g = {}
    for i in range(len(arr)):
        edges = []
        for j in range(len(arr)):
            if larger(arr[i], arr[j]):
                edges.append(j)
        g[i] = edges
    
    path = longest_path(g)
    revpath = longest_path(rev(g))
    
    for i in range(len(path)):
        path[i] += revpath[i] + 1
    return path


def main():
    n = int(input())
    for _ in range(n):
        w = int(input())
        bigarr = []
        for __ in range(w):
            arr = list(map(int, input().split()))
            bigarr.append(arr)
        print(' '.join(map(str, solve(bigarr))))

    
if __name__ == "__main__":
    main()