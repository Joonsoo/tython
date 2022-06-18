import sys
import ast
import conversions
import python3_10_pb2
import os


def main(argv):
    dest_dir = argv[1]
    for i in range(2, len(argv), 2):
        src_file = argv[i]
        module_name = argv[i + 1]

        print(i, src_file, module_name)
        with open(src_file, 'rt') as f:
            tree = ast.parse(f.read())
        proto = python3_10_pb2.Module()
        conversions.apply_module(tree, proto)

        dest_path = os.path.join(dest_dir, module_name + ".pb")
        with open(dest_path, 'wb') as f:
            f.write(proto.SerializeToString())
            print(f"Written ast proto for module {module_name} to {dest_path}")


if __name__ == '__main__':
    main(sys.argv)
