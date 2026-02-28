def solve(arr):
    for i in range(len(arr) - 1):
        if arr[i] > arr[i + 1]:
            return 1
    return len(arr)

def main():
    n = int(input())
    for _ in range(n):
        w = int(input())
        arr = list(map(int, input().split()))
        print(solve(arr))

    
if __name__ == "__main__":
    main()