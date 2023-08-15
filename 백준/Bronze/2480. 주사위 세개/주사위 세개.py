a,b,c = map(int, input().split())
if (a==b==c):
    print(10000+a*1000)
elif (a==b and a!=c):
    print(1000+a*100)
elif (a==c and a!=b):
    print(1000+a*100)
elif (b==c and a!=b):
    print(1000+b*100)
else:
    print(a*100 if(a>b and a>c) else(b*100 if(b>c) else c*100))