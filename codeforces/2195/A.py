def solve(arr):
    for n in arr:
        if n == 67:
            return "YES"
    return "NO"
        

def main():
    n = int(input())
    for _ in range(n):
        w = int(input())
        arr = list(map(int, input().split()))
        print(solve(arr))

    
if __name__ == "__main__":
    main()