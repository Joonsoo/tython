class slice:
    pass


class range:
    def __init__(self, *args):
        if len(args) == 1:
            self.start = 0
            self.stop = args[0]
            self.step = 1
        elif len(args) == 2:
            self.start = args[0]
            self.stop = args[1]
            self.step = 1
        else:
            assert len(args) == 3
            if args[2] == 0:
                raise ValueError("step is 0")
            self.start = args[0]
            self.stop = args[1]
            self.step = args[2]


def list(iter):
    l = []
    for i in iter:
        l.append(i)
    return l
