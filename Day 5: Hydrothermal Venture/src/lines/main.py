#!/usr/bin/env python3

def interpolatePart1(line: tuple[tuple[int, int], tuple[int, int]]) -> list[tuple[int, int]]:
    result = []
    x1, y1 = line[0]
    x2, y2 = line[1]

    # order of points does not matter
    if x1 == x2 or y1 == y2:
        if x1 == x2: # vertical line
            sign = [1, -1][y1 > y2]
            for y in range(y1, y2 + sign, sign):
                result.append((x1, y))
        else: # horizontal
            sign = [1, -1][x1 > x2]
            for x in range(x1, x2 + sign, sign):
                result.append((x, y1))

    return result

def interpolatePart2(line: tuple[tuple[int, int], tuple[int, int]]) -> list[tuple[int, int]]:
    result = []
    x1, y1 = line[0]
    x2, y2 = line[1]

    # order of points does not matter
    if x1 == x2: # vertical line
        sign = [1, -1][y1 > y2]
        for y in range(y1, y2 + sign, sign):
            result.append((x1, y))
    elif y1 == y2: # horizontal
        sign = [1, -1][x1 > x2]
        for x in range(x1, x2 + sign, sign):
            result.append((x, y1))
    else:
        Xsign = [1, -1][x1 > x2]
        Ysign = [1, -1][y1 > y2]
        y = y1
        for x in range(x1, x2 + Xsign, Xsign):
            result.append((x, y))
            y += Ysign
    return result

if __name__ == "__main__":
    with open('input') as f, open('sample') as sample:
        sample = [x.strip() for x in sample.readlines()]
        f = [x.strip() for x in f.readlines()]

        gridSize = 1000
        # gridSize = 10
        lines = [tuple(tuple(int(x) for x in y.split(',')) for y in z.split(' -> ')) for z in f]

        # Part 1
        grid = [0 for _ in range(gridSize*gridSize)]
        for line in lines:
            for point in interpolatePart1(line):
                grid[point[0] + point[1] * gridSize] += 1
        print(len(list(filter(lambda x: x >= 2, grid))))



        # Part 2
        grid = [0 for _ in range(gridSize*gridSize)]
        for line in lines:
            for point in interpolatePart2(line):
                grid[point[0] + point[1] * gridSize] += 1
        print(len(list(filter(lambda x: x >= 2, grid))))
