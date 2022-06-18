X = ValueError

try:
    X = ZeroDivisionError
    raise ValueError("Hmm..")
except X as x:
    print(x)
except Exception as e:
    print("..")