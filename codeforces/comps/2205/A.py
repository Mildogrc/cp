def solve(arr):
    max = 0
    for i in range(len(arr)):
        if arr[i] > arr[max]:
            max = i
    
    temp = arr[0]
    arr[0] = arr[max]
    arr[max] = temp
    return arr
        
        

def main():
    n = int(input())
    for _ in range(n):
        w = int(input())
        arr = list(map(int, input().split()))
        print(' '.join(map(str, solve(arr))))

    
if __name__ == "__main__":
    main()