import heapq

def solve(particles, store):
    maxY = particles[0][1]
    for particle in particles:
        if particle[1] > maxY:
            maxY = particle[1]
    useful = [[] for _ in range(maxY + 1)] 
    
    for particle in particles:
        useful[particle[1]].append(particle[0])
    
    sum = 0
    maxPlus1 = []
    heap = [0] * (maxY + 2)
    maxPossible = 0

    for i in range(len(useful) - 1, -1, -1):
        sum -= heap[0]
        heapq.heappop(heap)
        particles = useful[i]
        particles.sort(reverse=True)
        j = 0
        while(j < min(len(particles), i + 1) and particles[j] > heap[0]):
            sum -= heap[0]
            sum += particles[j]
            heapq.heappop(heap)
            heapq.heappush(heap, particles[j])
            j += 1
        maxPossible = max(maxPossible, sum)
        maxPlus1.append(sum - heap[0])
    maxPlus1.reverse()
    for i in range(len(maxPlus1) - 1):
        maxPlus1[i + 1] = max(maxPlus1[i + 1], maxPlus1[i])
    
    ans = []
    for particle in store:
        totalAllowed = min(maxY, particle[1])
        ans.append(max(maxPlus1[totalAllowed] + particle[0], maxPossible))

    return ans

def main():
    n = int(input())
    for _ in range(n):
        nm = list(map(int, input().split()))
        n = nm[0]
        m = nm[1]
        particles = []
        store = []
        for _ in range(n):
            particles.append(list(map(int, input().split())))
        for _ in range(m):
            store.append(list(map(int, input().split())))
        ans = solve(particles, store)
        print(' '.join(map(str, ans)))

    
if __name__ == "__main__":
    main()