# def func1(x):
#     print("func1")
#     return True
#
#
# def func2(x):
#     print("func2")
#     return True
#
#
# q = 2
# g = (x + y + q for x in [1, 2, 3] if func1(x) if func2(x) for y in [4, 5, 6])
# print(g)

def somefunc():
    return True


q = 0

if somefunc():
    q = 5
else:
    q = 4
print(q)

if somefunc():
    q = 7
print(q)

x = 0

while x < 5:
    if x % 2 == 0:
        print(x)
        x *= 2
    x += 1
