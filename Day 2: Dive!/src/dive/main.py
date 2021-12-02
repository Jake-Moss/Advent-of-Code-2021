#!/usr/bin/env python3

def general(data: list[tuple[str, int]]):
    """
    returns: (Part 1, Part 2)
    """
    depth, horizontal, aim = 0, 0, 0

    for move, x in data:
        match move:
            case "forward":
                horizontal += x
                depth += aim * x
            case "down":
                aim += x
            case "up":
                aim -= x

    return horizontal * aim, depth * horizontal


if __name__ == "__main__":
    with open('input') as f, open('sample') as sample:
        sample = [(x, int(y)) for x, y in [line.split(' ') for line in sample.readlines()]]
        f = [(x, int(y)) for x, y in [line.split(' ') for line in f.readlines()]]

        print(general(f))
