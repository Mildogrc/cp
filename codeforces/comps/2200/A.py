def solve(arr):
    c = 0
    max = -1
    for n in arr:
        if n > max:
            max = n
            c = 0
        if n == max:
            c += 1
    return c
        
        

def main():
    n = int(input())
    for _ in range(n):
        w = int(input())
        arr = list(map(int, input().split()))
        print(solve(arr))

    
if __name__ == "__main__":
    main()