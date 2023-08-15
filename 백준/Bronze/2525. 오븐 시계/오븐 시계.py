H, M = map(int, input().split())
C = int(input())
print((H+(M+C)//60)%24, (M+C)%60)