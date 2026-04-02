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