if __name__=='__main__':
    t = int(input())
    for j in range(t):
        n = int(input())
        count = [0]*101
        arr = list(map(int, input().split()))
        for i in range(len(arr)):
            count[arr[i]] += 1
        max = 0
        idx = 0
        for i in range(101):
            if count[i] >= max:
                max = count[i]
                idx = i
        print(f'#{n} {idx}')