import sys
DATA = [None] * 41
DATA[0] = (1, 0)
DATA[1] = (0, 1)

def fibo(n):
    if(DATA[n] != None):
        return DATA[n]
    else:
        x0, x1 = fibo(n-1)
        DATA[n-1] = (x0, x1)
        y0, y1 = fibo(n-2)
        DATA[n-2] = (y0, y1)
        DATA[n] = (x0 + y0, x1 + y1)
        return DATA[n]

num = int(sys.stdin.readline())
data  = [list(map(int,sys.stdin.readline().split())) for i in range(num)]
for i in range(len(data)):
    x, y = fibo(data[i][0])
    print(x, y)
