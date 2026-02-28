def solve(arr, n, l, r):
    i = 0
    outer = []
    inner = []
    while (i < l):
        outer.append(arr[i])
        i += 1
    while (i < r):
        inner.append(arr[i])
        i += 1
    while i < n:
        outer.append(arr[i])
        i += 1
    
    if len(inner) == 0:
        return outer
    
    inner_min = min(inner)
    inner_min_ind = inner.index(inner_min)
    fix_inner = []
    for i in range(len(inner)):
        ind = (i + inner_min_ind) % len(inner)
        fix_inner.append(inner[ind])
    
    if len(outer) == 0:
        return fix_inner

    
    final_fix = []
    i = 0
    while i < len(outer) and outer[i] < inner_min:
        final_fix.append(outer[i])
        i += 1
    for x in fix_inner:
        final_fix.append(x)
    while i < len(outer):
        final_fix.append(outer[i])
        i += 1
    # print(final_fix)
    return final_fix

def main():
    n = int(input())
    for _ in range(n):
        nlr = list(map(int, input().split()))
        n = nlr[0]
        l = nlr[1]
        r = nlr[2]
        arr = list(map(int, input().split()))
        ans = solve(arr, n, l, r)
        print(' '.join(map(str, ans)))

    
if __name__ == "__main__":
    main()