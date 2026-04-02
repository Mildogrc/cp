def solve(w):
    z = set()
    i = 2
    while i * i <= w:
        if w % i == 0:
            z.add(i)
        while w % i == 0:
            w = w // i
        i += 1
    if w > 1:
        z.add(w)
    prod = 1
    for x in z:
        prod *= x
    return prod

def main():
    n = int(input())
    for _ in range(n):
        w = int(input())
        print(solve(w))

    
if __name__ == "__main__":
    main()