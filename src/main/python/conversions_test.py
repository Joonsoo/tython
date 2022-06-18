import ast
import python3_10_pb2
import conversions

if __name__ == '__main__':
    tree = ast.parse("""
import ast

class XYZ:
    def __init__(self):
        self.p = 123
""")

    proto = python3_10_pb2.Module()

    conversions.apply_module(tree, proto)

    with open("proto.pb", "wb") as f:
        f.write(proto.SerializeToString())
