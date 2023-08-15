if __name__ == '__main__':
    t = int(input())
    for i in range(t):
        n, m = list(map(int, input().split()))

        arr1 = list(map(int, input().split()))  # n개
        arr2 = list(map(int, input().split()))  # m개

        result = 0
        if n < m:
            for k in range(m - n + 1):
                sum = 0
                for j in range(n):
                    sum += arr1[j] * arr2[j + k]
                if result < sum: result = sum
            print(f'#{i + 1} {result}')
        elif n > m:
            for k in range(n - m + 1):
                sum = 0
                for j in range(m):
                    sum += arr1[j + k] * arr2[j]
                if result < sum: result = sum
            print(f'#{i + 1} {result}')
