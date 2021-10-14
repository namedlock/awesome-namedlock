# coding=UTF-8


if __name__ == '__main__':
    import argparse

    parser = argparse.ArgumentParser()
    parser.add_argument("-f", "--file", type=str, help="file")
    parser.add_argument("-start", "--start", type=int, default=0, help="start")
    parser.add_argument("-end", "--end", type=int, help="end")

    args = parser.parse_args()

    file = args.file

    f = open(file)
    lines = f.readlines()

    startTime = args.start
    endTime = args.end

    nameMetricsDict = {}

    for line in lines:
        if line.startswith('/Library'):
            continue

        if line.startswith('time,name'):
            continue

        line = line.replace("\n", '')

        if line == '':
            continue

        fields = line.split("|")

        time = fields[7]

        timeInt = int(time)
        if timeInt >= endTime or timeInt < startTime:
            continue

        print(line)
