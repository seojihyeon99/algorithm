if __name__ == "__main__":
    t = int(input())    # 테스트 케이스 수
    for k in range(t):
        print(f'#{k+1}')
        n = int(input())
        arr = [[0 for i in range(n)] for j in range(n)]

        # 배열 생성하기
        for i in range(n):
            arr[i] = list(map(int, input().split()))

        # 90도 회전, 180도 회전, 270도 회전
        for i in range(n):
            # 90도 회전
            for j in range(n):
                print(f'{arr[n-j-1][i]}', end='')
            print(' ', end='')

            # 180도 회전
            for j in range(n):
                print(f'{arr[n-i-1][n-j-1]}', end='')
            print(' ', end='')

            # 270도 회전
            for j in range(n):
                print(f'{arr[j][n-i-1]}', end='')
            print()

