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

x = 0


def f1(x):
    print(x)
    global f1
    f1 = 2


print(x)
f1(10)
print(f"f1={f1}")
print(x)
