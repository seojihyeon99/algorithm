if __name__ == "__main__":
    # + 모양 방향 벡터(상/하/좌/우)
    dx1 = [-1,1,0,0]
    dy1 = [0,0,-1,1]
    # x 모양 방향 벡터(왼쪽위/오른쪽위/오른쪽아래/왼쪽아래)
    dx2 = [-1, -1, 1, 1]
    dy2 = [-1, 1, 1, -1]


    ncase = int(input())    #테스트 케이스의 개수
    for i in range(ncase):
        # 배열 초기화
        n,m = map(int, input().split())
        arr = [[0 for j in range(n)] for k in range(n)]
        for j in range(n):
            arr[j]=list(map(int, input().split()))
            

        count = 0 # 최종 가장 많이 잡을 수 있는 모기의 수
        for z in range(n):
            for l in range(n):
                count1=arr[z][l]  # + 방향 모기의 수
                count2=arr[z][l]  # x 방향 모기의 수
                # + 방향의 모기
                for j in range(1,m):
                    for k in range(4):
                        if 0<=z+j*dx1[k]<n and 0<=l+j*dy1[k]<n:
                            count1+=arr[z+j*dx1[k]][l+j*dy1[k]]
                # x 방향의 모기
                for j in range(1,m):
                    for k in range(4):
                        if 0 <= z + j * dx2[k] < n and 0 <= l + j * dy2[k] < n:
                            count2+=arr[z+j*dx2[k]][l+j*dy2[k]]
                if count < max(count1, count2) :
                    count = max(count1, count2)

        ###여기까지 코드
        print(f"#{i+1} {count}")
