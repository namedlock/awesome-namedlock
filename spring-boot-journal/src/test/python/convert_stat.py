# coding=UTF-8

import time
import datetime

class Metrics(object):
    def __init__(self, date, datetime, service_id, count):
        self.date = date
        self.datetime = datetime
        self.service_id = service_id
        self.count = int(count)


if __name__ == '__main__':
    import argparse

    parser = argparse.ArgumentParser()
    parser.add_argument("-f", "--file", type=str, help="测试数据")
    parser.add_argument("-span", "--time_span", type=int, default=10, help="时长")

    args = parser.parse_args()

    file = args.file

    f = open(file)
    lines = f.readlines()

    time_span = args.time_span

    nameMetricsDict = {}

    dateTimeArray = []
    metricsDict = {}

    service_id_array = ['com.taobao.reborn.service.MclarenService:1.0.0']

    for line in lines:
        if line.startswith('/Library'):
            continue

        if line.startswith('time,name'):
            continue

        line = line.replace("\n", '')

        # contains = False
        # for sid in service_id_array:
        #     if sid in line:
        #         contains = True
        # if not contains:
        #     continue

        fields = line.split("|")

        date = fields[0]
        dateTime_ = int(time.mktime(datetime.datetime.strptime(date, "%Y-%m-%d %H:%M:%S").timetuple()))
        service_id = fields[2]
        count = fields[3].split(',')[0]

        divided_dateTime_ = int(dateTime_ / time_span)

        # print(divided_dateTime_)

        dateTime_arrivedId = '{}_{}'.format(divided_dateTime_, 'received')
        dateTime_successId = '{}_{}'.format(divided_dateTime_, 'success')

        if divided_dateTime_ not in dateTimeArray:
            dateTimeArray.append(divided_dateTime_)

            arrivedMetric = Metrics(date, divided_dateTime_, dateTime_arrivedId, 0)
            metricsDict[dateTime_arrivedId] = arrivedMetric

            successMetric = Metrics(date, divided_dateTime_, dateTime_successId, 0)
            metricsDict[dateTime_successId] = successMetric

        if 'received' in line:
            metricsDict[dateTime_arrivedId].count += int(count)
            # print(metricsDict[dateTime_arrivedId].datetime, metricsDict[dateTime_arrivedId].service_id,
            #       metricsDict[dateTime_arrivedId].count)

        elif 'success' in line:
            metricsDict[dateTime_successId].count += int(count)
            # print(metricsDict[dateTime_successId].datetime, metricsDict[dateTime_successId].service_id,
            #       metricsDict[dateTime_successId].count)

    for t in dateTimeArray:
        dateTime_arrivedId = '{}_{}'.format(t, 'received')
        dateTime_successId = '{}_{}'.format(t, 'success')

        arrivedMetric = metricsDict[dateTime_arrivedId]
        successMetric = metricsDict[dateTime_successId]

        pass_rate = 1.0
        if arrivedMetric.count > 0:
            pass_rate = successMetric.count / arrivedMetric.count

        print(arrivedMetric.date, t, arrivedMetric.count, successMetric.count, pass_rate, sep=',')




