def solve(str):
    s = []
    for c in str:
        if len(s) == 0:
            s.append(c)
        else:
            if s[-1] == c:
                s.pop()
            else:
                s.append(c)
    return 'YES' if len(s) == 0 else 'NO'
        
        

def main():
    n = int(input())
    for _ in range(n):
        w = int(input())
        str = input()
        print(solve(str))

    
if __name__ == "__main__":
    main()