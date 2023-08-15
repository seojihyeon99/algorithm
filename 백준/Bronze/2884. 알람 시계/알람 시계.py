H, M = map(int, input().split())
if (M<45):
    print((H+23)%24,M+60-45)
else:
    print(H,M-45)