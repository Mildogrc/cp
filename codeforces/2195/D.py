# 0	1	2	3 a1
# 1	0	1	2 a2
# 2	1	0	1 a3
# 3	2	1	0 a4
# 1	1	1	1 (a1 + a4) / 3 (size of array) = asum
			
# 1	-1	-1	-1 a2 - a1
# 1	1	-1	-1 a3 - a2
# 1	1	1	-1 a4 - a3
			
# 2	0	0	0 a2 - a1 + asum
# 2	2	0	0 a3 - a2 + asum
# 2	2	2	0 a4 - a3 + asum


def solve(arr):
    n = len(arr)
    asum = (arr[0] + arr[-1])//(n-1)
    a = []
    prev = 0
    sum = 0
    for i in range(n - 1):
        curr = arr[i + 1] - arr[i] + asum
        a.append((curr - prev)//2)
        sum += a[-1]
        prev = curr
    a.append(asum - sum)
    return a


def main():
    n = int(input())
    for _ in range(n):
        w = int(input())
        arr = list(map(int, input().split()))
        print(' '.join(map(str, solve(arr))))

    
if __name__ == "__main__":
    main()