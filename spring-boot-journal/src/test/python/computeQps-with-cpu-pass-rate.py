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

    timeSegmentList = []
    timeSegment_name_metrics_dict = {}

    for name in nameMetricsDict.keys():
        metricArray = nameMetricsDict[name]

        for m in metricArray:
            time_segment = int(int(m.time) / 1000 / time_span)

            if time_segment not in timeSegmentList:
                timeSegmentList.append(time_segment)

            if time_segment not in timeSegment_name_metrics_dict.keys():
                m.time_ = time_segment * 1000 * time_span
                timeSegment_name_metrics_dict[time_segment] = {}

            if name not in timeSegment_name_metrics_dict[time_segment].keys():
                timeSegment_name_metrics_dict[time_segment][name] = m
            else:
                timeSegment_name_metrics_dict[time_segment][name].arrived += m.arrived
                timeSegment_name_metrics_dict[time_segment][name].passed += m.passed

    for ts in timeSegmentList:
        local_name_metrics_dict = timeSegment_name_metrics_dict[ts]

        total_arrive = 0
        for v in local_name_metrics_dict.values():
            total_arrive += int(v.arrived)

        total_passed = 0
        for v in local_name_metrics_dict.values():
            total_passed += int(v.passed)

        for local_name in local_name_metrics_dict.keys():
            local_metrics = local_name_metrics_dict[local_name]

            gradation_passed_rate = 1.0
            if local_metrics.arrived > 0:
                gradation_passed_rate = local_metrics.passed / local_metrics.arrived

            lr_pass_rate = 1.0
            if total_arrive > 0:
                lr_pass_rate = total_passed / total_arrive

            # print(total_arrive, local_metrics.passed, lr_pass_rate, sep=',')

            print(local_metrics.date, local_metrics.time, local_metrics.service_id, local_metrics.arrived,
                  local_metrics.passed, lr_pass_rate, gradation_passed_rate, local_metrics.cpu,  sep=',')
