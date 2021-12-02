#!/usr/bin/env python3

def general(data: list[int], n: int = 1):
    """
    Part 1: n = 1
    Part 2: n = 3
    """
    return sum([x < y for x, y in zip(data[:-n], data[n:])])

if __name__ == "__main__":
    with open('input') as f, open('sample') as sample:
        sample = [int(x) for x in sample.readlines()]
        f = [int(x) for x in f.readlines()]

        print(general(f, 1))
        print(general(f, 3))
