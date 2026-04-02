def add_blogs(a, blogs, outorder):
    for x in a:
        if x not in blogs:
            blogs.add(x)
            outorder.append(x)

def combine(a, b):
    blogs = set()
    outorder = []
    add_blogs(a, blogs, outorder)
    add_blogs(b, blogs, outorder)
    return outorder

def smaller(a, b):
    for i in range(min(len(a), len(b))):
        if a[i] < b[i]:
            return True
        elif a[i] > b[i]:
            return False
    return len(a) < len(b)

def blog_count(blog):
    sz_set = set()
    for x in blog:
        sz_set.add(x)
    return len(sz_set)

def solve(a):
    total_blogs = set()
    for x in a:
        for blog in x:
            total_blogs.add(blog)
    
    adj = {}
    for i in range(len(a)):
        for j in range(len(a)):
            if smaller(a[i], a[j]):
                if i not in adj:
                    adj[i] = []
                adj[i].append(j)
    print(adj)
    in_degree = [0] * len(adj)
    for x in adj:
        for y in adj[x]:
            in_degree[y] += 1
    
    zero_in_degree = []
    for x in adj:
        if in_degree[x] == 0:
            zero_in_degree.append(x)
    
    min_until = {}
    for x in zero_in_degree:
        min_until[x] = a[x]
    
    while len(zero_in_degree) > 0:
        blog = zero_in_degree.pop()
        for child in adj[blog]:
            in_degree[child] -= 1
            prop_min = combine(a[blog], a[child])
            if smaller(prop_min, min_until[child]):
                min_until[child] = prop_min
            if in_degree[child] == 0:
                zero_in_degree.append(child)
    
    final_min = None
    for x in min_until:
        if blog_count(min_until[x]) == len(total_blogs):
            if final_min is None or smaller(min_until[x], final_min):
                final_min = min_until[x]
    return final_min
    

def main():
    n = int(input())
    for _ in range(n):
        w = int(input())
        a = []
        for __ in range(w):
            a.append(list(map(int, input().split()))[1:][::-1])
        
        final = solve(a)
        print(final)
        # print(' '.join(map(str, final)))

    
if __name__ == "__main__":
    main()