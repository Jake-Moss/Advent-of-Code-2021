#!/usr/bin/env python3

def part1(data: list[str]):
    gamma = ''
    eps = ''
    for pos in zip(*data):
        if pos.count('1') > pos.count('0'):
            gamma += '1'
            eps += '0'
        else:
            gamma += '0'
            eps += '1'
    return int(gamma, 2) * int(eps, 2)

def part2(data: list[str]):
    def helper(data: list[str], pos: int, bit: int):
        if len(data) <= 1:
            return data

        bits = [line[pos] for line in data]

        if bit == 1:
            common = '1' if bits.count('1') >= bits.count('0') else '0' # most common
        else: # bit == 0
            common = '1' if bits.count('1') < bits.count('0') else '0' # least common

        new_data = [line for line in data if line[pos] == common]
        return helper(new_data, pos + 1, bit)

    oxygen = helper(data, 0, 1)
    co2 = helper(data, 0, 0)
    return int(oxygen[0], 2) * int(co2[0], 2)



if __name__ == "__main__":
    with open('input') as f, open('sample') as sample:
        sample = [x.strip() for x in sample.readlines()]
        f = [x.strip() for x in f.readlines()]

        print(part1(f))
        print(part2(f))
