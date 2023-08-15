# 겹치는 숫자가 없으면 1, 있으면 0
if __name__ == "__main__":
    n = int(input())
    for i in range(n):
        # 배열 입력 받기
        arr = [[0 for j in range(9)] for k in range(9)]
        for j in range(9):
            arr[j] = list(map(int, input().split()))

        # 한 행에 1~9까지의 수가 있는지 체크
        result = 1  # 겹치는 숫자가 없음
        for j in range(9):
            check = [0] * 10
            for k in range(9):
                check[arr[j][k]] = 1
            if sum(check) != 9:
                result = 0
                break
        if result == 0:
            print(f'#{i+1} {result}')
            continue

        # 한 열에 1~9까지의 수가 있는지 체크
        result = 1  # 겹치는 숫자가 없음
        for j in range(9):
            check = [0] * 10
            for k in range(9):
                check[arr[k][j]] = 1
            if sum(check) != 9:
                result = 0
                break
        if result == 0:
            print(f'#{i+1} {result}')
            continue

        dx = [-1, -1, -1, 0, 0, 1, 1, 1]
        dy = [-1, 0, 1, -1, 1, -1, 0, 1]
        # 한 3x3에 1~9까지의 수가 있는지 체크
        result = 1  # 겹치는 숫자가 없음
        for j in range(1, 8, 3):
            for k in range(1, 8, 3):
                check = [0] * 10
                check[arr[j][k]] = 1
                for l in range(8):
                    check[arr[j + dx[l]][k + dy[l]]] = 1
                if sum(check) != 9:
                    result = 0
                    break
            if result == 0:
                print(f'#{i+1} {result}')
                break

        # 겹치는 숫자가 없으면
        if result == 1:
            print(f'#{i+1} {result}')
