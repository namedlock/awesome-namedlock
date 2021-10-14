# coding=UTF-8


if __name__ == '__main__':
    import argparse

    parser = argparse.ArgumentParser()
    parser.add_argument("-f", "--file", type=str, help="测试数据")

    args = parser.parse_args()

    file = args.file

    f = open(file)
    lines = f.readlines()

    print('timestamp', 'app',  'pr', sep=',')

    for line in lines:
        if line.startswith('/Library'):
            continue

        if line.startswith('time,name'):
            continue

        line = line.replace("\n", '')

        fields = line.split('|')

        timestamp = fields[0]
        app = fields[3]
        pr = 1 - float(fields[8])

        print(timestamp, app, pr, sep=',')



