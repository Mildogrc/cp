def double(i):
    i = (i + 1) * 2 - 1
    return i

def solve(arr):
    map = {}
    for i in range(len(arr)):
        if i not in map.keys():
            j = set()
            l = []
            c = i
            while c < len(arr):
                l.append(c)
                j.add(arr[c])
                c = double(c)
            for k in l:
                map[k] = j    
    
    for i in range(len(arr)):
        if i not in map.keys() and arr[i] != i + 1:
            return "NO"
        else:
            if i+1 not in map[i]:
                return "NO"
    return "YES"

def main():
    # case1 = [1,4,3,2,5]
    # case2 = [1,4,2,3,5]
    # case3 = [2,1,3]
    # case4 = [2,4,3,1]
    # print(solve(case1))
    # print(solve(case2))
    # print(solve(case3))
    # print(solve(case4))

    n = int(input())
    for _ in range(n):
        w = int(input())
        arr = list(map(int, input().split()))
        print(solve(arr))

    
if __name__ == "__main__":
    main()