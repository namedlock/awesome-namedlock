# coding=UTF-8

class Metrics(object):

    def __init__(self, _date, _time, _service_id, _arrived, _passed, _cpu):
        self.date = _date
        self.time = _time
        self.service_id = _service_id
        self.arrived = int(_arrived)
        self.passed = int(_passed)
        self.cpu = float(_cpu)

if __name__ == '__main__':
    import argparse

    parser = argparse.ArgumentParser()
    parser.add_argument("-f", "--file", type=str, help="测试数据")
    parser.add_argument("-span", "--time_span", type=int, default=1, help="时长")

    args = parser.parse_args()

    file = args.file

    f = open(file)
    lines = f.readlines()

    time_span = args.time_span

    nameMetricsDict = {}

    print('date', 'timestamp', 'service_id',  'arrived', 'passed', 'pass_rate', 'cpu', sep=',')

    for line in lines:
        if line.startswith('/Library'):
            continue

        if line.startswith('time,name'):
            continue

        line = line.replace("\n", '')

        fields = line.split("|")
        date = fields[0]
        time = fields[7]
        service_id = fields[8]
        received = fields[11]
        passed = fields[12]
        cpu = fields[17]

        if service_id not in nameMetricsDict.keys():
            nameMetricsDict[service_id] = []

        metric = Metrics(date, time, service_id, received, passed, cpu)
        nameMetricsDict[service_id].append(metric)

    for key in nameMetricsDict.keys():
        metricArray = nameMetricsDict[key]

        timeList = []
        time2ReceivedDict = {}
        for m in metricArray:
            r_t = int(int(m.time) / 1000 / time_span)

            if r_t not in timeList:
                timeList.append(r_t)

            if r_t not in time2ReceivedDict.keys():
                m.time_ = r_t * 1000 * time_span
                time2ReceivedDict[r_t] = m
            else:
                time2ReceivedDict[r_t].arrived += m.arrived
                time2ReceivedDict[r_t].passed += m.passed

        for te in timeList:
            final_m = time2ReceivedDict[te]

            passed_rate = 1.0
            if final_m.arrived > 0:
                passed_rate = final_m.passed / final_m.arrived

            print(final_m.date, final_m.time, final_m.service_id, final_m.arrived,
                  final_m.passed, passed_rate, final_m.cpu, sep=',')
