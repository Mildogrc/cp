import math

def badpair(i,j):
    if i + j == 7 or i == j:
        return True
    return False

def solve(arr):
    badarray = []
    i = 0
    sum = 0
    while i < len(arr) - 1:
        if badpair(arr[i], arr[i+1]):
            sum += 1
            i += 1
        i += 1
    
    return sum


def main():
    n = int(input())
    for _ in range(n):
        w = int(input())
        arr = list(map(int, input().split()))
        print(solve(arr))

    
if __name__ == "__main__":
    main()